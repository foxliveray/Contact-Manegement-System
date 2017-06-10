package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConProcess;
import service.ContractService;
import util.AppException;
import util.Constant;

/**
 * Servlet implementation class AddApprovedOpinionServlet
 */
@WebServlet("/AddApprovedOpinionServlet")
public class AddApprovedOpinionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddApprovedOpinionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * 处理审批意见
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=null;
		session=request.getSession();
//		Integer userId=(Integer)session.getAttribute("userId");
		Integer userId=1;
		if(userId==null){
			response.sendRedirect("toLogin");
		}
		else{
			int conId=Integer.parseInt(request.getParameter("conId"));
			String content=request.getParameter("content");
			String standpoint=request.getParameter("standpoint");
			
			ConProcess conProcess=new ConProcess();
			conProcess.setConId(conId);
			conProcess.setUserId(userId);
			conProcess.setContent(content);
			
			if(standpoint.equals("true")){ //赞成
				conProcess.setState(Constant.DONE);
			}
			else{ //反对
				conProcess.setState(Constant.VETOED);
			}
			
			try{
				ContractService conService=new ContractService();
				
				conService.approve(conProcess);
				
				response.sendRedirect("ToBeApprovedServlet");
			}
			catch(AppException e){
				e.printStackTrace();
				
				response.sendRedirect("toError");
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.doPost(request, response);
	}

}
