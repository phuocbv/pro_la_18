/**
 * Copyright(C) 2017  Luvina
 * MstGroupLogic.java, 20/10/2017 phuocbv
 */
package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.MstGroup;

/**
 * interface MstGroupLogic
 * 
 * @author LA-AM
 *
 */
public interface MstGroupLogic {
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
	public boolean checkExistGroup(String groupId) throws ClassNotFoundException, SQLException;

	/**
	 * get group by id
	 * 
	 * @param groupId
	 *            : group_id in table mst_group
	 * @return MstGroup
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public MstGroup getGroupById(String groupId) throws ClassNotFoundException, SQLException;
}
