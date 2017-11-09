/**
 * Copyright(C) 2017  Luvina
 * ValidateUser.java, 02/11/2017 phuocbv
 */
package validate;

import java.sql.SQLException;
import java.time.LocalDate;
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
		Integer userId = userInfor.getUserId() > 0 ? userInfor.getUserId() : null;
		System.out.println(userId);
		// check login name (4)
		String loginName = userInfor.getLoginName();
		if (loginName == null || Constant.EMPTY_STRING.equals(loginName)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_LOGIN_NAME));
		} else if (!loginName.matches(Constant.LOGIN_NAME_PATTERN)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER019));
		} else if (loginName.length() < Constant.MIN_LENGTH_LOGIN_NAME
				|| loginName.length() > Constant.MAX_LENGTH_LOGIN_NAME) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER007_LOGIN_NAME));
		} else if (tblUserLogic.checkExistedLoginName(userId, loginName)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER003_LOGIN_NAME));
		}

		// check group id (2)
		String groupId = userInfor.getGroupId();
		if (groupId == null || Constant.ZERO.equals(groupId) || Constant.EMPTY_STRING.equals(groupId)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER002_GROUP));
		} else if (!mstGroupLogic.checkExistGroup(groupId)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER004_GROUP));
		}

		// check full name (2)
		String fullName = userInfor.getFullName();
		if (fullName == null || Constant.EMPTY_STRING.equals(fullName)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_FULL_NAME));
		} else if (fullName.length() > Constant.MAX_LENGTH_FULL_NAME) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER006_FULL_NAME));
		}

		// check full name kana (2)
		String fullNameKana = userInfor.getFullNameKana();
		if (fullNameKana != null && fullNameKana.length() > Constant.MAX_LENGTH_FULL_NAME_KANA) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER006_FULL_NAME_KANA));
		} else if (!fullNameKana.matches(Constant.FULL_NAME_KATA_PATTERN)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER009_FULL_NAME_KANA));
		}

		// check birthday (1)
		String year = userInfor.getBirthdayYear();
		String month = userInfor.getBirthdayMonth();
		String day = userInfor.getBirthdayDay();
		if (!Common.checkBirthday(year, month, day)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER011_BIRTHDAY));
		} else { // in case haven't error then add to birthday
			int birthdayYear = Common.parseInt(year, 0);
			int birthdayMonth = Common.parseInt(month, 0);
			int birthdayDay = Common.parseInt(day, 0);
			Date birthday = Common.toDate(birthdayYear, birthdayMonth, birthdayDay);
			userInfor.setBirthday(birthday);
		}

		// check email (4)
		String email = userInfor.getEmail();
		if (email == null || Constant.EMPTY_STRING.equals(email)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_EMAIL));
		} else if (email.length() > Constant.MAX_LENGTH_EMAIL) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER006_EMAIL));
		} else if (!email.matches(Constant.EMAIL_PATTERN)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER005_EMAIL));
		} else if (tblUserLogic.checkExistedEmail(userId, email)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER003_EMAIL));
		}

		// check phone (3)
		String tel = userInfor.getTel();
		if (tel == null || Constant.EMPTY_STRING.equals(tel)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_TEL));
		} else if (!tel.matches(Constant.TEL_PATTERN)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER005_TEL));
		} else if (tel.length() > Constant.MAX_LENGTH_TEL) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER006_TEL));
		}
		// check password (3)
		String password = userInfor.getPassword();
		if (password == null || Constant.EMPTY_STRING.equals(password)) {
			listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_PASSWORD));
		} else {
			if (password.length() < Constant.MIN_LENGTH_PASSWORD || password.length() > Constant.MAX_LENGTH_PASSWORD) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER007_PASSWORD));
			} else if (!password.matches(Constant.PASSWORD_PATTERN)) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER008_PASSWORD));
			}

			// check confirm password (1)
			String comfirmPassword = userInfor.getConfirmPassword();
			if (comfirmPassword == null || Constant.EMPTY_STRING.equals(comfirmPassword)) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER017));
			} else if (!comfirmPassword.equals(password)) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER017));
			}
		}

		// case chose level japan
		String codeLevel = userInfor.getCodeLevel();
		if (codeLevel != null && !Constant.ZERO.equals(codeLevel)) {
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
			} else if (LocalDate.of(endYear, endMonth, endDay)
					.compareTo(LocalDate.of(startYear, startMonth, startDay)) <= 0) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER012));
			} else { // in case haven't error then add to enddate
				userInfor.setEndDate(endDate);
			}

			// check total (2)
			String total = userInfor.getTotal();
			if (total == null || Constant.EMPTY_STRING.equals(total)) {
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER001_TOTAL));
			} else if (!total.matches(Constant.TOTAL_PATTERN)) {//check format total
				listError.add(MessageErrorProperties.getValue(ConstantProperties.ER018_TOTAL));
			}
		}

		return listError;
	}
}
