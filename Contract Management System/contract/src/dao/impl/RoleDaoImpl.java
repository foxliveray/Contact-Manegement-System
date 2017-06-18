package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RoleDao;
import model.Role;
import util.AppException;
import util.JDBCUtil;

/**
 * Role data access layer implementation class
 */
public class RoleDaoImpl implements RoleDao {

	/**
	 * 根据角色id获取角色信息
	 * 
	 * @param int id 
	 * @return Role Role object
	 * @throws AppException
	 */
	public Role getById(int id) throws AppException {
		// Declare role object
		Role role = null;
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement,query role's information based on role id, "?" is a placeholder
			String sql = "select name,description,function_ids from contractdb.role where id=? and del=0";
			
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setInt(1, id);
			// Query result set
			rs = psmt.executeQuery();
			
			// Save user's information by using Pole entity object when queried the results set 
			if (rs.next()) {
				role = new Role(); // Instantiate role object
				// Set values for role object
				role.setId(id);
				role.setName(rs.getString("name"));
				role.setDescription(rs.getString("description"));
				role.setFuncIds(rs.getString("function_ids"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RoleDaoImpl.getById");
		} finally {
			// Close the database operation object, release resources
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return role;
	}
	
	/**
	 * 查询所有角色
	 * 
	 * @return List<Role> Role object set
	 * @throws AppException
	 */
	public List<Role> getAll() throws AppException {
		// Initialiaze roleList
		List<Role> roleList = new ArrayList<Role>();
		
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement:query all role object set,"?" is a placeholder
			String sql = "select id,name,description,function_ids from contractdb.role where del = 0";
			
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();// Return result set
			// Loop to get information in result set,and save in ids
			while (rs.next()) {
				Role role = new Role(); // Instantiate role object
				// Set value to role
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDescription(rs.getString("description"));
				role.setFuncIds(rs.getString("function_ids"));
				
				roleList.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"impl.RoleDaoImpl.getAll");
		} finally {
			// Close the database operation object, release resources
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return roleList;
	}

	/**
	 *添加角色
	 * 
	 * @param Role Role object
	 * @return Return true if added successfully,otherwise return false
	 * @throws AppException
	 */
	public boolean add(Role role) throws AppException{
		boolean flag=false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "Insert into contractdb.role (name,description,function_ids) values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, role.getName());
			pstmt.setString(2, role.getDescription());
			pstmt.setString(3, role.getFuncIds());

			result = pstmt.executeUpdate();
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("doa.impl.RoleDaoImpl.add");
		} finally {
			JDBCUtil.CloseStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		
		return flag;
	}

	/**
	 *更新角色
	 * 
	 * @param Role Role object
	 * @return Return true if updated successfully,otherwise return false
	 * @throws AppException
	 */
	public boolean update(Role role) throws AppException{
		boolean flag=false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare sql:update operation status,content and time info of
			// contract according to user id,contract id and operation type
			String sql = "update contractdb.role set name = ?, description = ?, function_ids = ?" 
					+ "where id = ? ";

			// Pre-compiled sql, and set the value to parameter
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, role.getName());
			pstmt.setString(2, role.getDescription());
			pstmt.setString(3, role.getFuncIds());
			pstmt.setInt(4, role.getId());
			// Execute update, return the affected rows
			int count = pstmt.executeUpdate();

			if (count > 0) {// If affected lines greater than 0, the update is
							// successful
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RoleDaoImpl.update");
		} finally {
			// Close the database operation object
			JDBCUtil.CloseStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		
		return flag;
	}
	
	/**
	 *删除角色
	 * 
	 * @param int Role id
	 * @return Return true if deleted successfully,otherwise return false
	 * @throws AppException
	 */
	public boolean delete(int id) throws AppException{
		boolean flag=false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare sql:update operation status,content and time info of
			// contract according to user id,contract id and operation type
			String sql = "delete from contractdb.role " + "where id = ? ";

			// Pre-compiled sql, and set the value to parameter
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			int count = pstmt.executeUpdate();

			if (count > 0) {// If affected lines greater than 0, the update is
							// successful
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RoleDaoImpl.delete");
		} finally {
			// Close the database operation object
			JDBCUtil.CloseStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		
		return flag;
	}
}
