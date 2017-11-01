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
	public ArrayList<MstJapan> getAllMstJapan() throws ClassNotFoundException, SQLException ;
}
