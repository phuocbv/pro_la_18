/**
 * Copyright(C) 2017  Luvina
 * MstJapanLogic.java, 30/10/2017 phuocbv
 */
package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.MstJapan;

/**
 * process logic of japan
 * 
 * @author da
 *
 */
public interface MstJapanLogic {
	/**
	 * get all japan
	 * 
	 * @return ArrayList<MstJapan> : store list level japan
	 */
	public ArrayList<MstJapan> getAllMstJapan() throws ClassNotFoundException, SQLException;

	/**
	 * get mstJapan by code level
	 * 
	 * @param codeLevel
	 *            : code_level of mst japan
	 * @return MstJapan
	 */
	public MstJapan getMstJapanByCodeLevel(String codeLevel) throws ClassNotFoundException, SQLException;

	/**
	 * check exist japan
	 * 
	 * @param codeLevel
	 *            : code level om table mst_japan
	 * @return boolean : check exits
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean checkExistJapan(String codeLevel) throws ClassNotFoundException, SQLException;
}
