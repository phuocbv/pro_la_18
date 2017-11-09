/**
 * Copyright(C) 2017  Luvina
 * UserLogicImpl.java, 20/10/2017 phuocbv
 */
package logic.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import common.Common;
import dao.*;
import dao.impl.*;
import entity.TblDetailUserJapan;
import entity.TblUser;
import entity.UserInfor;
import logic.TblUserLogic;

/**
 * class user logic impl
 * 
 * @author LA-AM
 *
 */
public class TblUserLogicImpl implements TblUserLogic {
	private TblUserDAO tblUserDAO;
	private TblDetailUserJapanDAO tblDetailUserJapanDAO;
	private BaseDAO baseDAO;

	/**
	 * contructer
	 */
	public TblUserLogicImpl() {
		baseDAO = new BaseDAOImpl();
		tblUserDAO = new TblUserDAOImpl();
		tblDetailUserJapanDAO = new TblDetailUserJapanDAOImpl();
	}

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
	@Override
	public ArrayList<UserInfor> getListUsers(int offset, int limit, String groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException {
		// fullName = Common.filterString(fullName);
		return tblUserDAO.getListUsers(offset, limit, groupId, fullName, sortType, sortByFullName, sortByCodeLevel,
				sortByEndDate);
	}

	/**
	 * get total user
	 * 
	 * @param groupId
	 *            : field group_id in table tbl_user
	 * @param fullName
	 *            : field full_name in table tbl_user
	 * @return int : total user with condition input
	 */
	@Override
	public int getTotalUsers(String groupId, String fullName) throws ClassNotFoundException, SQLException {
		// fullName = Common.filterString(fullName);
		return tblUserDAO.getTotalUsers(groupId, fullName);
	}

	/**
	 * function check loginName exist
	 * 
	 * @param loginName
	 *            : field login_name in table tbl_user
	 * @return boolean : check exist login name
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean checkExistedLoginName(Integer userId, String loginName) throws ClassNotFoundException, SQLException {
		TblUser tblUser = tblUserDAO.getUserByLoginName(null, loginName);
		
		if (tblUser == null) {
			
		}
		
		
		// in case add
		if (userId == null) {
			// case not exist login name
			if (tblUser == null) {
				return false;
			}
			return true;
		} else { // case update
			if (tblUserDAO.getUserByLoginName(userId, loginName) != null) {
				
			}
			if (tblUser != null) {
				return true;
			}
			//tblUser = 
		}
		return true;
	}

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
	@Override
	public boolean checkExistedEmail(Integer userId, String email) throws ClassNotFoundException, SQLException {
		TblUser tblUser = tblUserDAO.getUserByEmail(userId, email);
		if (tblUser == null) {
			return false;
		} else if (userId != null) {
			return false;
		}
		return true;
	}

	/**
	 * function check loginName exist
	 * 
	 * @param loginName
	 *            : field login_name in table tbl_user
	 * @return boolean : check exist login name
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean createUser(UserInfor userInfor) throws ClassNotFoundException, SQLException {
		int groupId = Common.parseInt(userInfor.getGroupId(), 0);
		String salt = Common.MD5(Common.randomString());
		String password = Common.MD5(userInfor.getPassword(), salt);
		TblUser tblUser = new TblUser();
		tblUser.setGroupId(groupId);
		tblUser.setLoginName(userInfor.getLoginName());
		tblUser.setPassword(password);
		tblUser.setFullName(userInfor.getFullName());
		tblUser.setFullNameKana(userInfor.getFullNameKana());
		tblUser.setEmail(userInfor.getEmail());
		tblUser.setTel(userInfor.getTel());
		tblUser.setBirthday(userInfor.getBirthday());
		tblUser.setSalt(salt);
		try {
			baseDAO.dbConnection();// create connection
			baseDAO.setAutoCommit(false);// set auto commit = false
			Integer userId = tblUserDAO.insertUser(tblUser);
			if (userId == null) {// if insert tbl_user not success then return false
				return false;
			}
			if (userInfor.getCodeLevel() != null) {
				int total = Common.parseInt(userInfor.getTotal(), 0);
				TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan();
				tblDetailUserJapan.setUserId(userId);
				tblDetailUserJapan.setCodeLevel(userInfor.getCodeLevel());
				tblDetailUserJapan.setStartDate(userInfor.getStartDate());
				tblDetailUserJapan.setEndDate(userInfor.getEndDate());
				tblDetailUserJapan.setTotal(total);
				tblDetailUserJapanDAO.insertDetailUserJapan(tblDetailUserJapan);
			}
			baseDAO.commit();
		} catch (SQLException e) {
			baseDAO.rollBack();
			return false;
		} finally {
			baseDAO.closeConnect();
		}
		return true;
	}

	/**
	 * get user by id
	 * 
	 * @param id
	 *            is user_id in table tbl_user
	 * @return UserInfor is object of table tbl_user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public UserInfor getUserInforById(String id) throws ClassNotFoundException, SQLException {
		int userId = Common.parseInt(id, 0);
		TblUser tblUser = tblUserDAO.getTblUserById(userId);
		// check userId exist
		if (tblUser == null) {
			return null;
		}
		return tblUserDAO.getUserInforById(userId);
	}

	@Override
	public boolean updateUser(UserInfor userInfor) throws ClassNotFoundException, SQLException {
//		int userId = 
//		TblUser tblUser = tblUserDAO.getTblUserById()
		int groupId = Common.parseInt(userInfor.getGroupId(), 0);
		String salt = Common.MD5(Common.randomString());
		String password = Common.MD5(userInfor.getPassword(), salt);
		TblUser tblUser = new TblUser();
		tblUser.setGroupId(groupId);
		tblUser.setLoginName(userInfor.getLoginName());
		tblUser.setPassword(password);
		tblUser.setFullName(userInfor.getFullName());
		tblUser.setFullNameKana(userInfor.getFullNameKana());
		tblUser.setEmail(userInfor.getEmail());
		tblUser.setTel(userInfor.getTel());
		tblUser.setBirthday(userInfor.getBirthday());
		tblUser.setSalt(salt);
		try {
			baseDAO.dbConnection();// create connection
			baseDAO.setAutoCommit(false);// set auto commit = false
			Integer userId = tblUserDAO.insertUser(tblUser);
			if (userId == null) {// if insert tbl_user not success then return false
				return false;
			}
			if (userInfor.getCodeLevel() != null) {
				int total = Common.parseInt(userInfor.getTotal(), 0);
				TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan();
				tblDetailUserJapan.setUserId(userId);
				tblDetailUserJapan.setCodeLevel(userInfor.getCodeLevel());
				tblDetailUserJapan.setStartDate(userInfor.getStartDate());
				tblDetailUserJapan.setEndDate(userInfor.getEndDate());
				tblDetailUserJapan.setTotal(total);
				tblDetailUserJapanDAO.insertDetailUserJapan(tblDetailUserJapan);
			}
			baseDAO.commit();
		} catch (SQLException e) {
			baseDAO.rollBack();
			return false;
		} finally {
			baseDAO.closeConnect();
		}
		return true;
	}
}
