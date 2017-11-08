/**
 * Copyright(C) 2017  Luvina
 * ErrorController.java, 20/10/2017 phuocbv
 */
package controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import common.Constant;
import common.ConstantProperties;
import properties.MessageProperties;

/**
 * servlet show notification
 * 
 * @author LA-AM
 *
 */
@WebServlet(urlPatterns = Constant.URL_SUCCESS)
public class SuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			String type = req.getParameter(Constant.TYPE);
			String message = "";
			if (type == null || Constant.ERROR.equals(type)) {
				message = MessageProperties.getValue(ConstantProperties.SYSTEM_ERROR);
			} else if (Constant.INSERT_SUCCESS.equals(type)) {
				message = MessageProperties.getValue(ConstantProperties.MSG001);
			}
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM006);
			dispatcher.forward(req, resp);// forward to jsp page
		} catch (Exception e) {
			Common.processSystemError(req, resp);
		}

	}
}
