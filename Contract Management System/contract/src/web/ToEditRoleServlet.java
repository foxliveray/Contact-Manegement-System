package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Role;
import service.UserService;
import util.AppException;

/**
 * Servlet implementation class ToEditRoleServlet
 */
@WebServlet("/ToEditRoleServlet")
public class ToEditRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToEditRoleServlet() {
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
		Role currentRole=null;
		try {
			currentRole=userService.getRoleByRoleId(currentRoleId);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("/ToError");
		}
		request.setAttribute("editRole", currentRole);
		request.getRequestDispatcher("/editRole.jsp").forward(request, response);
		
	}

}
