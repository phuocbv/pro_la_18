package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.MstJapanDAO;
import entity.MstJapan;

/**
 * 
 * @author da
 *
 */
public class MstJapanDAOImpl extends BaseDAOImpl implements MstJapanDAO {
	private static String SQL_GET_ALL_JAPAN = "SELECT mst_japan.code_level, mst_japan.name_level FROM mst_japan ORDER BY mst_japan.code_level ASC";

	/**
	 * get all japan
	 * 
	 * @return ArrayList<MstJapan> : store list level japan
	 */
	public ArrayList<MstJapan> getAllMstJapan() {
		ArrayList<MstJapan> listJapan = new ArrayList<>();
		try {
			connection = getConnection();// get connection
			// if connect null then return
			if (connection == null) {
				return listJapan;
			}
			pstm = connection.prepareStatement(SQL_GET_ALL_JAPAN);// use PrepareStatement
			resultSet = pstm.executeQuery();// execute sql
			// repeat record get and add to list
			while (resultSet.next()) {
				MstJapan japan = new MstJapan();
				japan.setCodeLevel(resultSet.getString(MstJapan.CODE_LEVEL));
				japan.setNameLevel(resultSet.getString(MstJapan.NAME_LEVEL));
				listJapan.add(japan);
			}
		} catch (ClassNotFoundException e) {
			return listJapan;
		} catch (SQLException e) {
			return listJapan;
		} finally {
			closeConnect();
		}
		return listJapan;
	}
}
