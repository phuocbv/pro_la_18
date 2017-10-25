/**
 * Copyright(C) 2017  Luvina
 * DatabaseProperties.java, 20/10/2017 phuocbv
 */
package properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import common.Common;
import common.Constant;
import common.ConstantProperties;

/**
 * store config database
 * 
 * @author da
 *
 */
public class DatabaseProperties {
	public static Map<String, String> databaseProperties = getDatabaseProperties();

	/**
	 * get map properties of database
	 * 
	 * @return
	 */
	public static Map<String, String> getDatabaseProperties() {
		Map<String, String> map = new HashMap<String, String>();
		//get properties by path
		Properties properties = Common.getProperties(Constant.PATH_FILE_DATABASE_PROPERTIES);
		map.put(ConstantProperties.URL, properties.getProperty(ConstantProperties.URL));
		map.put(ConstantProperties.USERNAME, properties.getProperty(ConstantProperties.USERNAME));
		map.put(ConstantProperties.PASSWORD, properties.getProperty(ConstantProperties.PASSWORD));
		map.put(ConstantProperties.COUNT_PAGING, properties.getProperty(ConstantProperties.COUNT_PAGING));
		map.put(ConstantProperties.LIMIT_RECORD, properties.getProperty(ConstantProperties.LIMIT_RECORD));
		return map;
	}
}
