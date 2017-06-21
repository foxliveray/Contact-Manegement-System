package dao;

import java.util.ArrayList;

import model.Customer;
import util.AppException;

/**
 * @author 钱洋
 * @date 2017年6月13日 下午4:38:27
 */
public interface CustomerDao {
	
	/**
	 * 区分是否有同名的客户存在
	 * @param name 	(客户名)
	 * @return 返回True如果有客户有相同的名字，否则返回false
	 * @throws AppException
	 */
	public boolean isExist(String name) throws AppException;
	
	/**
	 * 区分是否有同名的客户存在
	 * @param name 	(客户名)
	 * @return 返回True如果有客户有相同的名字，否则返回false
	 * @throws AppException
	 */
	public boolean add(Customer customer) throws AppException;
	
	/**
	 * 获取所有客户信息
	 * @return 返回客户对象List
	 * @throws AppException
	 */
	public ArrayList <Customer> getAllCustomer() throws AppException;
	
	/**
	 * 根据id删除一个指定的客户
	 * @param Customer customer对象中获取id
	 * @return 返回True如果删除成功，否则返回false
	 * @throws AppException
	 */
	public boolean delete(Customer customer) throws AppException;
	
	/**
	 * 修改客户信息
	 * @param Customer customer
	 * @return 返回True如果修改成功，否则返回false
	 * @throws AppException
	 */
	public boolean update(Customer customer) throws AppException;
}
