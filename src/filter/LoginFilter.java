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

import common.Common;
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
		// TODO Auto-generated method stub

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
		String loginName = (String) Common.getSession(req.getSession(), Constant.SESSION_LOGGINED_USER);
		StringBuffer stringBuffer = new StringBuffer(req.getContextPath());

		// if path is /login
		if (Constant.URL_LOGIN.equals(path)) {
			if (loginName != null) {// if logined then direct to list user
				res.sendRedirect(stringBuffer.append(Constant.URL_LIST_USER).toString());
				return;
			} else {// to login page
				chain.doFilter(request, response);
				return;
			}
		}

		// if is folder /css/, /image/, /js/ then pass
		if (path.indexOf(Constant.FOLDER_CSS) > 0 || path.indexOf(Constant.FOLDER_IMAGES) > 0
				|| path.indexOf(Constant.FOLDER_JS) > 0) {
			chain.doFilter(request, response);
			return;
		}

		// in case logined
		if (loginName != null) {
			// domain name have not in list url do then redirect to list user
			if (!listUrlAllow.contains(path)) {
				res.sendRedirect(stringBuffer.append(Constant.URL_LIST_USER).toString());
				return;
			}
			chain.doFilter(request, response);
			return;
		}

		// if not login then redirect to path login
		res.sendRedirect(stringBuffer.append(Constant.URL_LOGIN).toString());

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
		listUrlAllow.add(Constant.URL_VIEW_EROR);
		listUrlAllow.add(Constant.URL_ADD_USER_INPUT);
		listUrlAllow.add(Constant.URL_ADD_USER_CONFIRM);
	}

}