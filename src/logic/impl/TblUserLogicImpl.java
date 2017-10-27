/**
 * Copyright(C) 2017  Luvina
 * UserLogicImpl.java, 20/10/2017 phuocbv
 */
package logic.impl;

import java.util.ArrayList;

import common.Common;
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
	 * @param offset
	 * @param limit
	 * @param groupId
	 * @param fullName
	 * @param sortType
	 * @param sortByFullName
	 * @param sortByCodeLevel
	 * @param sortByEndDate
	 * @return ArrayList<UserInfor>
	 */
	public ArrayList<UserInfor> getListUsers(int offset, int limit, String groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) {
		fullName = Common.filterString(fullName);
		return userDao.getListUsers(offset, limit, groupId, fullName, sortType, sortByFullName, sortByCodeLevel,
				sortByEndDate);
	}

	/**
	 * get total user
	 * 
	 * @param group_id
	 * @param fullName
	 * @return int 
	 */
	public int getTotalUsers(String groupId, String fullName) {
		return userDao.getTotalUsers(groupId, fullName);
	}
}
