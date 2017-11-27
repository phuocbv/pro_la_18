/**
 * Copyright(C) 2017  Luvina
 * LoginFilter.java, 20/10/2017 phuocbv
 */
package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Constant;

/**
 * filter login
 * 
 * @author da
 *
 */
@WebFilter(urlPatterns = Constant.URL_FILTER)
public class LoginFilter implements Filter {
	private List<String> listUrlAllow;// store list url allow

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// get relative path
		String path = req.getRequestURI().substring(req.getContextPath().length());
		// get session admin
		String loginName = (String) req.getSession().getAttribute(Constant.SESSION_LOGGINED_USER);
		StringBuffer url = new StringBuffer(req.getContextPath());

		// if path is /login
		if (Constant.URL_LOGIN.equals(path)) {
			if (loginName != null) {// if logined then direct to list user
				res.sendRedirect(url.append(Constant.URL_LIST_USER).toString());
			} else {// to login page
				chain.doFilter(request, response);
			}
		} else if (path.indexOf(Constant.FOLDER_CSS) > 0 || path.indexOf(Constant.FOLDER_IMAGES) > 0
				|| path.indexOf(Constant.FOLDER_JS) > 0) {
			// if is folder /css/, /image/, /js/ then pass
			chain.doFilter(request, response);
		} else if (loginName != null) {// in case logined
			// domain name have not in list url do then redirect to system error
			if (!listUrlAllow.contains(path)) {
				url.append(Constant.URL_SUCCESS).append("?type=").append(Constant.ERROR);
				res.sendRedirect(url.toString());
			} else {
				chain.doFilter(request, response);
			}
		} else {// if not login then redirect to path login
			res.sendRedirect(url.append(Constant.URL_LOGIN).toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		listUrlAllow = new ArrayList<String>();
		listUrlAllow.add(Constant.URL_LIST_USER);
		listUrlAllow.add(Constant.URL_LOGOUT);
		listUrlAllow.add(Constant.URL_DELETE_USER);
		listUrlAllow.add(Constant.URL_ADD_USER_INPUT);
		listUrlAllow.add(Constant.URL_ADD_USER_VALIDATE);
		listUrlAllow.add(Constant.URL_ADD_USER_CONFIRM);
		listUrlAllow.add(Constant.URL_ADD_USER_OK);
		listUrlAllow.add(Constant.URL_SHOW_DETAIL_USER);
		listUrlAllow.add(Constant.URL_SUCCESS);
		listUrlAllow.add(Constant.URL_EDIT_USER_INPUT);
		listUrlAllow.add(Constant.URL_EDIT_USER_VALIDATE);
		listUrlAllow.add(Constant.URL_EDIT_USER_CONFIRM);
		listUrlAllow.add(Constant.URL_EDIT_USER_OK);
		listUrlAllow.add(Constant.URL_CHANGE_PASSWORD);
	}

}