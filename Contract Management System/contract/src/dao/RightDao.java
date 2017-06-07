package dao;

import java.util.List;

import model.Right;
import util.AppException;

/**
 * Permission Data Access Layer Interface
 */
public interface RightDao {

	/**
	 * Get roleId according to the userId
	 * 
	 * @param userId 
	 * @return roleId 
	 * @throws AppException
	 */
	public int getRoleIdByUserId(int userId) throws AppException;
	
	/**
	 * Query user id set according to role id
	 * 
	 * @param roleId Role id
	 * @return User id set that meet the conditions; otherwise return null
	 * @throws AppException
	 */
	public List<Integer> getUserIdsByRoleId(int roleId) throws AppException;
	
	/**
	 * Get permission id according to user id
	 * 
	 * @param userId User id
	 * @return id Permission id
	 * @throws AppException
	 */
	public int getIdByUserId(int userId) throws AppException;
	
	/**
	 * Update contract content according to permission id,
	 * pass parameter though entity object
	 * 
	 * @param id permission id
	 * @return boolean Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean updateById(Right right) throws AppException;
	
	/**
	 * Save permission information
	 * 
	 * @param right permission object
	 * @return Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(Right right) throws AppException;
	
}
