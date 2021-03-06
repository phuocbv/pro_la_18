/**
 * Copyright(C) 2017  Luvina
 * MstGroupDAOImpl.java, 20/10/2017 phuocbv
 */
package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.MstGroupDAO;
import entity.MstGroup;

/**
 * class MstGroupDAOImpl manipulation with table mst_group
 * 
 * @author da
 *
 */
public class MstGroupDAOImpl extends BaseDAOImpl implements MstGroupDAO {
	// sql get all group
	private static String SQL_GET_ALL_GROUP = "SELECT mst_group.group_id, mst_group.group_name FROM mst_group ORDER BY mst_group.group_name ASC";
	private static String SQL_GET_GROUP_BY_GROUP_ID = "SELECT mst_group.group_id, mst_group.group_name FROM mst_group WHERE mst_group.group_id = ?";

	/**
	 * get all group
	 * 
	 * @return ArrayList<MstGroup> : store list all group
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ArrayList<MstGroup> getAllListGroups() throws ClassNotFoundException, SQLException {
		ArrayList<MstGroup> listGroup = new ArrayList<>();
		try {
			connection = getConnection();// get connection
			// if connect != null then run next
			if (connection != null) {
				pstm = connection.prepareStatement(SQL_GET_ALL_GROUP);// use PrepareStatement
				resultSet = pstm.executeQuery();// execute sql
				// repeat record get and add to list
				while (resultSet.next()) {
					MstGroup group = new MstGroup();
					group.setGroupId(resultSet.getInt(MstGroup.GROUP_ID));
					group.setGroupName(resultSet.getString(MstGroup.GROUP_NAME));
					listGroup.add(group);
				}
			}
		} finally {
			closeConnect();
		}
		return listGroup;
	}

	/**
	 * check exist group
	 * 
	 * @param groupId
	 *            : id of table mst_group
	 * @return MstGroup : exist group
	 */
	@Override
	public MstGroup getGroupById(int groupId) throws ClassNotFoundException, SQLException {
		MstGroup mstGroup = null;
		try {
			connection = getConnection();// get connection
			// if connect != null then run next
			if (connection != null) {
				pstm = connection.prepareStatement(SQL_GET_GROUP_BY_GROUP_ID);// use PrepareStatement
				pstm.setInt(1, groupId);
				resultSet = pstm.executeQuery();// execute sql
				// reader record
				if (resultSet.next()) {
					mstGroup = new MstGroup();
					mstGroup.setGroupId(resultSet.getInt(1));
					mstGroup.setGroupName(resultSet.getString(2));
				}
			}
		} finally {
			closeConnect();
		}
		return mstGroup;
	}

}
