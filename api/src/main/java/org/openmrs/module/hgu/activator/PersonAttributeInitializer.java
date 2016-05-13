package org.openmrs.module.hgu.activator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.context.Context;
import org.openmrs.module.hgu.HumanGeneticsModuleActivator;
import org.openmrs.module.hgu.HGUFormsConstants;


// TODO: Auto-generated Javadoc
/**
 * The Class PersonInitializer.
 */
public class PersonAttributeInitializer implements Initializer {

	/** The Constant log. */
	protected static final Log log = LogFactory.getLog(PersonAttributeInitializer.class);

	
	/* (non-Javadoc)
	 * @see org.openmrs.module.hgu.activator.Initializer#started()
	 */
	
	@Override
	public synchronized void started() {
		log.info("Setting 'person attribute' configuration for " + HumanGeneticsModuleActivator.ACTIVATOR_MODULE_NAME);
		
		{
			String uuid = HGUFormsConstants.HGU_CHILD_ONE_NAME_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("ChildOne's Name");
			type.setDescription("ChaildOne's Name");
			type.setFormat("java.lang.String");
			Context.getPersonService().savePersonAttributeType(type);
		}
		
		{
			String uuid = HGUFormsConstants.HGU_MOTHERS_BDAY_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("Mother's B'day");
			type.setDescription("Mother's B'day");
			type.setFormat("org.openmrs.util.AttributableDate");
			Context.getPersonService().savePersonAttributeType(type);
		}
		
		
		{
			String uuid = HGUFormsConstants.HGU_FATHERS_NAME_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("Father's Name");
			type.setDescription("Father's Name");
			type.setFormat("java.lang.String");
			Context.getPersonService().savePersonAttributeType(type);
		}
		
		
		{
			String uuid = HGUFormsConstants.HGU_FATHERS_BDAY_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("Father's B'day");
			type.setDescription("Father's B'day");
			type.setFormat("org.openmrs.util.AttributableDate");
			Context.getPersonService().savePersonAttributeType(type);
		}
		
		{
			String uuid = HGUFormsConstants.HGU_REFFERED_BY_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("Reffered By");
			type.setDescription("Reffered doctor's name");
			type.setFormat("java.lang.String");
			Context.getPersonService().savePersonAttributeType(type);
		}
		
		{
			String uuid = HGUFormsConstants.HGU_REASON_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("Reason");
			type.setDescription("Reason");
			type.setFormat("java.lang.String");
			Context.getPersonService().savePersonAttributeType(type);
		}
		
		{
			String uuid = HGUFormsConstants.HGU_CHILD_ONE_BIRTHDAY_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("Child One's B'day");
			type.setDescription("Child One's B'day");
			type.setFormat("org.openmrs.util.AttributableDate");
			Context.getPersonService().savePersonAttributeType(type);
		}
		
		//Addedon 27thApril 8.30PM
		{
			String uuid = HGUFormsConstants.HGU_CHILD_ONE_GENDER_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("ChildOne's Gender");
			type.setDescription("ChaildOne's Gender");
			type.setFormat("java.lang.String");
			Context.getPersonService().savePersonAttributeType(type);
		}
		
		
		{
			String uuid = HGUFormsConstants.HGU_CHILD_TWO_NAME_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("Child Two's Name");
			type.setDescription("ChaildTwo's Name");
			type.setFormat("java.lang.String");
			Context.getPersonService().savePersonAttributeType(type);
		}
		{
			String uuid = HGUFormsConstants.HGU_CHILD_TWO_BIRTHDAY_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("Child Two's B'day");
			type.setDescription("Child Two's B'day");
			type.setFormat("org.openmrs.util.AttributableDate");
			Context.getPersonService().savePersonAttributeType(type);
		}
		{
			String uuid = HGUFormsConstants.HGU_CHILD_TWO_GENDER_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("ChildTwo's Gender");
			type.setDescription("ChaildTwo's Gender");
			type.setFormat("java.lang.String");
			Context.getPersonService().savePersonAttributeType(type);
		}
		
		
		{
			String uuid = HGUFormsConstants.HGU_CHILD_THREE_NAME_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("Child Three's Name");
			type.setDescription("ChaildThree's Name");
			type.setFormat("java.lang.String");
			Context.getPersonService().savePersonAttributeType(type);
		}
		{
			String uuid = HGUFormsConstants.HGU_CHILD_THREE_BIRTHDAY_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("Child Three's B'day");
			type.setDescription("Child Three's B'day");
			type.setFormat("org.openmrs.util.AttributableDate");
			Context.getPersonService().savePersonAttributeType(type);
		}
		
		
		{
			String uuid = HGUFormsConstants.HGU_CHILD_THREE_GENDER_UUID;
			PersonAttributeType type = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
			if(type == null) {
				type = new PersonAttributeType();
				type.setUuid(uuid);
			}
			type.setName("ChildThree's Gender");
			type.setDescription("ChaildThree's Gender");
			type.setFormat("java.lang.String");
			Context.getPersonService().savePersonAttributeType(type);
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
