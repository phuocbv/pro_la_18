/**
 * Copyright(C) 2017  Luvina
 * AddUserInputUser.java, 30/10/2017 phuocbv
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import logic.impl.MstGroupLogicImpl;
import logic.impl.MstJapanLogicImpl;
import validate.ValidateUser;

/**
 * class add user
 * 
 * @author da
 *
 */
@WebServlet(urlPatterns = Constant.URL_ADD_USER_INPUT)
public class AddUserInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MstJapanLogic mstJapanLogic;
	private MstGroupLogic mstGroupLogic;

	/**
	 * contructer
	 */
	public AddUserInputController() {
		mstJapanLogic = new MstJapanLogicImpl();
		mstGroupLogic = new MstGroupLogicImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			setDataLogic(req, resp);
			setDefaultValue(req, resp);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM003);
			dispatcher.forward(req, resp);// forward to page jsp
		} catch (Exception e) {
			StringBuffer stringBuffer = new StringBuffer(req.getContextPath());
			try {
				// in case have error then send redirect to view error
				resp.sendRedirect(stringBuffer.append(Constant.URL_VIEW_EROR).toString());
			} catch (IOException e1) {

			}
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
			setDataLogic(req, resp);
			setDefaultValue(req, resp);
			UserInfor userInfor = new UserInfor();
			userInfor.setLoginName(req.getParameter(UserInfor.LOGIN_NAME));
			userInfor.setGroupId(req.getParameter(UserInfor.GROUP_ID));

			System.out.println(userInfor.getLoginName() + " - " + userInfor.getGroupId());

			userInfor.setFullName(req.getParameter(UserInfor.FULL_NAME));
			userInfor.setFullNameKana(req.getParameter(UserInfor.FULL_NAME));
			userInfor.setEmail(req.getParameter(UserInfor.EMAIL));
			userInfor.setTel(req.getParameter(UserInfor.TEL));
			userInfor.setPassword(req.getParameter(UserInfor.PASSWORD));
			userInfor.setConfirmPassword(req.getParameter(UserInfor.CONFIRM_PASSWORD));
			// Date birthday = Common.toDate(req.getParameter(UserInfor.BIRTHDAY_YEAR),
			// req.getParameter(UserInfor.BIRTHDAY_MONTH),
			// req.getParameter(UserInfor.BIRTHDAY_DAY));
			// userInfor.setBirthday(birthday);
			String codeLevel = req.getParameter(UserInfor.CODE_LEVEL);
			// in case have code level japan
			if (codeLevel != null && !Constant.EMPTY_STRING.equals(codeLevel) && !Constant.ZERO.equals(codeLevel)) {
				userInfor.setCodeLevel(codeLevel);
				// Date startDate = Common.toDate(req.getParameter(UserInfor.START_YEAR),
				// req.getParameter(UserInfor.START_MONTH),
				// req.getParameter(UserInfor.START_DAY));
				// Date endDate = Common.toDate(req.getParameter(UserInfor.END_YEAR),
				// req.getParameter(UserInfor.END_MONTH), req.getParameter(UserInfor.END_DAY));
				// userInfor.setStartDate(startDate);
				// userInfor.setEndDate(endDate);
				int total = Common.parseInt(req.getParameter(UserInfor.TOTAL), 0);
				userInfor.setTotal(total);
			}
			 ValidateUser validateUser = new ValidateUser();
			 List<String> listError = validateUser.validateUserInfor(userInfor);//
			// validate user
			//List<String> listError = new ArrayList<>();
			StringBuffer stringBuffer = new StringBuffer(req.getContextPath());
			if (listError.isEmpty()) {
				stringBuffer.append(Constant.URL_ADD_USER_CONFIRM);
				resp.sendRedirect(stringBuffer.toString());
			} else {
				req.setAttribute("listError", listError);
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM003);
				dispatcher.forward(req, resp);// forward to page jsp
			}
		} catch (Exception e) {
			StringBuffer stringBuffer = new StringBuffer(req.getContextPath());
			try {
				// in case have error then send redirect to view error
				resp.sendRedirect(stringBuffer.append(Constant.URL_VIEW_EROR).toString());
			} catch (IOException e1) {

			}
			// RequestDispatcher dispatcher =
			// this.getServletContext().getRequestDispatcher(Constant.VIEW_ERROR);
			// dispatcher.forward(req, resp);// forward đến trang jsp
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
		// String type = req.getParameter("type");
		List<Integer> listYear = Common.getListYear(Constant.START_YEAR, currentYear);
		List<Integer> listMonth = Common.getListMonth();
		List<Integer> listDay = Common.getListDay();
		req.setAttribute("expireYear", Common.getExpireYear());
		req.setAttribute("expireMonth", Common.getExpireMonth());
		req.setAttribute("expireDay", Common.getExpireDay());
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
	 */
	private void setDefaultValue(HttpServletRequest req, HttpServletResponse resp) {
		// get user infor in session
		UserInfor userInfor = (UserInfor) req.getSession().getAttribute(Constant.SESSION_USER_INFOR);
		if (userInfor == null) {
			userInfor = new UserInfor();
		}
		req.setAttribute("userInfor", userInfor);
	}
}
