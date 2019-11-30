package com.patrick.session.login.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patrick.session.login.utils.AuthUtils;

/**
 * 
 * @author Patrick Pan
 *
 */
public class AuthFilter implements Filter {

	private static final String LOGIN = "login";
	private static final String LOGIN_PAGE = "login.html";
	private static final String HOME_PAGE = "home";

	private static final Logger LOGGER = LoggerFactory.getLogger("sessionLogger");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("Receive a new request.");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String requestURI = httpRequest.getRequestURI();
		LOGGER.debug("Request URI: [{}]", requestURI);

		HttpSession session = httpRequest.getSession();

		if (AuthUtils.isSessionIdValid(session.getId())) {
			if (requestURI.endsWith(LOGIN) || requestURI.endsWith(LOGIN_PAGE)) {
				// Don't need to login again.
				httpResponse.sendRedirect(HOME_PAGE);
				return;
			}

			// chain.doFilter(request, response);
			httpRequest.getRequestDispatcher(HOME_PAGE).forward(request, response);
		} else {
			session.invalidate();

			if (requestURI.endsWith(LOGIN) || requestURI.endsWith(LOGIN_PAGE)) {
				chain.doFilter(request, response);
			} else {
				httpResponse.sendRedirect(LOGIN_PAGE);
			}
		}
	}

}
