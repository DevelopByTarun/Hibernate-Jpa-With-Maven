package com.inheritance.mapping.joined.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "regular_employee_inheritance_mapping_joined")
@PrimaryKeyJoinColumn(name = "emp_id")
public class RegularEmployeePersistentDTO extends EmployeePersistentDTO {
	
	@Column(name = "emp_salary", precision = 7, scale = 3, nullable = true, insertable = true, updatable = true)
	private double salary;
	
	@Column(name = "emp_designation", length = 15, nullable = true, insertable = true, updatable = true)
	private String designation;

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "RegularEmployeePersistentDTO [salary=" + salary + ", designation=" + designation + "]";
	}
}
