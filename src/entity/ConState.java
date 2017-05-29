package entity;

import java.util.Date;

/**
 *合同状态实体类
 */
public class ConState {
	
	private int id;			    //ID 
	private int conId;			// 合同id
	private int type;			//处理状态(1-草稿,2-会签,3-定稿,4-批准,5-签字)
	private Date time;			// 操作时间
	private int del;			// 是否删除，0没有删除，1删除
	
	////默认构造函数初始化
	public ConState(){
		this.id = 0;
		this.conId = 0;
		this.type = 0;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
