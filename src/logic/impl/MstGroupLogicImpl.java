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
		return mstGroupDAO.getAllListGroups();
	}

	/**
	 * check exist group
	 * 
	 * @param groupId
	 *            : id of table mst_group
	 * @return MstGroup : exist group
	 */
	public boolean checkExistGroup(String groupId) throws ClassNotFoundException, SQLException {
		int id = Common.parseInt(groupId, 0);
		MstGroup mstGroup = mstGroupDAO.checkExistGroup(id);
		if (mstGroup == null) {
			return false;
		}
		return true;
	}
}
