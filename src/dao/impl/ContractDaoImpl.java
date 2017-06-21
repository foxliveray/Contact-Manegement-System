package dao.Impl;

import util.AppException;
import util.JDBCUtil;
import model.Contract;
import dao.ContractDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractDaoImpl implements ContractDao {
	public boolean add(Contract contract) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into contract" + "(user_id,customer,num,name,beginTime,endTime,content) "
					+ "values(?,?,?,?,?,?,?)";

			psmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			psmt.setInt(1, contract.getUserId());
			psmt.setString(2, contract.getCustomer());
			psmt.setString(3, contract.getNum());
			psmt.setString(4, contract.getName());

			java.sql.Date beginTime = new java.sql.Date(contract.getBeginTime().getTime());
			java.sql.Date endTime = new java.sql.Date(contract.getEndTime().getTime());
			psmt.setDate(5, beginTime);
			psmt.setDate(6, endTime);
			psmt.setString(7, contract.getContent());

			psmt.executeUpdate();
			rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				contract.setId(rs.getInt(1));
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("ContractDao.add");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

	public Contract getById(int id) throws AppException {
		Contract contract = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id,name,user_id,customer,num,beginTime,endTime,content " + "from contract"
					+ " where id = ? and del = 0";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);

			rs = psmt.executeQuery();

			if (rs.next()) {
				contract = new Contract();
				contract.setId(rs.getInt("id"));
				contract.setName(rs.getString("name"));
				contract.setUserId(rs.getInt("user_id"));
				contract.setCustomer(rs.getString("customer"));
				contract.setNum(rs.getString("num"));
				contract.setBeginTime(rs.getDate("beginTime"));
				contract.setEndTime(rs.getDate("endTime"));
				contract.setContent(rs.getString("content"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ContractDaoImpl.getById");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return contract;
	}

	public List<Integer> getIdsByUserId(int userId) throws AppException {
		List<Integer> ids = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id " + "from contract " + "where user_id = ? and del = 0";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ContractDaoImpl.getIdsByUserId");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return ids;
	}

	public boolean updateById(Contract contract) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update contract set name = ?, customer = ?, beginTime = ?, endTime = ?, content = ? "
					+ "where id = ? and del = 0";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, contract.getName());
			psmt.setString(2, contract.getCustomer());
			java.sql.Date beginTime = new java.sql.Date(contract.getBeginTime().getTime());
			java.sql.Date endTime = new java.sql.Date(contract.getEndTime().getTime());
			psmt.setDate(3, beginTime);
			psmt.setDate(4, endTime);
			psmt.setString(5, contract.getContent());
			psmt.setInt(6, contract.getId());

			int count = psmt.executeUpdate();

			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ContractDaoImpl.updateById");
		} finally {
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

	public List<Integer> getIds() throws AppException {
		List<Integer> ids = new ArrayList<Integer>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id " + "from contract " + "where del = 0";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ContractDaoImpl.getIdsByUserId");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return ids;
	}
}
