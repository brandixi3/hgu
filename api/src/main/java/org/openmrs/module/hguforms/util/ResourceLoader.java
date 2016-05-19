package org.openmrs.module.hguforms.util;

import java.io.IOException;
import java.io.InputStream;

public interface ResourceLoader {

	InputStream getResourceAsStream(String resourcePath);
	
	String getResourceAsSting(String resourcePath, String encoding) throws IOException;
}