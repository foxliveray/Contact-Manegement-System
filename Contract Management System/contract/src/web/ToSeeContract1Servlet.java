package web;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContractDao;
import dao.Impl.ContractDaoImpl;
import model.Contract;
import service.ContractService;
import util.AppException;

/**
 * Servlet implementation class ToSeeContract1Servlet
 */
@WebServlet("/ToSeeContract1Servlet")
public class ToSeeContract1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToSeeContract1Servlet() {
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
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String begin=dateFormat.format(contract.getBeginTime());
			String end=dateFormat.format(contract.getEndTime());
			request.setAttribute("conId", contract.getId());
			request.setAttribute("name1", contract.getName());
			request.setAttribute("customer", contract.getCustomer());
			request.setAttribute("beginTime", begin);
			request.setAttribute("endTime", end);
			request.setAttribute("content", contract.getContent());
			request.getRequestDispatcher("/SeeContract.jsp").forward(request, response);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("toError");
		}
	}

}
