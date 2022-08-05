package com.dungnv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dungnv.dao.LoginDAO;
import com.dungnv.model.LoginBean;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final Long serialVersionUID = 1L;
	private LoginDAO loginDAO;
	
	@Override
	public void init() throws ServletException {
		loginDAO = new LoginDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		if (loginDAO.validate(loginBean)) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("loginsuccess.jsp");
			
			requestDispatcher.forward(req, resp);
		} else {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(req, resp);

		}
		
	}
} 

