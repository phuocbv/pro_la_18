/**
 * Copyright(C) 2017  Luvina
 * TblUserDAOImpl.java, 20/10/2017 phuocbv
 */
package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import common.Common;
import common.Constant;
import dao.TblUserDAO;
import entity.UserInfor;

/**
 * class user DAO impl
 * 
 * @author da
 *
 */
public class TblUserDAOImpl extends BaseDAOImpl implements TblUserDAO {
	// sql join table
	private StringBuffer sqlJoin = new StringBuffer()
			.append(" FROM tbl_user INNER JOIN mst_group ON tbl_user.group_id = mst_group.group_id ")
			.append(" INNER JOIN (tbl_detail_user_japan INNER JOIN mst_japan ON tbl_detail_user_japan.code_level = mst_japan.code_level) ")
			.append(" ON tbl_user.user_id = tbl_detail_user_japan.user_id ");

	// sql get all user
	private StringBuffer sqlGetListUser = new StringBuffer()
			.append(" SELECT tbl_user.user_id, tbl_user.full_name, tbl_user.birthday, mst_group.group_name, ")
			.append(" tbl_user.email, tbl_user.tel, mst_japan.name_level, ")
			.append(" tbl_detail_user_japan.end_date, tbl_detail_user_japan.total ").append(sqlJoin.toString());

	// sql get total user
	private StringBuffer sqlGetTotaluser = new StringBuffer().append(" SELECT count(*) as total_user ")
			.append(sqlJoin.toString());

	/**
	 * function get all user in user table
	 * 
	 * @param offset
	 * @param limit
	 * @param groupId
	 * @param fullName
	 * @param sortType
	 * @param sortByFullName
	 * @param sortByCodeLevel
	 * @param sortByEndDate
	 * @return
	 */
	@Override
	public ArrayList<UserInfor> getListUsers(int offset, int limit, String groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) {
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
			System.out.println("pstm: " + pstm.toString() + "\n sql:" + sql);
			StringBuffer stringBuffer = new StringBuffer();
			if (groupId != null && fullName != null) {// add param
				int i = 0;
				pstm.setString(++i, groupId);
				pstm.setString(++i, stringBuffer.append(Constant.PERCENT).append(Common.filterString(fullName))
						.append(Constant.PERCENT).toString());
			}
			resultSet = pstm.executeQuery();// execute sql
			while (resultSet.next()) {// lặp từng bản ghi lấy ra và thêm vào list
				UserInfor user = new UserInfor();
				user.setUserId(resultSet.getInt(UserInfor.USER_ID));
				user.setFullName(resultSet.getString(UserInfor.FULL_NAME));
				user.setBirthday(resultSet.getDate(UserInfor.BIRTHDAY));
				user.setGroupName(resultSet.getString(UserInfor.GROUP_NAME));
				user.setEmail(resultSet.getString(UserInfor.EMAIL));
				user.setTel(resultSet.getString(UserInfor.TEL));
				user.setNameLevel(resultSet.getString(UserInfor.NAME_LEVEL));
				user.setEndDate(resultSet.getDate(UserInfor.END_DATE));
				user.setTotal(resultSet.getInt(UserInfor.TOTAL));
				listUser.add(user);
			}
		} catch (ClassNotFoundException e) {
			return listUser;
		} catch (SQLException e) {
			return listUser;
		} finally {
			closeConnect();
		}
		return listUser;
	}

	/**
	 * get total user
	 * 
	 * @param groupId
	 * @param fullName
	 * @return int
	 */
	public int getTotalUsers(String groupId, String fullName) {
		int totalUser = 0;
		try {
			connection = getConnection();// get connection
			if (connection == null) {// if connect null then return
				return totalUser;
			}
			String sql = getSQLSearch(sqlGetTotaluser.toString(), groupId, fullName);// get SQL
			pstm = connection.prepareStatement(sql);// use PrepareStatement
			StringBuffer stringBuffer = new StringBuffer();
			// add param
			if (groupId != null && fullName != null) {
				int i = 0;
				pstm.setString(++i, groupId);
				pstm.setString(++i,
						stringBuffer.append(Constant.PERCENT).append(fullName).append(Constant.PERCENT).toString());
			}
			resultSet = pstm.executeQuery();// execute sql
			resultSet.next();
			totalUser = resultSet.getInt(UserInfor.TOTAL_USER);// read total user
		} catch (ClassNotFoundException e) {
			return totalUser;
		} catch (SQLException e) {
			return totalUser;
		} finally {
			closeConnect();
		}
		return totalUser;
	};

	/**
	 * function get SQL by param
	 * 
	 * @param groupId
	 * @param fullName
	 * @return String
	 */
	private String getSQLSearch(String firstSQL, String groupId, String fullName) {
		StringBuffer stringBuffer = new StringBuffer(firstSQL);
		if (groupId != null) {// in case group != null then add where
			stringBuffer.append(" WHERE (tbl_user.group_id = ? ");
			// in case group is "" or 0 then add where OR 1 = 1
			if (Constant.ZERO.equals(groupId) || Constant.EMPTY_STRING.equals(groupId)) {
				stringBuffer.append(" OR 1 = 1 ");
			}
			stringBuffer.append(" ) ");
		}
		if (fullName != null) {// in case fullName != null then add where
			stringBuffer.append(" AND ( tbl_user.full_name LIKE ? ");
			// in case fullName is "" then add where OR 1=1
			// if (Constant.EMPTY_STRING.equals(fullName)) {
			// stringBuffer.append(" OR 1 = 1 ");
			// }
			stringBuffer.append(" ) ");
		}
		return stringBuffer.toString();
	}

	/**
	 * get sql sort
	 * 
	 * @param SQL
	 *            clause sql
	 * @param sortType
	 * @param sortByFullName
	 * @param sortByCodeLevel
	 * @param sortByEndDate
	 * @return String
	 */
	private String getSQLSort(String SQL, String sortType, String sortByFullName, String sortByCodeLevel,
			String sortByEndDate) {
		StringBuffer stringBuffer = new StringBuffer(SQL);
		if (Constant.SORT_BY_FULL_NAME.equals(sortType)) {// in case sort by full name
			// add order full_name - name_level - end_date
			stringBuffer.append(" ORDER BY full_name ").append(sortByFullName).append(" , name_level ")
					.append(sortByCodeLevel).append(" , end_date ").append(sortByEndDate);
		} else if (Constant.SORT_BY_CODE_LEVEL.equals(sortType)) {
			// add order name_level - full_name - end_date
			stringBuffer.append(" ORDER BY name_level ").append(sortByCodeLevel).append(" , full_name ")
					.append(sortByFullName).append(" , end_date ").append(sortByEndDate);
		} else if (Constant.SORT_BY_END_DATE.equals(sortType)) {
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
	 * @param offset
	 * @param limit
	 * @return String
	 */
	private String getSQLPaging(String SQL, int offset, int limit) {
		StringBuffer stringBuffer = new StringBuffer(SQL);
		// add limit
		stringBuffer.append(" limit ").append(limit).append(" ");
		if (offset >= 0) {
			// add offset
			stringBuffer.append(" offset ").append(offset).append(" ");
		}
		return stringBuffer.toString();
	}

}
