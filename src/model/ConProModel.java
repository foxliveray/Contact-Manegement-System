package model;

import java.util.Date;

/**
 * Contract business entity class
 */
public class ConProModel {

	private int conId; 			
	private int userId;         
	private String conName; 	
	private Date drafTime;		
	private String content;     
	private int type;           
	
	/**
	 * No-arg constructor assigns initial values to object attributes
	 */
	public ConProModel() {
		this.conId = 0;
		this.userId=0;
		this.conName = "";
		this.drafTime = new Date();
		this.content="";
		this.type=0;
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
	
	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public Date getDrafTime() {
		return drafTime;
	}

	public void setDrafTime(Date drafTime) {
		this.drafTime = drafTime;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
}
