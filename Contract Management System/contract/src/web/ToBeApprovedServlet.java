package web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConBusiModel;
import service.ContractService;
import util.AppException;

/**
 * Access page of contract to be approved 待审批界面访问处理
 */
public class ToBeApprovedServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = null;
		session = request.getSession();

		Integer userId = (Integer) session.getAttribute("userId");
		session.setAttribute("userId", 1);//

		// if (userId == null) {
		// response.sendRedirect("toLogin");
		// } else {
		int toPage = Integer.parseInt(request.getParameter("toPage"));
		int trPerPage = Integer.parseInt(request.getParameter("trPerPage"));
		request.setAttribute("toPage", toPage);
		request.setAttribute("trPerPage", trPerPage);
		try {
			ContractService conService = new ContractService();
			List<ConBusiModel> conList = new ArrayList<ConBusiModel>();

			conList = conService.getDshphtList(1);
			request.setAttribute("contractList", conList);

			request.getRequestDispatcher("/dshphtList.jsp").forward(request, response);
		} catch (AppException e) {
			e.printStackTrace();
			// Redirect to the exception page
			response.sendRedirect("toError");
		}
		// }
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
