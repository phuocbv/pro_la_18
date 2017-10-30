/**
 * Copyright(C) 2017  Luvina
 * ErrorController.java, 20/10/2017 phuocbv
 */
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Constant;

/**
 * servlet show error
 * 
 * @author LA-AM
 *
 */
@WebServlet(urlPatterns = Constant.URL_VIEW_EROR)
public class ErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.VIEW_ERROR);
		dispatcher.forward(req, resp);// forward to jsp page
	}
}
