/**
 * Copyright(C) 2017  Luvina
 * AdminProperties.java, 20/10/2017 phuocbv
 */
package properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import common.Common;
import common.Constant;

/**
 * class store admin properties
 * 
 * @author da
 *
 */
public class AdminProperties {
	public static Map<String, String> adminProperties = getAdminProperties();

	/**
	 * get map properties of admin
	 * 
	 * @return
	 */
	public static Map<String, String> getAdminProperties() {
		Map<String, String> map = new HashMap<String, String>();
		//get properties by path
		Properties properties = Common.getProperties(Constant.PATH_FILE_ADMIN_PROPERTIES);
		map.put(Constant.LOGIN_NAME, properties.getProperty(Constant.LOGIN_NAME));
		map.put(Constant.PASSWORD, properties.getProperty(Constant.PASSWORD));
		map.put(Constant.SALT_ADMIN, properties.getProperty(Constant.SALT_ADMIN));
		return map;
	}
}
