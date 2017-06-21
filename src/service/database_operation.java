package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

import model.ConProcess;
import model.Contract;
import model.Log;


public class database_operation {
	public Connection getConn(){
		 Connection conn=null;
		 try{
			 try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     conn=DriverManager.getConnection(
		                "jdbc:mysql://127.0.0.1:3306/contractdb?useSSL=false", //?useSSL=false
		                "root","qy88721518"); 
			}catch(SQLException e){
				e.printStackTrace();
			}
		 return conn;
	}
	
	//锟斤拷询全锟斤拷未签锟斤拷锟斤拷同
	public List<Contract> select_contract_for_sign(){
		Connection con=getConn();
		ResultSet rs;
		ResultSetMetaData md;
		List<Contract> list = new ArrayList<Contract>();
		String sql = "select * from contract,contract_state,contract_process where contract.id=contract_state.con_id and contract_process.type = 3 and contract_process.state=0 and contract_state.type=4";
		PreparedStatement pstmt=null;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    md = rs.getMetaData();
		    int columnCount = md.getColumnCount();
		    while (rs.next()) {
		        Contract contract = new Contract();
		        contract.setId(rs.getInt("id"));
		        contract.setName(rs.getString("name"));
		        contract.setCustomer(rs.getString("customer"));
		        contract.setContent(rs.getString("content"));
		        contract.setNum(rs.getString("num"));
		        contract.setBeginTime(rs.getTime("beginTime"));
		        contract.setEndTime(rs.getTime("endTime"));
		        contract.setUserId(rs.getInt("user_id"));
		        contract.setDel(rs.getInt("del"));
		        list.add(contract);
		    }
			pstmt.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	//锟斤拷询全锟斤拷锟斤拷签锟斤拷锟斤拷同,锟斤拷if_select_all为0时锟斤拷询锟斤拷锟斤拷锟矫伙拷锟侥猴拷同锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷没锟絠d锟斤拷询
		public List<Contract> select_contract_after_sign(int if_select_all,int userid){
			Connection con=getConn();
			ResultSet rs;
			ResultSetMetaData md;
			List<Contract> list = new ArrayList<Contract>();
			String sql = "";
			if(if_select_all==0)
			    sql="select * from contract,contract_state,contract_process where contract.id=contract_state.con_id and contract_process.con_id=contract.id and contract_state.type=5 and contract_process.type=3";
			else
				sql="select * from contract,contract_state,contract_process where contract.id=contract_state.con_id and contract_process.con_id=contract.id and contract_state.type=5 and contract_process.type=3 and contract.user_id='"+userid+"'";
			PreparedStatement pstmt=null;
			try{
				pstmt = (PreparedStatement)con.prepareStatement(sql); 
				rs = pstmt.executeQuery();
			    md = rs.getMetaData();
			    int columnCount = md.getColumnCount();
			    while (rs.next()) {
			        Contract contract = new Contract();
			        contract.setId(rs.getInt("id"));
			        contract.setName(rs.getString("name"));
			        contract.setCustomer(rs.getString("customer"));
			        contract.setContent(rs.getString("content"));
			        contract.setNum(rs.getString("num"));
			        contract.setBeginTime(rs.getTime("beginTime"));
			        contract.setEndTime(rs.getTime("endTime"));
			        contract.setUserId(rs.getInt("user_id"));
			        contract.setDel(rs.getInt("del"));
			        list.add(contract);
			    }
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return list;
		}
		
		public ConProcess select_detail_by_conid(int id){
			Connection con = getConn();
			ConProcess cp = new ConProcess();
			ResultSet rs;
			ResultSetMetaData md;
			PreparedStatement pstmt=null;
			try{
				String sql = "select * from contract_process,contract_state where contract_process.con_id='"+id+"' and contract_state.con_id=contract_process.con_id and contract_state.type=5 and contract_process.type=3";
				pstmt = (PreparedStatement)con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			    md = rs.getMetaData();
		    	cp.setId(rs.getInt("id"));
		    	cp.setContent(rs.getString("content"));
		    	cp.setUserId(rs.getInt("user_id"));
		    	cp.setDel(rs.getInt("del"));
		    	cp.setType(rs.getInt("type"));
		    	cp.setTime(rs.getTimestamp("time"));	
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return cp;
		}
		
		
		//锟斤拷锟斤拷锟斤拷签锟斤拷锟斤拷同锟斤拷ID锟斤拷询签锟斤拷锟斤拷息
		public List<ConProcess> select_detail_after_sign(List<Contract> conlist){
			Connection con=getConn();
			List<ConProcess> list = new ArrayList<ConProcess>();
			ResultSet rs;
			ResultSetMetaData md;
			PreparedStatement pstmt=null;
			try{
				for(Contract c:conlist){
					//Contract contract = new Contract();
					String sql = "select * from contract_process,contract_state where contract_process.con_id="+c.getId()+" and contract_state.con_id=contract_process.con_id and contract_state.type=5 and contract_process.type=3 and contract_process.state=1";
					pstmt = (PreparedStatement)con.prepareStatement(sql);                                                                                       //and t_contract_Process.process_type=3
					rs = pstmt.executeQuery();
				    md = rs.getMetaData();
				    while(rs.next()){
				    	ConProcess cp = new ConProcess();
				    	cp.setId(rs.getInt("id"));
				    	cp.setContent(rs.getString("content"));
				    	cp.setUserId(rs.getInt("user_id"));
				    	cp.setDel(rs.getInt("del"));
				    	cp.setType(rs.getInt("type"));
				    	cp.setTime(rs.getTimestamp("time"));
				        list.add(cp);
				    }
				}
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return list;
		}
		
		public List<Contract> search_after_sign_contract_by_user(String search_words){
			String sql = "select * from contract,contract_state where contract.id=contract_state.con_id and contract_state.con_type=5 and contract_process.type=3 and contract.name like '%"+search_words+"%'";
			Connection con=getConn();
			ResultSet rs ;
			ResultSetMetaData md;
			List<Contract> list = new ArrayList<Contract>();
			PreparedStatement pstmt;
			try{
				pstmt = (PreparedStatement)con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			    md = rs.getMetaData();
			    while (rs.next()) {
			        Contract contract = new Contract();
			        contract.setId(rs.getInt("id"));
			        contract.setName(rs.getString("name"));
			        contract.setCustomer(rs.getString("customer"));
			        contract.setContent(rs.getString("content"));
			        contract.setNum(rs.getString("num"));
			        contract.setBeginTime(rs.getTime("beginTime"));
			        contract.setEndTime(rs.getTime("endTime"));
			        contract.setUserId(rs.getInt("user_id"));
			        contract.setDel(rs.getInt("del"));
			        System.out.println(contract.getName());
			        list.add(contract);
			    }
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return list;
		}
	
	//锟斤拷同签锟斤拷锟斤拷珊螅锟斤拷泻锟酵拷锟斤拷锟阶刺拷锟斤拷锟酵拷锟斤拷锟斤拷锟斤拷痰母锟斤拷锟�
	public void update_after_sign(ConProcess conProcess){
		Connection con=getConn();
		int i=0;
		Date now = new Date(); 
		java.sql.Timestamp date=null;
		date=new java.sql.Timestamp(now.getTime());
	
		String sql2="update contract_process set state=1,content='"+conProcess.getContent()+"' where type=3 and con_id="+conProcess.getConId();
		String sql_add_process="insert into contract_state(con_id,type,time)values(?,?,?)";
		PreparedStatement pstmt1,pstmt;
		try{
			pstmt1=(PreparedStatement)con.prepareStatement(sql2);
			pstmt1.executeUpdate();
			pstmt=(PreparedStatement)con.prepareStatement(sql_add_process);
			pstmt.setInt(1, conProcess.getConId());
			pstmt.setInt(2, conProcess.getType());
			pstmt.setTimestamp(3,(Timestamp)date);
			pstmt.executeUpdate();
		    con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	//签锟斤拷时锟斤拷锟斤拷锟斤拷
	public List<Contract> search_by_user(String search_words){
		String sql = "select * from contract,contract_state where contract.id=contract_state.con_id and contract_state.con_type=4 and contract.name like '%"+search_words+"%'";
		Connection con=getConn();
		ResultSet rs ;
		ResultSetMetaData md;
		List<Contract> list = new ArrayList<Contract>();
		PreparedStatement pstmt;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    md = rs.getMetaData();
		    while (rs.next()) {
		        Contract contract = new Contract();
		        contract.setId(rs.getInt("id"));
		        contract.setName(rs.getString("name"));
		        contract.setCustomer(rs.getString("customer"));
		        contract.setContent(rs.getString("content"));
		        contract.setNum(rs.getString("num"));
		        contract.setBeginTime(rs.getTime("beginTime"));
		        contract.setEndTime(rs.getTime("endTime"));
		        contract.setUserId(rs.getInt("user_id"));
		        contract.setDel(rs.getInt("del"));
		        System.out.println(contract.getName());
		        list.add(contract);
		    }
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	//锟叫讹拷锟斤拷志锟斤拷锟斤拷欠锟斤拷锟节ｏ拷锟斤拷锟斤拷锟斤拷锟津创斤拷
	public int if_table_log_exist(){
		String sql = "create table if not exists t_log("
				+ "id int primary key auto_increment not null,"
				+ "userid int,"
				+ "content text,"
				+ "time datetime,"
				+ "del int,"
				+ "foreign key (userid) references user(id) )engine=InnoDB default charset=utf8;";
		Connection con=getConn();
		PreparedStatement pstmt;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			pstmt.executeUpdate();
		    con.close();
		}catch(SQLException e){
			e.printStackTrace();
			return 1;
		}
		
		return 0;
	}
	
	
	//锟斤拷锟斤拷一锟斤拷锟斤拷志
	public int insert_log(Log log){
		String sql="insert into t_log(userid,content,time,del)values(?,?,?,?)";
		if_table_log_exist();
		Connection con=getConn();
		PreparedStatement pstmt;
		
		Date now = new Date(); 
		java.sql.Timestamp date=null;
		date=new java.sql.Timestamp(now.getTime());
		
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			pstmt.setInt(1,log.getUserId());
			pstmt.setString(2, log.getContent());
			pstmt.setTimestamp(3,(Timestamp)date);
			pstmt.setInt(4, log.getDel());
			pstmt.executeUpdate();
		    con.close();
		}catch(SQLException e){
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	
	
	//if_select_all,锟斤拷值为1时锟斤拷询锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷志锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷没锟絀d锟斤拷询
		public List<Log> select_Log_by_UserId(int if_select_all,int id){
			Connection con=getConn();
			ResultSet rs;
			ResultSetMetaData md;
			List<Log> list = new ArrayList<Log>();
			String sql="";
			if(if_select_all==0)
				sql="select * from t_log";
			else
				sql = "select * from t_log where t_log.userid='"+id+"'";
			PreparedStatement pstmt=null;
			try{
				pstmt = (PreparedStatement)con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			    md = rs.getMetaData();			    
			    while (rs.next()) {
			        Log log = new Log();
			        log.setId(rs.getInt("Id"));
			        log.setContent(rs.getString("content"));
			        log.setTime(rs.getTimestamp("time"));
			        log.setUserId(rs.getInt("userid"));
			        log.setDel(rs.getInt("del"));
			        list.add(log);
			    }
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return list;
		}
		
		public List<Log> search_log_by_operation(String operation){
			Connection con=getConn();
			ResultSet rs;
			ResultSetMetaData md;
			List<Log> list = new ArrayList<Log>();
			String sql="";
			sql = "select * from t_log,user where t_log.content like '%"+operation+"%'";
			PreparedStatement pstmt=null;
			try{
				pstmt = (PreparedStatement)con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			    md = rs.getMetaData();			    
			    while (rs.next()) {
			        Log log = new Log();
			        log.setId(rs.getInt("id"));
			        log.setContent(rs.getString("content"));
			        log.setTime(rs.getTimestamp("time"));
			        log.setUserId(rs.getInt("userid"));
			        log.setDel(rs.getInt("del"));
			        list.add(log);
			    }
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return list;
		}
}

