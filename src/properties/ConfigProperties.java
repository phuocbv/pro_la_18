/**
 * Copyright(C) 2017  Luvina
 * ConfigProperties.java, 01/11/2017 phuocbv
 */
package properties;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import common.Constant;

/**
 * store config common
 * 
 * @author da
 *
 */
public class ConfigProperties {
	static private Map<String, String> configProperties = new HashMap<String, String>();

	/**
	 * read propeties
	 */
	static {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		try {
			prop.load(classLoader.getResourceAsStream(Constant.PATH_FILE_CONFIG_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Enumeration<String> en = (Enumeration<String>) prop.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			configProperties.put(key, prop.getProperty(key));
		}
	}

	/**
	 * return value by key
	 * 
	 * @param key
	 *            mã các trường config
	 * @return value
	 */
	static public String getValue(String key) {
		String value = "";
		if (configProperties.containsKey(key)) {
			value = configProperties.get(key);
		}
		return value;
	}
}
