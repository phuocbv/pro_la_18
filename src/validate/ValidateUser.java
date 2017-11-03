/**
 * Copyright(C) 2017  Luvina
 * ValidateUser.java, 02/11/2017 phuocbv
 */
package validate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import common.Constant;
import common.ConstantProperties;
import dao.TblUserDAO;
import dao.impl.TblUserDAOImpl;
import entity.UserInfor;
import logic.TblUserLogic;
import logic.impl.TblUserLogicImpl;
import properties.MessageErrorProperties;

/**
 * class validate user
 * 
 * @author LA-AM
 *
 */
public class ValidateUser {
	private TblUserLogic tblUserLogic = null;

	/**
	 * contructer
	 */
	public ValidateUser() {
		tblUserLogic = new TblUserLogicImpl();
	}

	private static final String LOGINNAME_PATTERN = "[a-zA-Z0-9._]";

	/**
	 * method validate user infor
	 * 
	 * @param userInfor
	 *            : object user infor
	 * @return List<String> : list message error
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<String> validateUserInfor(UserInfor userInfor) throws ClassNotFoundException, SQLException {
		List<String> listError = new ArrayList<>();
		// check login name
		if (userInfor.getLoginName() == null || Constant.EMPTY_STRING.equals(userInfor.getLoginName())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_LOGIN_NAME));
		} else if (userInfor.getLoginName().length() < 4 || userInfor.getLoginName().length() > 15) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER007_LOGIN_NAME));
		} else if (userInfor.getLoginName().matches(LOGINNAME_PATTERN)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER019));
		} else if (tblUserLogic.checkLoginNameExist(userInfor.getLoginName())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER003_LOGIN_NAME));
		}

		// check group id
		if (userInfor.getGroupId() == null || Constant.ZERO.equals(userInfor.getGroupId())
				|| Constant.EMPTY_STRING.equals(userInfor.getGroupId())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER002_GROUP));
		} // check ton tai

		// check full name
		if (userInfor.getFullName() == null || Constant.EMPTY_STRING.equals(userInfor.getFullName())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_FULL_NAME));
		} else if (userInfor.getFullName().length() > 255) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER006_FULL_NAME));
		}

		// check full name kana
		if (userInfor.getFullNameKana() != null && userInfor.getFullNameKana().length() > 255) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER006_FULL_NAME_KANA));
		} // conf check kana
		return listError;
	}

	public static void main(String[] args) {
		Pattern pattern;
		String USERNAME_PATTERN = "[a-zA-Z_0-9]";

		pattern = Pattern.compile(USERNAME_PATTERN);

		System.out.println(pattern.matcher("sdfsdf").matches());
	}
}
