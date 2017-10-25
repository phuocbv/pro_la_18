/**
 * Copyright(C) 2017  Luvina
 * UserLogicImpl.java, 20/10/2017 phuocbv
 */
package logic.impl;

import java.util.ArrayList;

import dao.TblUserDAO;
import dao.impl.TblUserDAOImpl;
import entity.UserInfor;
import logic.TblUserLogic;

/**
 * class user logic impl
 * 
 * @author LA-AM
 *
 */
public class TblUserLogicImpl implements TblUserLogic {
	private TblUserDAO userDao;

	/**
	 * contructer
	 */
	public TblUserLogicImpl() {
		userDao = new TblUserDAOImpl();
	}

	/**
	 * get list user
	 * 
	 * @return Arraylist<TblUser>
	 */
	public ArrayList<UserInfor> getListUser(int offfset, int limit, String groupId, String fullName) {
		return userDao.getListUsers(offfset, limit, groupId, fullName);
	}

	/**
	 * get total user
	 * 
	 * @param group_id
	 * @param fullName
	 * @return
	 */
	public int getTotalUsers(String groupId, String fullName) {
		return userDao.getTotalUsers(groupId, fullName);
	}
}
