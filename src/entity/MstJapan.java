/**
 * Copyright(C) 2017  Luvina
 * MstJapan.java, 30/10/2017 phuocbv
 */
package entity;

/**
 * entity mst_japan
 * 
 * @author da
 *
 */
public class MstJapan {
	private String codeLevel;
	private String nameLevel;

	public static final String CODE_LEVEL = "code_level";
	public static final String NAME_LEVEL = "name_level";

	/**
	 * @return the codeLevel
	 */
	public String getCodeLevel() {
		return codeLevel;
	}

	/**
	 * @param codeLevel
	 *            the codeLevel to set
	 */
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	/**
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}

	/**
	 * @param nameLevel
	 *            the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

}
