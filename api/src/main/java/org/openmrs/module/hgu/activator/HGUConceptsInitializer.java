package org.openmrs.module.hgu.activator;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptDatatype;
import org.openmrs.ConceptDescription;
import org.openmrs.ConceptMap;
import org.openmrs.ConceptMapType;
import org.openmrs.ConceptName;
import org.openmrs.ConceptNumeric;
import org.openmrs.ConceptReferenceTerm;
import org.openmrs.ConceptSource;
import org.openmrs.api.ConceptService;
import org.openmrs.api.DuplicateConceptNameException;
import org.openmrs.api.context.Context;
import org.openmrs.module.hgu.HumanGeneticsModuleActivator;
import org.openmrs.module.hgu.HGUFormsConstants;
import org.openmrs.module.hguforms.util.DefaultResouceLoaderImpl;
import org.openmrs.module.hguforms.util.ResourceLoader;

import au.com.bytecode.opencsv.CSVReader;

public class HGUConceptsInitializer implements Initializer {
	
	protected static final Log log = LogFactory.getLog(HGUConceptsInitializer.class);
	
	protected final static char		CSV_DELIMITER		= '\t';
	protected final static String	CSV_INNER_DELIMITER = ",";
	
	/*
	 * Required headers that MUST be found on the concepts CSV file to be imported.
	 */
	protected final static String CSV_MAPPING_LFHC = "LFHC Mapping";
	protected final static String CSV_MAPPING_ICD10 = "ICD-10 Mapping";
	protected final static String CSV_NAME = "Name";
	protected final static String CSV_SHORTNAME = "Short Name";
	protected final static String CSV_SYNONYMS = "Synonyms (comma-separated)";
	protected final static String CSV_DESC = "Description";
	protected final static String CSV_ISSET = "Is set";
	protected final static String CSV_DATATYPE = "Datatype";
	protected final static String CSV_CLASS = "Class";
	protected final static String CSV_CONCEPT_LIST = "Subconcepts (comma-separated)";
	protected final static String CSV_NUMERIC_UNIT = "Numeric - Unit";
	protected final static String CSV_NUMERIC_ALLOWDECIMALS = "Numeric - Allow decimals";
	protected final static String CSV_NUMERIC_AHI = "Numeric - Absolute High";
	protected final static String CSV_NUMERIC_CHI = "Numeric - Critical High";
	protected final static String CSV_NUMERIC_NHI = "Numeric - Normal High";
	protected final static String CSV_NUMERIC_NLO = "Numeric - Normal Low";
	protected final static String CSV_NUMERIC_CLO = "Numeric - Critical Low";
	protected final static String CSV_NUMERIC_ALO = "Numeric - Absolute Low";
	
	// Array of the minimal set of headers to be found in the import CSV.
	protected final Set<String> requiredHeaders = new HashSet<String>();
	
	public HGUConceptsInitializer() {
		requiredHeaders.add(CSV_MAPPING_LFHC);
		requiredHeaders.add(CSV_MAPPING_ICD10);
		requiredHeaders.add(CSV_NAME);
		requiredHeaders.add(CSV_SHORTNAME);
		requiredHeaders.add(CSV_SYNONYMS);
		requiredHeaders.add(CSV_DESC);
		requiredHeaders.add(CSV_ISSET);
		requiredHeaders.add(CSV_DATATYPE);
		requiredHeaders.add(CSV_CLASS);
		requiredHeaders.add(CSV_CONCEPT_LIST);
		requiredHeaders.add(CSV_NUMERIC_UNIT);
		requiredHeaders.add(CSV_NUMERIC_ALLOWDECIMALS);
		requiredHeaders.add(CSV_NUMERIC_AHI);
		requiredHeaders.add(CSV_NUMERIC_CHI);
		requiredHeaders.add(CSV_NUMERIC_NHI);
		requiredHeaders.add(CSV_NUMERIC_NLO);
		requiredHeaders.add(CSV_NUMERIC_CLO);
		requiredHeaders.add(CSV_NUMERIC_ALO);
	}
	
	/*
	 * Helper class to set numeric boundaries to a ConceptNumeric instance
	 * from the six String-represented values coming out from the CSV.
	 */
	protected static class NumericBoundaries {
		
