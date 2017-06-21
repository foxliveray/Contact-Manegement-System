package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Right;
import model.Role;
import service.UserService;
import util.AppException;

/**
 * Servlet implementation class AssignPermissionServlet
 */
@WebServlet("/AssignPermissionServlet")
public class AssignPermissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignPermissionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		int permissionId=Integer.valueOf( request.getParameter("permissionId"));
		int userId=Integer.valueOf( request.getParameter("userId"));
		int roleId=Integer.valueOf( request.getParameter("roleId"));
		
		boolean flag = false;
		String message = "";
		UserService userService=new UserService();
		try {
			Role role=userService.getRoleByRoleId(roleId);
			Right newRight=new Right();
			newRight.setId(permissionId);
			newRight.setRoleId(roleId);
			newRight.setDescription(role.getName());
			newRight.setUserId(userId);
			
			flag=userService.UpdatePermission(newRight);
			if (flag) {
				message = "UpdatePermission Successful!";
			} else {
				message = "UpdatePermission failed!";
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "System exception!";
			response.sendRedirect("/ToError");
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("ToPermissionListServlet").forward(request, response);
	}

}
