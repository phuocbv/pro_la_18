/**
 * Copyright(C) 2017  Luvina
 * ValidateUser.java, 02/11/2017 phuocbv
 */
package validate;

import java.util.ArrayList;
import java.util.List;

import common.Constant;
import common.ConstantProperties;
import entity.UserInfor;
import properties.MessageErrorProperties;

/**
 * class validate user
 * 
 * @author LA-AM
 *
 */
public class ValidateUser {
	 private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._]{4,15}$"; 
	/**
	 * method validate user infor
	 * 
	 * @param userInfor
	 *            : object user infor
	 * @return List<String> : list message error
	 */
	public List<String> validateUserInfor(UserInfor userInfor) {
		List<String> listError = new ArrayList<>();
		//check login name
		if (!validateString(userInfor.getLoginName(), 4, 15)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER007_LOGIN_NAME));
		}
		//check group id
		if (userInfor.getGroupId() == null || Constant.ZERO.equals(userInfor.getGroupId())
				|| Constant.EMPTY_STRING.equals(userInfor.getGroupId())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER002_GROUP));
		}
		//check full name
		if (!validateString(userInfor.getFullName(), 1, 255)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER007_FULL_NAME));
		}
		//check full name kana
		if (!validateString(userInfor.getFullName(), 1, 255)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER007_FULL_NAME_KANA));
		}
		return listError;
	}

	/**
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	private boolean validateString(String value, int min, int max) {
		// test null or string ""
		if (value == null || Constant.EMPTY_STRING.equals(value)) {
			return false;
		}
		// test min <= value <= max
		if (value.length() < min || value.length() > max) {
			return false;
		}
		return true;
	}
}
