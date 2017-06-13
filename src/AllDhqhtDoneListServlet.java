package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConBusiModel;
import model.ConProModel;
import model.ConProcess;
import service.ContractService;
import util.AppException;

/**
 * Servlet implementation class AllDhqhtDoneListServlet
 */
@WebServlet("/AllDhqhtDoneListServlet")
public class AllDhqhtDoneListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllDhqhtDoneListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    request.setCharacterEncoding("UTF-8");
		
		/**	
			// Declare session
			HttpSession session = null;
			// Get session by using request
			session = request.getSession();
			//Integer userId = (Integer)session.getAttribute("userId");
			
			
			// If user is not login, jump to login page
			if (userId == null) {
				response.sendRedirect("Login");
			}else {
				
				**/
				
				try {
					// Initialize contractService
					ContractService contractService = new ContractService();
					// Initialize contractList
					List<ConProModel> contractList = new ArrayList<ConProModel>();
					// Call business logic layer to get list of contract to be countersigned 
					contractList = contractService.getAllDhqhtDoneList();
					System.out.println(contractList.size());
					// Save contractList to request
					request.setAttribute("contractList", contractList);
					
					// Forward to page of contract to be countersigned
					request.getRequestDispatcher("/allDhqhtDoneList.jsp").forward(request, response);
				} catch (AppException e) {
					e.printStackTrace();
					// Redirect to the exception page
					response.sendRedirect("toError");
				}
		//	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
