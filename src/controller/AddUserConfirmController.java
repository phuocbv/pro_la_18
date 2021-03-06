/**
 * Copyright(C) 2017  Luvina
 * AddUserConfirmController.java, 30/10/2017 phuocbv
 */
package controller;

import javax.servlet.RequestDispatcher;
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
import logic.TblUserLogic;
import logic.impl.MstGroupLogicImpl;
import logic.impl.MstJapanLogicImpl;
import logic.impl.TblUserLogicImpl;

/**
 * class add, edit user confirm
 * 
 * @author LA-AM
 *
 */
@WebServlet(urlPatterns = { Constant.URL_ADD_USER_OK, Constant.URL_ADD_USER_CONFIRM, Constant.URL_EDIT_USER_CONFIRM,
		Constant.URL_EDIT_USER_OK })
public class AddUserConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MstGroupLogic mstGroupLogic;
	private MstJapanLogic mstJapanLogic;
	private TblUserLogic tblUserLogic;

	/**
	 * contructer
	 */
	public AddUserConfirmController() {
		mstJapanLogic = new MstJapanLogicImpl();
		mstGroupLogic = new MstGroupLogicImpl();
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
			String keySession = req.getParameter(Constant.KEY_SESSION);
			UserInfor userInfor = (UserInfor) req.getSession().getAttribute(keySession);
			// check userInfor exist in session
			if (userInfor == null) {
				Common.processSystemError(req, resp, Constant.NOT_FOUND_USER);
				return;
			}
			//get userId in userInfor 
			int userId = userInfor.getUserId();
			if (userId > 0) {
				//check exist in database
				boolean checkExist = tblUserLogic.checkExistTblUserById(userId);
				if (!checkExist) {
					Common.processSystemError(req, resp, Constant.NOT_FOUND_USER);
					return;
				}
			}
			//get group_name for userInfor
			MstGroup mstGroup = mstGroupLogic.getGroupById(userInfor.getGroupId());
			userInfor.setGroupName(mstGroup.getGroupName());
			String codeLevel = userInfor.getCodeLevel();
			//if check have level japan then set name_level into userInfor
			if (codeLevel != null && !Constant.EMPTY_STRING.equals(codeLevel) && !Constant.ZERO.equals(codeLevel)) {
				MstJapan mstJapan = mstJapanLogic.getMstJapanByCodeLevel(userInfor.getCodeLevel());
				userInfor.setNameLevel(mstJapan.getNameLevel());
			}
			// url submit in ADM004
			StringBuffer urlSubmit = new StringBuffer();
			String urlActionSubmit = userId > 0 ? Constant.URL_EDIT_USER_OK : Constant.URL_ADD_USER_OK;
			urlSubmit.append(req.getContextPath()).append(urlActionSubmit);
			// url back of button back in ADM004
			StringBuffer urlBack = new StringBuffer();
			String urlActionBack = userId > 0 ? Constant.URL_EDIT_USER_INPUT : Constant.URL_ADD_USER_INPUT;
			urlBack.append(req.getContextPath()).append(urlActionBack).append("?type=").append(Constant.TYPE_ADM004)
					.append("&key=").append(keySession);
			req.setAttribute("urlSubmit", urlSubmit.toString());
			req.setAttribute("urlBack", urlBack.toString());
			req.setAttribute("keySession", keySession);
			req.setAttribute("userInfor", userInfor);
			req.setAttribute("method", Constant.METHOD_POST);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM004);
			dispatcher.forward(req, resp);// forward to page jsp
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
			String keySession = req.getParameter(Constant.KEY_SESSION);
			UserInfor userInfor = (UserInfor) req.getSession().getAttribute(keySession);
			boolean success = false;
			String type = Constant.ERROR;
			StringBuffer urlNotification = new StringBuffer(req.getContextPath());
			// check userInfor not null
			if (userInfor != null) {
				int userId = userInfor.getUserId();
				if (userId > 0) {// in case update user
					// check exist user in database
					boolean checkExist = tblUserLogic.checkExistTblUserById(userId);
					// in case exist user then update
					if (checkExist) {
						success = tblUserLogic.editUser(userInfor);// call user logic update
						type = Constant.UPDATE_SUCCESS;
					} else {
						success = false;
					}
				} else {// in case create user
					success = tblUserLogic.createUser(userInfor);
					type = Constant.INSERT_SUCCESS;
				}
				req.getSession().removeAttribute(keySession);
			}
			urlNotification.append(Constant.URL_SUCCESS).append("?").append(Constant.TYPE).append("=");
			type = success ? type : Constant.ERROR;
			urlNotification.append(type);
			resp.sendRedirect(urlNotification.toString());
		} catch (Exception e) {
			Common.processSystemError(req, resp, Constant.ERROR);
		}
	}
}
