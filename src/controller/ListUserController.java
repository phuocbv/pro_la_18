/**
 * Copyright(C) 2017  Luvina
 * ListUserController.java, 20/10/2017 phuocbv
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Common;
import common.Constant;
import common.ConstantProperties;
import entity.MstGroup;
import entity.UserInfor;
import logic.MstGroupLogic;
import logic.TblUserLogic;
import logic.impl.MstGroupLogicImpl;
import logic.impl.TblUserLogicImpl;
import properties.DatabaseProperties;

/**
 * list user controller
 * 
 * @author da
 *
 */
@WebServlet(urlPatterns = Constant.URL_LIST_USER)
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TblUserLogic tblUserLogic;
	private MstGroupLogic mstGroupLogic;

	/**
	 * contructer
	 */
	public ListUserController() {
		tblUserLogic = new TblUserLogicImpl();
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get param
		String fullName = request.getParameter(UserInfor.FULL_NAME);
		String groupId = request.getParameter(UserInfor.GROUP_ID);
		String type = request.getParameter(Constant.TYPE);
		String currentPage = request.getParameter(Constant.PAGE);
		// String sortType = request.getParameter(Constant.SORT_TYPE);
		//
		Map<String, String> map = new HashMap<>();
		map.put(Constant.TYPE, type);
		map.put(Constant.PAGE, request.getParameter(Constant.PAGE));
		map.put(Constant.KEY_FULL_NAME, fullName);
		map.put(Constant.KEY_GROUP_ID, groupId);
		map.put(Constant.SORT_TYPE, request.getParameter(Constant.SORT_TYPE));
		map.put(Constant.SORT_BY_FULL_NAME, request.getParameter(Constant.SORT_BY_FULL_NAME));
		map.put(Constant.SORT_BY_CODE_LEVEL, request.getParameter(Constant.SORT_BY_CODE_LEVEL));
		map.put(Constant.SORT_BY_END_DATE, request.getParameter(Constant.SORT_BY_END_DATE));

		if (type == null) {

		} else if (type == Constant.TYPE_SEARCH) {

		}

		HttpSession session = request.getSession();
		// change session if fullName and groupId != null
		Common.storeSession(session, Constant.SESSION_CONDITION_STORE, map);
		int page = 1;// default page
		// get current page
		if (currentPage != null) {
			try {
				page = Integer.parseInt(currentPage);
			} catch (NumberFormatException e) {
				page = 1;
			}
		}
		// get condition search
		// Map<String, String> data = (HashMap<String, String>)
		// Common.getSession(session,
		// Constant.SESSION_CONDITION_STORE);
		// System.out.println(map);
		// if (data != null) {
		// fullName = data.get(Constant.KEY_FULL_NAME);
		// groupId = data.get(Constant.KEY_GROUP_ID);
		// }
		// get total user
		int totalUser = tblUserLogic.getTotalUsers(groupId, fullName);
		// get limit in page
		int limit = Integer.parseInt(DatabaseProperties.databaseProperties.get(ConstantProperties.LIMIT_RECORD));
		List<Integer> listPaging = Common.getListPaging(totalUser, limit, page);
		int offset = Common.getOffset(page, limit);
		// get listUser and listGroup
		ArrayList<UserInfor> listUser = tblUserLogic.getListUser(offset, limit, groupId, fullName);
		ArrayList<MstGroup> listGroup = mstGroupLogic.getListGroup();
		request.setAttribute("listUser", listUser);
		request.setAttribute("listGroup", listGroup);
		request.setAttribute("listPaging", listPaging);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM002);
		dispatcher.forward(request, response);// forward đến trang jsp
	}
}
