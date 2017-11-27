/**
 * Copyright(C) 2017  Luvina
 * MstGroupLogicImpl.java, 20/10/2017 phuocbv
 */
package logic.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import common.Common;
import dao.MstGroupDAO;
import dao.impl.MstGroupDAOImpl;
import entity.MstGroup;
import logic.MstGroupLogic;

/**
 * class mstgroup logic impl
 * 
 * @author LA-AM
 *
 */
public class MstGroupLogicImpl implements MstGroupLogic {
	private MstGroupDAO mstGroupDAO;

	/**
	 * contructer
	 */
	public MstGroupLogicImpl() {
		mstGroupDAO = new MstGroupDAOImpl();
	}

	/**
	 * get all group
	 * 
	 * @return ArrayList<MstGroup> : store list all group
	 */
	@Override
	public ArrayList<MstGroup> getAllListGroups() throws ClassNotFoundException, SQLException {
		ArrayList<MstGroup>  list = mstGroupDAO.getAllListGroups();
		return list;
	}

	/**
	 * check exist group
	 * 
	 * @param groupId
	 *            : id of table mst_group
	 * @return boolean check exist group
	 */
	@Override
	public boolean checkExistGroup(String groupId) throws ClassNotFoundException, SQLException {
		int id = Common.parseInt(groupId, 0);
		MstGroup mstGroup = mstGroupDAO.getGroupById(id);
		if (mstGroup == null) {
			return false;
		}
		return true;
	}

	/**
	 * get group by id
	 * 
	 * @param groupId
	 *            : group_id in table mst_group
	 * @return MstGroup is object of table tbl_user
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public MstGroup getGroupById(String groupId) throws ClassNotFoundException, SQLException {
		int id = Common.parseInt(groupId, 0);
		MstGroup mstGroup = mstGroupDAO.getGroupById(id);
		return mstGroup;
	}
}
