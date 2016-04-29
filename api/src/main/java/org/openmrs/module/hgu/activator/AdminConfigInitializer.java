package org.openmrs.module.hgu.activator;

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
import org.openmrs.api.context.Context;
import org.openmrs.module.appframework.service.AppFrameworkService;
import org.openmrs.module.hgu.HumanGeneticsModuleActivator;


// TODO: Auto-generated Javadoc
/**
 * The Class AdminConfigInitializer.
 */
public class AdminConfigInitializer implements Initializer {


	/** The Constant log. */
	protected static final Log log = LogFactory.getLog(AdminConfigInitializer.class);

	/**
	 * Started.
	 *
	 * @see Initializer#started()
	 */
	@Override
	public synchronized void started() {
		log.info("Setting custom admin configuration for " +  HumanGeneticsModuleActivator.ACTIVATOR_MODULE_NAME);

		// Disabling the default Patient Registration app (page).
		AppFrameworkService service = Context.getService(AppFrameworkService.class);
		service.disableApp("referenceapplication.registrationapp.registerPatient");
		service.disableApp("reportingui.reports");

		// Disable unwanted apps and extensions
		service.disableApp("referenceapplication.vitals");
		service.disableExtension("referenceapplication.realTime.vitals");
		service.disableExtension("referenceapplication.realTime.simpleVisitNote");
		service.disableExtension("referenceapplication.realTime.simpleAdmission");
		service.disableExtension("referenceapplication.realTime.simpleDischarge");
		service.disableExtension("referenceapplication.realTime.simpleTransfer");

		
		/*
		
		// Disabling RecentVisits widget on clinician facing dashboard
		service.disableApp("coreapps.visits");
		// Disabling the activeVisitStatus fragment
		service.disableExtension("org.openmrs.module.coreapps.patientHeader.secondLineFragments.activeVisitStatus");
		// Disabling the Coreapps Start Visit link extension (in the Overall Actions panel)
		service.disableExtension("org.openmrs.module.coreapps.createVisit");
		service.disableApp("coreapps.activeVisits");

		AdministrationService adminService = Context.getAdministrationService();
		String pewsTime = adminService.getGlobalProperty(LFHCFormsConstants.PEWS_TIME_WINDOW_PROPERTY);
		if(pewsTime == null) {
			adminService.setGlobalProperty(LFHCFormsConstants.PEWS_TIME_WINDOW_PROPERTY, (new Integer(LFHCFormsConstants.PEWS_FALLBACK_TIMEWINDOW)).toString());
		}

		String pewsExpiry = adminService.getGlobalProperty(LFHCFormsConstants.PEWS_EXPIRY_PROPERTY);
		if(pewsExpiry == null) {
			// The default is an empty String that won't convert to any integer.
			// This triggers the default expiry time mechanism.
			adminService.setGlobalProperty(LFHCFormsConstants.PEWS_EXPIRY_PROPERTY, "");
		}

		// create visit type order property
		String propertyName = LFHCFormsConstants.VISIT_TYPES_ORDER_PROPERTY;
		if(adminService.getGlobalProperty(propertyName) == null) {

			adminService.setGlobalProperty(propertyName,
					"{ "
							+ "\"1\":\""+ LFHCFormsConstants.OUTPATIENT_VISIT_TYPE_UUID + "\","
							+ "\"2\":\""+ LFHCFormsConstants.EMERGENCY_VISIT_TYPE_UUID+ "\"," 
							+ "\"3\":\""+ LFHCFormsConstants.INPATIENT_VISIT_TYPE_UUID +"\","
							+ "\"4\":\""+ LFHCFormsConstants.OPERATING_THEATER_VISIT_TYPE_UUID +"\","
							+ "\"5\":\""+ LFHCFormsConstants.OUTREACH_VISIT_TYPE_UUID +"\""
							+ "}" 
					);
			GlobalProperty typesOrder = adminService.getGlobalPropertyObject(propertyName);
			typesOrder.setDescription("Order in which the Visit Types should appear. A JSON-like format with \"order\" as key and \"VisitType UUID\" as value");
			adminService.saveGlobalProperty(typesOrder);


		} */
	}

	/**
	 * Stopped.
	 *
	 * @see Initializer#stopped()
	 */
	@Override
	public void stopped() {
		log.info("Unsetting custom admin configuration for " + HumanGeneticsModuleActivator.ACTIVATOR_MODULE_NAME);

		AppFrameworkService service = Context.getService(AppFrameworkService.class);
		service.enableApp("referenceapplication.registrationapp.registerPatient");
		service.enableApp("reportingui.reports");
		/*service.enableApp("referenceapplication.vitals");
		service.enableApp("coreapps.activeVisits");
		service.enableExtension("referenceapplication.realTime.vitals");
		service.enableExtension("referenceapplication.realTime.simpleVisitNote");
		service.enableExtension("org.openmrs.module.coreapps.patientHeader.secondLineFragments.activeVisitStatus");
		service.enableExtension("org.openmrs.module.coreapps.createVisit");
		service.enableExtension("referenceapplication.realTime.simpleAdmission");
		service.enableExtension("referenceapplication.realTime.simpleDischarge");
		service.enableExtension("referenceapplication.realTime.simpleTransfer");
		service.enableApp("coreapps.visits");*/

	}
}
