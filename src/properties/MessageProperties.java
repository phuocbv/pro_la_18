/**
 * Copyright(C) 2017  Luvina
 * MessageProperties.java, 20/10/2017 phuocbv
 */
package properties;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import common.Common;
import common.Constant;

/**
 * class store message properties
 * 
 * @author da
 *
 */
public class MessageProperties {
	static private Map<String, String> messageProperties = new HashMap<String, String>();

	/**
	 * read propeties
	 */
	static {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		try {
			prop.load(classLoader.getResourceAsStream(Constant.PATH_FILE_MESSAGE_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
		@SuppressWarnings (value="unchecked")
		Enumeration<String> en = (Enumeration<String>) prop.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			messageProperties.put(key, Common.getJapanes(prop.getProperty(key)));
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
		if (messageProperties.containsKey(key)) {
			value = messageProperties.get(key);
		}
		return value;
	}
}
