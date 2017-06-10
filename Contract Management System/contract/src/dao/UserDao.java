package dao;

import model.User;
import util.AppException;

/**
 * @author 钱洋
 * @date 2017年6月7日 上午9:28:57
 */
public interface UserDao {

	/**
	 * 区分是否有同名的用户存在
	 * @param name 	(用户名)
	 * @return 返回True如果有用户有相同的名字，否则返回false
	 * @throws AppException
	 */
	public boolean isExist(String name) throws AppException;
	
	/**
	 * 保存用户信息
	 * @param user (user对象实例)
	 * @return 返回True如果有添加新用户成功，否则返回false
	 * @throws AppException
	 */
	public boolean add(User user) throws AppException;
	
	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param id  User id
	 * @return User 
	 * @throws AppException
	 */
	public User getById(int id) throws AppException;
}
