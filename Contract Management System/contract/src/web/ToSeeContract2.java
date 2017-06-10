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
 * Servlet implementation class ToSeeContract2
 */
@WebServlet("/ToSeeContract2")
public class ToSeeContract2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToSeeContract2() {
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
		Integer contractId = Integer.parseInt(request.getParameter("conId"));
	
		try {
			ContractService contractService = new ContractService();
			Contract contract = contractService.getContract(contractId);
			request.setAttribute("conId", contract.getId());
			request.setAttribute("name1", contract.getName());
			request.setAttribute("customer", contract.getCustomer());
			request.setAttribute("beginTime", contract.getBeginTime());
			request.setAttribute("endTime", contract.getBeginTime());
			request.setAttribute("content", contract.getContent());
			request.getRequestDispatcher("/SeeContract.jsp").forward(request, response);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("toError");
		}
	}

}
