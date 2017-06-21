package service;

import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;

import dao.CustomerDao;
import dao.Impl.CustomerDaoImpl;
import model.Customer;
import util.AppException;

/**
 * @author 钱洋
 * @date 2017年6月13日 下午5:03:35
 */
public class CustomerService {
	private CustomerDao customerDao = null;
	
	public CustomerService(){
		customerDao = new CustomerDaoImpl();
	}
	
	/**
	 *新客户添加
	 * @param customer Customer object
	 * @return Return true if add operation is successful, otherwise return false
	 * @throws AppException
	 */
	public boolean addNewCustomer(Customer customer) throws AppException{
		boolean flag = false;
		try{
			if(!customerDao.isExist(customer.getName())){
				flag = customerDao.add(customer);
			}
		} catch (AppException e){
			e.printStackTrace();
			throw new AppException("service.CustomerService.addNewCustomer");
		}
		return flag;
		
	}
	
	/**
	 *查询所有客户信息
	 * @return 返回客户对象List
	 * @throws AppException
	 */
	public ArrayList<Customer> getAllcustomerInfo() throws AppException{
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try{
			customers = customerDao.getAllCustomer();
		} catch (AppException e){
			e.printStackTrace();
			throw new AppException("service.CustomerService.getAllCustomerInfo");
		}
		
		return customers;
		
	}
	
	/**
	 * 根据id删除一个指定的客户
	 * @param 客户id 
	 * @return 返回True如果删除成功，否则返回false
	 * @throws AppException
	 */
	public boolean DeleteOneCustomer(Customer customer) throws AppException{
		boolean flag = false;
		
		try{
			flag = customerDao.delete(customer);
		} catch (AppException e){
			e.printStackTrace();
			throw new AppException("service.CustomerService.DeleteOneCustomer");
		}

		return flag;
		
	}
	
	/**
	 * 修改客户信息
	 * @param Customer customer
	 * @return 返回True如果修改成功，否则返回false
	 * @throws AppException
	 */
	public boolean UpdateCustomer(Customer customer) throws AppException{
		boolean flag = false;
		
		try{
			flag = customerDao.update(customer);
		}catch (AppException e){
			e.printStackTrace();
			throw new AppException("service.CustomerService.UpdateCustomer");
		}
		return flag;
	}
	/*
	public static void main(String[] args){
		ArrayList<Customer> customers = new ArrayList<Customer>();
		CustomerService cus = new CustomerService();
		try {
			customers = cus.getAllcustomerInfo();
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i=0;i<customers.size();i++){
			System.out.println(customers.get(i).getName());
		}
	}*/
}
