package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RightDao;
import model.PermissionBusiModel;
import model.Right;
import util.AppException;
import util.JDBCUtil;

/**
 * Permission data access layer implementation class
 */
public class RightDaoImpl implements RightDao {

	/**
	 * �����û�id��ȡ��ɫid
	 * 
	 * @param userId
	 * @return roleId
	 * @throws AppException
	 */
	public int getRoleIdByUserId(int userId) throws AppException {
		int roleId = -1; 
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select role_id " + "from t_right " + "where user_id = ? and del = 0";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				roleId = rs.getInt("role_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RightDaoImpl.getRoleIdByUserId");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return roleId;
	}

	/**
	 * ���ݽ�ɫid�����û�id
	 * 
	 * @param roleId
	 *            Role id
	 * @return Query user id that meet the meet the conditions,otherwise return
	 *         null
	 * @throws AppException
	 */
	public List<Integer> getUserIdsByRoleId(int roleId) throws AppException {
		List<Integer> userIds = new ArrayList<Integer>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "select user_id from t_right where role_id = ? and del = 0";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, roleId);

			rs = psmt.executeQuery();
			while (rs.next()) {
				userIds.add(rs.getInt("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RightDaoImpl.getUserIdsByRoleId");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return userIds;
	}

	/**
	 * �����û�id��ȡpermission id
	 * 
	 * @param userId
	 *            User id
	 * @return id Permission id
	 * @throws AppException
	 */
	public int getIdByUserId(int userId) throws AppException {
		int id = -1; 
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id " + "from t_right " + "where user_id = ? and del = 0";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RightDaoImpl.getIdByUserId");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return id;
	}

	/**
	 * �����û�id��ȡ��ѯ�û�-��ɫ��ϵ
	 * 
	 * @param userId
	 *            User id
	 * @return Right Right object
	 * @throws AppException
	 */
	public Right getRightByUserId(int userId) throws AppException {
		Right right = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * " + "from t_right " + "where user_id = ? and del = 0";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs = psmt.executeQuery();

			if (rs.next()) {
				right=new Right();
				right.setUserId(userId);
				right.setId(rs.getInt("id"));
				right.setRoleId(rs.getInt("role_id"));
				right.setDescription(rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RightDaoImpl.getIdByUserId");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return right;
	}

	/**
	 * �����û�-��ɫ��ϵ 
	 * 
	 * @param Right Right object
	 * @return boolean Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean updateById(Right right) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update t_right set user_id = ?, role_id = ?, description = ? " + "where id = ? and del = 0";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, right.getUserId());
			psmt.setInt(2, right.getRoleId());
			psmt.setString(3, right.getDescription());
			psmt.setInt(4, right.getId());

			int count = psmt.executeUpdate();

			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RightDaoImpl.updateById");
		} finally {
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

	/**
	 * �������û�-��ɫ��ϵ
	 * 
	 * @param right
	 *            permission object
	 * @return Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(Right right) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;

		int result = -1;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into contractdb.t_right (user_id,role_id,description)" + " values (?,?,?)";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, right.getUserId());
			psmt.setInt(2, right.getRoleId());
			psmt.setString(3, right.getDescription());

			result = psmt.executeUpdate();
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RightDaoImpl.add");
		} finally {
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

	/**
	 * ɾ���û�-��ɫ��ϵ
	 * 
	 * @param right
	 *            permission object
	 * @return Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean delete(int userId) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		int result = -1;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from t_right where user_id= ? ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userId);

			result = psmt.executeUpdate();
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RightDaoImpl.add");
		} finally {
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return flag;
	}
}
