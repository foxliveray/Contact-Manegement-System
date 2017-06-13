package service;

import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import util.AppException;

/**
 * @author 钱洋
 * @date 2017年6月7日 上午10:26:02
 */
public class UserService {
	private UserDao userDao = null;	//定义一个UserDao接口对象
	
	public UserService(){
		userDao = new UserDaoImpl();
	}
	
	/**
	 *用户注册
	 * @param user User object
	 * @return Return true if registration is successful, otherwise return false
	 * @throws AppException
	 */
	public boolean register(User user) throws AppException{
		boolean flag = false;
		try{
			if(!userDao.isExist(user.getName())){
				flag = userDao.add(user);
			}
		} catch(AppException e){
			e.printStackTrace();
			throw new AppException("service.UserService.register");
		}
		return flag;
		
	}
	
	/**
	 *获取所有用户信息
	 * @return Query all contracts that need to be allocated; Otherwise return
	 *         null
	 * @throws AppException
	 */
	public List<User> getUserList() throws AppException{
		List<User> userList=new ArrayList<User>();
		
		try {
			for(int i=0,j=1;i<userDao.getUserCount();i++,j++){
				while(userDao.getById(j)==null){
					j++;
				}
				userList.add(userDao.getById(j));
			}
			
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.UserService.getUserList");
		}
		
		return userList;
	}
	
	/**
	 *根据用户id获取指定用户信息
	 * @return Query all contracts that need to be allocated; Otherwise return
	 *         null
	 * @throws AppException
	 */
	public User getUserById(int id) throws AppException{
		User user=null;
		
		try {
			user=userDao.getById(id);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.UserService.getUserById");
		}
		
		return user;
	}
	
	/**
	 *添加用户
	 * @param User new User
	 * @return Return true if adding is successful, otherwise return false
	 * @throws AppException
	 */
	public boolean addUser(User user) throws AppException{
		boolean flag=false;
		
		try {
			flag=userDao.add(user);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.UserService.addUser");
		}
		
		return flag;
	}
	
	/**
	 *修改用户
	 * @param User new User
	 * @return Return true if edit is successful, otherwise return false
	 * @throws AppException
	 */
	public boolean editUser(User user) throws AppException{
		boolean flag=false;
		
		try {
			flag=userDao.update(user);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.UserService.editUser");
		}
		
		return flag;
	}
	
	/**
	 *删除用户
	 * @param User old User
	 * @return Return true if delete is successful, otherwise return false
	 * @throws AppException
	 */
	public boolean deleteUser(User user) throws AppException{
		boolean flag=false;
		
		try {
			flag=userDao.delete(user);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("service.UserService.deleteUser");
		}
		
		return flag;
	}
}
