/**
 * Copyright(C) 2017  Luvina
 * AddUserInputUser.java, 30/10/2017 phuocbv
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import common.Constant;
import entity.MstGroup;
import entity.MstJapan;
import logic.MstGroupLogic;
import logic.MstJapanLogic;
import logic.impl.MstGroupLogicImpl;
import logic.impl.MstJapanLogicImpl;

/**
 * class add user
 * 
 * @author da
 *
 */
@WebServlet(urlPatterns = Constant.URL_ADD_USER_INPUT)
public class AddUserInputUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MstJapanLogic mstJapanLogic;
	private MstGroupLogic mstGroupLogic;

	/**
	 * contructer
	 */
	public AddUserInputUser() {
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
			setDataLogic(req, resp);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM003);
			dispatcher.forward(req, resp);// forward đến trang jsp
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

	/**
	 * set value for field select box in screen ADM003
	 * 
	 * @param req
	 * @param resp
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void setDataLogic(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException {
		ArrayList<MstJapan> listJapan = mstJapanLogic.getAllMstJapan();
		ArrayList<MstGroup> listGroup = mstGroupLogic.getAllListGroups();
		List<Integer> listYear = Common.getListYear();
		List<Integer> listMonth = Common.getListMonth();
		List<Integer> listDay = Common.getListDay();
		req.setAttribute("listYear", listYear);
		req.setAttribute("listMonth", listMonth);
		req.setAttribute("listDay", listDay);
		req.setAttribute("listJapan", listJapan);
		req.setAttribute("listGroup", listGroup);
	}

	private void setDefaultValue() {

	}
}
