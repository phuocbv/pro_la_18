/**
 * Copyright(C) 2017  Luvina
 * LoginController.java, 20/10/2017 phuocbv
 */
package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Common;
import common.Constant;
import entity.UserInfor;
import logic.AuthLogic;
import logic.impl.AuthLogicImpl;

/**
 * class login controller
 * 
 * @author da
 *
 */
@WebServlet(urlPatterns = Constant.URL_LOGIN)
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthLogic authLogic = null;

	/**
	 * contructer
	 */
	public LoginController() {
		authLogic = new AuthLogicImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// tạo đối tượng RequestDispatcher để forward đến trang jsp
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM001);
		dispatcher.forward(request, response);// forward đến trang jsp
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			// get param
			String loginName = request.getParameter(UserInfor.LOGIN_NAME);
			String password = request.getParameter(UserInfor.PASSWORD);
			// validate admin login
			List<String> listMessage = authLogic.validateAdmin(loginName, password);
			StringBuffer stringBuffer = new StringBuffer(request.getContextPath());
			// if listMessage empty then redirect to list user else then forward to login
			if (listMessage.isEmpty()) {// if listMessage empty then login success
				HttpSession session = request.getSession();
				Common.storeSession(session, Constant.SESSION_LOGGINED_USER, loginName);
				stringBuffer.append(Constant.URL_LIST_USER);
				response.sendRedirect(stringBuffer.toString());
			} else {// if listMessage not empty then login not success
				request.setAttribute("loginName", loginName);
				request.setAttribute("listMessage", listMessage);
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM001);
				dispatcher.forward(request, response);// forward to page jsp
			}
		} catch (Exception e) {
			try {
				// in case have error then send redirect to view error
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.VIEW_ERROR);
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e1) {

			}
		}
	}
}
