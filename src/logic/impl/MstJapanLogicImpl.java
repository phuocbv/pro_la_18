package logic.impl;

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
	public ArrayList<MstJapan> getAllMstJapan() {
		return mstJapanDAO.getAllMstJapan();
	}

}
