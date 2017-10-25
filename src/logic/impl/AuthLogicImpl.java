/**
 * Copyright(C) 2017  Luvina
 * AuthLogicImpl.java, 20/10/2017 phuocbv
 */
package logic.impl;

import java.util.List;

import common.Common;
import common.Constant;
import common.ConstantProperties;
import logic.AuthLogic;
import properties.AdminProperties;
import properties.MessageErrorProperties;
import validate.LoginValidate;

/**
 * process logic login, logout
 * 
 * @author da
 *
 */
public class AuthLogicImpl implements AuthLogic {

	/**
	 * function validate admin
	 * 
	 * @param loginName
	 * @param password
	 * @param session
	 * @return
	 */
	@Override
	public List<String> validateAdmin(String loginName, String password) {
		LoginValidate loginValidate = new LoginValidate();
		// validate data
		List<String> listMessage = loginValidate.validate(loginName, password);
		// if message different empty then return list message
		// in case validate has error
		if (!listMessage.isEmpty()) {
			return listMessage;
		}
		// if LOGIN_NAME in adminProperties different loginName input then return
		// listMessage
		// case different compare LOGIN_NAME
		if (!AdminProperties.adminProperties.get(Constant.LOGIN_NAME).equals(loginName)) {
			listMessage.add(MessageErrorProperties.messageErrorProperties.get(ConstantProperties.ER016));
			return listMessage;
		}
		// md5 password
		String inputPassword = Common.MD5(password, AdminProperties.adminProperties.get(Constant.SALT_ADMIN));
		// compare password
		if (!AdminProperties.adminProperties.get(Constant.PASSWORD).equals(inputPassword)) {
			listMessage.add(MessageErrorProperties.messageErrorProperties.get(ConstantProperties.ER016));
			return listMessage;
		}
		return listMessage;
	}
}
