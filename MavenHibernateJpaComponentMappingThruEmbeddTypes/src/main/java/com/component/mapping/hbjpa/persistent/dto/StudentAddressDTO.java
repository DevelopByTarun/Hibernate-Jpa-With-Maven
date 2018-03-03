package com.component.mapping.hbjpa.persistent.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentAddressDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "student_local_city", length = 11, nullable = false, insertable = true, updatable = false)
	private String city;
	
	@Column(name = "student_local_pincode", length = 8, nullable = false, insertable = true, updatable = false)
	private String pincode;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "[city=" + city + ", pincode=" + pincode + "]";
	}
}
