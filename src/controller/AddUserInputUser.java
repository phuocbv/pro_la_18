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
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;

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
public class AddUserInputUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MstJapanLogic mstJapanLogic;
	private MstGroupLogic mstGroupLogic;

	/**
	 * contructer
	 */
	public AddUserInputUser() {
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
			dispatcher.forward(req, resp);// forward đến trang jsp
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			setDataLogic(req, resp);
			setDefaultValue(req, resp);
			String loginName = req.getParameter(UserInfor.LOGIN_NAME);
			String groupId = req.getParameter(UserInfor.GROUP_ID);
			String fullName = req.getParameter(UserInfor.FULL_NAME);
			String fullNameKana = req.getParameter(UserInfor.FULL_NAME_KANA);
			
			String birthdayYear = req.getParameter(UserInfor.BIRTHDAY_YEAR);
			String birthdayMonth = req.getParameter(UserInfor.BIRTHDAY_MONTH);
			String birthdayDay = req.getParameter(UserInfor.BIRTHDAY_DAY);
			
			String startYear = req.getParameter(UserInfor.START_YEAR);
			String startMonth = req.getParameter(UserInfor.START_MONTH);
			String startDay = req.getParameter(UserInfor.START_DAY);
			
			String endYear = req.getParameter(UserInfor.END_YEAR);
			String endMonth = req.getParameter(UserInfor.END_MONTH);
			String endDay = req.getParameter(UserInfor.END_DAY);
			
			Date birthday = Common.toDate(birthdayYear, birthdayMonth, birthdayDay);
			Date startDate = Common.toDate(startYear, startMonth, startDay);
			Date endDate = Common.toDate(endYear, endMonth, endDay);
			
			
			UserInfor userInfor = new UserInfor();
			userInfor.setLoginName(loginName);
			userInfor.setGroupId(groupId);
			userInfor.setFullName(fullName);
			userInfor.setFullNameKana(fullNameKana);
			userInfor.setBirthday(birthday);
			userInfor.setStartDate(startDate);
			userInfor.setEndDate(endDate);
			
			ValidateUser validateUser = new ValidateUser();
			List<String> listError = validateUser.validateUserInfor(userInfor);
			StringBuffer stringBuffer = new StringBuffer(req.getContextPath());
			if (listError.isEmpty()) {
				stringBuffer.append(Constant.URL_ADD_USER_CONFIRM);
				resp.sendRedirect(stringBuffer.toString());
			} else {
				req.setAttribute("listError", listError);
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.VIEW_ERROR);
				dispatcher.forward(req, resp);// forward đến trang jsp
			}
		} catch (Exception e) {
//			StringBuffer stringBuffer = new StringBuffer(req.getContextPath());
//			try {
//				// in case have error then send redirect to view error
//				resp.sendRedirect(stringBuffer.append(Constant.URL_VIEW_EROR).toString());
//			} catch (IOException e1) {
//
//			}
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.VIEW_ERROR);
			dispatcher.forward(req, resp);// forward đến trang jsp
		}
	}

	/**
	 * set value for field select box in screen ADM003
	 * 
	 * @param req : object request
	 * @param resp : object response
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void setDataLogic(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException {
		ArrayList<MstJapan> listJapan = mstJapanLogic.getAllMstJapan();
		ArrayList<MstGroup> listGroup = mstGroupLogic.getAllListGroups();
		int currentYear = Common.getCurrentYear();
		int currentMonth = Common.getCurrentMonth();
		int currentDay = Common.getCurrentDay();
		List<Integer> listYear = Common.getListYear(Constant.START_YEAR, currentYear);
		List<Integer> listMonth = Common.getListMonth();
		List<Integer> listDay = Common.getListDay();
		req.setAttribute("currentYear", currentYear);
		req.setAttribute("listYear", listYear);
		req.setAttribute("listMonth", listMonth);
		req.setAttribute("listDay", listDay);
		req.setAttribute("listJapan", listJapan);
		req.setAttribute("listGroup", listGroup);
	}

	/**
	 * set default value for field text box in screen ADM003
	 * 
	 * @param req : object request
	 * @param resp : object response
	 */
	private void setDefaultValue(HttpServletRequest req, HttpServletResponse resp) {
		//get user infor in session
		UserInfor userInfor = (UserInfor) req.getSession().getAttribute(Constant.SESSION_USER_INFOR);
		if (userInfor == null) {
			userInfor = new UserInfor();
		}
		req.setAttribute("userInfor", userInfor);
	}
}
