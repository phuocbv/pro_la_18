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
	public static final String PATH_FILE_CONFIG_PROPERTIES = "config.properties";
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
	public static final String SESSION_USER_INFOR = "USER_INFOR";

	// part file jsp
	public static final String ADM001 = "/view/jsp/ADM001.jsp";
	public static final String ADM002 = "/view/jsp/ADM002.jsp";
	public static final String ADM003 = "/view/jsp/ADM003.jsp";
	public static final String ADM004 = "/view/jsp/ADM004.jsp";
	public static final String ADM005 = "/view/jsp/ADM005.jsp";
	public static final String ADM006 = "/view/jsp/ADM006.jsp";
	public static final String ADM007 = "/view/jsp/ADM007.jsp";
	public static final String VIEW_ERROR = "/view/jsp/view_error.jsp";

	// url
	public static final String URL_HOME = "/";
	public static final String URL_LOGIN = "/login";
	public static final String URL_LOGOUT = "/logout";
	public static final String URL_LIST_USER = "/listUser.do";
	public static final String URL_ADD_USER_INPUT = "/addUserInput.do";
	public static final String URL_EDIT_USER_INPUT = "/editUserInput.do";
	public static final String URL_ADD_USER_VALIDATE = "/addUserValidate.do";
	public static final String URL_EDIT_USER_VALIDATE = "/editUserValidate.do";
	public static final String URL_ADD_USER_OK = "/addUserOK.do";
	public static final String URL_EDIT_USER_OK = "/editUserOK.do";
	public static final String URL_ADD_USER_CONFIRM = "/addUserConfirm.do";
	public static final String URL_EDIT_USER_CONFIRM = "/editUserConfirm.do";
	public static final String URL_SUCCESS = "/notification.do";
	public static final String URL_SHOW_DETAIL_USER = "/showDetailUser.do";
	public static final String URL_DELETE_USER = "/deleteUser.do";
	public static final String URL_CHANGE_PASSWORD = "/changePassword.do";

	// url filter
	public static final String URL_FILTER = "/*";

	// start - end time
	public static final int START_YEAR = 1980;
	public static final int START_DAY = 1;
	public static final int END_DAY = 31;
	public static final int START_MONTH = 1;
	public static final int END_MONTH = 12;

	// format date
	public static final String FORMAT_DATE = "yyyy/MM/dd";

	// paging
	public static final String PAGE = "page";

	// type
	public static final String TYPE = "type";
	public static final String TYPE_SEARCH = "search";
	public static final String TYPE_SORT = "sort";
	public static final String TYPE_PAGING = "paging";
	public static final String TYPE_BACK = "back";

	public static final String TYPE_ADM002 = "ADM002";
	public static final String TYPE_ADM003 = "ADM003";
	public static final String TYPE_ADM004 = "ADM004";
	public static final String TYPE_ADM005 = "ADM005";

	public static final String METHOD_GET = "GET";
	public static final String METHOD_POST = "POST";
	
	// action
	public static final String INSERT_SUCCESS = "insertSuccess";
	public static final String UPDATE_SUCCESS = "updateSuccess";
	public static final String DELETE_SUCCESS = "deleteSuccess";
	public static final String CHANGE_PASSWORD_SUCCESS = "changePasswordSuccess";
	public static final String NOT_FOUND_USER = "notFoundUser";
	public static final String ERROR = "error";
	
	public static final String KEY_SESSION = "key";

	// sort
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	public static final String SORT_TYPE = "sortType";
	public static final String SORT_BY_FULL_NAME = "sortByFullName";
	public static final String SORT_BY_CODE_LEVEL = "sortByCodeLevel";
	public static final String SORT_BY_END_DATE = "sortByEndDate";

	// search
	public static final String FULL_NAME = "fullName";
	public static final String GROUP_ID = "groupId";

	// default value
	public static final int DEFAULT_NUMBER_PAGE_IN_PAGE = 3;
	public static final int DEFAULT_LIMIT = 5;
	public static final int DEFAULT_PAGE = 1;

	// min - max length
	public static final int MIN_LENGTH_LOGIN_NAME = 4;
	public static final int MAX_LENGTH_LOGIN_NAME = 15;
	public static final int MAX_LENGTH_FULL_NAME_KANA = 255;
	public static final int MAX_LENGTH_FULL_NAME = 255;
	public static final int MAX_LENGTH_EMAIL = 255;
	public static final int MIN_LENGTH_PASSWORD = 5;
	public static final int MAX_LENGTH_PASSWORD = 15;
	public static final int MAX_LENGTH_TEL = 14;
	
	//pattern
	public static final String LOGIN_NAME_PATTERN = "^[a-zA-Z]{1}[a-zA-Z0-9_]*$";
	public static final String FULL_NAME_KATA_PATTERN = "^[ア-ンーッ ]*$";
	public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	public static final String TEL_PATTERN = "^[0-9]{1,4}-[0-9]{1,4}-[0-9]{1,4}$";
	public static final String TOTAL_PATTERN = "^[1-9]{1}[0-9]{0,10}$";
	public static final String PASSWORD_PATTERN = "^[a-zA-Z0-9!@#$%^&*(){}_+.,<>?;:'-=]{5,15}$";

}
