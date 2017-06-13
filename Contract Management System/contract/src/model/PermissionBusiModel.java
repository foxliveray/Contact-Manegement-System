package model;

/**
 * Permissions business entity class
 */
public class PermissionBusiModel {

	private int userId;			//用户id
	private int roleId;			//角色id
	private String userName;	//用户名
	private String roleName;	//角色名 
	
	/**
	 * No-arg constructor:assigned initial values to the object attribute
	 */
	public PermissionBusiModel(){
		this.userId = 0;
		this.roleId = 0;
		this.userName = "";
		this.roleName = "";
	}

	/*
	 * Provide setter and getter methods for attributes
	 * setter is used for setting the attribute's value, getter is used for getting the attribute's value
	 */
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
