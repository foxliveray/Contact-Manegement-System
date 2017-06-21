package dao;

import java.util.List;

import model.Right;
import util.AppException;

/**
 * Permission Data Access Layer Interface
 */
public interface RightDao {

	/**
	 * 根据用户id获取角色id
	 * 
	 * @param userId
	 * @return roleId
	 * @throws AppException
	 */
	public int getRoleIdByUserId(int userId) throws AppException;

	/**
	 * 根据角色id查找用户id
	 * 
	 * @param roleId
	 *            Role id
	 * @return User id set that meet the conditions; otherwise return null
	 * @throws AppException
	 */
	public List<Integer> getUserIdsByRoleId(int roleId) throws AppException;

	/**
	 * 根据用户id获取permission id
	 * 
	 * @param userId
	 *            User id
	 * @return id Permission id
	 * @throws AppException
	 */
	public int getIdByUserId(int userId) throws AppException;

	/**
	 * 根据用户id获取查询用户-角色关系
	 * 
	 * @param userId
	 *            User id
	 * @return id Permission id
	 * @throws AppException
	 */
	public Right getRightByUserId(int userId) throws AppException;

	/**
	 * 更新用户-角色关系 
	 * 
	 * @param Right Right object
	 * @return boolean Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean updateById(Right right) throws AppException;

	/**
	 * 保存新用户-角色关系
	 * 
	 * @param right
	 *            permission object
	 * @return Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(Right right) throws AppException;

	/**
	 * 删除用户-角色关系
	 * 
	 * @param right
	 *            permission object
	 * @return Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean delete(int userId) throws AppException;
}
