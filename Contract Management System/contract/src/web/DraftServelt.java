package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contract;
import service.ContractService;
import util.AppException;

/**
 * Servlet implementation class DraftServelt
 */
@WebServlet("/DraftServelt")
public class DraftServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DraftServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//HttpSession session = null;
		HttpSession session = null;
		// Get session by using request object
		session = request.getSession();
		Integer userId = 123;

		if (userId == null){
			//zhuce
		}else{
			String name = request.getParameter("name1");
			String customer = request.getParameter("customer");
			String content = request.getParameter("content");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			response.getWriter().append("Served at: ").append(name+" "+customer+" "+content+" "+beginTime);
			// Instantiate begin and end of java.util.Date type,for accepting transformed beginTime and endTime
			Date begin = new Date();
			Date end = new Date();
			
			// Define a date format object, transform the time of String type into java.util.Date data type 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
			// Initialize prompt message
			String message = "";
			
			try {
				begin = dateFormat.parse(beginTime);
				end = dateFormat.parse(endTime);
				
				// Build a Contract object and assign value for the object's attribute
				Contract contract = new Contract();
				contract.setId(userId);
				contract.setName(name);
				contract.setCustomer(customer);
				contract.setBeginTime(begin);
				contract.setEndTime(end);
				contract.setContent(content);
				
				// Initialize contractService
				ContractService contractService = new ContractService();
				
				// Operation success or failure, return draft page, giving prompt message
				if (contractService.draft(contract)) {
					message = "Drafting succeeded!";
					// Transform the information created now to page for display
					request.setAttribute("contract", contract);
				} else {
					message = "Drafting failure!";
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
				message = "Contract data is required. Incorrect date format";
			} catch (AppException e) {
				e.printStackTrace();
				//Redirect to the exception page
				response.sendRedirect("toError");
				return;
			}
			// Save message to request
			request.setAttribute("message", message);
			// Forward to draft page 
			request.getRequestDispatcher("draft.jsp").forward(request, response);
			
		}
		
	}

}
