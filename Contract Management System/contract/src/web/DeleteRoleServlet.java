package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import util.AppException;

/**
 * Servlet implementation class DeleteRoleServlet
 */
@WebServlet("/DeleteRoleServlet")
public class DeleteRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRoleServlet() {
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
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		UserService userService=new UserService();
		int currentRoleId=Integer.parseInt(request.getParameter("selectRoleId"));
		
		try {
			userService.deleteRole(currentRoleId);
			
			request.getRequestDispatcher("ToRoleListServlet").forward(request, response);
			
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("/ToError");
		}
	}

}
