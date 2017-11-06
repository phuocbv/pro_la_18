package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.MstJapan;

/**
 * interface MstJapanDAO
 * 
 * @author da
 *
 */
public interface MstJapanDAO {
	/**
	 * get all japan
	 * 
	 * @return ArrayList<MstJapan> : store list level japan
	 */
	public ArrayList<MstJapan> getAllMstJapan() throws ClassNotFoundException, SQLException;

	/**
	 * get mstJapan by code level
	 * 
	 * @param codeLevel : code_level of mst japan
	 * @return MstJapan : object of table mst_japan
	 */
	public MstJapan getMstJapanByCodeLevel(String codeLevel) throws ClassNotFoundException, SQLException;
}
