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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			// get param
			String type = request.getParameter(Constant.TYPE);
			HttpSession session = request.getSession();// get session
			if (type == null) {
				
			}
			Map<String, String> dataSession = (HashMap<String, String>) Common.getSession(session,
					Constant.SESSION_CONDITION_STORE);// get condition get user in session
			int page = 1;// default page
			if (type == null) {// first request then set params sort and default sort type
				Common.remoteSession(session, Constant.SESSION_CONDITION_STORE);//remote session
				dataSession = new HashMap<>();
				dataSession.put(Constant.SORT_TYPE, Constant.SORT_BY_FULL_NAME);
				dataSession.put(Constant.SORT_BY_FULL_NAME, Constant.ASC);
				dataSession.put(Constant.SORT_BY_CODE_LEVEL, Constant.ASC);
				dataSession.put(Constant.SORT_BY_END_DATE, Constant.DESC);
				Common.storeSession(session, Constant.SESSION_CONDITION_STORE, dataSession);
			} else if (Constant.TYPE_SEARCH.equals(type)) {// in case submit form search
				String fullName = request.getParameter(UserInfor.FULL_NAME);// get param fullName
				String groupId = request.getParameter(UserInfor.GROUP_ID);// get param groupId
				dataSession.put(Constant.FULL_NAME, fullName);// put into session
				dataSession.put(Constant.GROUP_ID, groupId);
			} else if (Constant.TYPE_SORT.equals(type)) {// click into sort
				String sortType = request.getParameter(Constant.SORT_TYPE);
				dataSession.put(Constant.SORT_TYPE, sortType);
				if (Constant.SORT_BY_FULL_NAME.equals(sortType)) {// if is sort by fullName
					String sortByFullName = request.getParameter(Constant.SORT_BY_FULL_NAME);
					dataSession.put(Constant.SORT_BY_FULL_NAME, sortByFullName);
				} else if (Constant.SORT_BY_CODE_LEVEL.equals(sortType)) {// if is sort by codeLevel
					String sortByCodeLevel = request.getParameter(Constant.SORT_BY_CODE_LEVEL);
					dataSession.put(Constant.SORT_BY_CODE_LEVEL, sortByCodeLevel);
				} else if (Constant.SORT_BY_END_DATE.equals(sortType)) {// if is sort by endDate
					String sortByEndDate = request.getParameter(Constant.SORT_BY_END_DATE);
					dataSession.put(Constant.SORT_BY_END_DATE, sortByEndDate);
				}
			} else if (Constant.TYPE_PAGING.equals(type)) {// paging
				String currentPage = request.getParameter(Constant.PAGE);
				if (currentPage != null) {// get current page
					try {
						page = Integer.parseInt(currentPage);
					} catch (NumberFormatException e) {
						page = 1;
					}
				}

			}
			// add page into session
			dataSession.put(Constant.PAGE, String.valueOf(page));
			int totalUser = tblUserLogic.getTotalUsers(dataSession.get(Constant.GROUP_ID),
					dataSession.get(Constant.FULL_NAME));// get total user
			// get limit in page
			int limit = Integer.parseInt(DatabaseProperties.databaseProperties.get(ConstantProperties.LIMIT_RECORD));
			page = Integer.parseInt(dataSession.get(Constant.PAGE));//get current page
			List<Integer> listPaging = Common.getListPaging(totalUser, limit, page);// get list paging
			int offset = Common.getOffset(page, limit);// get offset of paging
			ArrayList<UserInfor> listUser = tblUserLogic.getListUsers(offset, limit,
					(String) dataSession.get(Constant.GROUP_ID), (String) dataSession.get(Constant.FULL_NAME),
					(String) dataSession.get(Constant.SORT_TYPE), (String) dataSession.get(Constant.SORT_BY_FULL_NAME),
					(String) dataSession.get(Constant.SORT_BY_CODE_LEVEL),
					(String) dataSession.get(Constant.SORT_BY_END_DATE)); // get listUser
			ArrayList<MstGroup> listGroup = mstGroupLogic.getListGroup();// get list group
			request.setAttribute("totalUser", totalUser);
			request.setAttribute("listUser", listUser);
			request.setAttribute("listGroup", listGroup);
			request.setAttribute("listPaging", listPaging);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(Constant.ADM002);
			dispatcher.forward(request, response);// forward đến trang jsp
		} catch (Exception e) {
			StringBuffer stringBuffer = new StringBuffer(request.getContextPath());
			try {
				// in case have error then send redirect to view error
				response.sendRedirect(stringBuffer.append(Constant.URL_VIEW_EROR).toString());
			} catch (IOException e1) {

			}
		}
	}
}
