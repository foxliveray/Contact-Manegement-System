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

import model.Contract;
import model.User;
import service.ContractService;
import service.UserService;
import util.AppException;

/**
 * Servlet for accessing operator assignment page
 */
@WebServlet("/ToAssignOperServlet")
public class ToAssignOperServlet extends HttpServlet {

	/**
	 * Jump to operator assignment page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	/**
	 * Process GET requests
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set the request's character encoding
		request.setCharacterEncoding("UTF-8");
		
		// Declare session
		HttpSession session = null;
		// Get session by using request
		session = request.getSession();
		//Integer userId = (Integer)session.getAttribute("userId");
		Integer userId=1;
		// If user is not login, jump to login page
		if (userId == null) {
			response.sendRedirect("toLogin");
		}else {
			// Get contract id
			int conId = Integer.parseInt(request.getParameter("conId"));
			try {
			// Initialize contractService
			ContractService contractService = new ContractService();
			// Query contract information according to contract id
			Contract contract = contractService.getContract(conId);
			
			// Initialize userService
			UserService userService = new UserService();
			
			/*
			 * Need to get user list with "countersign", "approve", "sign" permissions 
			 * Operator role have above rights, here using hard-coding way to set operator role's role id to 2,
			 * RoleId = 2 corresponding to the role's primary key who have rights as above in contract rights table
			 */
			int roleId = 2;
			// Initialize userList
			List<User> userList = new ArrayList<User>();
			// Get user list according to role id
			userList = userService.getUserListByRoleId(roleId);
			
			// Save contract to request
			request.setAttribute("contract", contract);
			// Save userList to request
			request.setAttribute("userList", userList);
			// Forward to operator assignment page
			request.getRequestDispatcher("assignOperator.jsp").forward(request,response);
			} catch (AppException e) {
				e.printStackTrace();
				//Redirect to the exception page
				//response.sendRedirect("toError");
			}
		}
	}
}
