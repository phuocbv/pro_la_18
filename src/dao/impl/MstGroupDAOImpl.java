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
 * class MstGroupDAOImpl
 * manipulation with table mst_group
 * 
 * @author da
 *
 */
public class MstGroupDAOImpl extends BaseDAOImpl implements MstGroupDAO {
	// sql get all group
	private static String SQL_GET_ALL_GROUP = "SELECT mst_group.group_id, mst_group.group_name FROM mst_group ORDER BY mst_group.group_name ASC";

	/**
	 * get all group
	 * 
	 * @return ArrayList<MstGroup> : store list all group
	 */
	@Override
	public ArrayList<MstGroup> getAllListGroups() {
		ArrayList<MstGroup> listGroup = new ArrayList<>();
		try {
			connection = getConnection();// get connection
			// if connect null then return
			if (connection == null) {
				return listGroup;
			}
			pstm = connection.prepareStatement(SQL_GET_ALL_GROUP);// use PrepareStatement
			resultSet = pstm.executeQuery();// execute sql
			// repeat record get and add to list
			while (resultSet.next()) {
				MstGroup group = new MstGroup();
				group.setGroupId(resultSet.getInt(MstGroup.GROUP_ID));
				group.setGroupName(resultSet.getString(MstGroup.GROUP_NAME));
				listGroup.add(group);
			}
		} catch (ClassNotFoundException e) {
			return listGroup;
		} catch (SQLException e) {
			return listGroup;
		} finally {
			closeConnect();
		}
		return listGroup;
	}

}
