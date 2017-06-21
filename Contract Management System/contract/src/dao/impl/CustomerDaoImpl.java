package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			String sql = "Select id from customer where name = ? and del = 0";
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
	 * 添加一个新客户
	 * @param Customer customer
	 * @return 返回True如果添加客户成功，否则返回false
	 * @throws AppException
	 */
	public boolean add(Customer customer) throws AppException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean flag = false;
		int result = -1;
		try{
			conn = JDBCUtil.getConnection();
			String sql = "Insert into customer (name,address,tel,fax,code,bank,account) values (?,?,?,?,?,?,?)";
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

	public ArrayList<Customer> getAllCustomer() throws AppException {
		ArrayList<Customer> customer = new ArrayList<Customer>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "Select * from customer where del = 0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Customer cus = new Customer();
				cus.setId(rs.getInt("id"));
				cus.setName(rs.getString("name"));
				cus.setAddress(rs.getString("address"));
				cus.setTel(rs.getString("tel"));
				cus.setFax(rs.getString("fax"));
				cus.setCode(rs.getString("code"));
				cus.setBank(rs.getString("bank"));
				cus.setAccout(rs.getString("account"));
				customer.add(cus);
			}
		} catch(SQLException e){
			e.printStackTrace();
			throw new AppException("dao.Impl.CustomerDaoImpl.getAllCustomer");
		} finally {
			JDBCUtil.closeResultSet(rs);
			JDBCUtil.ClosePreparedStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		return customer;
	}

	/**
	 * 根据id删除一个指定的客户
	 * @param 客户id 
	 * @return 返回True如果删除成功，否则返回false
	 * @throws AppException
	 */
	public boolean delete(Customer customer) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "Delete from customer where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customer.getId());
			int count = pstmt.executeUpdate();
			if (count > 0){
				flag = true;
			}
		} catch (SQLException e){
			e.printStackTrace();
			throw new AppException("dao.Impl.CustomerDaoImpl.delete");
		} finally {
			JDBCUtil.ClosePreparedStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

	/**
	 * 修改客户信息
	 * @param Customer customer
	 * @return 返回True如果修改成功，否则返回false
	 * @throws AppException
	 */
	public boolean update(Customer customer) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = JDBCUtil.getConnection();
			String sql = "Update customer set name = ?, address = ?, tel = ?, fax = ?, code = ?, bank = ?, account =? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getAddress());
			pstmt.setString(3, customer.getTel());
			pstmt.setString(4, customer.getFax());
			pstmt.setString(5, customer.getCode());
			pstmt.setString(6, customer.getBank());
			pstmt.setString(7, customer.getAccout());
			pstmt.setInt(8, customer.getId());
			
			int count = pstmt.executeUpdate();
			if(count > 0){
				flag = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new AppException("dao.Impl.CustomerDaoImpl.update");
		}finally{
			JDBCUtil.CloseStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		return flag;
	}

}
