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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		boolean flag = false;
		String message = "";
		User user = new User();
		UserService userService = new UserService();
		
		if (name == "" || password == "" || password2 == "") {
			System.out.println("---Entered incorrectly!---");
			System.out.println("User name, password, and the repeat password can not be empty!");
			message = "User name, password, and the repeated password can not be empty!";
		} else if (!password2.equals(password)) {
			System.out.println("---Entered incorrectly!---");
			System.out.println("Repeat password and password should keep consistent!");
			message = "Repeat password and password should keep consistent!";
		} else {
			try {
				
				user.setName(name);
				user.setPassword(password);
				flag = userService.addUser(user);
				if (flag) {
					message = "Registration Successful!";
					System.out.println("Registration Successful!");
					response.sendRedirect("ToLogin");
				} else {
					message = "Registration failed!";
					System.out.println("Registration failed!");
					request.setAttribute("message", message);
					request.getRequestDispatcher("/Register.jsp").forward(request, response);
					
				}
			} catch (AppException e) {
				e.printStackTrace();
				message = "System exception!";
				response.sendRedirect("/ToError");
			}
		}

	}

}
