package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CustomerDao;
import model.Customer;
import util.AppException;
import util.JDBCUtil;

/**
 * @author 钱洋
 * @date 2017年6月13日 下午4:43:21
 */
public class CustomerDaoImpl implements CustomerDao{

	/**
	 * 区分是否有同名的客户存在
	 * @param name 	(客户名)
	 * @return 返回True如果有客户有相同的名字，否则返回false
	 * @throws AppException
	 */
	public boolean isExist(String name) throws AppException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean flag = false;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "Select id from t_customer where name = ? and del = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()){
				flag = true;
			}
		} catch (SQLException e){
			e.printStackTrace();
			throw new AppException("dao.impl.CustomerDaoImpl.isExist");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.ClosePreparedStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

	/**
	 * 区分是否有同名的客户存在
	 * @param name 	(客户名)
	 * @return 返回True如果有客户有相同的名字，否则返回false
	 * @throws AppException
	 */
	public boolean add(Customer customer) throws AppException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean flag = false;
		int result = -1;
		try{
			conn = JDBCUtil.getConnection();
			String sql = "Insert into t_customer (name,address,tel,fax,code,bank,account) values (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getAddress());
			pstmt.setString(3, customer.getTel());
			pstmt.setString(4, customer.getFax());
			pstmt.setString(5, customer.getCode());
			pstmt.setString(6, customer.getBank());
			pstmt.setString(7, customer.getAccout());
			
			result = pstmt.executeUpdate();
			if (result > 0){
				flag = true;
			}
		} catch (SQLException e){
			e.printStackTrace();
			throw new AppException("dao.impl.CustomerDaoImpl.add");
		} finally {
			JDBCUtil.CloseStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

}
