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
		 ArrayList<UserInfor> list =  tblUserDAO.getListUsers(offset, limit, groupId, fullName, sortType, sortByFullName, sortByCodeLevel,
				sortByEndDate);
		 return list;
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
		int sum = tblUserDAO.getTotalUsers(groupId, fullName);
		return sum;
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
		String salt = Common.SHA1(Common.randomString());
		String password = Common.SHA1(userInfor.getPassword(), salt);
		userInfor.setPassword(password);
		TblUser tblUser = getTblUserFromUserInfor(userInfor);
		tblUser.setSalt(salt);
		try {
			baseDAO.dbConnection();// create connection
			baseDAO.setAutoCommit(false);// set auto commit = false
			Integer userId = tblUserDAO.insertUser(tblUser);
			if (userId == null) {// if insert tbl_user not success then return false
				return false;
			}
			// if exist japan
			if (userInfor.getCodeLevel() != null) {
				TblDetailUserJapan tblDetailUserJapan = getTblDetailUserJapanFromUserInfor(userInfor);
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
	 * get tblUser from UserInfor
	 * 
	 * @param userInfor
	 *            is object store data
	 * @return TblUser is object of table tbl_user
	 */
	private TblUser getTblUserFromUserInfor(UserInfor userInfor) {
		TblUser tblUser = new TblUser();
		int groupId = Common.parseInt(userInfor.getGroupId(), 0);
		tblUser.setGroupId(groupId);
		tblUser.setUserId(userInfor.getUserId());
		tblUser.setLoginName(userInfor.getLoginName());
		tblUser.setPassword(userInfor.getPassword());
		tblUser.setFullName(userInfor.getFullName());
		tblUser.setFullNameKana(userInfor.getFullNameKana());
		tblUser.setEmail(userInfor.getEmail());
		tblUser.setTel(userInfor.getTel());
		tblUser.setBirthday(userInfor.getBirthday());
		return tblUser;
	}

	/**
	 * get tblDetailUserJapan from UserInfor
	 * 
	 * @param userInfor
	 *            is object store data
	 * @return TblDetailUserJapan is object of table tbl_detail_user_japan
	 */
	private TblDetailUserJapan getTblDetailUserJapanFromUserInfor(UserInfor userInfor) {
		int total = Common.parseInt(userInfor.getTotal(), 0);
		TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan();
		tblDetailUserJapan.setUserId(userInfor.getUserId());
		tblDetailUserJapan.setCodeLevel(userInfor.getCodeLevel());
		tblDetailUserJapan.setStartDate(userInfor.getStartDate());
		tblDetailUserJapan.setEndDate(userInfor.getEndDate());
		tblDetailUserJapan.setTotal(total);
		return tblDetailUserJapan;
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
		int userId = userInfor.getUserId();
		TblUser tblUser = getTblUserFromUserInfor(userInfor);
		TblDetailUserJapan detailUserJapan = tblDetailUserJapanDAO.gettDetailUserJapanByUserId(userId);
		try {
			boolean check = true;
			baseDAO.dbConnection();// create connection
			baseDAO.setAutoCommit(false);// set auto commit = false
			tblUserDAO.updateUser(tblUser);
			// if exist japan
			if (userInfor.getCodeLevel() != null) {
				TblDetailUserJapan tblDetailUserJapan = getTblDetailUserJapanFromUserInfor(userInfor);
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
		UserInfor userInfor = tblUserDAO.getUserInforById(userId);
		return userInfor;
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
		boolean check = tblUserDAO.updatePasswrord(userId, newPassword);
		return check;
	}
}
