/**
 * Copyright(C) 2017  Luvina
 * MstGroupDAO.java, 20/10/2017 phuocbv
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.MstGroup;

/**
 * interface MstGroupDAO
 * 
 * @author da
 *
 */
public interface MstGroupDAO {
	/**
	 * get all group
	 * 
	 * @return ArrayList<MstGroup> : store list all group
	 */
	public ArrayList<MstGroup> getAllListGroups() throws ClassNotFoundException, SQLException;

	/**
	 * check exist group
	 * 
	 * @param groupId
	 *            : id of table mst_group
	 * @return MstGroup : exist group
	 */
	public MstGroup checkExistGroup(int groupId) throws ClassNotFoundException, SQLException;
}
