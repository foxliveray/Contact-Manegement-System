package service;

import dao.CustomerDao;
import dao.impl.CustomerDaoImpl;
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
}
