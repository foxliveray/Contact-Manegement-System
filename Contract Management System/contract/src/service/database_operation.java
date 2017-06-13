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
		                "root","123456"); 
			}catch(SQLException e){
				e.printStackTrace();
			}
		 return conn;
	}
	
	//查询全部未签订合同
	public List<Contract> select_contract_for_sign(){
		Connection con=getConn();
		ResultSet rs;
		ResultSetMetaData md;
		List<Contract> list = new ArrayList<Contract>();
		String sql = "select * from contract,contract_state where contract.id=contract_state.con_id and contract_state.type=4";
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
	
	//查询全部已签订合同
		public List<Contract> select_contract_after_sign(){
			Connection con=getConn();
			ResultSet rs;
			ResultSetMetaData md;
			List<Contract> list = new ArrayList<Contract>();
			String sql = "select * from contract,contract_state where contract.id=contract_state.con_id and contract_state.type=5";
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
		
		public List<Contract> search_after_sign_contract_by_user(String search_words){
			String sql = "select * from contract,contract_state where contract.id=contract_state.con_id and contract_state.type=5 and contract.name like '%"+search_words+"%'";
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
	
	//合同签订完成后，进行合同操作状态及合同操作流程的更新
	public void update_after_sign(ConProcess conProcess){
		Connection con=getConn();
		int i=0;
		Date now = new Date(); 
		java.sql.Timestamp date=null;
		date=new java.sql.Timestamp(now.getTime());
	
		String sql="update contract_state set type=5 where con_id="+conProcess.getConId();
		String sql_add_process="insert into contract_process(con_id,type,state,user_id,content,time,del)values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt,pstmt1;
		try{
			pstmt=(PreparedStatement)con.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt1=(PreparedStatement)con.prepareStatement(sql_add_process);
			pstmt.setInt(1, conProcess.getConId());
			pstmt.setInt(2, conProcess.getState());
			pstmt.setInt(3,conProcess.getUserId());
			pstmt.setString(4, conProcess.getContent());
			pstmt.setTimestamp(5,(Timestamp)date);
			pstmt.setInt(6,conProcess.getDel());
			pstmt1.executeUpdate();
		    con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//签订页面查询框	
	public List<Contract> search_by_user(String search_words){
		String sql = "select * from contract,contract_state where contract.id=contract_state.con_id and contract_state.type=4 and contract.name like '%"+search_words+"%'";
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
	
	
	//判断日志表格是否存在，不存在则创建
	public int if_table_log_exist(){
		String sql = "create table if not exists log("
				+ "id int primary key auto_increment not null,"
				+ "userid int,"
				+ "content text,"
				+ "time datetime,"
				+ "del int,"
				+ "foreign key (userid) references t_user(id) )engine=InnoDB default charset=utf8;";
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
	
	
	//插入一条日志
	public int insert_log(Log log){
		String sql="insert into log(user_id,content,time,del)values(?,?,?,?)";
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
	
	
	
	//if_select_all,当值为1时查询所有用户的日志，否则根据用户Id查询
		public List<Log> select_Log_by_UserId(int if_select_all,int id){
			Connection con=getConn();
			ResultSet rs;
			ResultSetMetaData md;
			List<Log> list = new ArrayList<Log>();
			String sql="";
			if(if_select_all==1)
				sql="select * from log";
			else
				sql = "select * from log where log.user_id='"+id+"'";
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
			        log.setUserId(rs.getInt("user_id"));
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
			sql = "select * from log,user where log.content like '%"+operation+"%'";
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

