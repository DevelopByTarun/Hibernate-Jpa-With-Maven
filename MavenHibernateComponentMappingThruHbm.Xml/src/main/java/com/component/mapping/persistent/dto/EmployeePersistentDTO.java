package com.component.mapping.persistent.dto;

public class EmployeePersistentDTO {
	
	private int id;
	
	private String name;
	
	private EmployeeDetailsDTO empDetails;
	
	private String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EmployeeDetailsDTO getEmpDetails() {
		return empDetails;
	}

	public void setEmpDetails(EmployeeDetailsDTO empDetails) {
		this.empDetails = empDetails;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
