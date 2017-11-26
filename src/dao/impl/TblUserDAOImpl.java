/**
 * Copyright(C) 2017  Luvina
 * TblUserDAOImpl.java, 20/10/2017 phuocbv
 */
package dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.Constant;
import dao.TblUserDAO;
import entity.TblUser;
import entity.UserInfor;

/**
 * class user DAO impl : manipulation with table tbl_user
 * 
 * @author da
 *
 */
public class TblUserDAOImpl extends BaseDAOImpl implements TblUserDAO {
	// sql join table
	private StringBuffer sqlJoin = new StringBuffer()
			.append(" FROM tbl_user INNER JOIN mst_group ON tbl_user.group_id = mst_group.group_id ")
			.append(" LEFT JOIN (tbl_detail_user_japan INNER JOIN mst_japan ON tbl_detail_user_japan.code_level = mst_japan.code_level) ")
			.append(" ON tbl_user.user_id = tbl_detail_user_japan.user_id ").append(" WHERE 1 = 1 ");

	// sql get all user
	private StringBuffer sqlGetListUser = new StringBuffer()
			.append(" SELECT tbl_user.user_id, tbl_user.full_name, tbl_user.birthday, mst_group.group_name, ")
			.append(" tbl_user.email, tbl_user.tel, mst_japan.name_level, ")
			.append(" tbl_detail_user_japan.end_date, tbl_detail_user_japan.total ").append(sqlJoin.toString());

	// sql get total user
	private StringBuffer sqlGetTotaluser = new StringBuffer().append(" SELECT count(*) as total_user ")
			.append(sqlJoin.toString());

	private StringBuffer sqlGetTblUser = new StringBuffer()
			.append(" SELECT tbl_user.user_id, tbl_user.group_id, tbl_user.login_name, tbl_user.password, ")
			.append(" tbl_user.full_name, tbl_user.full_name_kana, tbl_user.email, tbl_user.tel, tbl_user.birthday, tbl_user.salt ")
			.append(" FROM tbl_user ");

	private StringBuffer sqlInsertUser = new StringBuffer()
			.append(" INSERT INTO tbl_user (group_id, login_name, password, ")
			.append(" full_name, full_name_kana, email, tel, birthday, salt) ")
			.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ");

	private StringBuffer sqlGetUserInforById = new StringBuffer()
			.append(" SELECT tbl_user.user_id, tbl_user.group_id, mst_group.group_name, tbl_user.login_name, ")
			.append(" tbl_user.full_name, tbl_user.full_name_kana, tbl_user.birthday, ")
			.append(" tbl_user.email, tbl_user.tel, mst_japan.code_level, mst_japan.name_level, tbl_detail_user_japan.start_date, ")
			.append(" tbl_detail_user_japan.end_date, tbl_detail_user_japan.total ").append(sqlJoin.toString())
			.append(" AND tbl_user.user_id = ? ");

	private StringBuffer sqlUpdateUser = new StringBuffer().append(" UPDATE tbl_user ")
			.append(" SET tbl_user.group_id = ?, tbl_user.full_name = ?, tbl_user.full_name_kana = ?, ")
			.append(" tbl_user.email = ?, tbl_user.tel = ?, tbl_user.birthday = ? WHERE tbl_user.user_id = ?");

	private String sqlUpdatePassword = "UPDATE tbl_user SET tbl_user.password = ? WHERE tbl_user.user_id = ?";

	private String sqlDeleteTblUser = "DELETE FROM tbl_user WHERE tbl_user.user_id = ?";

