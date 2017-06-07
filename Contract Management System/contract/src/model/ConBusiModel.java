package model;

import java.util.Date;

/**
 * Contract business entity class
 */
public class ConBusiModel {

	private int conId; 			//Contract id
	private String conName; 	//Contract name
	private Date drafTime;		//Draft time
	
	/**
	 * No-arg constructor assigns initial values to object attributes
	 */
	public ConBusiModel() {
		this.conId = 0;
		this.conName = "";
		this.drafTime = new Date();
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
