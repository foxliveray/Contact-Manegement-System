package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import dao.UserDao;
import model.User;
import util.AppException;
import util.JDBCUtil;

/**
 * @author 钱洋
 * @date 2017年6月7日 上午9:50:27
 */
public class UserDaoImpl implements UserDao {

	/**
	 * 区分是否有同名的用户存在
	 * 
	 * @param name(用户名)
	 * @return 返回True如果有用户有相同的名字，否则返回false
	 * @throws AppException
	 */
	@Override
	public boolean isExist(String name) throws AppException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		boolean flag = false;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "Select id from user where name = ? and del = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.UserDaoImpl.isExist");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.ClosePreparedStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 *            (user对象实例)
	 * @return 返回True如果新用户添加成功，否则返回false
	 * @throws AppException
	 */
	@Override
	public boolean add(User user) throws AppException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean flag = false;
		int result = -1;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "Insert into user (name,password) values (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());

			result = pstmt.executeUpdate();
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("doa.impl.UserDaoImpl.add");
		} finally {
			JDBCUtil.CloseStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

	public int login(String name, String password) throws AppException {
		int userId = -1;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id from user where name = ? and password = ? and del = 0";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			if (rs.next()) {
				userId = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.ruanko.dao.impl.UserDaoImpl.login");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.ClosePreparedStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return userId;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 *            (user对象实例)
	 * @return 返回True如果用户修改成功，否则返回false
	 * @throws AppException
	 */
	public boolean update(User user) throws AppException {
		boolean flag = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update user set name = ?, password = ? " + "where id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getId());
			int count = pstmt.executeUpdate();

			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.UserDaoImpl.update");
		} finally {
			JDBCUtil.CloseStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}

		return flag;
	}

	/**
	 * 删除用户
	 * 
	 * @param user
	 *            (user对象实例)
	 * @return 返回True如果用户删除成功，否则返回false
	 * @throws AppException
	 */
	public boolean delete(User user) throws AppException {
		boolean flag = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from user " + "where id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());

			int count = pstmt.executeUpdate();

			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.UserDaoImpl.delete");
		} finally {
			JDBCUtil.CloseStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}

		return flag;
	}

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param id
	 *            User id
	 * @return User User object
	 * @throws AppException
	 */
	public User getById(int id) throws AppException {
		User user = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id,name,password " + "from user " + "where id = ? and del = 0";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.UserDaoImpl.getById");
		} finally {

			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return user;
	}

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param String
	 *            UserName
	 * @return User
	 * @throws AppException
	 */
	public User getByName(String name) throws AppException {
		User user = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id,password " + "from user " + "where name = ? and del = 0";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			rs = psmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(name);
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.UserDaoImpl.getById");
		} finally {

			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return user;
	}

	/**
	 * 获取用户总数
	 * 
	 * @return int Total count of User
	 * @throws AppException
	 */
	public int getUserCount() throws AppException {
		int totalCount = 0;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select count(*) as totalNum" + " from user ";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt("totalNum");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.UserDaoImpl.getUserCount");
		} finally {
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return totalCount;
	}
}
