/**
 * Copyright(C) 2017  Luvina
 * MessageErrorProperties.java, 20/10/2017 phuocbv
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
 * class store message error properties
 * 
 * @author da
 *
 */
public class MessageErrorProperties {
	static private Map<String, String> messageErrorProperties = new HashMap<String, String>();

	/**
	 * read propeties
	 */
	static {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		try {
			prop.load(classLoader.getResourceAsStream(Constant.PATH_FILE_MESSAGE_ERROR_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
		@SuppressWarnings (value="unchecked")
		Enumeration<String> en = (Enumeration<String>) prop.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			messageErrorProperties.put(key, Common.getJapanes(prop.getProperty(key)));
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
		if (messageErrorProperties.containsKey(key)) {
			value = messageErrorProperties.get(key);
		}
		return value;
	}
}
