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

import model.ConProcess;
import model.Contract;
import model.Role;
import service.database_operation;

/**
 * Servlet implementation class Signed
 */
@WebServlet("/Signed")
public class Signed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signed() {
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
		List<Contract> lists = new ArrayList<Contract>();
		List<ConProcess> conlist = new ArrayList<ConProcess>();
		String searchwords=request.getParameter("searchwords");
		/*
		 * �����ȡ�û���ݺ�id
		 */
		int if_admin=1;
		HttpSession session = null;
		// Get session by using request
		session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

			
		if(searchwords==""||searchwords==null){
			lists=d_o.select_contract_after_sign(0,userId);//��һ������Ϊ0ʱ��ѯ�����û���ͬ��������ݵڶ����������û�id��ѯ
	        request.setAttribute("lists", lists);//��lists�ŵ�������	
	        
	        conlist=d_o.select_detail_after_sign(lists);//��ѯǩ����ϸ��Ϣ
	        request.setAttribute("conlist", conlist);
		}else{
			lists=d_o.search_after_sign_contract_by_user(searchwords);
	        request.setAttribute("lists", lists);//��lists�ŵ�������
	        
	        conlist=d_o.select_detail_after_sign(lists);//��ѯǩ����ϸ��Ϣ
	        request.setAttribute("conlist", conlist);
		}
       
        String curPage=request.getParameter("curPage");
		request.setAttribute("curpage", curPage);
        request.getRequestDispatcher("/Singed.jsp").forward(request, response);
	}

}
