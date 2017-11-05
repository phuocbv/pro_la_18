/**
 * Copyright(C) 2017  Luvina
 * UserInfor.java, 20/10/2017 phuocbv
 */
package entity;

import java.util.Date;

/**
 * class entity user infor
 * 
 * @author da
 *
 */
public class UserInfor {
	private int userId;
	private String fullName;
	private Date birthday;
	private String groupName;
	private String email;
	private String tel;
	private String nameLevel;
	private Date endDate;
	private String total;

	private String loginName;
	private String groupId;
	private String fullNameKana;
	private String password;
	private String confirmPassword;
	private String codeLevel;
	private Date startDate;

	private String birthdayYear;
	private String birthdayMonth;
	private String birthdayDay;

	private String startYear;
	private String startMonth;
	private String startDay;

	private String endYear;
	private String endMonth;
	private String endDay;

	// field name in ADM002 of database
	// public static final String USER_ID = "user_id";
	public static final String FULL_NAME = "full_name";
	// public static final String BIRTHDAY = "birthday";
	public static final String EMAIL = "email";
	public static final String TEL = "tel";
	// public static final String GROUP_NAME = "group_name";
	// public static final String NAME_LEVEL = "name_level";
	// public static final String END_DATE = "end_date";
	// public static final String TOTAL = "total";
	public static final String FULL_NAME_KANA = "full_name_kana";

	// japan
	public static final String CODE_LEVEL = "code_level";

	// param
	public static final String LOGIN_NAME = "login_name";
	public static final String PASSWORD = "password";
	public static final String CONFIRM_PASSWORD = "confirm_password";
	public static final String GROUP_ID = "group_id";
	public static final String START_DATE = "start_date";
	public static final String END_DATE = "end_date";
	public static final String TOTAL = "total";

	// birthday
	public static final String BIRTHDAY_YEAR = "birthday_year";
	public static final String BIRTHDAY_MONTH = "birthday_month";
	public static final String BIRTHDAY_DAY = "birthday_day";

	// start date
	public static final String START_YEAR = "start_year";
	public static final String START_MONTH = "start_month";
	public static final String START_DAY = "start_day";

	// end date
	public static final String END_YEAR = "end_year";
	public static final String END_MONTH = "end_month";
	public static final String END_DAY = "end_day";

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}

	/**
	 * @param nameLevel
	 *            the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the fullNameKana
	 */
	public String getFullNameKana() {
		return fullNameKana;
	}

	/**
	 * @param fullNameKana
	 *            the fullNameKana to set
	 */
	public void setFullNameKana(String fullNameKana) {
		this.fullNameKana = fullNameKana;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the codeLevel
	 */
	public String getCodeLevel() {
		return codeLevel;
	}

	/**
	 * @param codeLevel
	 *            the codeLevel to set
	 */
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword
	 *            the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the birthdayYear
	 */
	public String getBirthdayYear() {
		return birthdayYear;
	}

	/**
	 * @param birthdayYear
	 *            the birthdayYear to set
	 */
	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	/**
	 * @return the birthdayMonth
	 */
	public String getBirthdayMonth() {
		return birthdayMonth;
	}

	/**
	 * @param birthdayMonth
	 *            the birthdayMonth to set
	 */
	public void setBirthdayMonth(String birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}

	/**
	 * @return the birthdayDay
	 */
	public String getBirthdayDay() {
		return birthdayDay;
	}

	/**
	 * @param birthdayDay
	 *            the birthdayDay to set
	 */
	public void setBirthdayDay(String birthdayDay) {
		this.birthdayDay = birthdayDay;
	}

	/**
	 * @return the startYear
	 */
	public String getStartYear() {
		return startYear;
	}

	/**
	 * @param startYear
	 *            the startYear to set
	 */
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	/**
	 * @return the startMonth
	 */
	public String getStartMonth() {
		return startMonth;
	}

	/**
	 * @param startMonth
	 *            the startMonth to set
	 */
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	/**
	 * @return the startDay
	 */
	public String getStartDay() {
		return startDay;
	}

	/**
	 * @param startDay
	 *            the startDay to set
	 */
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	/**
	 * @return the endYear
	 */
	public String getEndYear() {
		return endYear;
	}

	/**
	 * @param endYear
	 *            the endYear to set
	 */
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	/**
	 * @return the endMonth
	 */
	public String getEndMonth() {
		return endMonth;
	}

	/**
	 * @param endMonth
	 *            the endMonth to set
	 */
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	/**
	 * @return the endDay
	 */
	public String getEndDay() {
		return endDay;
	}

	/**
	 * @param endDay
	 *            the endDay to set
	 */
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

}
