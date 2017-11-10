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
import entity.UserInfor;
import logic.TblUserLogic;
import logic.impl.TblUserLogicImpl;

/**
 * 
 * @author da
 *
 */
@WebServlet(urlPatterns = Constant.URL_SHOW_DETAIL_USER)
public class ShowDetailUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TblUserLogic tblUserLogic = null;

	public ShowDetailUserController() {
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
			String userId = req.getParameter("userId");
			UserInfor userInfor = null;
			// check userId input
			if (userId != null && !Constant.EMPTY_STRING.equals(userId)) {
				userInfor = tblUserLogic.getUserInforById(userId);
			}
			// check userInfor == null
			if (userInfor == null) {
				StringBuffer url = new StringBuffer(req.getContextPath()).append(Constant.URL_SUCCESS).append("?type=")
						.append(Constant.ERROR);
				resp.sendRedirect(url.toString());
				return;
			}
			req.setAttribute("userInfor", userInfor);
			req.setAttribute("userId", userId);
			StringBuffer urlSubmit = new StringBuffer().append(req.getContextPath())
					.append(Constant.URL_ADD_USER_INPUT);
			StringBuffer urlBack = new StringBuffer().append(req.getContextPath()).append(Constant.URL_LIST_USER)
					.append("?type=back");
			req.setAttribute("urlSubmit", urlSubmit.toString());
			req.setAttribute("urlBack", urlBack.toString());
			req.setAttribute("method", Constant.METHOD_GET);
			req.setAttribute("userId", userId);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM004);
			dispatcher.forward(req, resp);// forward to page jsp
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try {
//			String paramUserId = req.getParameter("userId");
//			String type = req.getParameter(Constant.TYPE);
//			UserInfor userInfor = tblUserLogic.getUserInforById(paramUserId);
//			StringBuffer url = new StringBuffer(req.getContextPath());
//			if (userInfor == null) {
//				url.append(Constant.URL_SUCCESS).append("?type=")
//						.append(Constant.ERROR);
//			}
//			
//			resp.sendRedirect(url.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
