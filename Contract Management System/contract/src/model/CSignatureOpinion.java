package model;

/**
 * Countersign opinion entity class
 */
public class CSignatureOpinion {

	private int conId; 			// Contract id
	private String csOperator; 	// Countersign operator
	private String opinion;		// Countersign opinion
	
	/**
	 * No-arg constructor assigns initial values to object attributes
	 */
	public CSignatureOpinion() {
		this.conId = 0;
		this.csOperator = "";
		this.opinion = "";
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

	public String getCsOperator() {
		return csOperator;
	}

	public void setCsOperator(String csOperator) {
		this.csOperator = csOperator;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
}
