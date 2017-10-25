/**
 * Copyright(C) 2017  Luvina
 * AuthLogic.java, 20/10/2017 phuocbv
 */
package logic;

import java.util.List;

/**
 * interface LoginLogic
 * 
 * @author da
 *
 */
public interface AuthLogic {
	/**
	 * function logic login
	 * 
	 * @param loginName
	 * @param password
	 * @param session
	 * @return
	 */
	public List<String> validateAdmin(String loginName, String password);
}