		private final ConceptNumeric c;
		
		private Double ahi = null;
		private Double chi = null;
		private Double nhi = null;
		private Double nlo = null;
		private Double clo = null;
		private Double alo = null;
		
		public NumericBoundaries(
				final ConceptNumeric c, final Log log,
				String ahi, String chi, String nhi,
				String nlo, String clo, String alo
				)
		{
			this.c = c;
			
			try {
				if(!ahi.isEmpty())
					this.ahi = Double.parseDouble(ahi);
			} catch (NumberFormatException e) {
				log.warn("Concept '" + c.getName() + "''s boundary could not be parsed as a double: '" + ahi + "'. It was ignored.");
			}
			try {
				if(!chi.isEmpty())
					this.chi = Double.parseDouble(chi);
			} catch (NumberFormatException e) {
				log.warn("Concept '" + c.getName() + "''s boundary could not be parsed as a double: '" + chi + "'. It was ignored.");
			}
			try {
				if(!nhi.isEmpty())
					this.nhi = Double.parseDouble(nhi);
			} catch (NumberFormatException e) {
				log.warn("Concept '" + c.getName() + "''s boundary could not be parsed as a double: '" + nhi + "'. It was ignored.");
			}
			try {
				if(!nlo.isEmpty())
					this.nlo = Double.parseDouble(nlo);
			} catch (NumberFormatException e) {
				log.warn("Concept '" + c.getName() + "''s boundary could not be parsed as a double: '" + nlo + "'. It was ignored.");
			}
			try {
				if(!clo.isEmpty())
					this.clo = Double.parseDouble(clo);
			} catch (NumberFormatException e) {
				log.warn("Concept '" + c.getName() + "''s boundary could not be parsed as a double: '" + clo + "'. It was ignored.");
			}
			try {
				if(!alo.isEmpty())
					this.alo = Double.parseDouble(alo);
			} catch (NumberFormatException e) {
				log.warn("Concept '" + c.getName() + "''s boundary could not be parsed as a double: '" + alo + "'. It was ignored.");
			}
		}
		
		public ConceptNumeric getConceptNumericWithBoundaries() {
			if(ahi != null)
				c.setHiAbsolute(ahi);
			if(chi != null)
				c.setHiCritical(chi);
			if(nhi != null)
				c.setHiNormal(nhi);
			if(nlo != null)
				c.setLowNormal(nlo);
			if(clo != null)
				c.setLowCritical(clo);
			if(alo != null)
				c.setLowAbsolute(alo);
			return c;
		}
	}

	/**
	 * @see Initializer#started()
	 */
	@Override
	public synchronized void started() {
		log.info("Setting additional/custom concepts for " + HumanGeneticsModuleActivator.ACTIVATOR_MODULE_NAME);
		
		final ConceptService cs = Context.getConceptService();
		
		// Step 1: Setting up the new concept source(s).
		addConceptSources(cs);
		
		// Step 2: Adding the new concepts.
		Map<String, Map<String, String>> concepts = getConceptMapFromCSVResource("hguConcepts.csv", new DefaultResouceLoaderImpl());
		addMappedConcepts(cs, concepts);
	}
	
	/**
	 * First step: addition of the Concept Sources.
	 * This is step needs to be completed when it comes to adding concepts.
	 * @param cs The ConceptService used throughout the {@link Initializer#}
	 */
	protected void addConceptSources(final ConceptService cs) {
		ConceptSource conceptSource = cs.getConceptSourceByName(HGUFormsConstants.HGU_CONCEPT_SOURCE);
		if(conceptSource == null) {
			conceptSource = new ConceptSource();
			conceptSource.setName(HGUFormsConstants.HGU_CONCEPT_SOURCE);
			conceptSource.setDescription("Custom concepts defined at HGU.");
			cs.saveConceptSource(conceptSource);
		}
	}
	
	/**
	 * Parses the (api-located) CSV resource listing the custom concepts.
	 * @return A LFHC ID indexed map of map-represented concepts. Each map represented concept is a map representation
	 * of the list of the concept's properties (name, description, ... etc).
	 */
	protected Map<String, Map<String, String>> getConceptMapFromCSVResource(final String csvPath, final ResourceLoader resourceLoader) {

		final InputStream inputStream = resourceLoader.getResourceAsStream(csvPath); 
		
		Map<String, Map<String, String>> concepts = new HashMap<String, Map<String, String>>();
		try {
			
			CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream), CSV_DELIMITER);
	        
