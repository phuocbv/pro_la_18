/**
 * Copyright(C) 2017  Luvina
 * TblUserLogicImpl.java, 20/10/2017 phuocbv
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
		TblUser tblUser = tblUserDAO.getUserByLoginName(userId, loginName);
		if (tblUser == null) {
			return false;
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
		String salt = Common.SHA1(Common.randomString());
		String password = Common.SHA1(userInfor.getPassword(), salt);
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
	 * update tbl_user
	 * 
	 * @param userInfor
	 *            : object contain
	 * @return boolean : check update success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean editUser(UserInfor userInfor) throws ClassNotFoundException, SQLException {
		int groupId = Common.parseInt(userInfor.getGroupId(), 0);
		int userId = userInfor.getUserId();
		TblUser tblUser = new TblUser();
		tblUser.setUserId(userId);
		tblUser.setGroupId(groupId);
		tblUser.setFullName(userInfor.getFullName());
		tblUser.setFullNameKana(userInfor.getFullNameKana());
		tblUser.setEmail(userInfor.getEmail());
		tblUser.setTel(userInfor.getTel());
		tblUser.setBirthday(userInfor.getBirthday());
		TblDetailUserJapan detailUserJapan = tblDetailUserJapanDAO.gettDetailUserJapanByUserId(userId);
		try {
			boolean check = true;
			baseDAO.dbConnection();// create connection
			baseDAO.setAutoCommit(false);// set auto commit = false
			tblUserDAO.updateUser(tblUser);
			if (userInfor.getCodeLevel() != null) {
				int total = Common.parseInt(userInfor.getTotal(), 0);
				TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan();
				tblDetailUserJapan.setUserId(userInfor.getUserId());
				tblDetailUserJapan.setCodeLevel(userInfor.getCodeLevel());
				tblDetailUserJapan.setStartDate(userInfor.getStartDate());
				tblDetailUserJapan.setEndDate(userInfor.getEndDate());
				tblDetailUserJapan.setTotal(total);
				if (detailUserJapan == null) {// if not exist then add to database
					check = tblDetailUserJapanDAO.insertDetailUserJapan(tblDetailUserJapan);
				} else {
					check = tblDetailUserJapanDAO.updateDetailUserJapan(tblDetailUserJapan);
				}
			} else {// if exist detailUserJapan and codeLevel is null then delete detailUserJapan
				if (detailUserJapan != null) {
					check = tblDetailUserJapanDAO.deleteDetailUserJapan(userId);
				}
			}
			if (!check) {
				baseDAO.rollBack();
				return false;
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
	 * remote user
	 * 
	 * @param userId
	 *            is user_id in table tbl_user
	 * @return boolean is check remove success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean removeUser(int userId) throws ClassNotFoundException, SQLException {
		try {
			baseDAO.dbConnection();// create connection
			baseDAO.setAutoCommit(false);// set auto commit = false
			tblDetailUserJapanDAO.deleteDetailUserJapan(userId);
			tblUserDAO.deleteUser(userId);
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

	/**
	 * check exist tbl_user by id
	 * 
	 * @param id
	 *            is user_id in table tbl_user
	 * @return boolean check exist tbl_user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean checkExistTblUserById(int id) throws ClassNotFoundException, SQLException {
		TblUser tblUser = tblUserDAO.getTblUserById(id);
		if (tblUser == null) {
			return false;
		}
		return true;
	}

	/**
	 * change password of user
	 * 
	 * @param userId
	 *            is user_id in table tbl_user
	 * @param newPassword
	 *            is new password
	 * @return boolean check change pass success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean changePasswrordOfUser(Integer userId, String newPassword)
			throws ClassNotFoundException, SQLException {
		TblUser tblUser = tblUserDAO.getTblUserById(userId);
		newPassword = Common.SHA1(newPassword, tblUser.getSalt());
		return tblUserDAO.updatePasswrord(userId, newPassword);
	}
}
