package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;
import util.AppException;

/**
 * Servlet implementation class AddCustomerServlet
 */
@WebServlet("/AddCustomer")
public class AddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("customername");
		String address = request.getParameter("address");
		String tel = request.getParameter("telephone");
		String fax = request.getParameter("fax");
		String code = request.getParameter("code");
		String bank = request.getParameter("bank");
		String account = request.getParameter("account");
		
		boolean flag = false;
		String message = "";
		Customer customer = new Customer();
		CustomerService customerService = new CustomerService();
		
		if(name == "" || address == "" || tel == "" || fax == "" || code == "" || bank == "" || account == ""){
			System.out.println("---Entered incorrectly!---");
			System.out.println("Customer name, address, tel, fax, code, bank and account can not be empty!");
			message = "Customer name, address, tel, fax, code, bank and account can not be empty!";
		}
		else {
			try{
				customer.setName(name);
				customer.setAddress(address);
				customer.setTel(tel);
				customer.setFax(fax);
				customer.setCode(code);
				customer.setBank(bank);
				customer.setAccout(account);
				flag = customerService.addNewCustomer(customer);
				if (flag){
					message = "Add new customer Successful!";
					System.out.println("Add new customer Successful!");
					response.sendRedirect("Frame1.jsp"); 
				} 
				else{
					message = "Add new customer failed!";
					System.out.println("Add new customer failed!");
					request.setAttribute("message", message);
					request.getRequestDispatcher("Index.jsp").forward(request, response);
				}
			} catch (AppException e) {
				e.printStackTrace();
				message = "System exception!";
				response.sendRedirect("/ToError");
			}
		}
	}

}
