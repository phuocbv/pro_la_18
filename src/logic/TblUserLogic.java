/**
 * Copyright(C) 2017  Luvina
 * TblUserLogic.java, 20/10/2017 phuocbv
 */
package logic;

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
			String sortByFullName, String sortByCodeLevel, String sortByEndDate);

	/**
	 * get total user
	 * 
	 * @param group_id
	 * @param fullName
	 * @return int
	 */
	public int getTotalUsers(String groupId, String fullName);
}
