package org.openmrs.module.hgu.activator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.PersonAttributeType;
import org.openmrs.RelationshipType;
import org.openmrs.api.context.Context;
import org.openmrs.module.hgu.HumanGeneticsModuleActivator;
import org.openmrs.module.hgu.HGUFormsConstants;


// TODO: Auto-generated Javadoc
/**
 * The Class PersonInitializer.
 */
public class RelationshipTypesInitializer implements Initializer {

	/** The Constant log. */
	protected static final Log log = LogFactory.getLog(RelationshipTypesInitializer.class);

	
	/* (non-Javadoc)
	 * @see org.openmrs.module.hgu.activator.Initializer#started()
	 */
	
	@Override
	public synchronized void started() {
		log.info("Setting 'relationship types' configuration for " + HumanGeneticsModuleActivator.ACTIVATOR_MODULE_NAME);
		
		{
			String uuid = HGUFormsConstants.HGU_RELATIONSHIP_HUSBAND_SPOUSE;
			RelationshipType type = Context.getPersonService().getRelationshipTypeByUuid(uuid);
			if(type == null) {
				type = new RelationshipType();
				type.setUuid(uuid);
			}
			type.setName("Husband to Wife");
			type.setDescription("Husband to Wife relationship");
			type.setaIsToB("Husband");
			type.setbIsToA("Wife");
			Context.getPersonService().saveRelationshipType(type);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.hgu.activator.Initializer#stopped()
	 */
	@Override
	public void stopped() {
		log.info("Unsetting 'person' configuration for " + HumanGeneticsModuleActivator.ACTIVATOR_MODULE_NAME);
	}
}