			String[] header = csvReader.readNext();
			
			// Checking the headers
			Set<String> actualHeaders = new HashSet<String>(Arrays.asList(header));
			String faultyHeaders = "";
			String sep = ", ";
			for(String requiredHeader : requiredHeaders){
				if(!actualHeaders.contains(requiredHeader)) {
					faultyHeaders += requiredHeader + sep;
				}
			}
			if(!faultyHeaders.isEmpty()) {
				faultyHeaders = faultyHeaders.substring(0, faultyHeaders.length() - sep.length());
				log.error("Could not process CSV file for custom concepts due to faulty header(s), please review the following header(s): " + faultyHeaders + ".");
				csvReader.close();
				return concepts;
			}
			
	        String[] line = null;
	        int csvRow = 0;
	        while((line = csvReader.readNext()) != null) {
	        	
	        	csvRow++;
	        	if(line.length != header.length) {
	        		log.warn("Line " + csvRow + " has " + line.length + " column(s), while the CSV header has " + header.length + " columns, line cannot be parsed, skippinh through to the next line.");
	        		continue;
	        	}
	        	
	        	// We obtain a map of the concep's properties.
	        	Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < header.length; i++) {
	                String string = header[i];
	                String val = line[i];
	                map.put(string, val);
	            }

                String lfhcId = map.get(CSV_MAPPING_LFHC);
                concepts.put(lfhcId, map);
	        }
	        
	        csvReader.close();
		}
		catch (IOException e) {
			log.error(e);
		}
		
		return concepts;
	}

	/**
	 * Processes the map representation of the new concepts by reading and checking the map properties before saving the concepts.
	 * @param cs The {@link ConceptService} instance.
	 * @param mappedConcepts The map representation of all custom concepts (output of the CSV parsing)
	 */
	protected void addMappedConcepts(final ConceptService cs, final Map<String, Map<String, String>> mappedConcepts) {

		if(mappedConcepts.size() == 0)
			return;
		
		ConceptSource lfhcSource = cs.getConceptSourceByName(HGUFormsConstants.HGU_CONCEPT_SOURCE);
		
		Map<String, Concept> conceptsToSave = new HashMap<String, Concept>(); 
		Map<String, String> sets = new HashMap<String, String>();	// We hold the concept lists (sets or answers) until all concepts are created
		
		for(Map<String, String> mappedConcept : mappedConcepts.values()) {
			
			//
			// Initial sanity checks.
			//
			String lfhcMappedId = mappedConcept.get(CSV_MAPPING_LFHC);
			if(lfhcMappedId.isEmpty()) {
				log.warn("Concept found with no LFHC mapping. Skipping through to next concept in the CSV source.");
				continue;
			}
			String lfhcId = getCodeFromMappedId(lfhcMappedId);
			if(lfhcId.isEmpty()) {
				log.warn("This mapped ID is not correctly formated: " + lfhcMappedId + ". Skipping through to next concept in the CSV source.");
				continue;
			}
			Concept newConcept = cs.getConceptByMapping(lfhcId, HGUFormsConstants.HGU_CONCEPT_SOURCE);
			if(newConcept != null) {
				log.info("Concept " + lfhcMappedId + " already exists. Skipping through to next concept in the CSV source.");
				continue;
			}

			//
			// Reading and checking the new concept properties. 
			//
			String icd10Id = mappedConcept.get(CSV_MAPPING_ICD10);
			String name = mappedConcept.get(CSV_NAME);
			if(name.isEmpty()) {
				log.warn("Concept " + lfhcMappedId + " has no '" + CSV_NAME + "' property provided. Skipping through to next concept.");
				continue;
			}
			String shortName = mappedConcept.get(CSV_SHORTNAME);
			String desc = mappedConcept.get(CSV_DESC);
			if(desc.isEmpty()) {
				desc = "NO DESCRIPTION PROVIDED. PLEASE FILL IN A DESCRIPTION FOLLOWING OPENMRS GUIDELINES FOR CREATING NEW CONCEPTS.";
				log.warn("Concept " + lfhcMappedId + " has no '" + CSV_DESC + "' property provided. It was saved with a warning placeholder description.");
			}
			ConceptDescription description = new ConceptDescription(desc, Locale.ENGLISH);
			String synonyms = mappedConcept.get(CSV_SYNONYMS);
			if(synonyms.isEmpty())
				synonyms = "";
			String conceptClassName = mappedConcept.get(CSV_CLASS);
			ConceptClass conceptClass = cs.getConceptClassByName(conceptClassName);
			if(conceptClass == null) {
				log.warn("Could not save concept '" + name + "': invalid Concept Class: " + conceptClassName + ". Skipping through to next concept in the CSV source.");
				continue;
			}
			String datatypeName = mappedConcept.get(CSV_DATATYPE);
			ConceptDatatype datatype = cs.getConceptDatatypeByName(datatypeName);
			if(datatype == null) {
				log.warn("Could not save concept '" + name + "': invalid Datatype: " + datatypeName + ". Skipping through to next concept in the CSV source.");
				continue;
			}
			String unit = mappedConcept.get(CSV_NUMERIC_UNIT);
			String ahi = mappedConcept.get(CSV_NUMERIC_AHI);
			String chi = mappedConcept.get(CSV_NUMERIC_CHI);
			String nhi = mappedConcept.get(CSV_NUMERIC_NHI);
			String nlo = mappedConcept.get(CSV_NUMERIC_NLO);
			String clo = mappedConcept.get(CSV_NUMERIC_CLO);
			String alo = mappedConcept.get(CSV_NUMERIC_ALO);
			String allowDecimals = mappedConcept.get(CSV_NUMERIC_ALLOWDECIMALS);
			String isSetString = mappedConcept.get(CSV_ISSET);
			String conceptList = mappedConcept.get(CSV_CONCEPT_LIST);
			boolean isComplex = !conceptList.isEmpty(); 
			if(isComplex)
				sets.put(lfhcMappedId, conceptList);

			//
			// Filling the new Concept instance
			//
			newConcept = new Concept();
			if(!synonyms.isEmpty()) {
				Collection<ConceptName> names = new HashSet<ConceptName>();
				String[] synList = synonyms.split(CSV_INNER_DELIMITER);
				for(String syn : synList) {
					names.add(new ConceptName(syn.trim(), Locale.ENGLISH));
				}
				newConcept.setNames(names);
			}
			newConcept.setFullySpecifiedName(new ConceptName(name, Locale.ENGLISH));
			if("Numeric".equalsIgnoreCase(datatypeName.trim())) {
				newConcept = new ConceptNumeric(newConcept);
				((ConceptNumeric) newConcept).setUnits(unit);
				((ConceptNumeric) newConcept).setAllowDecimal( parseBoolean(allowDecimals, false) );
				newConcept = (new NumericBoundaries(((ConceptNumeric) newConcept), log, ahi, chi, nhi, nlo, clo, alo)).getConceptNumericWithBoundaries();
			}
			newConcept.setShortName(new ConceptName(shortName, Locale.ENGLISH));
			newConcept.addDescription(description);
			newConcept.setConceptClass(conceptClass);
			newConcept.setDatatype(datatype);
			{	//The LFHC mapping
				ConceptReferenceTerm refTerm = new ConceptReferenceTerm(lfhcSource, lfhcId, "");
				ConceptMapType mapType = cs.getConceptMapTypeByUuid(ConceptMapType.SAME_AS_MAP_TYPE_UUID);
				ConceptMap map = new ConceptMap(refTerm, mapType);
				newConcept.addConceptMapping(map);
			}
			newConcept.setSet( parseBoolean(isSetString, false) );
			
			// Caching of new concepts
			conceptsToSave.put(lfhcMappedId, newConcept);
		}
		
		// Saving all concepts
		for(String lfhcMappedId : conceptsToSave.keySet()) {
			saveConceptWithConceptList(cs, conceptsToSave, sets, lfhcMappedId);
		}		
	}
	
	private boolean parseBoolean(String strBool, boolean defaultBool) {
		boolean res = defaultBool;
		strBool = strBool.trim().toLowerCase();
		if("y".equals(strBool) || "yes".equals(strBool) || "true".equals(strBool) || "1".equals(strBool))
			res = true;
		if(strBool.isEmpty() || "n".equals(strBool) || "no".equals(strBool) || "false".equals(strBool) || "0".equals(strBool))
			res = false;
		return res;
	}
	
	
	// Stack the concepts already saved to end the recursive saving calls.
	protected Set<String> conceptsDone = new HashSet<String>();
	
	/*
	 * Recursing concept saving routine.
	 */
	private Concept saveConceptWithConceptList(final ConceptService cs, Map<String, Concept> concepts, Map<String, String> sets, String lfhcMappedId) {
		
		if(conceptsDone.contains(lfhcMappedId))
			return null;
		
		Concept conceptToSave = concepts.get(lfhcMappedId);
		if(conceptToSave == null) {
			log.error("Concept '" + lfhcMappedId + "' was requested for being saved but was not found in the cache of concepts to be saved. Please double check this ID.");
			conceptsDone.add(lfhcMappedId);
			return null;
		}			
		
		String conceptList = sets.get(lfhcMappedId);
		if(conceptList != null) {
			
			String[] setConcepts = conceptList.split(CSV_INNER_DELIMITER);		
			
			for(String conceptId : setConcepts) {
				Concept memberConcept = null;
				// Figuring out if the conceptId is CIEL-implicit or LFHC mapping
				String[] split = conceptId.split(":");
				conceptId = conceptId.trim();	// Front spaces are sometimes added in the CSV
				if(split.length >= 2)
				{	
					if(conceptId.equals(lfhcMappedId)) {
						log.error("Concept '" + conceptToSave.getName() + "' contains itself as an answer or set member. Skipping to the next concept.");
						conceptsDone.add(conceptId);
						return null;
					}
					// LFHC mapping (recursive call)
					memberConcept = saveConceptWithConceptList(cs, concepts, sets, conceptId);
				}
				else
				{	// CIEL
					memberConcept = cs.getConceptByMapping(conceptId, HGUFormsConstants.CIEL_CONCEPT_SOURCE);
					if(memberConcept == null) {
						log.warn("Concept '" + conceptToSave.getName() + "' " + HGUFormsConstants.CIEL_CONCEPT_SOURCE + " answer or set member concept [" + conceptId + "] not found. Answer or set member will be missing.");
					}
				}
				if(memberConcept != null) {
					if(conceptToSave.isSet())
						conceptToSave.addSetMember(memberConcept);
					else 
						conceptToSave.addAnswer(new ConceptAnswer(memberConcept));
				}
			}
		}
		
		conceptsDone.add(lfhcMappedId);
		return saveConcept(cs, conceptToSave);
	}

