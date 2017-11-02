/**
 * Copyright(C) 2017  Luvina
 * DatabaseProperties.java, 20/10/2017 phuocbv
 */
package properties;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import common.Constant;

/**
 * store config database
 * 
 * @author da
 *
 */
public class DatabaseProperties {
	static private Map<String, String> databaseProperties = new HashMap<String, String>();

	/**
	 * read propeties
	 */
	static {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		try {
			prop.load(classLoader.getResourceAsStream(Constant.PATH_FILE_DATABASE_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Enumeration<String> en = (Enumeration<String>) prop.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			databaseProperties.put(key, prop.getProperty(key));
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
		if (databaseProperties.containsKey(key)) {
			value = databaseProperties.get(key);
		}
		return value;
	}
}
