package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contract;
import service.ContractService;
import util.AppException;

/**
 * Servlet implementation class ToModifyServlet
 */
@WebServlet("/ToModifyServlet")
public class ToModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Integer conId=Integer.parseInt(request.getParameter("conId"));

		ContractService contractService = new ContractService();
		try {

			Contract contract = contractService.getContract(conId);
			String s=String.valueOf(conId);
			request.setAttribute("conId", s);
			request.setAttribute("name1", contract.getName());
			request.setAttribute("customer", contract.getCustomer());
			request.setAttribute("beginTime", contract.getBeginTime().toString());
			request.setAttribute("endTime", contract.getEndTime().toString());
			request.setAttribute("content", contract.getContent());
			request.getRequestDispatcher("/ModifyContract.jsp").forward(request, response);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("toError");
		}
	}

}
