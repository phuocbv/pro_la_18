/**
 * Copyright(C) 2017  Luvina
 * MessageProperties.java, 20/10/2017 phuocbv
 */
package properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import common.Common;
import common.Constant;
import common.ConstantProperties;

/**
 * class store message properties
 * 
 * @author da
 *
 */
public class MessageProperties {
	public static Map<String, String> messageProperties = getMessageProperties();

	/**
	 * get map properties of message
	 * 
	 * @return Map<String, String> : list message
	 */
	public static Map<String, String> getMessageProperties() {
		Map<String, String> map = new HashMap<String, String>();
		//get properties by path
		Properties properties = Common.getProperties(Constant.PATH_FILE_MESSAGE_PROPERTIES);
		map.put(ConstantProperties.MSG001, Common.getJapanes(properties.getProperty(ConstantProperties.MSG001)));
		map.put(ConstantProperties.MSG002, Common.getJapanes(properties.getProperty(ConstantProperties.MSG002)));
		map.put(ConstantProperties.MSG003, Common.getJapanes(properties.getProperty(ConstantProperties.MSG003)));
		map.put(ConstantProperties.MSG004, Common.getJapanes(properties.getProperty(ConstantProperties.MSG004)));
		map.put(ConstantProperties.MSG005, Common.getJapanes(properties.getProperty(ConstantProperties.MSG005)));
		return map;
	}
}
