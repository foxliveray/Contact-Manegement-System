package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.ConProcessDao;
import model.ConProModel;
import model.ConProcess;
import util.AppException;
import util.JDBCUtil;

public class ConProcessDaoImpl implements ConProcessDao {

	/**
	 * Judgement whether the record of specified contract id is exist in the
	 * contract process table
	 * 
	 * @param conId
	 *            Contract id
	 * @return boolean Return true if exist,otherwise return false
	 * @throws AppException
	 */
	public boolean isExist(int conId) throws AppException {
		boolean flag = false; 
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "select count(id) as n from contract_process where con_id = ? and del = 0";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conId);
			rs = psmt.executeQuery();
			rs.next();
			int n = rs.getInt("n"); 
									
			if (n > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ConProcessDaoImpl.isExist");
		} finally {

			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return flag;
	}

	/**
	 * Add contract operation process information
	 * 
	 * @param conProcess
	 *            Contract process object
	 * @return boolean Return true if exist,otherwise return false
	 * @throws AppException
	 */
	public boolean add(ConProcess conProcess) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into contract_process(con_id,user_id,type,state,content) values(?,?,?,?,?)";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conProcess.getConId());
			psmt.setInt(2, conProcess.getUserId());
			psmt.setInt(3, conProcess.getType());
			psmt.setInt(4, conProcess.getState());
			psmt.setString(5, conProcess.getContent());

