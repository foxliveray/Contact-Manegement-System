package model;

import java.util.Date;

/**
 * Contract business entity class
 */
public class ConBusiModel {

	private int conId; 			
	private String conName; 	
	private Date drafTime;		
	
	/**
	 * No-arg constructor assigns initial values to object attributes
	 */
	public ConBusiModel() {
		this.conId = 0;
		this.conName = "";
		this.drafTime = new Date();
	}

	public int getConId() {
		return conId;
	}

	public void setConId(int conId) {
		this.conId = conId;
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
	
}
