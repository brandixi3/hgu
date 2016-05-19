package org.openmrs.module.hgu.activator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.FormService;
import org.openmrs.api.context.Context;
import org.openmrs.module.formentryapp.FormEntryAppService;
import org.openmrs.module.formentryapp.FormManager;
import org.openmrs.module.formentryapp.page.controller.forms.ExtensionForm;
import org.openmrs.module.hgu.HumanGeneticsModuleActivator;
import org.openmrs.module.hguforms.util.ExtensionFormUtil;
import org.openmrs.module.htmlformentry.HtmlForm;
import org.openmrs.module.htmlformentry.HtmlFormEntryService;
import org.openmrs.module.htmlformentryui.HtmlFormUtil;
import org.openmrs.ui.framework.resource.ResourceFactory;
import org.openmrs.ui.framework.resource.ResourceProvider;

public class HtmlFormsInitializer implements Initializer {

	protected static final Log log = LogFactory.getLog(HtmlFormsInitializer.class);

	protected static final String providerName = "hgu";
	protected static final String formsPath = "htmlforms/";
	
	/**
	 * @see Initializer#started()
	 */
	@Override
	public synchronized void started() {
		log.info("Setting HGU forms for " + HumanGeneticsModuleActivator.ACTIVATOR_MODULE_NAME);

		final ResourceFactory resourceFactory = ResourceFactory.getInstance();
		final ResourceProvider resourceProvider = resourceFactory.getResourceProviders().get(providerName);
		
		// Scanning the forms resources folder
		final List<String> formPaths = new ArrayList<String>();
		final File formsDir = resourceProvider.getResource(formsPath); // The ResourceFactory can't return File instances, hence the ResourceProvider need
		if(formsDir == null || formsDir.isDirectory() == false) {
			log.error("No HTML forms could be retrieved from the provided folder: " + providerName + ":" + formsPath);
			return;
		}
		for(File file : formsDir.listFiles()) 
			formPaths.add(formsPath + file.getName());	// Adding each file's path to the list
		
		// Save form + add its meta data
		final FormManager formManager = Context.getRegisteredComponent("formManager", FormManager.class);
		final FormEntryAppService hfeAppService = Context.getRegisteredComponent("formEntryAppService", FormEntryAppService.class);
		final FormService formService = Context.getFormService();
		final HtmlFormEntryService hfeService = Context.getService(HtmlFormEntryService.class);
		for (String formPath : formPaths) {
			// Save form
			HtmlForm htmlForm = null; 
			try {
				htmlForm = HtmlFormUtil.getHtmlFormFromUiResource(resourceFactory, formService, hfeService, providerName, formPath);
			} catch (IOException e) {
				log.error("Could not generate HTML form from the following resource file: " + formPath, e);
				continue;
			} catch (IllegalArgumentException e) {
				log.error("Error while parsing the form's XML: " + formPath, e);
				continue;
			}
			// Adds meta data
			ExtensionForm extensionForm = null;
			try {
				extensionForm = ExtensionFormUtil.getExtensionFormFromUiResourceAndForm(resourceFactory, providerName, formPath, hfeAppService, formManager, htmlForm.getForm());
			} catch (Exception e) {
				log.error("The form was created but its extension point could not be created in Manage Forms \\ Configure Metadata: " + formPath, e);
				continue;
			}
		}
	}

	/**
	 * @see Initializer#stopped()
	 */
	@Override
	public void stopped() {
		//TODO: Perhaps disable the forms?
	}
	
}