	/**
	 * get list user
	 * 
	 * @param offset
	 *            : position get data
	 * @param limit
	 *            : limit records view on screen
	 * @param groupId
	 *            : value in field group_id of table tbl_user
	 * @param fullName
	 *            : value in field group_id of table tbl_user
	 * @param sortType
	 *            : sort type
	 * @param sortByFullName
	 *            : sort follow field full_name in tbl_user
	 * @param sortByCodeLevel
	 *            : sort follow field code_level in mst_japan
	 * @param sortByEndDate
	 *            : sort follow field end_date
	 * @return ArrayList<UserInfor> : store list object UserInfor for view on screen
	 */
	@Override
	public ArrayList<UserInfor> getListUsers(int offset, int limit, String groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException {
		ArrayList<UserInfor> listUser = new ArrayList<>();
		try {
			connection = getConnection();// get connection
			if (connection == null) {// if connect null then return
				return listUser;
			}
			String sql = getSQLSearch(sqlGetListUser.toString(), groupId, fullName);// get SQL search
			sql = getSQLSort(sql, sortType, sortByFullName, sortByCodeLevel, sortByEndDate);// get SQL sort
			sql = getSQLPaging(sql, offset, limit);// add paging
			pstm = connection.prepareStatement(sql);// use PrepareStatement
			setParam(sql, groupId, fullName);
			System.out.println(pstm.toString());
			resultSet = pstm.executeQuery();// execute sql
			int i;
			while (resultSet.next()) {
				i = 0;
				UserInfor user = new UserInfor();
				user.setUserId(resultSet.getInt(++i));
				user.setFullName(resultSet.getString(++i));
				user.setBirthday(resultSet.getDate(++i));
				user.setGroupName(resultSet.getString(++i));
				user.setEmail(resultSet.getString(++i));
				user.setTel(resultSet.getString(++i));
				user.setNameLevel(resultSet.getString(++i));
				user.setEndDate(resultSet.getDate(++i));
				user.setTotal(resultSet.getString(++i));
				listUser.add(user);
			}
		} finally {
			closeConnect();
		}
		return listUser;
	}

	/**
	 * get total user
	 * 
	 * @param groupId
	 *            : field group_id in table tbl_user
	 * @param fullName
	 *            : field full_name in table tbl_user
	 * @return int : total user with condition input
	 */
	@Override
	public int getTotalUsers(String groupId, String fullName) throws ClassNotFoundException, SQLException {
		int totalUser = 0;
		try {
			connection = getConnection();// get connection
			if (connection == null) {// if connect null then return
				return totalUser;
			}
			String sql = getSQLSearch(sqlGetTotaluser.toString(), groupId, fullName);// get SQL
			setParam(sql, groupId, fullName);// set param into pstm
			resultSet = pstm.executeQuery();// execute sql
			resultSet.next();
			totalUser = resultSet.getInt(1);// read total user
		} finally {
			closeConnect();
		}
		return totalUser;
	};

	/**
	 * set param for pstm
	 * 
	 * @param sql
	 *            : clause sql
	 * @param groupId
	 *            : value in field group_id of table tbl_user
	 * @param fullName
	 *            : value in field of full_name table tbl_user
	 * @throws SQLException
	 */
	private void setParam(String sql, String groupId, String fullName) throws SQLException {
		pstm = connection.prepareStatement(sql);
		StringBuffer stringBuffer = new StringBuffer();
		int i = 0;
		// add where groupId
		if (groupId != null && !Constant.ZERO.equals(groupId) && !Constant.EMPTY_STRING.equals(groupId)) {
			pstm.setString(++i, groupId);
		}
		// add where fullName
		if (fullName != null && !Constant.EMPTY_STRING.equals(fullName)) {
			pstm.setString(++i, stringBuffer.append(Constant.PERCENT).append(fullName).append(Constant.PERCENT).toString());
		}
	}

	/**
	 * add condition search
	 * 
	 * @param firstSQL
	 *            : clause sql
	 * @param groupId
	 *            : value in field group_id of table tbl_user
	 * @param fullName
	 *            : value in field of full_name table tbl_user
	 * @return string : clause sql after add condition search
	 */
	private String getSQLSearch(String firstSQL, String groupId, String fullName) {
		StringBuffer stringBuffer = new StringBuffer(firstSQL);
		// if groupId is not null , is not zero, is not empty string then add condition
		// in where
		if (groupId != null && !Constant.ZERO.equals(groupId) && !Constant.EMPTY_STRING.equals(groupId)) {
			stringBuffer.append(" AND (tbl_user.group_id = ? ) ");
		}
		// if fullName is not null , is not empty string then add condition in where
		if (fullName != null && !Constant.EMPTY_STRING.equals(fullName)) {
			stringBuffer.append(" AND ( tbl_user.full_name LIKE ? ) ");
		}
		return stringBuffer.toString();
	}

	/**
	 * add condition sort
	 * 
	 * @param SQL
	 *            : clause sql
	 * @param sortType
	 *            : sort type
	 * @param sortByFullName
	 *            : sort follow field full_name in table tbl_user
	 * @param sortByCodeLevel
	 *            : sort follow field code_level in table tbl_user
	 * @param sortByEndDate
	 *            : sort follow field end_date in table tbl_user
	 * @return String : sql after add condition search
	 */
	private String getSQLSort(String SQL, String sortType, String sortByFullName, String sortByCodeLevel,
			String sortByEndDate) {
		StringBuffer stringBuffer = new StringBuffer(SQL);
		if (Constant.SORT_BY_FULL_NAME.equals(sortType)) {// in case sort by full name
			// add order full_name - name_level - end_date
			stringBuffer.append(" ORDER BY full_name ").append(sortByFullName).append(" , name_level ")
					.append(sortByCodeLevel).append(" , end_date ").append(sortByEndDate);
		} else if (Constant.SORT_BY_CODE_LEVEL.equals(sortType)) {// sort by code level
			// add order name_level - full_name - end_date
			stringBuffer.append(" ORDER BY name_level ").append(sortByCodeLevel).append(" , full_name ")
					.append(sortByFullName).append(" , end_date ").append(sortByEndDate);
		} else if (Constant.SORT_BY_END_DATE.equals(sortType)) {// sort by end date
			// add order end_date - full_name - name_level
			stringBuffer.append(" ORDER BY end_date ").append(sortByEndDate).append(" , full_name ")
					.append(sortByFullName).append(" , name_level ").append(sortByCodeLevel);
		}
		return stringBuffer.toString();
	}

	/**
	 * function get SQL paging
	 * 
	 * @param SQL
	 *            : string SQL input
	 * @param offset
	 *            : record location in db
	 * @param limit
	 *            : limit record
	 * @return String : string SQL
	 */
	private String getSQLPaging(String SQL, int offset, int limit) {
		StringBuffer stringBuffer = new StringBuffer(SQL);
		// add limit
		stringBuffer.append(" limit ").append(limit);
		if (limit > 0) {
			// add offset
			stringBuffer.append(" offset ").append(offset);
		}
		return stringBuffer.toString();
	}

	/**
	 * get user by login name
	 * 
	 * @param userId
	 *            : user_id of table mst_user
	 * @param loginName
	 *            : login_name of table
	 * @return TblUser : object TblUser
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public TblUser getUserByLoginName(Integer userId, String loginName) throws ClassNotFoundException, SQLException {
		TblUser tblUser = null;
		try {
			connection = getConnection();// get connection
			if (connection == null) {
				return tblUser;
			}
			StringBuffer sql = new StringBuffer(sqlGetTblUser.toString()).append(" WHERE tbl_user.login_name = ?  ");
			if (userId != null) {
				sql.append(" AND tbl_user.user_id != ? ");
			}
			int i;
			i = 0;
			pstm = connection.prepareStatement(sql.toString());
			pstm.setString(++i, loginName);
			if (userId != null) {
				pstm.setInt(++i, userId);
			}
			resultSet = pstm.executeQuery();// execute sql
			// repeat record get and add to list
			if (resultSet.next()) {
				i = 0;
				tblUser = new TblUser();
				tblUser.setUserId(resultSet.getInt(++i));
				tblUser.setGroupId(resultSet.getInt(++i));
				tblUser.setLoginName(resultSet.getString(++i));
				tblUser.setPassword(resultSet.getString(++i));
				tblUser.setFullName(resultSet.getString(++i));
				tblUser.setFullNameKana(resultSet.getString(++i));
				tblUser.setEmail(resultSet.getString(++i));
				tblUser.setTel(resultSet.getString(++i));
				tblUser.setBirthday(resultSet.getDate(++i));
				tblUser.setSalt(resultSet.getString(++i));
			}
		} finally {
			closeConnect();
		}
		return tblUser;
	}

	/**
	 * get TblUser by email
	 * 
	 * @param userId
	 *            : field user_id of table mst_user
	 * @param email
	 *            : field email of table mst_user
	 * @return TblUser : object TblUser
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public TblUser getUserByEmail(Integer userId, String email) throws ClassNotFoundException, SQLException {
		TblUser tblUser = null;
		try {
			connection = getConnection();// get connection
			if (connection == null) {
				return tblUser;
			}
			StringBuffer sql = new StringBuffer(sqlGetTblUser.toString()).append(" WHERE tbl_user.email = ? ");
			if (userId != null) {
				sql.append(" AND tbl_user.user_id != ? ");
			}
			int i = 0;
			pstm = connection.prepareStatement(sql.toString());
			pstm.setString(++i, email);
			if (userId != null) {
				pstm.setInt(++i, userId);
			}
			resultSet = pstm.executeQuery();// execute sql
			if (resultSet.next()) {
				i = 0;
				tblUser = new TblUser();
				tblUser.setUserId(resultSet.getInt(++i));
				tblUser.setGroupId(resultSet.getInt(++i));
				tblUser.setLoginName(resultSet.getString(++i));
				tblUser.setPassword(resultSet.getString(++i));
				tblUser.setFullName(resultSet.getString(++i));
				tblUser.setFullNameKana(resultSet.getString(++i));
				tblUser.setEmail(resultSet.getString(++i));
				tblUser.setTel(resultSet.getString(++i));
				tblUser.setBirthday(resultSet.getDate(++i));
				tblUser.setSalt(resultSet.getString(++i));
			}
		} finally {
			closeConnect();
		}
		return tblUser;
	}

	/**
	 * insert tblUser in database
	 * 
	 * @param tblUser
	 *            : object tbl_user
	 * @return boolean : check insert success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public Integer insertUser(TblUser tblUser) throws ClassNotFoundException, SQLException {
		Integer userId = null;
		int i = 0;
		try {
			if (connection == null) {
				return userId;
			}
			//config return user_id but not commit
			pstm = connection.prepareStatement(sqlInsertUser.toString(), Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(++i, tblUser.getGroupId());
			pstm.setString(++i, tblUser.getLoginName());
			pstm.setString(++i, tblUser.getPassword());
			pstm.setString(++i, tblUser.getFullName());
			pstm.setString(++i, tblUser.getFullNameKana());
			pstm.setString(++i, tblUser.getEmail());
			pstm.setString(++i, tblUser.getTel());
			pstm.setDate(++i, new Date(tblUser.getBirthday().getTime()));
			pstm.setString(++i, tblUser.getSalt());
			pstm.executeUpdate();
			resultSet = pstm.getGeneratedKeys();
			if (resultSet.next()) {
				userId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			userId = null;
		}
		return userId;
	}

	/**
	 * update tblUser in database
	 * 
	 * @param tblUser
	 *            : object tbl_user
	 * @return boolean : check update success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean updateUser(TblUser tblUser) throws ClassNotFoundException, SQLException {
		int i = 0;
		pstm = connection.prepareStatement(sqlUpdateUser.toString());
		pstm.setInt(++i, tblUser.getGroupId());
		pstm.setString(++i, tblUser.getFullName());
		pstm.setString(++i, tblUser.getFullNameKana());
		pstm.setString(++i, tblUser.getEmail());
		pstm.setString(++i, tblUser.getTel());
		pstm.setDate(++i, new Date(tblUser.getBirthday().getTime()));
		pstm.setInt(++i, tblUser.getUserId());
		int row = pstm.executeUpdate();
		if (row == 0) {
			return false;
		}
		return true;
	}

	/**
	 * delete tbl_user
	 * 
	 * @param userId
	 *            is user_id in table tbl_user
	 * @return boolean check delete tbl_user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean deleteUser(int userId) throws ClassNotFoundException, SQLException {
		pstm = connection.prepareStatement(sqlDeleteTblUser);
		pstm.setInt(1, userId);
		int row = pstm.executeUpdate();
		if (row == 0) {
			return false;
		}
		return true;
	}

	/**
	 * get user by id
	 * 
	 * @param id
	 *            is user_id in table tbl_user
	 * @return UserInfor is object of table tbl_user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public UserInfor getUserInforById(int id) throws ClassNotFoundException, SQLException {
		UserInfor userInfor = null;
		try {
			connection = getConnection();// get connection
			if (connection == null) {// if connect null then return
				return userInfor;
			}
			pstm = connection.prepareStatement(sqlGetUserInforById.toString());// use PrepareStatement
			pstm.setInt(1, id);
			resultSet = pstm.executeQuery();// execute sql
			int i;
			if (resultSet.next()) {
				i = 0;
				userInfor = new UserInfor();
				userInfor.setUserId(resultSet.getInt(++i));
				userInfor.setGroupId(resultSet.getString(++i));
				userInfor.setGroupName(resultSet.getString(++i));
				userInfor.setLoginName(resultSet.getString(++i));
				userInfor.setFullName(resultSet.getString(++i));
				userInfor.setFullNameKana(resultSet.getString(++i));
				userInfor.setBirthday(resultSet.getDate(++i));
				userInfor.setEmail(resultSet.getString(++i));
				userInfor.setTel(resultSet.getString(++i));
				userInfor.setCodeLevel(resultSet.getString(++i));
				userInfor.setNameLevel(resultSet.getString(++i));
				userInfor.setStartDate(resultSet.getDate(++i));
				userInfor.setEndDate(resultSet.getDate(++i));
				userInfor.setTotal(resultSet.getString(++i));
			}
		} finally {
			closeConnect();
		}
		return userInfor;
	}

	/**
	 * get tbl user by id
	 * 
	 * @param id
	 *            is user_id in table tbl_user
	 * @return TblUser is object of table tbl_user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public TblUser getTblUserById(int id) throws ClassNotFoundException, SQLException {
		TblUser tblUser = null;
		try {
			connection = getConnection();// get connection
			if (connection == null) {// if connect null then return
				return tblUser;
			}
			StringBuffer sqlGetTblUserById = new StringBuffer(sqlGetTblUser.toString());
			sqlGetTblUserById.append(" WHERE tbl_user.user_id = ? ");
			pstm = connection.prepareStatement(sqlGetTblUserById.toString());// use PrepareStatement
			pstm.setInt(1, id);
			resultSet = pstm.executeQuery();// execute sql
			int i;
			if (resultSet.next()) {
				i = 0;
				tblUser = new TblUser();
				tblUser.setUserId(resultSet.getInt(++i));
				tblUser.setGroupId(resultSet.getInt(++i));
				tblUser.setLoginName(resultSet.getString(++i));
				tblUser.setPassword(resultSet.getString(++i));
				tblUser.setFullName(resultSet.getString(++i));
				tblUser.setFullNameKana(resultSet.getString(++i));
				tblUser.setEmail(resultSet.getString(++i));
				tblUser.setTel(resultSet.getString(++i));
				tblUser.setBirthday(resultSet.getDate(++i));
				tblUser.setSalt(resultSet.getString(++i));
			}
		} finally {
			closeConnect();
		}
		return tblUser;
	}

	/**
	 * update password
	 * 
	 * @param userId
	 *            is user_id in table tbl_user
	 * @param newPassword
	 *            is new password
	 * @return boolean : check update success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean updatePasswrord(Integer userId, String newPassword) throws ClassNotFoundException, SQLException {
		try {
			connection = getConnection();// get connection
			if (connection == null) {// if connect null then return
				return false;
			}
			int i = 0;
			pstm = connection.prepareStatement(sqlUpdatePassword.toString());
			pstm.setString(++i, newPassword);
			pstm.setInt(++i, userId);
			int row = pstm.executeUpdate();
			if (row == 0) {
				return false;
			}
		} finally {
			closeConnect();
		}
		return true;
	}
}
