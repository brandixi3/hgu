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
		
	}

	/**
	 * @see Initializer#stopped()
	 */
	@Override
	public void stopped() {
		
	}
}
