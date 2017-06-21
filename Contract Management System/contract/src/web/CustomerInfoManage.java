package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;
import util.AppException;

/**
 * Servlet implementation class CustomerInfoManage
 */
@WebServlet("/CustomerInfoManage")
public class CustomerInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerInfoManage() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String type=request.getParameter("type");
		if(type==null){
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
		else if(type.equals("delete")){
			Integer id=Integer.parseInt(request.getParameter("id"));
			CustomerService cus = new CustomerService();
			Customer customer = new Customer();
			customer.setId(id);
			try {
				if(cus.DeleteOneCustomer(customer)){
					out.write("删除成功");
				}else{
					out.write("删除失败");
				}
			} catch (AppException e) {
				e.printStackTrace();
			}
			
		}
		else if(type.equals("modify")){
			Integer id=Integer.parseInt(request.getParameter("id"));
			String name=request.getParameter("NewClientName");
			String address = request.getParameter("NewAddr");
			String tel=request.getParameter("NewTel");
			String fax=request.getParameter("NewFax");
			String code=request.getParameter("NewPostCode");
			String bank=request.getParameter("NewBankName");
			String account=request.getParameter("NewBankAccount");
			if(id!=null){
				CustomerService cus = new CustomerService();
				Customer customer = new Customer();
				customer.setId(id);
				customer.setName(name);
				customer.setAddress(address);
				customer.setTel(tel);
				customer.setFax(fax);
				customer.setCode(code);
				customer.setBank(bank);
				customer.setAccout(account);
				
				try {
					if(cus.UpdateCustomer(customer)){
						out.write("修改成功");
					}else
						out.write("修改失败");
				} catch (AppException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
