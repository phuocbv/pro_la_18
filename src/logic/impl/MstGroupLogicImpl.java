/**
 * Copyright(C) 2017  Luvina
 * MstGroupLogicImpl.java, 20/10/2017 phuocbv
 */
package logic.impl;

import java.util.ArrayList;

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
	public ArrayList<MstGroup> getListGroups() {
		return mstGroupDAO.getListGroups();
	}

}
