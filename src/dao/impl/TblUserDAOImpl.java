/**
 * Copyright(C) 2017  Luvina
 * TblUserDAOImpl.java, 20/10/2017 phuocbv
 */
package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import common.Constant;
import dao.TblUserDAO;
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
			if (connection == null) {// nếu connect null thì return
				return listUser;
			}
			String sql = getSQLSearch(sqlGetListUser.toString(), groupId, fullName);// get SQL search
			sql = getSQLSort(sql, sortType, sortByFullName, sortByCodeLevel, sortByEndDate);// get SQL sort
			sql = getSQLPaging(sql, offset, limit);// add paging
			pstm = connection.prepareStatement(sql);// sử dụng PrepareStatement
			setParam(sql, groupId, fullName);
			System.out.println(pstm.toString());
			resultSet = pstm.executeQuery();// execute sql
			while (resultSet.next()) {// lặp từng bản ghi lấy ra và thêm vào list
				UserInfor user = new UserInfor();
				/*user.setUserId(resultSet.getInt(UserInfor.USER_ID));
				user.setFullName(resultSet.getString(UserInfor.FULL_NAME));
				user.setBirthday(resultSet.getDate(UserInfor.BIRTHDAY));
				user.setGroupName(resultSet.getString(UserInfor.GROUP_NAME));
				user.setEmail(resultSet.getString(UserInfor.EMAIL));
				user.setTel(resultSet.getString(UserInfor.TEL));
				user.setNameLevel(resultSet.getString(UserInfor.NAME_LEVEL));
				user.setEndDate(resultSet.getDate(UserInfor.END_DATE));
				user.setTotal(resultSet.getInt(UserInfor.TOTAL));*/
				user.setUserId(resultSet.getInt(1));
				user.setFullName(resultSet.getString(2));
				user.setBirthday(resultSet.getDate(3));
				user.setGroupName(resultSet.getString(4));
				user.setEmail(resultSet.getString(5));
				user.setTel(resultSet.getString(6));
				user.setNameLevel(resultSet.getString(7));
				user.setEndDate(resultSet.getDate(8));
				user.setTotal(resultSet.getInt(9));
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
//			totalUser = resultSet.getInt(UserInfor.TOTAL_USER);// read total user
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
			pstm.setString(++i, stringBuffer.append(fullName).toString());
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
			stringBuffer.append(" AND ( tbl_user.full_name REGEXP ? ) ");
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

}