			int result = psmt.executeUpdate();

			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ConProcessDaoImpl.add");
		} finally {
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

	/**
	 * Query all contract id set that meet the conditions from contract process
	 * table
	 * 
	 * @param Contract
	 *            process object
	 * @return Contract id set
	 * @throws AppException
	 */
	public List<Integer> getConIds(ConProcess conProcess) throws AppException {
		List<Integer> conIds = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "select con_id from contract_process "
					+ "where user_id= ? and type = ? and state = ? and del=0";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conProcess.getUserId());
			psmt.setInt(2, conProcess.getType());
			psmt.setInt(3, conProcess.getState());

			rs = psmt.executeQuery();

			while (rs.next()) {
				conIds.add(rs.getInt("con_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ConProcessDaoImpl.getConIds");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return conIds;
	}
	
	public List<ConProModel> getNotForUserConIds(ConProcess conProcess) throws AppException {
		List<ConProModel> conList = new ArrayList<ConProModel>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "select con_id,user_id,content from contract_process "
					+ "where type = ? and state = ? and del=0";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conProcess.getType());
			psmt.setInt(2, conProcess.getState());

			rs = psmt.executeQuery();

			while (rs.next()) {
				ConProModel conProModel=new ConProModel();
				conProModel.setConId(rs.getInt("con_id"));
				conProModel.setUserId(rs.getInt("user_id"));
				conProModel.setContent(rs.getString("content"));
				conList.add(conProModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ConProcessDaoImpl.getConIds");
		} finally {

			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return conList;
	}
	
	/**
	 * Update operation status,content and time info of contract according to
	 * user id,contract id and operation type
	 * 
	 * @param userId
	 *            User id
	 * @param conId
	 *            Contract id
	 * @param type
	 *            Operation type
	 * @return boolean Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean update(ConProcess conProcess) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "update contract_process set state = ?, content = ?, time = ? "
					+ "where user_id = ? and con_id = ? and type = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conProcess.getState());
			psmt.setString(2, conProcess.getContent());

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			java.sql.Timestamp time = new java.sql.Timestamp(conProcess.getTime().getTime());
			df.format(time); 
			psmt.setTimestamp(3, time);
			psmt.setInt(4, conProcess.getUserId());
			psmt.setInt(5, conProcess.getConId());
			psmt.setInt(6, conProcess.getType());
			int count = psmt.executeUpdate();

			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ConProcessDaoImpl.update");
		} finally {
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return flag;
	}

	/**
	 * Query total number of eligible records from contract process table
	 * according to contract id,operation type and its processing state
	 * 
	 * @param con_id
	 *            Contract id
	 * @param type
	 *            Operation type
	 * @param state
	 *            State corresponding to the operation type
	 * @return Total number of eligible records
	 * @throws AppException
	 */
	public int getTotalCount(ConProcess conProcess) throws AppException {
		int totalCount = 0;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "select count(id) as n from contract_process "
					+ "where con_id = ? and type = ? and state = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conProcess.getConId());
			psmt.setInt(2, conProcess.getType());
			psmt.setInt(3, conProcess.getState());

			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt("n");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ConProcessDaoImpl.getTotalCount");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return totalCount;
	}

	/**
	 * Query contract process id set according to contract id,operation type and
	 * its corresponding operation state
	 * 
	 * @param conId
	 *            Contract id
	 * @param type
	 *            Operation type
	 * @param state
	 *            Operation state that corresponding operation type
	 * @return Contract process id set
	 * @throws AppException
	 */
	public List<Integer> getIds(int conId, int type, int state) throws AppException {
		List<Integer> ids = new ArrayList<Integer>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id from contract_process " + "where con_id= ? and type = ? and state = ? and del=0";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conId);
			psmt.setInt(2, type);
			psmt.setInt(3, state);

			rs = psmt.executeQuery();

			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ConProcessDaoImpl.getIds");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return ids;
	}

	/**
	 * Query contract process information according to contract process id
	 * 
	 * @param id
	 *            Contract id
	 * @return Contract process object
	 * @throws AppException
	 */
	public ConProcess getById(int id) throws AppException {
		ConProcess conProcess = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id,con_id,user_id,type,state,content,time "
					+"from contract_process "
					+"where id = ? and del = 0";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id); 
			rs = psmt.executeQuery();

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
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return conProcess;
	}
	
	/**  ����
	 * Query contract process information according to contract id and user id
	 * 
	 * @param id
	 *            Contract id
	 *            user id
	 * @return Contract process object
	 * @throws AppException
	 */
	public ConProcess getById2(int conId,Integer userId) throws AppException {
		ConProcess conProcess = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		System.out.println(5);
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id,con_id,user_id,type,state,content,time "
					+"from contract_process "
					+"where con_id = ? and user_id = ? and del = 0";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conId); 
			psmt.setInt(2, userId); 

			rs = psmt.executeQuery();
			if(rs.next()) {
				conProcess = new ConProcess();
				conProcess.setId(rs.getInt("id"));
				conProcess.setConId(rs.getInt("con_id"));
				conProcess.setUserId(rs.getInt("user_id"));
				conProcess.setType(rs.getInt("type"));
				conProcess.setState(rs.getInt("state"));
				conProcess.setContent(rs.getString("content"));
				conProcess.setTime(rs.getTimestamp("time"));
				System.out.println(conProcess.getConId());
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"dao.impl.ConProcessDaoImpl.getById2");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return conProcess;
	}

	/**
	 * ���ݺ�ͬid�����ͱ�־λ��״̬��־λ��ȡ��ͬ������Ϣ
	 * 
	 * @param int
	 *            contractId,int conProcess type,int conProcess state
	 * @return ConProcess Contract Process object
	 * @throws AppException
	 */
	public ConProcess getByConId_type_state(int conId, int type, int state) throws AppException {
		ConProcess conProcess = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select id,user_id,content,time " + "from contract_process "
					+ "where con_id = ? and type = ? and state = ? and del = 0";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conId); 
			psmt.setInt(2,type);
			psmt.setInt(3, state);

			rs = psmt.executeQuery();

			if (rs.next()) {
				conProcess = new ConProcess();
				conProcess.setId(rs.getInt("id"));
				conProcess.setConId(conId);
				conProcess.setUserId(rs.getInt("user_id"));
				conProcess.setType(type);
				conProcess.setState(state);
				conProcess.setContent(rs.getString("content"));
				conProcess.setTime(rs.getTimestamp("time"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ConProcessDaoImpl.getByConId_type_state");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.CloseStatement(psmt);
			JDBCUtil.closeConnection(conn);
		}

		return conProcess;
	}
}


