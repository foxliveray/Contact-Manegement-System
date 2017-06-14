package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PermissionBusiModel;
import model.Right;
import model.Role;
import model.User;
import service.UserService;
import util.AppException;

/**
 * Servlet implementation class ToAuthorizeServlet
 */
@WebServlet("/ToAuthorizeServlet")
public class ToAuthorizeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToAuthorizeServlet() {
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
		int currentUserId=Integer.parseInt(request.getParameter("userId"));
		PermissionBusiModel permission=new PermissionBusiModel();
		List<Role> roleList=new ArrayList<Role>();
		try {
			Right currentRight=userService.getRightByUserID(currentUserId);
			permission.setRoleId(currentRight.getRoleId());
			permission.setRoleName(currentRight.getDescription());
			permission.setUserId(currentUserId);
			User currentUser=userService.getUserById(currentUserId);
			permission.setUserName(currentUser.getName());
			
			roleList=userService.getRoleList();
			
			request.setAttribute("permissionId", currentRight.getId());
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("/ToError");
		}
		request.setAttribute("permission", permission);
		request.setAttribute("roleList", roleList);
		request.getRequestDispatcher("/assignPermission.jsp").forward(request, response);
	}

}
