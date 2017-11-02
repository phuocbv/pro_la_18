/**
 * Copyright(C) 2017  Luvina
 * LoginValidate.java, 20/10/2017 phuocbv
 */
package validate;

import java.util.ArrayList;

import common.Constant;
import common.ConstantProperties;
import properties.MessageErrorProperties;

/**
 * validata data of form login
 * 
 * @author da
 *
 */
public class ValidateLogin {
	/**
	 * method validate data
	 * 
	 * @param loginName
	 *            is user name of account admin
	 * @param password
	 *            is password of account admin
	 * @return ArrayList<String> list message if has error
	 */
	public ArrayList<String> validate(String loginName, String password) {
		ArrayList<String> listMessage = new ArrayList<>();
		// test empty login name
		if (Constant.EMPTY_STRING.equals(loginName)) {
			listMessage.add(MessageErrorProperties.getValue(ConstantProperties.ER001_LOGIN_NAME));
		}
		// test empty password
		if (Constant.EMPTY_STRING.equals(password)) {
			listMessage.add(MessageErrorProperties.getValue(ConstantProperties.ER001_PASSWORD));
		}
		return listMessage;
	}
}
