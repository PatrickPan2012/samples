package com.patrick.session.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Patrick
 *
 */
@Controller
public class ContentController {

	@RequestMapping(path = "/home", method = { RequestMethod.GET, RequestMethod.POST })
	public void showContent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		String username = req.getSession().getAttribute("username").toString();
		PrintWriter writer = resp.getWriter();
		writer.println(String.format("Hi, %s! Welcome to your home page.", username));
		writer.flush();
	}
}
