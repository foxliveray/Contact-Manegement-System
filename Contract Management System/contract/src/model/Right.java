package model;


/**
 * 权限实体类
 */
public class Right {
	
	private int id;			    //ID
	private int userId;			//用户id
	private int roleId;			//角色id
	private String description; //描述
	private int del;			// 是否删除，0没有删除，1删除
	
	//默认构造函数初始化
	public Right() {
		this.id = 0;
		this.userId = 0;
		this.roleId = 0;
		this.description = "";
		this.del = 0;
	}

	//各个成员变量的get和set方法
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

}
