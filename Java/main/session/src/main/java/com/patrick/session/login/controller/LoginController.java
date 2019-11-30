package com.patrick.session.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.patrick.session.login.utils.AuthUtils;

/**
 * 
 * @author Patrick Pan
 *
 */
@Controller
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger("sessionLogger");

	@PostMapping("/login")
	public void doLogin(
			@RequestParam(name = "username", required = false, defaultValue = "defaultUsername") String username,
			@RequestParam(name = "password", required = false, defaultValue = "defaultPassword") String password,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.debug("Receive parameter: username = [{}]", username);
		LOGGER.debug("Receive parameter: password = [{}]", password);

		HttpSession session = req.getSession();
		session.setAttribute("username", username);
		AuthUtils.addSessionId(session.getId());

		resp.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		PrintWriter writer = resp.getWriter();
		writer.println(String.format("Hi, %s! You have logined successfully and you can visit '/home'.", username));
		writer.flush();
	}
}
