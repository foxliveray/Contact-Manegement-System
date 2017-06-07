package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RightDao;
import model.Right;
import util.AppException;
import util.JDBCUtil;

/**
 * Permission data access layer implementation class
 */
public class RightDaoImpl implements RightDao {

	/**
	 * Get role id according to user id
	 * 
	 * @param userId 
	 * @return roleId 
	 * @throws AppException
	 */
	public int getRoleIdByUserId(int userId) throws AppException {
		int roleId = -1; // Initialize roleId
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement,query roleId based on userId, "?" is a placeholder
			String sql = "select role_id "
					+"from t_right "
					+"where user_id = ? and del = 0";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder '?'
			psmt.setInt(1, userId);
			// Query result set
			rs = psmt.executeQuery();
			
			// Assigned the queried role id to roleId
			if (rs.next()) {
				roleId = rs.getInt("role_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RightDaoImpl.getRoleIdByUserId");
		} finally {
			// Close the database operation object, release resources
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return roleId;
	}
	
	/**
	 * Query user id according to role id
	 * 
	 * @param roleId Role id
	 * @return Query user id that meet the meet the conditions,otherwise return null
	 * @throws AppException
	 */
	public List<Integer> getUserIdsByRoleId(int roleId) throws AppException  {
		// Initialize userIds
		List<Integer> userIds = new ArrayList<Integer>();
		
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement,query user id based on role id, "?" is a placeholder
			String sql = "select user_id from t_right where role_id = ? and del = 0";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, roleId);
			
			rs = psmt.executeQuery();// Return result set
			// Get information in result set by loop,and save in userIds
			while (rs.next()) {
				userIds.add(rs.getInt("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"dao.impl.RightDaoImpl.getUserIdsByRoleId");
		} finally {
			// Close the database operation object, release resources
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return userIds;
	}
	
	/**
	 * Get permission id according to user id
	 * 
	 * @param userId User id
	 * @return id Permission id
	 * @throws AppException
	 */
	public int getIdByUserId(int userId) throws AppException {
		int id = -1; // Initialize role object
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement:query permissionId based on userId, "?" is a placeholder
			String sql = "select id "
					+"from t_right "
					+"where user_id = ? and del = 0";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder '?'
			psmt.setInt(1, userId);
			// Query result set
			rs = psmt.executeQuery();
			
			// Assigned the queried permission id to id
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RightDaoImpl.getIdByUserId");
		} finally {
			// Close the database operation object, release resources
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return id;
	}
	
	/**
	 * Update contract content according to permission id,
	 * pass parameter though entity object
	 * 
	 * @param id permission id
	 * @return boolean Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean updateById(Right right) throws AppException {
		boolean flag = false;// Operation flag
		//Declare Connection object,PreparedStatement object
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			// Create database connection
			conn =JDBCUtil.getConnection();
			// Delare sql:update permission information according to permission id
			String sql = "update t_right set user_id = ?, role_id = ?, description = ? " 
					+"where id = ? and del = 0";

			// Pre-compiled sql, and set the value to parameter
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, right.getUserId());
			psmt.setInt(2, right.getRoleId());
			psmt.setString(3, right.getDescription());
			psmt.setInt(4, right.getId());
			
			// Execute update, return the affected rows
			int count = psmt.executeUpdate();
			
			if (count > 0) {// If affected lines greater than 0, the update is successful
				flag = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RightDaoImpl.updateById");
		} finally {
			// Close the database operation object, release resources
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * Save permission information
	 * 
	 * @param right permission object
	 * @return Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(Right right) throws AppException {
		boolean flag = false;// Operation flag
		//Declare Connection object,PreparedStatement object
		Connection conn = null; 
		PreparedStatement psmt = null;
		
		int result = -1;
		try {
			conn = JDBCUtil.getConnection();// Create database connection
			// Declare operation statement:save permission information , "?" is a placeholder
			String sql = "insert into t_right (user_id,role_id,description)"
					+ " values (?,?,?)";
			
			psmt = conn.prepareStatement(sql);// re-compiled sql
			// Set value to placeholder 
			psmt.setInt(1, right.getUserId());
			psmt.setInt(2, right.getRoleId());
			psmt.setString(3, right.getDescription());

			result = psmt.executeUpdate();// Execute update operation, return the affected rows
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RightDaoImpl.add");
		} finally {
			// Close the database operation object, release resources
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

}
