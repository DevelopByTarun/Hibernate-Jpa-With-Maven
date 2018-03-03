package com.embedding.persistent;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AddressEmbedding implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "emp_embedding_home_city", length = 11, nullable = false, insertable = true, updatable = false)
	private String city;
	
	@Column(name = "emp_embedding_home_state", length = 11, nullable = false, insertable = true, updatable = false)
	private String state;
	
	@Column(name = "emp_embedding_home_pincode", length = 8, nullable = false, insertable = true, updatable = false)
	private String pincode;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "AddressEmbedding [city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
	}
}
