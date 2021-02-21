package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.utility.PathConstant;
import com.myclass.utility.UrlConstant;

@WebServlet(urlPatterns = {PathConstant.PATH_HOME, "/"})
public class HomeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8128574883945021239L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(UrlConstant.URL_HOMEPAGE)
		.forward(req, resp);
	}
}
