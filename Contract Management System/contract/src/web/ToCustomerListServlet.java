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
 * Servlet implementation class ToCustomerListServlet
 */
@WebServlet("/ToCustomerListServlet")
public class ToCustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToCustomerListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = null;
		session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect("ToLogin");
		}else {
			CustomerService cus = new CustomerService();
			ArrayList<Customer> cusList = new ArrayList<Customer>();
			try {
				cusList = cus.getAllcustomerInfo();
				request.setAttribute("cusList", cusList);
				request.getRequestDispatcher("/CustomerList.jsp").forward(request, response);
			} catch (AppException e) {
				e.printStackTrace();
				response.sendRedirect("ToError");
			}
		}
	}

}
