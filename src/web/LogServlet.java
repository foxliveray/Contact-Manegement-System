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

import model.Contract;
import model.Log;
import model.Role;
import service.database_operation;

/**
 * Servlet implementation class LogServlet
 */
@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogServlet() {
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
		database_operation d_o=new database_operation();
		Log log=new Log();
		List<Log> lists = new ArrayList<Log>();
		int if_admin=1;
		HttpSession session = null;
		// Get session by using request
		session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		String operation=request.getParameter("searchwords");
		if(operation==""||operation==null){
			lists=d_o.select_Log_by_UserId(0,userId); //��ǰһ������Ϊ1ʱ���Թ���Ա��ݲ�ѯ�����û���־��ǰһ������Ϊ����ʱ�����û�ID��ѯ���ڶ���������
		}else 
			lists=d_o.search_log_by_operation(operation);
		request.setAttribute("lists", lists);//��lists�ŵ������� 
		String curPage=request.getParameter("curPage");
		request.setAttribute("curpage", curPage);
		request.getRequestDispatcher("/log.jsp").forward(request, response);
	}

}
