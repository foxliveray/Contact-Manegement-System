package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConProcess;
import model.Contract;
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
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		database_operation d_o=new database_operation();
		List<Contract> lists = new ArrayList<Contract>();
		String searchwords=request.getParameter("searchwords");
		if(searchwords==""||searchwords==null){
			lists=d_o.select_contract_after_sign();
	        request.setAttribute("lists", lists);//将lists放到作用域	
		}else{
			lists=d_o.search_after_sign_contract_by_user(searchwords);
	        request.setAttribute("lists", lists);//将lists放到作用域
		}
       
        String curPage=request.getParameter("curPage");
		request.setAttribute("curpage", curPage);
        request.getRequestDispatcher("/Singed.jsp").forward(request, response);
	}

}
