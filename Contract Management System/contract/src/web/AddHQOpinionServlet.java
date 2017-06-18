package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConProcess;
import service.ContractService;
import util.AppException;

/**
 * Servlet for countersign contract
 */
@WebServlet("/AddHQOpinionServlet")
public class AddHQOpinionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	public AddHQOpinionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * Process Post requests of countersign contract
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set the request's character encoding
		response.setContentType("text/html;charset=UTF-8");
		String docType=request.getParameter("text");
		System.out.println(docType);
		
		// Declare session
		HttpSession session = null;
		// Get session by using request
		session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");

		// If user is not login, jump to login page
		if (userId == null) {
			response.sendRedirect("ToLogin");
		} else {
			
			// Get contract id
			int conId = Integer.parseInt(request.getParameter("conId"));

			// Get countersign opinion 
			String content = request.getParameter("text");
			
			
			// Instantiate conProcess object for  encapsulates countersign information
			ConProcess conProcess = new ConProcess();
			conProcess.setConId(conId);
			conProcess.setUserId(userId);
			conProcess.setContent(content);
			
			try {
				// Initialize contractService
				ContractService contractService = new ContractService();
				// Call business logic layer to do contract countersign
				contractService.counterSign(conProcess);
				
				
				// After countersigned,redirect to page of contract to be countersigned
				//request.getRequestDispatcher("/DhqhtListServlet").forward(request,response);
				response.sendRedirect("DhqhtListServlet");
				
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
		
	}
}
