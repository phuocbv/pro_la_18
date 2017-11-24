/**
 * Copyright(C) 2017  Luvina
 * Common.java, 20/10/2017 phuocbv
 */
package common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import properties.ConfigProperties;

/**
 * class project function helper
 * 
 * @author da
 *
 */
public class Common {
	/**
	 * function encode
	 * 
	 * @param value
	 *            : string need encode
	 * @return String : encoded string
	 */
	public static String SHA1(String value) {
		String result = "";
		try {
			MessageDigest m = MessageDigest.getInstance("SHA1");
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
	public static String SHA1(String value, String salt) {
		StringBuffer result = new StringBuffer();
		result.append(SHA1(value));
		result.append(salt);
		return SHA1(result.toString());
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
		int numberPageInPage = parseInt(ConfigProperties.getValue(ConstantProperties.NUMBER_PAGE_IN_PAGE),
				Constant.DEFAULT_NUMBER_PAGE_IN_PAGE);
		// total page follow limit
		int totalPage = totalPage(totalRecords, limit);// (int) Math.ceil((float) totalRecords / limit);
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
	 *            : type sort
	 * @return string : type sort
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
	 * get current year
	 * 
	 * @return int : current year
	 */
	public static int getYearNow() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * get current month
	 * 
	 * @return int current month
	 */
	public static int getMonthNow() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * get current day
	 * 
	 * @return current day
	 */
	public static int getDayNow() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * get expired day
	 * 
	 * @return int is exprire day
	 */
	public static int getExpireDay() {
		Calendar now = Calendar.getInstance();
		int expireYear = parseInt(ConfigProperties.getValue(ConstantProperties.EXPIRE_NUMBER_YEAR), 1);
		now.add(Calendar.YEAR, expireYear);
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * get expire year
	 * 
	 * @return int : expire year
	 */
	public static int getExpireYear() {
		Calendar now = Calendar.getInstance();
		int expireYear = parseInt(ConfigProperties.getValue(ConstantProperties.EXPIRE_NUMBER_YEAR), 1);
		now.add(Calendar.YEAR, expireYear);
		return now.get(Calendar.YEAR);
	}

	/**
	 * get expire month
	 * 
	 * @return int : expire month
	 */
	public static int getExpireMonth() {
		Calendar now = Calendar.getInstance();
		int expireYear = parseInt(ConfigProperties.getValue(ConstantProperties.EXPIRE_NUMBER_YEAR), 1);
		now.add(Calendar.YEAR, expireYear);
		return now.get(Calendar.MONTH) + 1;
	}

	/**
	 * get list year
	 * 
	 * @return List<Integer> list year 1980 - current year
	 */
	public static List<Integer> getListYear(int startYear, int endYear) {
		List<Integer> listYear = new ArrayList<>();
		for (int i = startYear; i <= endYear; i++) {
			listYear.add(i);
		}
		return listYear;
	}

	/**
	 * get list month
	 * 
	 * @return List<Integer> list month
	 */
	public static List<Integer> getListMonth() {
		List<Integer> listMonth = new ArrayList<>();
		for (int i = Constant.START_MONTH; i <= Constant.END_MONTH; i++) {
			listMonth.add(i);
		}
		return listMonth;
	}

	/**
	 * get list day
	 * 
	 * @return List<Integer> list day
	 */
	public static List<Integer> getListDay() {
		List<Integer> listDay = new ArrayList<>();
		for (int i = Constant.START_DAY; i <= Constant.END_DAY; i++) {
			listDay.add(i);
		}
		return listDay;
	}

	/**
	 * new date from year, month, day
	 * 
	 * @param year
	 *            : year create date
	 * @param month
	 *            : month create date
	 * @param day
	 *            : day create date
	 * @return date : create exist date from year, month, day
	 */
	public static Date toDate(int year, int month, int day) {
		Calendar now = Calendar.getInstance();
		now.setLenient(false);
		now.set(year, month - 1, day);
		try {
			return now.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * format date
	 * 
	 * @param year
	 *            : input year convert
	 * @param month
	 *            : input month convert
	 * @param day
	 *            : input day convert
	 * @return string : string after convert
	 */
	public static String convertToString(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		return formatter.format(calendar.getTime());
	}

	/**
	 * Convert date input to Year,Month,Day store in ArrayList<br>
	 * date[0] = Year, date[1]=Month , date[2]=Day
	 * 
	 * @param date
	 *            is param convert to array integer
	 *
	 * @return ArrayList containt year, month, day
	 */
	public static ArrayList<Integer> toArrayInteger(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// get year, month, day
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		// add in arraylist
		ArrayList<Integer> listInteger = new ArrayList<>();
		listInteger.add(year);
		listInteger.add(month + 1);
		listInteger.add(day);
		return listInteger;
	}

	/**
	 * check birthday
	 * 
	 * @param yearInput
	 *            : year validate
	 * @param monthInput
	 *            : month validate
	 * @param dayInput
	 *            : day validate
	 * @return boolean : exist date
	 */
	public static boolean checkBirthday(String yearInput, String monthInput, String dayInput) {
		int year = parseInt(yearInput, 0);
		int month = parseInt(monthInput, 0);
		int day = parseInt(dayInput, 0);
		Date date = toDate(year, month, day);
		if (date == null) {
			return false;
		}
		return true;
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
	
//	public static String filterString(String value) {
//		if (va)
//		
//		return value;
//	}

	/**
	 * proccess when system error
	 * 
	 * @param req
	 * @param resp
	 * @param type
	 */
	public static void processSystemError(HttpServletRequest req, HttpServletResponse resp, String type) {
		StringBuffer urlNotification = new StringBuffer(req.getContextPath());
		urlNotification.append(Constant.URL_SUCCESS).append("?type=").append(type);
		try {
			// in case have error then send redirect to view error
			resp.sendRedirect(urlNotification.toString());
		} catch (IOException e1) {
		}
	}
}
