package logic.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.MstJapanDAO;
import dao.impl.MstJapanDAOImpl;
import entity.MstJapan;
import logic.MstJapanLogic;

public class MstJapanLogicImpl implements MstJapanLogic {
	private MstJapanDAO mstJapanDAO;

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
		return mstJapanDAO.getAllMstJapan();
	}

	/**
	 * get mstJapan by code level
	 * 
	 * @param codeLevel
	 *            : code_level of mst japan
	 * @return MstJapan
	 */
	@Override
	public MstJapan getMstJapanByCodeLevel(String codeLevel) throws ClassNotFoundException, SQLException {
		return mstJapanDAO.getMstJapanByCodeLevel(codeLevel);
	}

	/**
	 * check exist japan
	 * 
	 * @param codeLevel
	 *            : code level om table mst_japan
	 * @return boolean : check exits
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
