/**
 * Copyright(C) 2017  Luvina
 * AdminProperties.java, 20/10/2017 phuocbv
 */
package properties;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import common.Constant;

/**
 * class store admin properties
 * 
 * @author da
 *
 */
public class AdminProperties {
	private static Map<String, String> adminProperties = new HashMap<String, String>();

	/**
	 * read propeties
	 */
	static {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		try {
			prop.load(classLoader.getResourceAsStream(Constant.PATH_FILE_ADMIN_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
		@SuppressWarnings (value="unchecked")//remove warning
		Enumeration<String> en = (Enumeration<String>) prop.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			adminProperties.put(key, prop.getProperty(key));
		}
	}

	/**
	 * return value by key
	 * 
	 * @param key
	 *            config
	 * @return value
	 */
	static public String getValue(String key) {
		String value = "";
		if (adminProperties.containsKey(key)) {
			value = adminProperties.get(key);
		}
		return value;
	}

}
