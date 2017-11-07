/**
 * Copyright(C) 2017  Luvina
 * TblUserLogic.java, 20/10/2017 phuocbv
 */
package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.UserInfor;

/**
 * interface user login
 * 
 * @author LA-AM
 *
 */
public interface TblUserLogic {
	/**
	 * get list user
	 * 
	 * @param offset
	 *            : position get data
	 * @param limit
	 *            : limit records view on screen
	 * @param groupId
	 *            : value in field group_id of table tbl_user
	 * @param fullName
	 *            : value in field group_id of table tbl_user
	 * @param sortType
	 *            : sort type
	 * @param sortByFullName
	 *            : sort follow field full_name in tbl_user
	 * @param sortByCodeLevel
	 *            : sort follow field code_level in mst_japan
	 * @param sortByEndDate
	 *            : sort follow field end_date
	 * @return ArrayList<UserInfor> : store list object UserInfor for view on screen
	 */
	public ArrayList<UserInfor> getListUsers(int offset, int limit, String groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException;

	/**
	 * get total user
	 * 
	 * @param groupId
	 *            : field group_id in table tbl_user
	 * @param fullName
	 *            : field full_name in table tbl_user
	 * @return int : total user with condition input
	 */
	public int getTotalUsers(String groupId, String fullName) throws ClassNotFoundException, SQLException;

	/**
	 * function check loginName exist
	 * 
	 * @param loginName
	 *            : field login_name in table tbl_user
	 * @return boolean : check exist login name
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean checkExistedLoginName(Integer userId, String loginName) throws ClassNotFoundException, SQLException;

	/**
	 * check Existed email
	 * 
	 * @param userId
	 *            : field user_id in table tbl_user
	 * @param email
	 *            : field email in table tbl_user
	 * @return boolean : check exist email
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean checkExistedEmail(Integer userId, String email) throws ClassNotFoundException, SQLException;

	/**
	 * create tbl_user
	 * 
	 * @param userInfor
	 *            : object userInfor
	 * @return boolean : check create user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean createUser(UserInfor userInfor) throws ClassNotFoundException, SQLException;
	
	public UserInfor getUserById(String id) throws ClassNotFoundException, SQLException;
}
