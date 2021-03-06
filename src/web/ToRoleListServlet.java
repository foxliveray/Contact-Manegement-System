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

import model.Role;
import service.UserService;
import util.AppException;

/**
 * Servlet implementation class ToRoleListServlet
 */
@WebServlet("/ToRoleListServlet")
public class ToRoleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToRoleListServlet() {
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
		
		HttpSession session=null;
		session=request.getSession();
		
		Integer userId=(Integer)session.getAttribute("userId");
		
//		if(userId==null){
//			response.sendRedirect("toLogin");
//		}
//		else{
			UserService userService=new UserService();
			List<Role> roleList=new ArrayList<Role>();
			
			try {
				roleList=userService.getRoleList();
				request.setAttribute("roleList", roleList);
				
				request.getRequestDispatcher("/roleList.jsp").forward(request, response);
				
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// Redirect to the exception page
				response.sendRedirect("ToError");
			}
//		}
	}

}
