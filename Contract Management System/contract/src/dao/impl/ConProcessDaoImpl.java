package dao.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.ConProcessDao;
import model.ConProcess;
import util.AppException;
import util.JDBCUtil;

/**
 * Contract process 实现层
 */
public class ConProcessDaoImpl implements ConProcessDao{
	
	/**
	 * 判断合同id是否已经存在
	 * 输入：合同id
	 * 返回true存在，false 不存在
	 * @throws AppException
	 */
	public boolean isExist(int conId) throws AppException{
		boolean flag = false;// Operation flag
		
		// Declare database connection object, pre-compiled object and result set object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement:specifies the number of contract id's records, "?" is a placeholder
			String sql = "select count(id) as n from contract_process where con_id = ? and del = 0";
				
			psmt = conn.prepareStatement(sql);// pre-compiled sql
			// Set value for the placeholder
			psmt.setInt(1, conId);
			// Execute query operation
			rs = psmt.executeQuery();
			rs.next();
			int n =  rs.getInt("n"); // Parameter "n" represents the total number of records
			if (n > 0) {
				flag = true; // Designated contract ID exist,flag is set to true
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.isExist");
		} finally {
			// Close database object operation, release resources
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * 添加一个合同信息
	 * 输入  合同进程类
	 * 返回是否成功
	 * @throws AppException
	 */
	public boolean add(ConProcess conProcess)  throws AppException{	
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and result set object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement:save contact operation process information, "?" is a placeholder
			String sql = "insert into contract_process(con_id,user_id,type,state,content) values(?,?,?,?,?)";
				
			psmt = conn.prepareStatement(sql);// pre-compiled sql
			// Set values for the placeholder
			psmt.setInt(1, conProcess.getConId());
			psmt.setInt(2, conProcess.getUserId());
			psmt.setInt(3, conProcess.getType());
			psmt.setInt(4, conProcess.getState());
			psmt.setString(5, conProcess.getContent());
		
			int result = psmt.executeUpdate();// Execute update
			
			if(result > 0){// If the affected lines greater than 0, the operation success
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.add");
		} finally {
			// Close database object operation, release resources
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * 查找所有符合条件的id
	 * 输入：conProcess
	 * 返回：id集合
	 * @throws AppException
	 */
	public List<Integer> getConIds(ConProcess conProcess) throws AppException {
		// Initialize conIds
		List<Integer> conIds = new ArrayList<Integer>();
		
		// Declare database connection object, pre-compiled object and result set object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement:query contract id according to user id,contract operation type and operation state, "?" is a placeholder
			String sql = "select con_id from contract_process " +
					"where user_id= ? and type = ? and state = ? and del=0";
				
			psmt = conn.prepareStatement(sql);// pre-compiled sql
			// Set values for the placeholder
			psmt.setInt(1, conProcess.getUserId());
			psmt.setInt(2, conProcess.getType());
			psmt.setInt(3, conProcess.getState());
			
			// Execute query operation
			rs = psmt.executeQuery();
			
			// Get information in result set by loop,and save it to conIds
			while (rs.next()) {
				conIds.add(rs.getInt("con_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.getConIds");
		} finally {
			// Close database object operation, release resources
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return conIds;
	}
	
	/**
	 * 更新状态
	 *输入：conProcess
	 *返回是否更新成功
	 * @throws AppException
	 */
	public boolean update(ConProcess conProcess) throws AppException {
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare sql:update operation status,content and time info of contract according to user id,contract id and operation type
			String sql = "update contract_process set state = ?, content = ?, time = ? " 
					+"where user_id = ? and con_id = ? and type = ?";

			// Pre-compiled sql, and set the value to parameter 
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conProcess.getState());
			psmt.setString(2, conProcess.getContent());

			// Date format is锛歽yyy-MM-dd聽hh:mm:ss聽聽聽聽聽
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			java.sql.Timestamp time = new java.sql.Timestamp(conProcess.getTime().getTime());
			df.format(time); // Formatting time
			psmt.setTimestamp(3, time);
			psmt.setInt(4, conProcess.getUserId());
			psmt.setInt(5, conProcess.getConId());
			psmt.setInt(6, conProcess.getType());
			// Execute update, return the affected rows
			int count = psmt.executeUpdate();
			
			if (count > 0) {// If affected lines greater than 0, the update is successful
				flag = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ConProcessDaoImpl.update");
		} finally {
			// Close the database operation object 
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 *查询总数
	 * 输入:conProcess
	 * 返回：总数
	 * @throws AppException
	 */
	public int getTotalCount(ConProcess conProcess) throws AppException{
		int totalCount = 0; // Initialize totalCount
		
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement:query total number of eligible records according to contract id,operation type and its processing state, "?" is a placeholder
			String sql = "select count(id) as n from contract_process "
				 +"where con_id = ? and type = ? and state = ?";
				
			psmt = conn.prepareStatement(sql);// Pre-compiled sql
			// Set value to placeholder 
			psmt.setInt(1, conProcess.getConId());
			psmt.setInt(2, conProcess.getType());
			psmt.setInt(3, conProcess.getState());
			// Execute query operation 
			rs = psmt.executeQuery();
			rs.next();
			totalCount =  rs.getInt("n");  // Parameter "n" represents the total number of records
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.getTotalCount");
		} finally {
			// Close database object operation, release resources
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return totalCount;
	}
	
	/**
	 * 查询符合条件的合同进程id
	 * 
	 * @param conId 合同号
	 * @param type 合同类型 
	 * @param 合同状态
	 * 返回合同id集合
	 * @throws AppException
	 */
	public List<Integer> getIds(int conId, int type, int state) throws AppException {
		// Initialize ids
		List<Integer> ids = new ArrayList<Integer>();
		
		// Declare database connection object, pre-compiled object and results set object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement:query contract process id set according to contract id,operation type and its corresponding operation state, "?" is a Placeholder
			String sql = "select id from contract_process " +
					"where con_id= ? and type = ? and state = ? and del=0";
				
			psmt = conn.prepareStatement(sql);// Pre-compiled sql
			// Set values for the placeholder 
			psmt.setInt(1, conId);
			psmt.setInt(2, type);
			psmt.setInt(3, state);
			
			// Execute query operation 
			rs = psmt.executeQuery();
			
			// Get information in result set by loop,and save it to ids
			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.getIds");
		} finally {
			// Close database object operation, release resources
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return ids;
	}
	
	/**
	 * 查询合同信息
	 * 
	 *输入：合同id
	 * 返回contract process类
	 * @throws AppException
	 */
	public ConProcess getById(int id) throws AppException {
		// Declare conProcess
		ConProcess conProcess = null;
		
		// Declare database connection object, pre-compiled object and result set object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Define sql:query contract process information according to contract process id
			String sql = "select id,con_id,user_id,type,state,content,time "
					+"from contract_process "
					+"where id = ? and del = 0";

			//Pre-compiled sql, and set the parameter values
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id); //Set contract id
			
			// Query result set
			rs = psmt.executeQuery();

			// Get information in result set by loop,and encapsulate to conProcess entity
			if(rs.next()) {
				conProcess = new ConProcess();
				conProcess.setId(rs.getInt("id"));
				conProcess.setConId(rs.getInt("con_id"));
				conProcess.setUserId(rs.getInt("user_id"));
				conProcess.setType(rs.getInt("type"));
				conProcess.setState(rs.getInt("state"));
				conProcess.setContent(rs.getString("content"));
				conProcess.setTime(rs.getTimestamp("time"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"dao.impl.ConProcessDaoImpl.getById");
		} finally {
			// Close database operation object 
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return conProcess;
	}
}