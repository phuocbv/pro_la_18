/**
 * Copyright(C) 2017  Luvina
 * BaseDAOImpl.java, 20/10/2017 phuocbv
 */
package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.ConstantProperties;
import dao.BaseDAO;
import properties.DatabaseProperties;

/**
 * class base DAO impl
 * 
 * @author da
 *
 */
public class BaseDAOImpl implements BaseDAO {
	protected static Connection connection = null;// connection to database
	protected static PreparedStatement pstm = null;// prepared statement
	protected static ResultSet resultSet = null;

	/**
	 * function return connection
	 * 
	 * @return Connection : connection to sql
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		return getMySQLConnection();
	}

	/**
	 * get mysql connection
	 * 
	 * @return Connection : connect to mysql
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
		String connectionURL = DatabaseProperties.getValue(ConstantProperties.URL);
		String userName = DatabaseProperties.getValue(ConstantProperties.USERNAME);
		String password = DatabaseProperties.getValue(ConstantProperties.PASSWORD);
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}

	/**
	 * close connection
	 * 
	 * @param
	 * @return
	 * @throws SQLException
	 */
	@Override
	public void closeConnect() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (pstm != null) {
			pstm.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	/**
	 * function call connection
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */
	@Override
	public void dbConnection() throws SQLException, ClassNotFoundException {
		connection = getConnection();
	}

	/**
	 * fuction set auto commit
	 * 
	 * @param value
	 *            : set auto commit
	 * @throws SQLException
	 */
	@Override
	public void setAutoCommit(boolean value) throws SQLException {
		if (connection != null) {
			connection.setAutoCommit(value);
		}
	}

	/**
	 * commit database
	 * 
	 * @throws SQLException
	 */
	@Override
	public void commit() throws SQLException {
		// pstm.executeUpdate();
		if (connection != null) {
			connection.commit();
		}
	}

	/**
	 * function rollBack
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public void rollBack() throws SQLException, ClassNotFoundException {
		if (connection != null) {
			connection.rollback();
		}
	}
}
