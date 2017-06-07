package dao;

import java.util.List;

import model.Role;
import util.AppException;

public interface RoleDao {

	/**
	 * Query role's information according to id
	 * 
	 * @param id RoleId
	 * @return Role 
	 * @throws AppException
	 */
	public Role getById(int id) throws AppException;
	
	/**
	 * Query all role object set
	 * 
	 * @return Role object set
	 * @throws AppException
	 */
	public List<Role> getAll() throws AppException;
	
}
