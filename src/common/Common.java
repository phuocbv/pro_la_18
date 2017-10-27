/**
 * Copyright(C) 2017  Luvina
 * Common.java, 20/10/2017 phuocbv
 */
package common;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import properties.DatabaseProperties;

/**
 * class project function helper
 * 
 * @author da
 *
 */
public class Common {
	/**
	 * function md5
	 * 
	 * @param value
	 * @return
	 */
	public static String MD5(String value) {
		String result = "";
		try {
			MessageDigest m = MessageDigest.getInstance(Constant.MD5);
			m.update(value.getBytes(), 0, value.length());
			result = new BigInteger(1, m.digest()).toString(16).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			result = "";
		}
		return result;
	}

	/**
	 * function md5 with 2 param
	 * 
	 * @param value
	 * @param salt
	 * @return
	 */
	public static String MD5(String value, String salt) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(MD5(value));
		stringBuffer.append(salt);
		return MD5(stringBuffer.toString());
	}

	/**
	 * function random string
	 * 
	 * @param length
	 * @return
	 */
	public static String randomString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < Constant.LENGTH_SALT; i++) {
			int character = (int) (Math.random() * Constant.ALPHA_NUMERIC_STRING.length());
			builder.append(Constant.ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	/**
	 * get list paging
	 * 
	 * @param tolalRecords
	 * @param limit
	 * @param currentPage
	 * @return
	 */
	public static List<Integer> getListPaging(int tolalRecords, int limit, int currentPage) {
		List<Integer> list = new ArrayList<>();
		// if total <= limit then not display paging
		if (tolalRecords <= limit) {
			return list;
		}
		int countPaging = Integer.parseInt(DatabaseProperties.databaseProperties.get(ConstantProperties.COUNT_PAGING));
		// count page follow limit
		int countPage = (int) Math.ceil((float) tolalRecords / limit);
		if (currentPage < 1) {
			currentPage = 1;
		}
		if (currentPage > countPage) {
			currentPage = countPage;
		}
		int partition = (currentPage - 1) / countPaging; // partition of current page
		int start = partition * countPaging + 1;// start paging
		int end = (partition + 1) * countPaging;// end paging
		// add to paging
		for (int i = start; i <= end; i++) {
			list.add(i);
			if (i == countPage) {
				break;
			}
		}
		return list;
	}

	/**
	 * filter string
	 * 
	 * @param value
	 * @return
	 */
	public static String filterString(String value) {
		if (value != null) {
			value = value.replace("%", "?%").replace("/", "?/").replace("_", "?_").replace("'", "?'").replace("'",
					"?'");
		}
		return value;
	}

	/**
	 * get offset
	 * 
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	public static int getOffset(int currentPage, int limit) {
		return (currentPage - 1) * limit;
	}

	/**
	 * store object in session
	 * 
	 * @param session
	 * @param loginedUser
	 */
	public static void storeSession(HttpSession session, String key, Object object) {
		session.setAttribute(key, object);
	}

	/**
	 * get session by key
	 * 
	 * @param session
	 * @return
	 */
	public static Object getSession(HttpSession session, String key) {
		return session.getAttribute(key);
	}

	/**
	 * remote session by key
	 * 
	 * @param session
	 */
	public static void remoteSession(HttpSession session, String key) {
		session.removeAttribute(key);
	}

	/**
	 * format japanese
	 * 
	 * @param value
	 * @return
	 */
	public static String getJapanes(String value) {
		String result = "";
		try {
			result = new String(value.getBytes(Constant.ISO_8859_1), Constant.UTF_8);
		} catch (UnsupportedEncodingException e) {
			result = "";
		}
		return result;
	}

	/**
	 * get properties of message
	 * 
	 * @return Properties
	 */
	public static Properties getProperties(String fileName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		try {
			prop.load(classLoader.getResourceAsStream(fileName));
		} catch (Exception e) {
			return null;
		}
		return prop;
	}
}
