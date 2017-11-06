/**
 * Copyright(C) 2017  Luvina
 * TblDeatilUserJapanDao.java, 20/10/2017 phuocbv
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
public interface TblDeatilUserJapanDAO {
	/**
	 * insert in detail user japan
	 * 
	 * @param tblDeatilUserJapanDAO
	 * @return boolean test insert success
	 */
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException;
}
