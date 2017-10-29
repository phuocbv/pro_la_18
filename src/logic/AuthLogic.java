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
	 * function validate admin
	 * 
	 * @param loginName
	 *            : is username of account admin
	 * @param password
	 *            is password of account admin
	 * @return List<String> : list message
	 */
	public List<String> validateAdmin(String loginName, String password);
}
