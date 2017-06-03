package model;

import java.util.Date;

/**
 *合同处理过程实体类
 */
public class ConProcess {

	private int id;			    //ID
	private int conId;			// 合同id
	private int userId;			// 用户 id
	private int type;			// 正在进行的过程(1-会签 ,2-批准,3-签成功)
	private int state;			// 处理状态(0-未完成,1-完成,2-退回)
	private String content;		// 处理内容
	private Date time;			// 处理时间
	private int del;			// 是否删除，0没有删除，1删除
	
	//默认构造函数初始化
	public ConProcess(){
		this.id = 0;
		this.conId = 0;
		this.userId = 0;
		this.type = 0;
		this.state = 0;
		this.content = "";
		this.time = new Date();
		this.del = 0;
	}

	//各个成员变量的get和set方法
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConId() {
		return conId;
	}

	public void setConId(int conId) {
		this.conId = conId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
}

