package service;

import java.util.ArrayList;
import java.util.List;

import dao.RightDao;
import dao.RoleDao;
import dao.UserDao;
import dao.Impl.RightDaoImpl;
import dao.Impl.RoleDaoImpl;
import dao.Impl.UserDaoImpl;
import model.PermissionBusiModel;
import model.Right;
import model.Role;
import model.User;
import util.AppException;

/**
 * @author 钱洋
 * @date 2017年6月7日 上午10:26:02
 */
public class UserService {
	private UserDao userDao = null;	
	private RightDao rightDao=null;
	private RoleDao roleDao=null;
	
	public UserService(){
		userDao = new UserDaoImpl();
		rightDao=new RightDaoImpl();
		roleDao=new RoleDaoImpl();
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
		int userId = -1; 
		try {
			
			userId = userDao.login(name, password); 
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.UserService.login");
		}
		
		return userId;
	}
	public List<User> getUserListByRoleId(int roleId) throws AppException {
		
		List<User> userList = new ArrayList<User>();
		
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
				
				User user = userDao.getById(userId);
				if (user != null) {
					userList.add(user); 
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.UserService.getUserList");
		}	
		
		return userList;
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
			
			e.printStackTrace();
			throw new AppException("service.UserService.getUserList");
		}
		
		return userList;
	}
	
	/**
	 *根据用户id获取指定用户信息
	 * @return Query user info according to userId
	 * @throws AppException
	 */
	public User getUserById(int id) throws AppException{
		User user=null;
		
		try {
			user=userDao.getById(id);
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new AppException("service.UserService.getUserById");
		}
		
		return user;
	}
	
	/**
	 *根据用户名获取指定用户信息
	 *@param String name
	 * @return Query user info according to userName
	 * @throws AppException
	 */
	public User getUserByName(String name) throws AppException{
		User user=null;
		
		try {
			user=userDao.getByName(name);
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new AppException("service.UserService.getUserByName");
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
		boolean flag1=false,flag2=false;
		
		try {
			flag1=userDao.add(user);
			User userWithId=getUserByName(user.getName());
			
			Right newRight=new Right();
			newRight.setUserId(userWithId.getId());
			newRight.setRoleId(3);
			newRight.setDescription("nothing");
			flag2=rightDao.add(newRight);
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new AppException("service.UserService.addUser");
		}
		
		if(flag1&&flag2){
			return true;
		}
		else{
			return false;
		}
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
		boolean flag1=false,flag2=false;
		
		try {
			
			flag2=rightDao.delete(user.getId());
			
			flag1=userDao.delete(user);
			
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new AppException("service.UserService.deleteUser");
		}
		
		if(flag1&&flag2){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 *根据用户id获取用户-角色信息
	 *@param int userId User ID
	 * @return Right right object
	 * @throws AppException
	 */
	public Right getRightByUserID(int userId) throws AppException {
		Right right=null;
		
		try {
			right=rightDao.getRightByUserId(userId);
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new AppException("service.UserService.getRightByUserID");
		}
		
		return right;
	}
	
	/**
	 *获取所有用户-角色信息
	 * @return Query all rights that need to be allocated; Otherwise return
	 *         null
	 * @throws AppException
	 */
	public List<PermissionBusiModel> getPMList() throws AppException{
		List<User> userList=new ArrayList<User>();
		List<PermissionBusiModel> pmList=new ArrayList<PermissionBusiModel>();
		
		try {
			for(int i=0,j=1;i<userDao.getUserCount();i++,j++){
				while(userDao.getById(j)==null){
					j++;
				}
				userList.add(userDao.getById(j));
			}
			for(int i=0;i<userList.size();i++){
				User user=userList.get(i);	
				int roleId=rightDao.getRoleIdByUserId(user.getId());
				Role role=roleDao.getById(roleId);
				
				PermissionBusiModel onepm=new PermissionBusiModel();
				onepm.setUserId(user.getId());
				onepm.setUserName(user.getName());
				onepm.setRoleId(roleId);
				onepm.setRoleName(role.getName());
				pmList.add(onepm);
			}
			
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new AppException("service.UserService.getUserList");
		}
		
		return pmList;
	}
	
	/**
	 *更新用户-角色信息
	 *@param Right right object
	 * @return Return true if update is successful, otherwise return false
	 * @throws AppException
	 */
	public boolean UpdatePermission(Right right) throws AppException{
		boolean flag=false;
		
		try {
			flag=rightDao.updateById(right);
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new AppException("service.UserService.UpdatePermission");
		}
		
		return flag;
	}
	
	/**
	 *根据角色id获取角色信息
	 *@param int userId User ID
	 * @return Right right object
	 * @throws AppException
	 */
	public Role getRoleByRoleId(int roleId) throws AppException{
		Role role=null;
		
		try {
			role=roleDao.getById(roleId);
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new AppException("service.UserService.getRoleByRoleId");
		}
		
		return role;
	}
	
	/**
	 *获取所有角色信息
	 * @return Query all roles that need to be allocated; Otherwise return
	 *         null
	 * @throws AppException
	 */
	public List<Role> getRoleList() throws AppException{
		List<Role> roleList=new ArrayList<Role>();
		
		try {
			roleList=roleDao.getAll();
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new AppException("service.UserService.getRoleList");
		}
		
		return roleList;
	}
	
	/**
	 *添加角色
	 *
	 *@param Role Role object
	 * @return Return true if added successfully,otherwise return false
	 * @throws AppException
	 */
	public boolean addRole(Role role) throws AppException{
		boolean flag=false;
		
		try {
			flag=roleDao.add(role);
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new AppException("service.UserService.addRole");
		}
		
		return flag;
	}
	
	/**
	 *更新角色
	 * 
	 * @param Role Role object
	 * @return Return true if updated successfully,otherwise return false
	 * @throws AppException
	 */
	public boolean updateRole(Role role) throws AppException{
		boolean flag=false;
		
		try {
			flag=roleDao.update(role);
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new AppException("service.UserService.updateRole");
		}
		
		return flag;
	}
	
	/**
	 *删除角色
	 * 
	 * @param int Role id
	 * @return Return true if deleted successfully,otherwise return false
	 * @throws AppException
	 */
	public boolean deleteRole(int roleId) throws AppException{
		boolean flag=false;
		
		try {
			flag=roleDao.delete(roleId);
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new AppException("service.UserService.deleteRole");
		}
		
		return flag;
	}
}
