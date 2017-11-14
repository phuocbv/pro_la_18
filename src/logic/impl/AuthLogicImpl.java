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
import validate.ValidateLogin;

/**
 * process logic login
 * 
 * @author da
 *
 */
public class AuthLogicImpl implements AuthLogic {

	/**
	 * function validate admin
	 * 
	 * @param loginName
	 *            : is username of account admin
	 * @param password
	 *            : is password of account admin
	 * @return List<String> : list message
	 */
	@Override
	public List<String> validateAdmin(String loginName, String password) {
		ValidateLogin loginValidate = new ValidateLogin();
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
		if (!AdminProperties.getValue(Constant.LOGIN_NAME).equals(loginName)) {
			listMessage.add(MessageErrorProperties.getValue(ConstantProperties.ER016));
			return listMessage;
		}
		// md5 password
		String inputPassword = Common.MD5(password, AdminProperties.getValue(Constant.SALT_ADMIN));
		// compare password
		if (!AdminProperties.getValue(Constant.PASSWORD).equals(inputPassword)) {
			listMessage.add(MessageErrorProperties.getValue(ConstantProperties.ER016));
			return listMessage;
		}
		return listMessage;
	}
}
