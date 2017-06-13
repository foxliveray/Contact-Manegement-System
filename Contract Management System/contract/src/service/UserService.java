package service;

import java.util.ArrayList;
import java.util.List;

import dao.Impl.RightDaoImpl;
import dao.Impl.RoleDaoImpl;

import model.PermissionBusiModel;
import model.Right;
import model.Role;

import dao.RightDao;
import dao.RoleDao;

import dao.UserDao;
import dao.Impl.UserDaoImpl;
import model.User;
import util.AppException;

/**
 * @author 钱洋
 * @date 2017年6月7日 上午10:26:02
 */
public class UserService {
	private UserDao userDao = null;	//定义一个UserDao接口对象
	private RoleDao roleDao = null;// Define a roleDao interface object
	private RightDao rightDao = null;// Define a userDao rightDao object
	
	public UserService(){
		userDao = new UserDaoImpl();
		roleDao = new RoleDaoImpl();
		rightDao = new RightDaoImpl();
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
	 * User login
	 * 
	 * @param name 
	 * @param password 
	 * @return Query the matched user number according to the condition , otherwise it returns 0
	 * @throws AppException
	 */
	public int login(String name, String password) throws AppException {
		int userId = -1; // Declare userId
		try {
			//Get userId
			userId = userDao.login(name, password); 
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.UserService.login");
		}
		// Return userId
		return userId;
	}
	
	/**
	 * Get role information that corresponding to the user
	 * 
	 * @param userId 
	 * @return Role object
	 * @throws AppException
	 */
	public Role getUserRole(int userId) throws AppException {	
		Role role = null;// Declare role
		int roleId = -1; // Initialize roleId
		try {
			// Get the roleId that corresponding to the user
			roleId = rightDao.getRoleIdByUserId(userId);
			if(roleId > 0){
				// Get role information
				role = roleDao.getById(roleId); 
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.UserService.getUserRole");
		}
		return role;
	}
	
	/**
	 * Get user list that corresponding to the role
	 * 
	 * @param roleId 
	 * @return User list
	 * @throws AppException
	 */
	public List<User> getUserListByRoleId(int roleId) throws AppException {
		// Initialize  userList
		List<User> userList = new ArrayList<User>();
		// Declare userIds
		List<Integer> userIds = null;; 
		
		try {
			/*
			 * 1.Get designated user's userIds
			 */
			userIds = rightDao.getUserIdsByRoleId(roleId);
			
			/*
			 * 2.Get user information list according to userIds
			 */ 
			for (int userId : userIds) {
				// Get user's information
				User user = userDao.getById(userId);
				if (user != null) {
					userList.add(user); 
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.UserService.getUserList");
		}	
		// Return userList
		return userList;
	}
	
	/**
	 * Get user permission list
	 * 
	 * @return permissionList  User permission list
	 * @throws AppException
	 */
	public List<PermissionBusiModel> getYhqxList() throws AppException {
		// Initialize permissionList
		List<PermissionBusiModel> permissionList = new ArrayList<PermissionBusiModel>();
		// Declare userIds
		List<Integer> userIds = null; 
		
		try {
			/*
			 * 1.Get user id set
			 */
			userIds = userDao.getIds();
			
			/*
			 * 2.Get user permission information: user information and corresponding role information
			 */
			for (int userId : userIds) {
			
				// Initialize business entity class object
				PermissionBusiModel permission = new PermissionBusiModel();
				
				User user = userDao.getById(userId); // Get user information according to user id
				int roleId = -1;
				roleId = rightDao.getRoleIdByUserId(userId); // Get role id according to user id
				
				if (roleId > 0) {
					Role role = roleDao.getById(roleId); // Get role information according to role id
					// Save role information to permission
					permission.setRoleId(roleId);
					permission.setRoleName(role.getName());
				}
				
				// Save user information to permission
				permission.setUserId(userId);
				permission.setUserName(user.getName());
				
				permissionList.add(permission);
			}
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.UserService.getYhqxList");
		}	
		// Permission business entity set
		return permissionList;
	}
	
	/**
	 * Get role list
	 * 
	 * @return Role object set
	 * @throws AppException
	 */
	public List<Role> getRoleList() throws AppException {	
		// Initialize role set
		List<Role> roleList = new ArrayList<Role>();
		
		try {
			// Get all role object set
			roleList = roleDao.getAll();
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.UserService.getRoleList");
		}
		return roleList;
	}
	
	/**
	 * Configure permission
	 *  
	 * @param right Permission object
	 * @return boolean Return true if operation successful,otherwise return false
	 * @throws AppException
	 */
	public boolean assignPermission(Right right) throws AppException {
		boolean flag = false;// Define flag
		
		try {
			//  Get user's role 
			int roleId = -1; // Initialize roleId
			// Get user's role id
			roleId = rightDao.getRoleIdByUserId(right.getUserId());
			// Declare role object
			Role role = null;
			if (roleId > 0) {
				// Get role information
				role = roleDao.getById(roleId);
			}
		
			/*
			 * Judgement role of user owned before,if user has a role before,so change the role,otherwise add a new role
			 */
			if (role != null) {
				// Get user's permission
				int rightId = rightDao.getIdByUserId(right.getUserId());
				// Set permission id to right object
				right.setId(rightId);
				right.setDescription("update");
				// Update permission information
				flag = rightDao.updateById(right);
			} else {
				flag = rightDao.add(right);
			}
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.UserService.assignPermission");
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