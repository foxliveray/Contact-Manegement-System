package dao.impl;

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
			String sql = "Select id from t_user where name = ? and del = 0";
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
			String sql = "Insert into t_user (name,password) values (?,?)";
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
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare sql:update operation status,content and time info of
			// contract according to user id,contract id and operation type
			String sql = "update t_user set name = ?, password = ? " + "where id = ? ";

			// Pre-compiled sql, and set the value to parameter
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getId());
			// Execute update, return the affected rows
			int count = pstmt.executeUpdate();

			if (count > 0) {// If affected lines greater than 0, the update is
							// successful
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.UserDaoImpl.update");
		} finally {
			// Close the database operation object
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
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare sql:update operation status,content and time info of
			// contract according to user id,contract id and operation type
			String sql = "delete from t_user " + "where id = ? ";

			// Pre-compiled sql, and set the value to parameter
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
			
			int count = pstmt.executeUpdate();

			if (count > 0) {// If affected lines greater than 0, the update is
							// successful
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.UserDaoImpl.delete");
		} finally {
			// Close the database operation object
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
		// Declare user object
		User user = null;

		// Declare database connection object, pre-compiled object and result
		// set object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement:query user information according to
			// the user id , "?" is a placeholder
			String sql = "select id,name,password " + "from t_user " + "where id = ? and del = 0";
			// pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder
			psmt.setInt(1, id);
			// Query resultSet
			rs = psmt.executeQuery();

			// Save user information in Pole entity object when queried out
			// resultSet
			if (rs.next()) {
				user = new User(); // Instantiate user objects
				// Set value to user object
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.UserDaoImpl.getById");
		} finally {
			// Close database object operation, release resources
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

		// Declare database connection object, pre-compiled object and result
		// set object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			// Create database connection
			conn = JDBCUtil.getConnection();
			// Declare operation statement:query user information according to
			// the user id , "?" is a placeholder
			String sql = "select count(*) as totalNum" + " from t_user ";
			// pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Query resultSet
			rs = psmt.executeQuery();

			// Save user information in Pole entity object when queried out
			// resultSet
			rs.next();
			totalCount = rs.getInt("totalNum");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.UserDaoImpl.getUserCount");
		} finally {
			// Close database object operation, release resources
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return totalCount;
	}
}
