package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import service.CustomerService;
import util.AppException;

/**
 * Servlet for accessing draft contract page
 */
@WebServlet("/ToDraftServlet")
public class ToDraftServlet extends HttpServlet {

	/**
	 * Jump to draft contract page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Set the request's character encoding
		request.setCharacterEncoding("UTF-8");
		
		// Declare session
		HttpSession session = null;
		// Get session by using request object
		session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// If user is not login, jump to login page
		if (userId == null) {
			response.sendRedirect("ToLogin");
		}else {
			// Forward to draft page
			CustomerService cus = new CustomerService();
			ArrayList<Customer> cusList = new ArrayList<Customer>();
			try {
				cusList = cus.getAllcustomerInfo();
				for(int i=0;i<cusList.size();i++){
					System.out.println(cusList.get(i).getName());
				}
				request.setAttribute("cusList", cusList);
				request.getRequestDispatcher("/darft.jsp").forward(request, response);
			} catch (AppException e) {
				e.printStackTrace();
				response.sendRedirect("ToError");
			}
		}
	}
	
	/**
	 * Process GET requests
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// call doPost() to process request
		this.doPost(request, response);
	}

}
