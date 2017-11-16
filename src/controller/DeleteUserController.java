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
import logic.TblUserLogic;
import logic.impl.TblUserLogicImpl;

/**
 * servlet delete controller : delete user in tbl_user
 * 
 * @author da
 *
 */
@WebServlet(urlPatterns = Constant.URL_DELETE_USER)
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TblUserLogic tblUserLogic = null;

	/**
	 * contructer
	 */
	public DeleteUserController() {
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
			String paramId = req.getParameter("userId");
			int userId = Common.parseInt(paramId, 0);
			boolean checkExistTblUser = false;
			// check userId input
			if (userId > 0) {
				checkExistTblUser = tblUserLogic.checkExistTblUserById(userId);
			}
			// check userInfor exist
			if (!checkExistTblUser) {
				Common.processSystemError(req, resp, Constant.NOT_FOUND_USER);
				return;
			}
			boolean success = tblUserLogic.removeUser(userId);// call logic delete user
			// send redirect to url notification
			StringBuffer url = new StringBuffer(req.getContextPath()).append(Constant.URL_SUCCESS).append("?type=");
			String type = success ? Constant.DELETE_SUCCESS : Constant.ERROR;
			url.append(type);
			resp.sendRedirect(url.toString());
		} catch (Exception e) {
			Common.processSystemError(req, resp, Constant.ERROR);
		}
	}
}
