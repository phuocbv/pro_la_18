/**
 * Copyright(C) 2017  Luvina
 * ValidateUser.java, 02/11/2017 phuocbv
 */
package validate;

import java.util.ArrayList;
import java.util.List;

import common.Constant;
import entity.UserInfor;
import properties.ConfigProperties;

/**
 * class validate user
 * 
 * @author LA-AM
 *
 */
public class ValidateUser {
	/**
	 * method validate user infor
	 * 
	 * @param userInfor
	 *            : object user infor
	 * @return List<String> : list message error
	 */
	public List<String> validateUserInfor(UserInfor userInfor) {
		List<String> listError = new ArrayList<>();

		if (userInfor.getLoginName() == null || Constant.EMPTY_STRING.equals(userInfor.getLoginName())) {
			
		}
		return listError;
	}
}
