package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConBusiModel;
import model.ConProcess;
import service.ContractService;
import util.AppException;

/**
 * ������������ʴ���
 */
@WebServlet("/ApprovedConListServlet")
public class ApprovedConListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApprovedConListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		HttpSession session = null;
		session = request.getSession();

		Integer userId = (Integer) session.getAttribute("userId");

//		if (userId == null) {
//			response.sendRedirect("toLogin");
//		} else {
			try {
				ContractService conService = new ContractService();
				List<ConBusiModel> conList = new ArrayList<ConBusiModel>();
				List<ConProcess> shpOpinionList=new ArrayList<ConProcess>();

				conList = conService.getApprovedConList(userId);//
				shpOpinionList=conService.getSHPOpinionList(conList);
				
				request.setAttribute("contractList", conList);
				request.setAttribute("shpOpinionList", shpOpinionList);
				
				request.getRequestDispatcher("/ApprovedConList.jsp").forward(request, response);
			} catch (AppException e) {
				e.printStackTrace();
				// Redirect to the exception page
				response.sendRedirect("toError");
			}
//		}

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
