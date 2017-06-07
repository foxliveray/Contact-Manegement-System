package service;

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
}
