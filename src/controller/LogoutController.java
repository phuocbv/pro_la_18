/**
 * Copyright(C) 2017  Luvina
 * LogoutController.java, 20/10/2017 phuocbv
 */
package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import common.Constant;

/**
 * logout controller
 * 
 * @author da
 *
 */
@WebServlet(urlPatterns = Constant.URL_LOGOUT)
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().invalidate(); //remove all value in session
			StringBuffer stringBuffer = new StringBuffer(request.getContextPath());
			stringBuffer.append(Constant.URL_LOGIN);
			response.sendRedirect(stringBuffer.toString());
		} catch (Exception e) {
			Common.processSystemError(request, response, Constant.ERROR);
		}
	}
}
