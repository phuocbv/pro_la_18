/**
 * Copyright(C) 2017  Luvina
 * MstGroup.java, 23/10/2017 phuocbv
 */
package entity;

/**
 * class entity MstGroup
 * 
 * @author da
 *
 */
public class MstGroup {
	private int groupId;
	private String groupName;

	// attribute in table database
	public static final String GROUP_ID = "group_id";
	public static final String GROUP_NAME = "group_name";

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
