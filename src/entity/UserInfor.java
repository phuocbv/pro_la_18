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
	private int total;

	// field name in ADM002 of database
	public static final String USER_ID = "user_id";
	public static final String FULL_NAME = "full_name";
	public static final String BIRTHDAY = "birthday";
	public static final String EMAIL = "email";
	public static final String TEL = "tel";
	public static final String GROUP_NAME = "group_name";
	public static final String NAME_LEVEL = "name_level";
	public static final String END_DATE = "end_date";
	public static final String TOTAL = "total";

	//param
	public static final String LOGIN_NAME = "login_name";
	public static final String PASSWORD = "password";
	public static final String GROUP_ID = "group_id";
	
	// total user
	public static final String TOTAL_USER = "total_user";

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
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

}
