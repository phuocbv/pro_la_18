/**
 * Copyright(C) 2017  Luvina
 * Constant.java, 20/10/2017 phuocbv
 */
package common;

/**
 * class project constant
 * 
 * @author da
 *
 */
public class Constant {
	// path file properties
	public static final String PATH_FILE_ADMIN_PROPERTIES = "admin.properties";
	public static final String PATH_FILE_DATABASE_PROPERTIES = "database.properties";
	public static final String PATH_FILE_MESSAGE_ERROR_PROPERTIES = "message_error.properties";
	public static final String PATH_FILE_MESSAGE_PROPERTIES = "message.properties";

	// attribute admin properties
	public static final String LOGIN_NAME = "LOGIN_NAME";
	public static final String PASSWORD = "PASSWORD";
	public static final String SALT_ADMIN = "SALT_ADMIN";

	// md5
	public static final String MD5 = "MD5";

	// String empty
	public static final String EMPTY_STRING = "";
	public static final String ZERO = "0";
	public static final String PERCENT = "%";

	// public folder
	public static final String FOLDER_CSS = "/css/";
	public static final String FOLDER_IMAGES = "/images/";
	public static final String FOLDER_JS = "/js/";

	// format string
	public static final String UTF_8 = "UTF-8";
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String CONTANT_TYPE = "text/html; charset=UTF-8";

	// length salt
	public static final int LENGTH_SALT = 20;
	public static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	// session
	public static final String SESSION_LOGGINED_USER = "LOGINED_USER";
	public static final String SESSION_CONDITION_STORE = "CONDITION_STORE";

	// part file jsp
	public static final String ADM001 = "/view/jsp/ADM001.jsp";
	public static final String ADM002 = "/view/jsp/ADM002.jsp";
	public static final String ADM003 = "/view/jsp/ADM003.jsp";
	public static final String ADM004 = "/view/jsp/ADM004.jsp";
	public static final String VIEW_ERROR = "/view/jsp/view_error.jsp";
	
	// url
	public static final String URL_HOME = "/";
	public static final String URL_LOGIN = "/login";
	public static final String URL_LOGOUT = "/logout";
	public static final String URL_LIST_USER = "/listUser.do";
	public static final String URL_VIEW_EROR = "/viewError.do";

	// url filter
	public static final String URL_FILTER = "/*";

	//paging
	public static final String PAGE = "page";
	
	//type
	public static final String TYPE = "type";
	public static final String TYPE_SEARCH = "search";
	public static final String TYPE_SORT = "sort";
	public static final String TYPE_PAGING = "paging";
	
	//sort
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	public static final String SORT_TYPE = "sortType";
	public static final String SORT_BY_FULL_NAME = "sortByFullName";
	public static final String SORT_BY_CODE_LEVEL = "sortByCodeLevel";
	public static final String SORT_BY_END_DATE = "sortByEndDate";
	
	//search
	public static final String FULL_NAME = "fullName";
	public static final String GROUP_ID = "groupId";
	
	//default value
	public static final int DEFAULT_NUMBER_PAGE_IN_PAGE = 3;
	public static final int DEFAULT_LIMIT = 5;
}
