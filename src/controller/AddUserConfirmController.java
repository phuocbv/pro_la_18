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
 * class add user ok
 * 
 * @author LA-AM
 *
 */
@WebServlet(urlPatterns = { Constant.URL_ADD_USER_OK, Constant.URL_ADD_USER_CONFIRM })
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

			if (userInfor == null) {
				StringBuffer stringBuffer = new StringBuffer(req.getContextPath());
				stringBuffer.append(Constant.URL_SUCCESS).append("?type=").append(Constant.ERROR);
				resp.sendRedirect(stringBuffer.toString());
				return;
			}
			MstGroup mstGroup = mstGroupLogic.getGroupById(userInfor.getGroupId());
			userInfor.setGroupName(mstGroup.getGroupName());
			String codeLevel = userInfor.getCodeLevel();
			// check have level japan
			if (codeLevel != null && !Constant.EMPTY_STRING.equals(codeLevel) && !Constant.ZERO.equals(codeLevel)) {
				MstJapan mstJapan = mstJapanLogic.getMstJapanByCodeLevel(userInfor.getCodeLevel());
				userInfor.setNameLevel(mstJapan.getNameLevel());
			}
			StringBuffer urlSubmit = new StringBuffer();
			urlSubmit.append(req.getContextPath()).append(Constant.URL_ADD_USER_OK);
			StringBuffer urlBack = new StringBuffer();
			urlBack.append(req.getContextPath()).append(Constant.URL_ADD_USER_INPUT).append("?type=")
					.append(Constant.TYPE_ADM004).append("&key=").append(keySession);
			req.setAttribute("urlSubmit", urlSubmit.toString());
			req.setAttribute("urlBack", urlBack.toString());
			req.setAttribute("keySession", keySession);
			req.setAttribute("userInfor", userInfor);
			req.setAttribute("method", Constant.METHOD_POST);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM004);
			dispatcher.forward(req, resp);// forward to page jsp
		} catch (Exception e) {
			Common.processSystemError(req, resp);
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
			if (userInfor != null) {
				success = tblUserLogic.createUser(userInfor);
				req.getSession().removeAttribute(keySession);
			}
			StringBuffer stringBuffer = new StringBuffer(req.getContextPath());
			stringBuffer.append(Constant.URL_SUCCESS);
			stringBuffer.append("?");
			stringBuffer.append(Constant.TYPE);
			stringBuffer.append("=");
			if (success) {
				stringBuffer.append(Constant.INSERT_SUCCESS);
			} else {
				stringBuffer.append(Constant.ERROR);
			}
			resp.sendRedirect(stringBuffer.toString());
		} catch (Exception e) {
			Common.processSystemError(req, resp);
		}
	}

}
