package model;

import java.util.Date;

/**
 * 合同实体类
 */
public class Contract {
	private int id; 			//ID
	private int userId; 		// 用户 id
	private String customer; 	// 顾客
	private String num; 		// 合同号
	private String name; 		// 合同名称
	private Date beginTime; 	// 开始时间
	private Date endTime; 		// 结束时间
	private String content; 	// 合同内容
	private int del; 			// 是否删除，0没有删除，1删除

	//默认构造函数初始化
	public Contract() {
		this.id = 0;
		this.userId = 0;
		this.customer = "";
		this.num = "";
		this.name = "";
		this.beginTime = new Date();
		this.endTime = new Date();
		this.content = "";
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

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
}
