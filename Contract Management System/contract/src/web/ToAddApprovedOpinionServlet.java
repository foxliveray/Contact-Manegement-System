package web;

import java.io.IOException;
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
 * Servlet implementation class toAddApprovedOpinionServlet
 */
@WebServlet("/ToAddApprovedOpinionServlet")
public class ToAddApprovedOpinionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToAddApprovedOpinionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 跳转至审批界面
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		HttpSession session = null;
		session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect("toLogin");
		} else {
			int conId = Integer.parseInt(request.getParameter("conId"));

			try {
				ContractService conService = new ContractService();

				Contract contract = conService.getContract(conId);
				request.setAttribute("contract", contract);

				request.getRequestDispatcher("/addSHPOpinion.jsp").forward(request, response);
			} catch (AppException e) {
				e.printStackTrace();

				response.sendRedirect("toError");
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.doPost(request, response);
	}

}
