package dao.Impl;

import java.sql.Connection;
import util.AppException;
import util.JDBCUtil;
import model.ConState;
import dao.ConStateDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.ConState;

public class ConStateDaoImpl implements ConStateDao{
	public boolean add(ConState conState) throws AppException{	
		boolean flag = false;// Operation flag
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement,save contract operation state, "?" is a placeholder
			
			String sql = "insert into contract_state(con_id,type) values(?,?)";
				
			psmt = conn.prepareStatement(sql);// Pre-compiled sql
			// Set value for the placeholder 
			psmt.setInt(1, conState.getConId());
			psmt.setInt(2, conState.getType());
		
			int result = psmt.executeUpdate();// Execute update 
			
			if(result > 0){// If affected lines greater than 0, so operation success
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"com.ruanko.dao.impl.ContStateDaoImpl.add");
		} finally {
			// Close the database operation object, release resources
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}
	
	public List<Integer> getConIdsByType(int type) throws AppException {
		// Initialize  conIds
		List<Integer> conIds = new ArrayList<Integer>();
		
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			//Declare operation statement,query contract ids that meet the conditions , "?" is a Placeholder
			String sql = "select con_id from contract_state where type=? and del=0";
				
			psmt = conn.prepareStatement(sql);//Pre-compiled sql
			// Set values for the placeholder 
			psmt.setInt(1, type);
			
			// Execute query operation 
			rs = psmt.executeQuery();
			
			//Get information in result set by loop,and save it to conIds
			while (rs.next()) {
				conIds.add(rs.getInt("con_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConStateDaoImpl.getConIdsByType");
		} finally {
			// Close database operation object, release resources
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return conIds;
	}

	public ConState getConState(int conId, int type) throws AppException {
		// Declare conState
		ConState conState = null;

		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			//Define SQL statement: query contract state information according to the contract id and type
			String sql = "select id,con_id,type,time "
					+"from contract_state "
					+"where con_id = ? and type = ? and del = 0";

			//Pre-compiled sql, and set the parameter values
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conId); //Set contract id
			psmt.setInt(2, type);
			
			// Query result set
			rs = psmt.executeQuery();

			//Get information in result set by loop,and encapsulated into conState object
			if(rs.next()) {
				conState = new ConState();
				conState.setId(rs.getInt("id"));
				conState.setConId(rs.getInt("con_id"));
				conState.setType(rs.getInt("type"));
				conState.setTime(rs.getDate("time"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"dao.impl.ConStateDaoImpl.getByConId");
		} finally {
			// Close the database operation object, release resources
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return conState;
	}
	
	public boolean isExist(int con_id, int type) throws AppException {
		boolean flag = false;// Operation flag
		
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement,query total number of eligible records according to contract id and operation type, "?" is a Placeholder
			String sql = "select count(id) as n from contract_state "
				 +"where con_id = ? and type = ? and del = 0";
				
			psmt = conn.prepareStatement(sql);// Pre-compiled sql
			// Set values for the placeholder 
			psmt.setInt(1, con_id);
			psmt.setInt(2, type);

			// Execute query operation 
			rs = psmt.executeQuery();
			rs.next();
			int n = rs.getInt("n"); // Parameter "n" represents the total number of records
			if (n > 0) {
				flag = true;  // If record exist,flag is set to true
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConStateDaoImpl.isExist");
		} finally {
			// Close database operation object, release resources
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

}
