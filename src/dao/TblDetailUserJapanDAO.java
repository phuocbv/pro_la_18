/**
 * Copyright(C) 2017  Luvina
 * TblDeatilUserJapanDAO.java, 20/10/2017 phuocbv
 */
package dao;

import java.sql.SQLException;

import entity.TblDetailUserJapan;

/**
 * interface tblDetailUserDAO
 * 
 * @author da
 *
 */
public interface TblDetailUserJapanDAO {
	/**
	 * insert in detail user japan
	 * 
	 * @param tblDeatilUserJapanDAO
	 * @return boolean test insert success
	 * @throws SQLException
	 */
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException;

	/**
	 * update detail user japan
	 * 
	 * @param tblDetailUserJapan is object of table tbl_detail_user_japan
	 * @return boolean check update success
	 * @throws SQLException
	 */
	public boolean updateDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException;
}
