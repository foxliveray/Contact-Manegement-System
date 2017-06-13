package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import util.AppException;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserServlet() {
		super();
		// TODO Auto-generated constructor stub
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		int userId = Integer.valueOf(request.getParameter("userId"));

		boolean flag = false;
		String message = "";
		User user = new User();
		UserService userService = new UserService();

		if (userName == "" || password == "" || password2 == "") {
			message = "User name, password, and the repeated password can not be empty!";
		} else if (!password2.equals(password)) {
			message = "Repeat password and password should keep consistent!";
		} else {
			user.setId(userId);
			user.setName(userName);
			user.setPassword(password);
			try {
				flag = userService.editUser(user);
				if (flag) {
					message = "Registration Successful!";
				} else {
					message = "Registration failed!";
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "System exception!";
				response.sendRedirect("/ToError");
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("ToUserListServlet").forward(request, response);
		}
	}

}
