/**
 * Copyright(C) 2017  Luvina
 * AddUserInputUser.java, 30/10/2017 phuocbv
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import common.Constant;
import entity.MstGroup;
import entity.MstJapan;
import entity.UserInfor;
import logic.MstGroupLogic;
import logic.MstJapanLogic;
import logic.TblUserLogic;
import logic.impl.MstGroupLogicImpl;
import logic.impl.MstJapanLogicImpl;
import logic.impl.TblUserLogicImpl;
import validate.ValidateUser;

/**
 * class add user
 * 
 * @author da
 *
 */
@WebServlet(urlPatterns = { Constant.URL_ADD_USER_INPUT, Constant.URL_ADD_USER_VALIDATE })
public class AddUserInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MstJapanLogic mstJapanLogic;
	private MstGroupLogic mstGroupLogic;
	private TblUserLogic tblUserLogic;

	/**
	 * contructer
	 */
	public AddUserInputController() {
		mstJapanLogic = new MstJapanLogicImpl();
		mstGroupLogic = new MstGroupLogicImpl();
		tblUserLogic = new TblUserLogicImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			setDataLogic(req, resp);
			UserInfor userInfor = setDefaultValue(req, resp);
			StringBuffer url = new StringBuffer(req.getContextPath());
			int userId = userInfor.getUserId();
			if (userId > 0) {
				url.append(Constant.URL_SHOW_DETAIL_USER);
			} else {
				url.append(Constant.URL_LIST_USER);
			}
			req.setAttribute("url", url.toString());
			req.setAttribute("userInfor", userInfor);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM003);
			dispatcher.forward(req, resp);// forward to page jsp
		} catch (Exception e) {
			Common.processSystemError(req, resp);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			UserInfor userInfor = setDefaultValue(req, resp);
			ValidateUser validateUser = new ValidateUser();
			List<String> listError = validateUser.validateUserInfor(userInfor);// validate user
			StringBuffer stringBuffer = new StringBuffer();
			// case haven't error
			if (listError.isEmpty()) {
				stringBuffer.append(req.getContextPath());
				String keySession = Common.MD5(Common.randomString());
				stringBuffer.append(Constant.URL_ADD_USER_CONFIRM);
				stringBuffer.append("?");
				stringBuffer.append(Constant.KEY_SESSION);
				stringBuffer.append("=");
				stringBuffer.append(keySession);
				req.getSession().setAttribute(keySession, userInfor);
				resp.sendRedirect(stringBuffer.toString());
			} else {// case validate have error then back ADM003
				setDataLogic(req, resp);
				req.setAttribute("userInfor", userInfor);
				req.setAttribute("listError", listError);
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM003);
				dispatcher.forward(req, resp);// forward to page jsp
			}
		} catch (Exception e) {
			Common.processSystemError(req, resp);
		}
	}

	/**
	 * set value for field select box in screen ADM003
	 * 
	 * @param req
	 *            : object request
	 * @param resp
	 *            : object response
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void setDataLogic(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException {
		ArrayList<MstJapan> listJapan = mstJapanLogic.getAllMstJapan();
		ArrayList<MstGroup> listGroup = mstGroupLogic.getAllListGroups();
		int currentYear = Common.getYearNow();
		int currentMonth = Common.getMonthNow();
		int currentDay = Common.getDayNow();
		int expireYear = Common.getExpireYear();
		List<Integer> listYear = Common.getListYear(Constant.START_YEAR, currentYear);
		List<Integer> listMonth = Common.getListMonth();
		List<Integer> listDay = Common.getListDay();
		List<Integer> listExpireYear = Common.getListYear(Constant.START_YEAR, expireYear);
		req.setAttribute("expireMonth", Common.getExpireMonth());
		req.setAttribute("expireDay", Common.getExpireDay());
		req.setAttribute("expireYear", expireYear);
		req.setAttribute("listExpireYear", listExpireYear);
		req.setAttribute("currentYear", currentYear);
		req.setAttribute("currentMonth", currentMonth);
		req.setAttribute("currentDay", currentDay);
		req.setAttribute("listYear", listYear);
		req.setAttribute("listMonth", listMonth);
		req.setAttribute("listDay", listDay);
		req.setAttribute("listJapan", listJapan);
		req.setAttribute("listGroup", listGroup);
	}

	/**
	 * set default value for field text box in screen ADM003
	 * 
	 * @param req
	 *            : object request
	 * @param resp
	 *            : object response
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private UserInfor setDefaultValue(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException {
		String type = req.getParameter(Constant.TYPE);
		UserInfor userInfor = null;
		if (type == null || Constant.TYPE_ADM002.equals(type)) {
			userInfor = new UserInfor();
		} else if (Constant.TYPE_ADM004.equals(type)) {
			String keySession = req.getParameter(Constant.KEY_SESSION);
			userInfor = (UserInfor) req.getSession().getAttribute(keySession);
			req.getSession().removeAttribute(keySession);
		} else if (Constant.TYPE_ADM003.equals(type)) {
			userInfor = new UserInfor();
			userInfor.setLoginName(req.getParameter(UserInfor.LOGIN_NAME));
			userInfor.setGroupId(req.getParameter(UserInfor.GROUP_ID));
			userInfor.setFullName(req.getParameter(UserInfor.FULL_NAME));
			userInfor.setFullNameKana(req.getParameter(UserInfor.FULL_NAME_KANA));
			// set birthday
			userInfor.setBirthdayYear(req.getParameter(UserInfor.BIRTHDAY_YEAR));
			userInfor.setBirthdayMonth(req.getParameter(UserInfor.BIRTHDAY_MONTH));
			userInfor.setBirthdayDay(req.getParameter(UserInfor.BIRTHDAY_DAY));

			userInfor.setEmail(req.getParameter(UserInfor.EMAIL));
			userInfor.setTel(req.getParameter(UserInfor.TEL));
			userInfor.setPassword(req.getParameter(UserInfor.PASSWORD));
			userInfor.setConfirmPassword(req.getParameter(UserInfor.CONFIRM_PASSWORD));

			String codeLevel = req.getParameter(UserInfor.CODE_LEVEL);
			// in case have code level japan
			if (codeLevel != null && !Constant.EMPTY_STRING.equals(codeLevel) && !Constant.ZERO.equals(codeLevel)) {
				userInfor.setCodeLevel(codeLevel);
				// set start date
				userInfor.setStartYear(req.getParameter(UserInfor.START_YEAR));
				userInfor.setStartMonth(req.getParameter(UserInfor.START_MONTH));
				userInfor.setStartDay(req.getParameter(UserInfor.START_DAY));
				// set end date
				userInfor.setEndYear(req.getParameter(UserInfor.END_YEAR));
				userInfor.setEndMonth(req.getParameter(UserInfor.END_MONTH));
				userInfor.setEndDay(req.getParameter(UserInfor.END_DAY));

				userInfor.setTotal(req.getParameter(UserInfor.TOTAL));
			}
		} else if (Constant.TYPE_ADM005.equals(type)) {
			String userId = req.getParameter("userId");
			userInfor = tblUserLogic.getUserById(userId);
			// get array integer of birthday
			ArrayList<Integer> birthday = Common.toArrayInteger(userInfor.getBirthday());
			userInfor.setBirthdayYear(String.valueOf(birthday.get(0)));
			userInfor.setBirthdayMonth(String.valueOf(birthday.get(1)));
			userInfor.setBirthdayDay(String.valueOf(birthday.get(2)));
		}
		return userInfor;
	}
}
