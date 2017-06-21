package dao.Impl;

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
	 * 根据id查询角色信息
	 * 
	 * @param int id 
	 * @return Role Role object
	 * @throws AppException
	 */
	public Role getById(int id) throws AppException {
		Role role = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select name,description,function_ids from contractdb.t_role where id=? and del=0";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				role = new Role();
				role.setId(id);
				role.setName(rs.getString("name"));
				role.setDescription(rs.getString("description"));
				role.setFuncIds(rs.getString("function_ids"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RoleDaoImpl.getById");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return role;
	}
	
	/**
	 * 查询所有角色对象的集合
	 * 
	 * @return List<Role> Role object set
	 * @throws AppException
	 */
	public List<Role> getAll() throws AppException {
		List<Role> roleList = new ArrayList<Role>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id,name,description,function_ids from contractdb.t_role where del = 0";
			
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				Role role = new Role(); 
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
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return roleList;
	}

	/**
	 *	添加一个角色
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
			String sql = "Insert into contractdb.t_role (name,description,function_ids) values (?,?,?)";
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
			throw new AppException("dao.impl.RoleDaoImpl.add");
		} finally {
			JDBCUtil.CloseStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		
		return flag;
	}

	/**
	 *更新一个角色
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
			conn = JDBCUtil.getConnection();
			String sql = "update contractdb.t_role set name = ?, description = ?, function_ids = ?" 
					+ "where id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, role.getName());
			pstmt.setString(2, role.getDescription());
			pstmt.setString(3, role.getFuncIds());
			pstmt.setInt(4, role.getId());

			int count = pstmt.executeUpdate();

			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RoleDaoImpl.update");
		} finally {
			JDBCUtil.CloseStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		
		return flag;
	}
	
	/**
	 *删除一个角色
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
			conn = JDBCUtil.getConnection();
			String sql = "delete from contractdb.t_role " + "where id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			int count = pstmt.executeUpdate();

			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RoleDaoImpl.delete");
		} finally {
			JDBCUtil.CloseStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		
		return flag;
	}
}
