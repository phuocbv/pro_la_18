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

	private StringBuffer sqlUpdateDetailUserJapan = new StringBuffer().append(" UPDATE tbl_detail_user_japan ")
			.append(" SET tbl_detail_user_japan.code_level = ?, tbl_detail_user_japan.start_date = ?, ")
			.append(" tbl_detail_user_japan.end_date = ?, tbl_detail_user_japan.total=? WHERE user_id = ? ");

	private StringBuffer sqlGetDetailUserJapanByUserId = new StringBuffer()
			.append(" SELECT tbl.detail_user_japan_id, tbl.user_id, tbl.code_level, ")
			.append(" tbl.start_date, tbl.end_date, tbl.total FROM tbl_detail_user_japan AS tbl ")
			.append(" WHERE tbl.user_id = ?");

	private String sqlDeleteDetailUserJapan = "DELETE FROM tbl_detail_user_japan WHERE tbl_detail_user_japan.user_id = ?";

	/**
	 * insert in detail user japan
	 * 
	 * @param tblDeatilUserJapanDAO
	 * @return boolean test insert success
	 * @throws SQLException
	 */
	@Override
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException {
		int i = 0;
		pstm = connection.prepareStatement(sqlInsertDetailUserJapan.toString());
		pstm.setInt(++i, tblDetailUserJapan.getUserId());
		pstm.setString(++i, tblDetailUserJapan.getCodeLevel());
		pstm.setDate(++i, new Date(tblDetailUserJapan.getStartDate().getTime()));
		pstm.setDate(++i, new Date(tblDetailUserJapan.getEndDate().getTime()));
		pstm.setInt(++i, tblDetailUserJapan.getTotal());
		//System.out.println(pstm.toString());
		pstm.executeUpdate();
		return true;
	}

	/**
	 * update detail user japan
	 * 
	 * @param tblDetailUserJapan
	 *            is object of table tbl_detail_user_japan
	 * @return boolean check update success
	 * @throws SQLException
	 */
	@Override
	public boolean updateDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException {
		int i = 0;
		pstm = connection.prepareStatement(sqlUpdateDetailUserJapan.toString());// use PrepareStatement
		pstm.setString(++i, tblDetailUserJapan.getCodeLevel());
		pstm.setDate(++i, new Date(tblDetailUserJapan.getStartDate().getTime()));
		pstm.setDate(++i, new Date(tblDetailUserJapan.getEndDate().getTime()));
		pstm.setInt(++i, tblDetailUserJapan.getTotal());
		pstm.setInt(++i, tblDetailUserJapan.getUserId());
		//System.out.println(pstm.toString());
		pstm.executeUpdate();
		return true;
	}

	/**
	 * delete detail user japan
	 * 
	 * @param userId
	 *            is user_id in table tbl_user
	 * @return boolean check delete success
	 * @throws SQLException
	 */
	@Override
	public boolean deleteDetailUserJapan(Integer userId) throws SQLException {
		pstm = connection.prepareStatement(sqlDeleteDetailUserJapan);
		pstm.setInt(1, userId);
		pstm.executeUpdate();
		return true;
	}

	/**
	 * get detail user japan by user id
	 * 
	 * @param userId
	 *            is user_id in table tbl_detail_user_japan_by_user_id
	 * @return TblDetailUserJapan is object of table
	 *         tbl_detail_user_japan_by_user_id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public TblDetailUserJapan gettDetailUserJapanByUserId(int userId) throws SQLException, ClassNotFoundException {
		TblDetailUserJapan tblDetailUserJapan = null;
		try {
			connection = getConnection();// get connection
			pstm = connection.prepareStatement(sqlGetDetailUserJapanByUserId.toString());// use PrepareStatement
			pstm.setInt(1, userId);
			resultSet = pstm.executeQuery();// execute sql
			int i;
			if (resultSet.next()) {
				i = 0;
				tblDetailUserJapan = new TblDetailUserJapan();
				tblDetailUserJapan.setDetailUserJapanId(resultSet.getInt(++i));
				tblDetailUserJapan.setUserId(resultSet.getInt(++i));
				tblDetailUserJapan.setCodeLevel(resultSet.getString(++i));
				tblDetailUserJapan.setStartDate(resultSet.getDate(++i));
				tblDetailUserJapan.setEndDate(resultSet.getDate(++i));
				tblDetailUserJapan.setTotal(resultSet.getInt(++i));
			}
		} finally {
			closeConnect();
		}
		return tblDetailUserJapan;
	}
}
