/**
 * Copyright(C) 2017  Luvina
 * TblUserDAO.java, 20/10/2017 phuocbv
 */
package dao;

import java.util.ArrayList;

import entity.UserInfor;

/**
 * interface UserDAO
 * 
 * @author da
 *
 */
public interface TblUserDAO {
	/**
	 * 
	 * @param offfset
	 * @param limit
	 * @param groupId
	 * @param fullName
	 * @return
	 */
	public ArrayList<UserInfor> getListUsers(int offset, int limit, String groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate);

	/**
	 * 
	 * @param groupId
	 * @param fullName
	 * @return
	 */
	public int getTotalUsers(String groupId, String fullName);
}
