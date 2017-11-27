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
	private static String SQL_GET_MST_JAPAN_BY_CODE_LEVEL = "SELECT mst_japan.code_level, mst_japan.name_level FROM mst_japan WHERE mst_japan.code_level = ?";

	/**
	 * get all japan
	 * 
	 * @return ArrayList<MstJapan> : store list level japan
	 */
	@Override
	public ArrayList<MstJapan> getAllMstJapan() throws ClassNotFoundException, SQLException {
		ArrayList<MstJapan> listJapan = new ArrayList<>();
		try {
			connection = getConnection();// get connection
			// check connection
			if (connection != null) {
				pstm = connection.prepareStatement(SQL_GET_ALL_JAPAN);// use PrepareStatement
				resultSet = pstm.executeQuery();// execute sql
				int i;
				while (resultSet.next()) {
					i = 0;
					MstJapan japan = new MstJapan();
					japan.setCodeLevel(resultSet.getString(++i));
					japan.setNameLevel(resultSet.getString(++i));
					listJapan.add(japan);
				}
			}
		} finally {
			closeConnect();
		}
		return listJapan;
	}

	/**
	 * get mstJapan by code level
	 * 
	 * @param codeLevel
	 *            : code_level of mst japan
	 * @return MstJapan : object of table mst_japan
	 */
	@Override
	public MstJapan getMstJapanByCodeLevel(String codeLevel) throws ClassNotFoundException, SQLException {
		MstJapan mstJapan = null;
		try {
			connection = getConnection();// get connection
			// check connection
			if (connection != null) {
				pstm = connection.prepareStatement(SQL_GET_MST_JAPAN_BY_CODE_LEVEL);// use PrepareStatement
				pstm.setString(1, codeLevel);
				resultSet = pstm.executeQuery();// execute sql
				int i;
				if (resultSet.next()) {
					i = 0;
					mstJapan = new MstJapan();
					mstJapan.setCodeLevel(resultSet.getString(++i));
					mstJapan.setNameLevel(resultSet.getString(++i));
				}
			}
		} finally {
			closeConnect();
		}
		return mstJapan;
	}
}
