/**
 * Copyright(C) 2017  Luvina
 * MstJapanLogicImpl.java, 20/10/2017 phuocbv
 */
package logic.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.MstJapanDAO;
import dao.impl.MstJapanDAOImpl;
import entity.MstJapan;
import logic.MstJapanLogic;

/**
 * class japan logic impl
 * 
 * @author LA-AM
 *
 */
public class MstJapanLogicImpl implements MstJapanLogic {
	private MstJapanDAO mstJapanDAO;

	/**
	 * contructer
	 */
	public MstJapanLogicImpl() {
		mstJapanDAO = new MstJapanDAOImpl();
	}

	/**
	 * get all japan
	 * 
	 * @return ArrayList<MstJapan> : store list level japan
	 */
	@Override
	public ArrayList<MstJapan> getAllMstJapan() throws ClassNotFoundException, SQLException {
		ArrayList<MstJapan> list = mstJapanDAO.getAllMstJapan();
		return list;
	}

	/**
	 * get mstJapan by code level
	 * 
	 * @param codeLevel
	 *            : code_level of mst japan
	 * @return MstJapan is object of table tbl_user
	 */
	@Override
	public MstJapan getMstJapanByCodeLevel(String codeLevel) throws ClassNotFoundException, SQLException {
		MstJapan mstJapan = mstJapanDAO.getMstJapanByCodeLevel(codeLevel);
		return mstJapan;
	}

	/**
	 * check exist japan
	 * 
	 * @param codeLevel
	 *            : code level om table mst_japan
	 * @return boolean : check exits detail japan
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean checkExistJapan(String codeLevel) throws ClassNotFoundException, SQLException {
		MstJapan mstJapan = mstJapanDAO.getMstJapanByCodeLevel(codeLevel);
		if (mstJapan == null) {
			return false;
		}
		return true;
	}

}
