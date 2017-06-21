package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConProcess;
import model.Contract;
import service.ContractService;
import util.AppException;

/**
 * Servlet implementation class NewFile
 */
@WebServlet("/NewFile")
public class NewFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewFile() {
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

						// Declare session
	
							// Get contract id
							  
							int conId = Integer.parseInt(request.getParameter("conId"));
							Integer userId =Integer.parseInt(request.getParameter("userId"));
							try {
							
								// Initialize contractService
								ContractService contractService = new ContractService();
								// Query contract process information according to Contract id
								
								ConProcess conProcess = contractService.getConProcesscontent(conId, userId);
								
								// Save contract process information to request
								request.setAttribute("conProcess", conProcess);
								//  Forward to detail check page
								request.getRequestDispatcher("/NewFile.jsp").forward(request, response);
								 
							} catch (AppException e) {
								e.printStackTrace();
								// Redirect to the exception page
								response.sendRedirect("toError");
							}
						
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
