package org.openmrs.module.hgu.activator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.EncounterType;
import org.openmrs.api.EncounterService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hgu.HumanGeneticsModuleActivator;

public class EncounterTypesInitializer implements Initializer {

	protected static final Log log = LogFactory.getLog(EncounterTypesInitializer.class);

	@Override
	public synchronized void started() {
		log.info("Setting new Encounter Types for " + HumanGeneticsModuleActivator.ACTIVATOR_MODULE_NAME);
		EncounterService es = Context.getEncounterService();

		{
			String name = "Report Notes";
			String desc = "Report related information";
			String uuid = "2f1fe661-fed8-4eef-b152-8d6264dd1205";
			EncounterType encounterType = es.getEncounterTypeByUuid(uuid);
			if(encounterType == null) {
				encounterType = new EncounterType(name, desc);
				encounterType.setUuid(uuid);
				es.saveEncounterType(encounterType);
			}
		}
		{
			String name = "Lab Test Order";
			String desc = "";
			String uuid = "39deb891-8dc6-451b-9361-dbf564f27250";
			EncounterType encounterType = es.getEncounterTypeByUuid(uuid);
			if(encounterType == null) {
				encounterType = new EncounterType(name, desc);
				encounterType.setUuid(uuid);
				es.saveEncounterType(encounterType);
			}
		}
		
		{
			String name = "physician’s details ";
			String desc = "physician’s details ";
			String uuid = "9b9c20df-873b-4b87-86b2-badbcb4d04a0";
			EncounterType encounterType = es.getEncounterTypeByUuid(uuid);
			if(encounterType == null) {
				encounterType = new EncounterType(name, desc);
				encounterType.setUuid(uuid);
				es.saveEncounterType(encounterType);
			}
		}
	}

	/**
	 * @see Initializer#stopped()
	 */
	@Override
	public void stopped() {
		
	}
}
