/**
 * Copyright(C) 2017  Luvina
 * DeleteController.java, 10/11/2017 phuocbv
 */
package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import common.Constant;
import entity.UserInfor;
import logic.TblUserLogic;
import logic.impl.TblUserLogicImpl;

/**
 * 
 * @author da
 *
 */
@WebServlet(urlPatterns = Constant.URL_DELETE_USER)
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TblUserLogic tblUserLogic = null;

	public DeleteController() {
		tblUserLogic = new TblUserLogicImpl();
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
			String userId = req.getParameter("userId");
			UserInfor userInfor = null;
			// check userId input
			if (userId != null && !Constant.EMPTY_STRING.equals(userId)) {
				userInfor = tblUserLogic.getUserInforById(userId);
			}
			// check userInfor == null
			if (userInfor == null) {
				Common.processSystemError(req, resp, Constant.ERROR);
				return;
			}
			// send redirect to url notification
			StringBuffer url = new StringBuffer(req.getContextPath()).append(Constant.URL_SUCCESS).append("?type=");
			boolean success = tblUserLogic.removeUser(userInfor.getUserId());// call logic delete user
			String type = success ? Constant.DELETE_SUCCESS : Constant.ERROR;
			url.append(type);
			resp.sendRedirect(url.toString());
		} catch (Exception e) {
			Common.processSystemError(req, resp, Constant.ERROR);
		}
	}
}
