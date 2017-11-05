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
import javax.servlet.http.HttpSession;

import common.Constant;
import entity.MstGroup;
import entity.MstJapan;
import entity.UserInfor;
import logic.MstGroupLogic;
import logic.MstJapanLogic;
import logic.impl.MstGroupLogicImpl;
import logic.impl.MstJapanLogicImpl;

/**
 * class add user
 * 
 * @author LA-AM
 *
 */
@WebServlet(urlPatterns = {Constant.URL_ADD_USER_OK})
public class AddUserConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MstGroupLogic mstGroupLogic;
	private MstJapanLogic mstJapanLogic;

	/**
	 * contructer
	 */
	public AddUserConfirmController() {
		mstJapanLogic = new MstJapanLogicImpl();
		mstGroupLogic = new MstGroupLogicImpl();
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
			HttpSession session = req.getSession();
			UserInfor userInfor = (UserInfor) session.getAttribute(Constant.SESSION_USER_INFOR);
			MstGroup mstGroup = mstGroupLogic.getGroupById(userInfor.getGroupId());
			//MstJapan mstJapan = null;
			String codeLevel = userInfor.getCodeLevel();
			if (codeLevel != null && !Constant.EMPTY_STRING.equals(codeLevel) && !Constant.ZERO.equals(codeLevel)) {
				MstJapan mstJapan = mstJapanLogic.getMstJapanByCodeLevel(userInfor.getCodeLevel());
				req.setAttribute("mstJapan", mstJapan);
			}
			req.setAttribute("userInfor", userInfor);
			req.setAttribute("mstGroup", mstGroup);
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
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
