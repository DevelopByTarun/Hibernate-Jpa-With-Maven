package com.association.mapping.bd.persistent.dto;

public class AddressPersistentDTO {
	
	private int aid;
	
	private String country;
	
	private EmployeePersistentDTO employee;

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public EmployeePersistentDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeePersistentDTO employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "AddressPersistentDTO [aid=" + aid + ", country=" + country + "]";
	}
}
