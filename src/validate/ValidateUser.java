/**
 * Copyright(C) 2017  Luvina
 * ValidateUser.java, 02/11/2017 phuocbv
 */
package validate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.Common;
import common.Constant;
import common.ConstantProperties;
import entity.UserInfor;
import logic.MstGroupLogic;
import logic.MstJapanLogic;
import logic.TblUserLogic;
import logic.impl.MstGroupLogicImpl;
import logic.impl.MstJapanLogicImpl;
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
	private MstGroupLogic mstGroupLogic = null;
	private MstJapanLogic mstJapanLogic = null;

	/**
	 * contructer
	 */
	public ValidateUser() {
		tblUserLogic = new TblUserLogicImpl();
		mstGroupLogic = new MstGroupLogicImpl();
		mstJapanLogic = new MstJapanLogicImpl();
	}

	public static final String LOGIN_NAME_PATTERN = "^[a-zA-Z]{1}[a-zA-Z0-9_]{3,14}$";
	public static final String FULL_NAME_KATA_PATTERN = "^[ア-ンーッ ]{0,255}$";
	public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	public static final String TEL_PATTERN = "^[0-9]{1,4}-[0-9]{4}-[0-9]{4}$";
	public static final String TOTAL_PATTERN = "^[1-9]{1}[0-9]{0,10}$";
	public static final String PASSWORD_PATTERN = "^[a-zA-Z0-9!@#$%^&*(){}_+.,<>?;:'-=]{6,15}$";

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
		// check login name (4)
		if (userInfor.getLoginName() == null || Constant.EMPTY_STRING.equals(userInfor.getLoginName())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_LOGIN_NAME));
		} else if (userInfor.getLoginName().length() < Constant.MIN_LENGTH_LOGIN_NAME
				|| userInfor.getLoginName().length() > Constant.MAX_LENGTH_FULL_NAME) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER007_LOGIN_NAME));
		} else if (!userInfor.getLoginName().matches(LOGIN_NAME_PATTERN)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER019));
		} else if (tblUserLogic.checkExistedLoginName(null, userInfor.getLoginName())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER003_LOGIN_NAME));
		}

		// check group id (2)
		if (userInfor.getGroupId() == null || Constant.ZERO.equals(userInfor.getGroupId())
				|| Constant.EMPTY_STRING.equals(userInfor.getGroupId())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER002_GROUP));
		} else if (!mstGroupLogic.checkExistGroup(userInfor.getGroupId())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER004_GROUP));
		}

		// check full name (2)
		if (userInfor.getFullName() == null || Constant.EMPTY_STRING.equals(userInfor.getFullName())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_FULL_NAME));
		} else if (userInfor.getFullName().length() > Constant.MAX_LENGTH_FULL_NAME) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER006_FULL_NAME));
		}

		// check full name kana (2)
		if (userInfor.getFullNameKana() != null
				&& userInfor.getFullNameKana().length() > Constant.MAX_LENGTH_FULL_NAME_KANA) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER006_FULL_NAME_KANA));
		} else if (!userInfor.getFullNameKana().matches(FULL_NAME_KATA_PATTERN)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER009_FULL_NAME_KANA));
		}

		// check birthday (1)
		if (!Common.checkBirthday(userInfor.getBirthdayYear(), userInfor.getBirthdayMonth(),
				userInfor.getBirthdayDay())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER011_BIRTHDAY));
		} else { // in case haven't error then add to birthday
			int birthdayYear = Common.parseInt(userInfor.getBirthdayYear(), 0);
			int birthdayMonth = Common.parseInt(userInfor.getBirthdayMonth(), 0);
			int birthdayDay = Common.parseInt(userInfor.getBirthdayDay(), 0);
			Date birthday = Common.toDate(birthdayYear, birthdayMonth, birthdayDay);
			userInfor.setBirthday(birthday);
		}

		// check email (4)
		if (userInfor.getEmail() == null || Constant.EMPTY_STRING.equals(userInfor.getEmail())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_EMAIL));
		} else if (userInfor.getEmail().length() > 255) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER006_EMAIL));
		} else if (!userInfor.getEmail().matches(EMAIL_PATTERN)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER005_EMAIL));
		} else if (tblUserLogic.checkExistedEmail(null, userInfor.getEmail())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER003_EMAIL));
		}

		// check phone (3)
		if (userInfor.getTel() == null || Constant.EMPTY_STRING.equals(userInfor.getTel())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_TEL));
		} else if (userInfor.getTel().length() > Constant.MAX_LENGTH_TEL) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER006_TEL));
		} else if (!userInfor.getTel().matches(TEL_PATTERN)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER005_TEL));
		}

		// check password (3)
		if (userInfor.getPassword() == null || Constant.EMPTY_STRING.equals(userInfor.getPassword())) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_PASSWORD));
		} else {
			if (userInfor.getPassword().length() < Constant.MIN_LENGTH_PASSWORD
					|| userInfor.getPassword().length() > Constant.MAX_LENGTH_PASSWORD) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER007_PASSWORD));
			} else if (!userInfor.getPassword().matches(PASSWORD_PATTERN)) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER008_PASSWORD));
			}

			// check confirm password (1)
			if (userInfor.getConfirmPassword() == null
					|| Constant.EMPTY_STRING.equals(userInfor.getConfirmPassword())) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER017));
			} else if (!userInfor.getConfirmPassword().equals(userInfor.getPassword())) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER017));
			}
		}

		// case chose level japan
		String codeLevel = userInfor.getCodeLevel();
		if (codeLevel != null && !Constant.EMPTY_STRING.equals(codeLevel) && !Constant.ZERO.equals(codeLevel)) {
			int startYear = Common.parseInt(userInfor.getStartYear(), 0);
			int startMonth = Common.parseInt(userInfor.getStartMonth(), 0);
			int startDay = Common.parseInt(userInfor.getStartDay(), 0);
			Date startDate = Common.toDate(startYear, startMonth, startDay);

			// check code level exist (1)
			if (!mstJapanLogic.checkExistJapan(codeLevel)) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER004_CODE_LEVEL));
			}

			// check start date (1)
			if (startDate == null) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER011_START_DATE));
			} else { // in case haven't error then add to startdate
				userInfor.setStartDate(startDate);
			}
			int endYear = Common.parseInt(userInfor.getEndYear(), 0);
			int endMonth = Common.parseInt(userInfor.getEndMonth(), 0);
			int endDay = Common.parseInt(userInfor.getEndDay(), 0);
			Date endDate = Common.toDate(endYear, endMonth, endDay);

			// check end date (2)
			if (endDate == null) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER011_END_DATE));
			} else if (endDate.compareTo(startDate) <= 0) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER012));
			} else { // in case haven't error then add to enddate
				userInfor.setEndDate(endDate);
			}

			// check total (2)
			if (userInfor.getTotal() == null || Constant.EMPTY_STRING.equals(userInfor.getTotal())) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_TOTAL));
			} else if (!userInfor.getTotal().matches(TOTAL_PATTERN)) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER018_TOTAL));
			}
		}

		return listError;
	}
}
