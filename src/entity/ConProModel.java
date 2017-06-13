package model;

import java.util.Date;

/**
 * Contract business entity class
 */
public class ConProModel {

	private int conId; 			//Contract id
	private int userId;         //userId
	private String conName; 	//Contract name
	private Date drafTime;		//Draft time
	private String content;     //content of the process opinion
	
	/**
	 * No-arg constructor assigns initial values to object attributes
	 */
	public ConProModel() {
		this.conId = 0;
		this.userId=0;
		this.conName = "";
		this.drafTime = new Date();
		this.content="";
	}

	/*
     * Provide setter and getter methods for attributes
	 * setter is used for setting the attribute's value, getter is used for getting the attribute's value
	 */
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
	
}
