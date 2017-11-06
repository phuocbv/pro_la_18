/**
 * Copyright(C) 2017  Luvina
 * TblUserDAO.java, 20/10/2017 phuocbv
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.TblUser;
import entity.UserInfor;

/**
 * interface UserDAO
 * 
 * @author da
 *
 */
public interface TblUserDAO{
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
	 * get user by login name
	 * 
	 * @param userId
	 *            : user_id of table mst_user
	 * @param loginName
	 *            : login_name of table
	 * @return TblUser : object TblUser
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public TblUser getUserByLoginName(Integer userId, String loginName) throws ClassNotFoundException, SQLException;

	/**
	 * get TblUser by email
	 * 
	 * @param userId
	 *            : field user_id of table mst_user
	 * @param email
	 *            : field email of table mst_user
	 * @return TblUser : object TblUser
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public TblUser getUserByEmail(Integer userId, String email) throws ClassNotFoundException, SQLException;

	/**
	 * insert tblUser in database
	 * 
	 * @param tblUser 
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int insertUser(TblUser tblUser) throws ClassNotFoundException, SQLException;

}
