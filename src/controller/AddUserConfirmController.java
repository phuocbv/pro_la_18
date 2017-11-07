/**
 * Copyright(C) 2017  Luvina
 * AddUserConfirmController.java, 30/10/2017 phuocbv
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String keySession = req.getParameter(Constant.KEY_SESSION);
			UserInfor userInfor = (UserInfor) req.getSession().getAttribute(keySession);
			if (userInfor != null) {
				MstGroup mstGroup = mstGroupLogic.getGroupById(userInfor.getGroupId());
				req.setAttribute("mstGroup", mstGroup);
				// MstJapan mstJapan = null;
				String codeLevel = userInfor.getCodeLevel();
				if (codeLevel != null && !Constant.EMPTY_STRING.equals(codeLevel) && !Constant.ZERO.equals(codeLevel)) {
					MstJapan mstJapan = mstJapanLogic.getMstJapanByCodeLevel(userInfor.getCodeLevel());
					req.setAttribute("mstJapan", mstJapan);
				}
			}
			req.setAttribute("keySession", keySession);
			req.setAttribute("userInfor", userInfor);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM004);
			dispatcher.forward(req, resp);// forward to page jsp
		} catch (Exception e) {
			StringBuffer stringBuffer = new StringBuffer(req.getContextPath());
			try {
				// in case have error then send redirect to view error
				resp.sendRedirect(stringBuffer.append(Constant.URL_VIEW_EROR).toString());
			} catch (IOException e1) {

			}
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String keySession = req.getParameter(Constant.KEY_SESSION);
			UserInfor userInfor = (UserInfor) req.getSession().getAttribute(keySession);
			boolean success = false;
			if (userInfor != null) {
				success = tblUserLogic.createUser(userInfor);
				req.getSession().removeAttribute(Constant.KEY_SESSION);
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
			e.printStackTrace();
			StringBuffer stringBuffer = new StringBuffer(req.getContextPath());
			try {
				// in case have error then send redirect to view error
				resp.sendRedirect(stringBuffer.append(Constant.URL_VIEW_EROR).toString());
			} catch (IOException e1) {

			}
		}
	}

}
