/**
 * Copyright(C) 2017  Luvina
 * MessageErrorProperties.java, 20/10/2017 phuocbv
 */
package properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import common.Common;
import common.Constant;
import common.ConstantProperties;

/**
 * class store message error properties
 * 
 * @author da
 *
 */
public class MessageErrorProperties {
	public static Map<String, String> messageErrorProperties = getMessageErrorProperties();

	/**
	 * get Message Error Properties
	 * 
	 * @return Map<String, String>
	 */
	public static Map<String, String> getMessageErrorProperties() {
		Map<String, String> listError = new HashMap<String, String>();
		// get Properties from path
		Properties properties = Common.getProperties(Constant.PATH_FILE_MESSAGE_ERROR_PROPERTIES);
		listError.put(ConstantProperties.ER001, Common.getJapanes(properties.getProperty(ConstantProperties.ER001)));
		listError.put(ConstantProperties.ER001_LOGIN_NAME,
				Common.getJapanes(properties.getProperty(ConstantProperties.ER001_LOGIN_NAME)));
		listError.put(ConstantProperties.ER001_PASSWORD,
				Common.getJapanes(properties.getProperty(ConstantProperties.ER001_PASSWORD)));
		listError.put(ConstantProperties.ER001_LOGIN_NAME_AND_PASSWORD,
				Common.getJapanes(properties.getProperty(ConstantProperties.ER001_LOGIN_NAME_AND_PASSWORD)));
		listError.put(ConstantProperties.ER016, Common.getJapanes(properties.getProperty(ConstantProperties.ER016)));
		return listError;
	}

	public static void main(String[] args) {
		System.out.println(messageErrorProperties.get(ConstantProperties.ER001_LOGIN_NAME));
	}
}
