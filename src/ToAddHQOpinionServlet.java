package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.AppException;

import model.Contract;
import service.ContractService;
//import service.ContractService;


/**
 * Servlet implementation class ToAddHQOpinionServlet
 */
@WebServlet("/ToAddHQOpinionServlet")
public class ToAddHQOpinionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToAddHQOpinionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
				/**
				// Declare session
				HttpSession session = null;
				// Get session by using request
				session = request.getSession();
				Integer userId = (Integer)session.getAttribute("userId");
				
				// If user is not login, jump to login page
				if (userId == null) {
					response.sendRedirect("toLogin");
				} else {
                     **/
					// Get contract id
					  
					int conId = Integer.parseInt(request.getParameter("conId"));

                    
				
					try {
					
					
				        
				       
						// Initialize contractService
						ContractService contractService = new ContractService();
						// Query contract information according to Contract id
						
						Contract contract = contractService.getContract(conId);
						

						// Save contract to request
						request.setAttribute("contract", contract.getName());
						//  Forward to countersign page
						//contract.setName("1");
						
						request.getRequestDispatcher("/addHQOpinion.jsp").forward(
								request, response);
						 System.out.println(contract.getName());
						 
						//System.out.println(contract.getName());
					} catch (AppException e) {
						e.printStackTrace();
						// Redirect to the exception page
						response.sendRedirect("toError");
					}
				
				
	        //}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Set the request's character encoding
				
}
}
