/**
 * Copyright(C) 2017  Luvina
 * ChangePasswordController.java, 10/11/2017 phuocbv
 */
package controller;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import common.Constant;
import entity.UserInfor;
import logic.TblUserLogic;
import logic.impl.TblUserLogicImpl;
import validate.ValidateUser;

/**
 * servlet change controller : change password
 * 
 * @author LA-AM
 *
 */
@WebServlet(urlPatterns = Constant.URL_CHANGE_PASSWORD)
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TblUserLogic tblUserLogic = null;

	/**
	 * contructer
	 */
	public ChangePasswordController() {
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
			String paramId = req.getParameter("userId");
			int userId = Common.parseInt(paramId, 0);
			boolean checkExist = tblUserLogic.checkExistTblUserById(userId);
			// check exist user
			if (!checkExist) {
				Common.processSystemError(req, resp, Constant.NOT_FOUND_USER);
				return;
			}
			req.setAttribute("userId", paramId);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM007);
			dispatcher.forward(req, resp);// forward to jsp page
		} catch (Exception e) {
			Common.processSystemError(req, resp, Constant.ERROR);
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
			String paramId = req.getParameter("userId");
			int userId = Common.parseInt(paramId, 0);
			boolean checkExist = tblUserLogic.checkExistTblUserById(userId);
			// check exist user
			if (!checkExist) {
				Common.processSystemError(req, resp, Constant.NOT_FOUND_USER);
				return;
			}
			UserInfor userInfor = new UserInfor();
			userInfor.setPassword(req.getParameter("newPassword"));
			userInfor.setConfirmPassword(req.getParameter("confirmPassword"));
			ValidateUser validateUser = new ValidateUser();
			List<String> listMessage = validateUser.validatePassword(userInfor);// validate password
			// validate success
			if (listMessage.isEmpty()) {
				boolean checkSuccess = tblUserLogic.changePasswrordOfUser(userId, userInfor.getPassword());
				String type = checkSuccess ? Constant.CHANGE_PASSWORD_SUCCESS : Constant.ERROR;
				Common.processSystemError(req, resp, type);
				return;
			}
			req.setAttribute("userId", paramId);
			req.setAttribute("listMessage", listMessage);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM007);
			dispatcher.forward(req, resp);// forward to jsp page
		} catch (Exception e) {
			//e.printStackTrace();
			Common.processSystemError(req, resp, Constant.ERROR);
		}
	}
}
