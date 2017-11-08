/**
 * Copyright(C) 2017  Luvina
 * TblDetailUserJapanDaoImpl.java, 20/10/2017 phuocbv
 */
package dao.impl;

import java.sql.Date;
import java.sql.SQLException;

import dao.TblDetailUserJapanDAO;
import entity.TblDetailUserJapan;

/**
 * class TblDetailUserJapanDaoImpl use table tbl_detail_user_japan
 * 
 * @author da
 *
 */
public class TblDetailUserJapanDAOImpl extends BaseDAOImpl implements TblDetailUserJapanDAO {
	private StringBuffer sqlInsertDetailUserJapan = new StringBuffer()
			.append(" INSERT INTO tbl_detail_user_japan(user_id, code_level, start_date, end_date, total) ")
			.append(" VALUES (?, ?, ?, ?, ?) ");

	/**
	 * insert in detail user japan
	 * 
	 * @param tblDeatilUserJapanDAO
	 * @return boolean test insert success
	 * @throws SQLException
	 */
	@Override
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException {
		try {
			if (connection == null) {
				return false;
			}
			int i = 0;
			pstm = connection.prepareStatement(sqlInsertDetailUserJapan.toString());
			pstm.setInt(++i, tblDetailUserJapan.getUserId());
			pstm.setString(++i, tblDetailUserJapan.getCodeLevel());
			pstm.setDate(++i, new Date(tblDetailUserJapan.getStartDate().getTime()));
			pstm.setDate(++i, new Date(tblDetailUserJapan.getEndDate().getTime()));
			pstm.setInt(++i, tblDetailUserJapan.getTotal());
			pstm.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

}
