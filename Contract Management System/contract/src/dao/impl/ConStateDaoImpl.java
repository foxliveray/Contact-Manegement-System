package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ConStateDao;
import model.ConState;
import util.AppException;
import util.DBUtil;

/**
 * Contract status data access layer implementation class
 */
public class ConStateDaoImpl implements ConStateDao {

	/**
	 * Add contract operation status information
	 * 
	 * @param conState
	 *            Contract status object
	 * @return boolean Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(ConState conState) throws AppException {
		boolean flag = false;// Operation flag

		// Declare Connection object,PreparedStatement object and ResultSet
		// object
		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement,save contract operation status, "?"
			// is a Placeholder
			String sql = "insert into t_contract_state(con_id,type) values(?,?)";

			psmt = conn.prepareStatement(sql);// Pre-compiled sql
			// Set values for the placeholder
			psmt.setInt(1, conState.getConId());
			psmt.setInt(2, conState.getType());

			int result = psmt.executeUpdate();// Execute update

			if (result > 0) {// If affected lines greater than 0, so operation
								// success
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ConStateDaoImpl.add");
		} finally {
			// Close database operation object, release resources
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return flag;
	}

	/**
	 * Query contract ids that meet the conditions according to contract type
	 * 
	 * @param type
	 *            Operation type
	 * @return Contract ids
	 * @throws AppException
	 */
	public List<Integer> getConIdsByType(int type) throws AppException {
		// Initialize conIds
		List<Integer> conIds = new ArrayList<Integer>();

		// Declare Connection object,PreparedStatement object and ResultSet
		// object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement,query contract ids that meet the
			// conditions , "?" is a Placeholder
			String sql = "select con_id from t_contract_state where type=? and del=0";

			psmt = conn.prepareStatement(sql);// Pre-compiled sql
			// Set values for the placeholder
			psmt.setInt(1, type);

			// Execute query operation
			rs = psmt.executeQuery();

			// Get information in result set by loop,and save it to conIds
			while (rs.next()) {
				conIds.add(rs.getInt("con_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.ConStateDaoImpl.getConIdsByType");
		} finally {
			// Close database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return conIds;
	}

	/**
	 * Query contract state information according to contract id and type
	 * 
	 * @param conId Contract id
	 * @param type Operation type
	 * @return Contract state object
	 * @throws AppException
	 */
	public ConState getConState(int conId, int type) throws AppException {
		// Declare conState
		ConState conState = null;

		// Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Define SQL statement: query contract state information according to the contract id and type
			String sql = "select id,con_id,type,time " + "from t_contract_state "
					+ "where con_id = ? and type = ? and del = 0";

			// Pre-compiled sql, and set the parameter values
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conId); // Set contract id
			psmt.setInt(2, type);

			// Query result set
			rs = psmt.executeQuery();

			// Get information in result set by loop,and encapsulated into conState object
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
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return conState;
	}

	/**
	 * Judgement records in contract table  according to contract id and type
	 * Judgement though the statistics of the total eligible records
	 * If total number of records is greater than 0, the record exist, return true, otherwise the record does not exist, returns false
	 * 
	 * @param con_id Countract id
	 * @param type Operation type
	 * @return boolean Exist return true,otherwise return false
	 * @throws AppException
	 */
	public boolean isExist(int con_id, int type) throws AppException {
		boolean flag = false;// Operation flag
		
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement,query total number of eligible records according to contract id and operation type, "?" is a Placeholder
			String sql = "select count(id) as n from t_contract_state "
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
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		
		return flag;
	}

}
