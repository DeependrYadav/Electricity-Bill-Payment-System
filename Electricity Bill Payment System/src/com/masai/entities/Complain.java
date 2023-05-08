package com.masai.entities;

import java.io.Serializable;

public class Complain implements Serializable {
	private final int complainId;
	private final String consumerName;
	private final String consumerEmail;
	private String discription;
	private String status;


	public Complain(int complainId, String consumerName, String consumerEmail, String discription,String status) {
		super();
		this.complainId = complainId;
		this.consumerName = consumerName;
		this.consumerEmail = consumerEmail;
		this.discription = discription;
		this.status = status;
	}
	

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public int getComplainId() {
		return complainId;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public String getConsumerEmail() {
		return consumerEmail;
	}

	@Override
	public String toString() {
		return  "ComplainId=" + complainId + ", consumerName=" + consumerName + ", consumerEmail="
				+ consumerEmail + ", discription=" + discription +", Status of complain=" + status +
				"\n---------------------------------------------------------------------------------";
	}

	

}