//	private int saveCount = 0;
	
	/**
	 * Wrapper around the atomic saving action handling a few specific exceptions.
	 * @param cs
	 * @param concept
	 * @return The saved {@link Concept}
	 */
	protected Concept saveConcept(final ConceptService cs, Concept concept) {
		try {
			concept = cs.saveConcept(concept);
		}
		catch (DuplicateConceptNameException e) {
			log.error("The name of concept '" + concept.getFullySpecifiedName(Locale.ENGLISH) + "' is a duplicate, it could therefore not be saved. Skipping through to next concept.");
		}
		catch (NonUniqueObjectException e) {
			log.error("Saving concept '" + concept.getFullySpecifiedName(Locale.ENGLISH) + "' produced an exception, please investigate the stack trace.", e);
		}
		catch (ConstraintViolationException e) {
			log.error("Saving concept '" + concept.getFullySpecifiedName(Locale.ENGLISH) + "' produced an exception, please investigate the stack trace.", e);
		}
		return concept;
	}
	
	/**
	 * 
	 * @param mappedId
	 * @return The code out of a mapped id, an empty String otherwise. Eg. "1001" out of "CIEL:1001"
	 */
	protected String getCodeFromMappedId(String mappedId) {
		String split[] = mappedId.split(":");
		if(split.length != 2) {
			return "";
		}
		return split[1];
	}

	/**
	 * @see Initializer#stopped()
	 */
	@Override
	public void stopped() {
	}
}