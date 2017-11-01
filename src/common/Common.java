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
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import properties.ConfigProperties;

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
	 *            : string need encode
	 * @return String : encoded string
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
	 *            : string need encode
	 * @param salt
	 *            : string random
	 * @return String : encode string with salt
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
	 * @return String : string random
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
	 *            : total record of result data
	 * @param limit
	 *            : number record on page
	 * @param currentPage
	 * @return List<Integer> : list paging
	 */
	public static List<Integer> getListPaging(int totalRecords, int limit, int currentPage) {
		List<Integer> list = new ArrayList<>();
		// if total <= limit then not display paging
		if (totalRecords <= limit) {
			return list;
		}
		// read total paging in config
		int numberPageInPage = parseInt(
				ConfigProperties.configProperties.get(ConstantProperties.NUMBER_PAGE_IN_PAGE),
				Constant.DEFAULT_NUMBER_PAGE_IN_PAGE);
		// total page follow limit
		int totalPage = totalPage(totalRecords, limit);// (int) Math.ceil((float) totalRecords / limit);
		if (currentPage < 1) {// if page < 1 still display paging 1 2 3 and data empty
			currentPage = 1;
		}
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		int currentRange = getCurrentRange(currentPage, numberPageInPage);// get current range
		int start = getStartPage(currentRange, numberPageInPage);// start paging
		int end = getEndPage(currentRange, numberPageInPage, totalPage);// end paging
		// add to paging
		for (int i = start; i <= end; i++) {
			list.add(i);
		}
		return list;
	}

	/**
	 * get total page
	 * 
	 * @param totalRecords
	 *            : total record
	 * @param limit
	 *            : limit record view
	 * @return int : total page
	 */
	public static int totalPage(int totalRecords, int limit) {
		return (int) Math.ceil((float) totalRecords / limit);
	}

	/**
	 * get current range
	 * 
	 * @param currentPage
	 *            : current page of web
	 * @param numberRange
	 *            : number range
	 * @return int is current range
	 */
	private static int getCurrentRange(int currentPage, int numberPageInPage) {
		return (int) Math.ceil((float) currentPage / numberPageInPage);
	}

	/**
	 * get start page
	 * 
	 * @param currentRange
	 *            : current page of web
	 * @param numberPageInPage
	 *            : number page in page
	 * @return int : start page
	 */
	private static int getStartPage(int currentRange, int numberPageInPage) {
		return (currentRange - 1) * numberPageInPage + 1;// start paging
	}

	/**
	 * get end page
	 * 
	 * @param currentRange
	 *            : current page of web
	 * @param numberPageInPage
	 * @param totalPage
	 * @return
	 */
	private static int getEndPage(int currentRange, int numberPageInPage, int totalPage) {
		int end = currentRange * numberPageInPage;// end paging
		return end > totalPage ? totalPage : end;
	}

	/**
	 * convert string to int
	 * 
	 * @param input
	 *            string need convert
	 * @param defaultValue
	 *            default value when has error
	 * @return int value after parse
	 */
	public static int parseInt(String input, int defaultValue) {
		int result = defaultValue;
		try {
			result = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			result = defaultValue;
		}
		return result;
	}

	/**
	 * check sort
	 * 
	 * @param sort
	 * @return
	 */
	public static String checkSort(String sort) {
		if (Constant.DESC.equals(sort)) {
			return Constant.DESC;
		}
		return Constant.ASC;
	}

	/**
	 * get offset
	 * 
	 * @param currentPage
	 *            : current page
	 * @param limit
	 *            : limit record
	 * @return int : offset of record
	 */
	public static int getOffset(int currentPage, int limit) {
		return (currentPage - 1) * limit;
	}

	/**
	 * get list year
	 * 
	 * @return List<Integer> list year 1980 - current year
	 */
	public static List<Integer> getListYear() {
		List<Integer> listYear = new ArrayList<>();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = Constant.START_YEAR; i <= currentYear; i++) {
			listYear.add(i);
		}
		return listYear;
	}

	/**
	 * get list month
	 * 
	 * @return List<Integer> list month 1 - 12
	 */
	public static List<Integer> getListMonth() {
		List<Integer> listMonth = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			listMonth.add(i);
		}
		return listMonth;
	}

	/**
	 * get list day
	 * 
	 * @return List<Integer> list day 1 - 31
	 */
	public static List<Integer> getListDay() {
		List<Integer> listDay = new ArrayList<>();
		for (int i = 1; i <= 31; i++) {
			listDay.add(i);
		}
		return listDay;
	}

	/**
	 * store object in session
	 * 
	 * @param session
	 *            : object session
	 * @param key
	 *            : key session
	 * @param object
	 *            : value session
	 */
	public static void storeSession(HttpSession session, String key, Object object) {
		session.setAttribute(key, object);
	}

	/**
	 * get session by key
	 * 
	 * @param session
	 *            : object session
	 * @param key
	 *            : key session
	 * @return Object : value session get by key
	 */
	public static Object getSession(HttpSession session, String key) {
		return session.getAttribute(key);
	}

	/**
	 * remote session by key
	 * 
	 * @param session
	 *            : object session
	 */
	public static void remoteSession(HttpSession session, String key) {
		session.removeAttribute(key);
	}

	/**
	 * format japanese
	 * 
	 * @param value
	 *            : string need convert
	 * @return String : string japanes
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
	 * get object properties
	 * 
	 * @return Properties : object Properties for read file .properties
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
