/**
 * Copyright(C) 2017  Luvina
 * BaseDAO.java, 20/10/2017 phuocbv
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * interface BaseDAO
 * 
 * @author da
 *
 */
public interface BaseDAO {
	/**
	 * close connection
	 * 
	 * @param
	 * @return
	 */
	public void closeConnect();

	/**
	 * function return connection
	 * 
	 * @return Connection : connection to sql
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException;
}
