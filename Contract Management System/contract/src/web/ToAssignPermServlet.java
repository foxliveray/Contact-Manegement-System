package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PermissionBusiModel;
import model.Role;
import service.UserService;
import util.AppException;

/**
 * Servlet for accessing permission configuration page
 */
@WebServlet("/ToAssignPermServlet")
public class ToAssignPermServlet extends HttpServlet {

	/**
	 * Jump to permission configuration page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set character set of request to "UTF-8"
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		// Declare session
		HttpSession session = null;
		// Get session by using request object
		session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// If the user is not login, then jump to login page
		if (userId == null) {
			response.sendRedirect("toLogin");
		} else {

			// Get user id, user name and role id to configure permission
			int uId = Integer.parseInt(request.getParameter("userId"));
			String userName = (String)request.getParameter("uName");
			int roleId = Integer.parseInt(request.getParameter("roleId"));
			// Save user permission information to permission business entity object
			// Initialize permission business entity class
			PermissionBusiModel permission = new PermissionBusiModel();
			permission.setUserId(uId);
			permission.setUserName(userName);
			permission.setRoleId(roleId);
			
			// Save permission to request
			request.setAttribute("permission", permission);
			
			try {
				// nitialize userService
				UserService userService = new UserService();
				// Initialize roleList
				List<Role> roleList = new ArrayList<Role>();
				// Call business logic layer to get all role list
				roleList = userService.getRoleList();

				// Save roleList to request
				request.setAttribute("roleList", roleList);
				// Forward to permission configuration page
				request.getRequestDispatcher("/assignPermission.jsp").forward(
						request, response);
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
