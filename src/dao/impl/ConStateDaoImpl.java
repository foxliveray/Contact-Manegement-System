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
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "insert into contract_state(con_id,type) values(?,?)";
				
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conState.getConId());
			psmt.setInt(2, conState.getType());
		
			int result = psmt.executeUpdate();
			
			if(result > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"com.ruanko.dao.impl.ContStateDaoImpl.add");
		} finally {
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}
	
	public List<Integer> getConIdsByType(int type) throws AppException {
		List<Integer> conIds = new ArrayList<Integer>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select con_id from contract_state where type=? and del=0";
				
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, type);

			rs = psmt.executeQuery();

			while (rs.next()) {
				conIds.add(rs.getInt("con_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConStateDaoImpl.getConIdsByType");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return conIds;
	}

	public ConState getConState(int conId, int type) throws AppException {
		ConState conState = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id,con_id,type,time "
					+"from contract_state "
					+"where con_id = ? and type = ? and del = 0";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conId); 
			psmt.setInt(2, type);

			rs = psmt.executeQuery();

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
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return conState;
	}
	
	public boolean isExist(int con_id, int type) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select count(id) as n from contract_state "
				 +"where con_id = ? and type = ? and del = 0";
				
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, con_id);
			psmt.setInt(2, type);

			rs = psmt.executeQuery();
			rs.next();
			int n = rs.getInt("n"); 
			if (n > 0) {
				flag = true; 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConStateDaoImpl.isExist");
		} finally {

			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}
	public ConState getConStateById(int conId) throws AppException{
				ConState conState = null;

				Connection conn = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				try {
					conn = JDBCUtil.getConnection();
					String sql = "select id,con_id,type,time " + "from contract_state "
							+ "where con_id = ? and del = 0";

					psmt = conn.prepareStatement(sql);
					psmt.setInt(1, conId); 

					rs = psmt.executeQuery();
					if (rs.next()) {
						conState = new ConState();
						conState.setId(rs.getInt("id"));
						conState.setConId(rs.getInt("con_id"));
						conState.setType(rs.getInt("type"));
						conState.setTime(rs.getDate("time"));
					}

				} catch (SQLException e) {
					e.printStackTrace();
					throw new AppException("dao.impl.ConStateDaoImpl.getByConId");
				} finally {
					JDBCUtil.closeResultSet(rs);
					JDBCUtil.CloseStatement(psmt);
					JDBCUtil.closeConnection(conn);
				}
				
				return conState;
	}
}
