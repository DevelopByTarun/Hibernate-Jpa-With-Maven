package com.component.mapping.persistent.dto;

public class EmployeeDetailsDTO {
	
	private String designation;
	
	private String company;

	private double salary;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "[designation=" + designation + ", company=" + company + ", salary=" + salary + "]";
	}
}
