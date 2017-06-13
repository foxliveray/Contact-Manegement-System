package dao;

import java.util.List;

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
	 * Query  UserId according to user name and password
	 * @param name User name
	 * @param password 
	 * @throws AppException
	 */
	public int login(String name,String password) throws AppException;
	
	/**
	 * Query user's information according to id
	 * 
	 * @param id  User id
	 * @return User 
	 * @throws AppException
	 */
	public User getById(int id) throws AppException;
	
	/**
	 * Query user id set
	 * 
	 * @return User id set
	 * @throws AppException
	 */
	public List<Integer> getIds() throws AppException;
	public boolean update(User user) throws AppException;
	public boolean delete(User user) throws AppException;
	public int getUserCount() throws AppException;
}
