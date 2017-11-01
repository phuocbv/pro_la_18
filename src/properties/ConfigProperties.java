/**
 * Copyright(C) 2017  Luvina
 * ConfigProperties.java, 01/11/2017 phuocbv
 */
package properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import common.Common;
import common.Constant;
import common.ConstantProperties;

/**
 * store config common
 * 
 * @author da
 *
 */
public class ConfigProperties {
	public static Map<String, String> configProperties = getConfigProperties();

	/**
	 * get map properties of config
	 * 
	 * @return Map<String, String> store config
	 */
	public static Map<String, String> getConfigProperties() {
		Map<String, String> map = new HashMap<String, String>();
		//get properties by path
		Properties properties = Common.getProperties(Constant.PATH_FILE_CONFIG_PROPERTIES);
		map.put(ConstantProperties.NUMBER_PAGE_IN_PAGE, properties.getProperty(ConstantProperties.NUMBER_PAGE_IN_PAGE));
		map.put(ConstantProperties.LIMIT_RECORD, properties.getProperty(ConstantProperties.LIMIT_RECORD));
		return map;
	}
}
