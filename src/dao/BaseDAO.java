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
	 */
	public void closeConnect() throws SQLException;

	/**
	 * function return connection
	 * 
	 * @return Connection : connection to sql
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException;

	/**
	 * fuction set auto commit
	 * 
	 * @param value
	 *            : set auto commit
	 */
	public void setAutoCommit(boolean value) throws SQLException;

	// public void executeUpdate() throws SQLException;

	/**
	 * commit database
	 * 
	 * @throws SQLException
	 */
	public void commit() throws SQLException;

	/**
	 * function call connection
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */
	public void transactionConnection() throws SQLException, ClassNotFoundException;

	/**
	 * function rollBack
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void rollBack() throws SQLException, ClassNotFoundException;
}
