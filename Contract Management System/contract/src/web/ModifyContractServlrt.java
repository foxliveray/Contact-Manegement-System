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

import model.Contract;
import service.ContractService;
import util.AppException;

/**
 * Servlet implementation class ModifyContractServlrt
 */
@WebServlet("/ModifyContractServlrt")
public class ModifyContractServlrt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyContractServlrt() {
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
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Integer conId=Integer.parseInt(request.getParameter("conId"));
		String name = request.getParameter("name1");
		String customer = request.getParameter("customer");
		String content = request.getParameter("content");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		ContractService contractService = new ContractService();
		Date begin = new Date();
		Date end = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			begin = dateFormat.parse(beginTime);
			end = dateFormat.parse(endTime);
			Contract contract = contractService.getContract(conId);
			contract.setCustomer(customer);
			contract.setName(name);
			contract.setBeginTime(begin);
			contract.setEndTime(end);
			contract.setContent(content);
			contractService.ModifyContract(contract);
			request.setAttribute("conId", conId);
			response.sendRedirect("ContractListForUser");
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("toError");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("toError");
		}
	}

}
