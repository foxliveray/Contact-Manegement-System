package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Right;
import service.UserService;
import util.AppException;

/**
 * Servlet for configuring Permission
 */
@WebServlet("/AssignPermServlet")
public class AssignPermServlet extends HttpServlet {

	/**
	 * Process request of configuring Permission
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set the request's character encoding
		request.setCharacterEncoding("UTF-8");

		// Declare session
		HttpSession session = null;
		// Get session by using request
		session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		// If user is not login, jump to login page
		if (userId == null) {
			response.sendRedirect("toLogin");
		} else {

			/*
			 * Get Permission configuration information
			 */
			// Get user id that configured permission
			int uId = Integer.parseInt(request.getParameter("userId"));
			// Get roleId
			int roleId = Integer.parseInt(request.getParameter("roleId"));
			// Create a right object,and assign value to it
			Right right = new Right();
			right.setUserId(uId);
			right.setRoleId(roleId);
			
			try {
				// Initialize userService
				UserService userService = new UserService();
				// Call business logic layer to configure permission
				userService.assignPermission(right);
				
				// After configuration,redirect to permission configuration page
				response.sendRedirect("toYhqxList");
			} catch (AppException e) {
				e.printStackTrace();
				// Redirect to the exception page
				response.sendRedirect("toError");
			}
		}
	}

	/**
	 * Process GET requests
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Call doPost() to process request
		this.doPost(request, response);
	}

}
