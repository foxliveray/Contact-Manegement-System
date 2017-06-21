package model;

public class CSignatureOpinion {

	private int conId; 			
	private String csOperator; 	
	private String opinion;		
	
	/**
	 * No-arg constructor assigns initial values to object attributes
	 */
	public CSignatureOpinion() {
		this.conId = 0;
		this.csOperator = "";
		this.opinion = "";
	}

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
