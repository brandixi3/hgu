package org.openmrs.module.hgu;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.ModuleActivator;
import org.openmrs.module.hgu.activator.AdminConfigInitializer;
import org.openmrs.module.hgu.activator.Initializer;
import org.openmrs.module.hgu.activator.PersonAttributeInitializer;

// TODO: Auto-generated Javadoc
/**
 * This class contains the logic that is run every time this module is either started or stopped.
 */
public class HumanGeneticsModuleActivator implements ModuleActivator {
	
	/** The log. */
	protected Log log = LogFactory.getLog(getClass());
	
	/** The Constant ACTIVATOR_MODULE_NAME. */
	public final static String ACTIVATOR_MODULE_NAME = "HGU Module";
		
	
	/**
	 * Gets the initializers.
	 *
	 * @return the initializers
	 */
	public List<Initializer> getInitializers() {
		List<Initializer> l = new ArrayList<Initializer>();
		l.add(new PersonAttributeInitializer());
		l.add(new AdminConfigInitializer());
		return l;
	}
	
	
	/**
	 * Will refresh context.
	 *
	 * @see ModuleActivator#willRefreshContext()
	 */
	public void willRefreshContext() {
		log.info("Refreshing Human Genetics Module Module");
	}
	
	/**
	 * Context refreshed.
	 *
	 * @see ModuleActivator#contextRefreshed()
	 */
	public void contextRefreshed() {
		log.info("Human Genetics Module Module refreshed");
	}
	
	/**
	 * Will start.
	 *
	 * @see ModuleActivator#willStart()
	 */
	public void willStart() {
		log.info("Starting Human Genetics Module Module");
	}
	
	/**
	 * Started.
	 *
	 * @see ModuleActivator#started()
	 */
	public void started() {
		
		for (Initializer initializer : getInitializers()) {
			initializer.started();
		}
		
		log.info("Human Genetics Module Module started");
	}
	
	/**
	 * Will stop.
	 *
	 * @see ModuleActivator#willStop()
	 */
	public void willStop() {
		log.info("Stopping Human Genetics Module Module");
	}
	
	/**
	 * Stopped.
	 *
	 * @see ModuleActivator#stopped()
	 */
	public void stopped() {
		
		for (int i = getInitializers().size() - 1; i >= 0; i--) {
			getInitializers().get(i).stopped();
		}
		log.info("Human Genetics Module Module stopped");
	}
		
}
