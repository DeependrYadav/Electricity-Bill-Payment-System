package com.masai.entities;

public class Complain {
	private final int complainId;
	private final String consumerName;
	private final String consumerEmail;
	private String discription;

	public Complain(int complainId, String consumerName, String consumerEmail, String discription) {
		super();
		this.complainId = complainId;
		this.consumerName = consumerName;
		this.consumerEmail = consumerEmail;
		this.discription = discription;
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
		return "ComplainId=" + complainId + ", consumerName=" + consumerName + ", consumerEmail="
				+ consumerEmail + ", discription=" + discription;
	}

	

}
