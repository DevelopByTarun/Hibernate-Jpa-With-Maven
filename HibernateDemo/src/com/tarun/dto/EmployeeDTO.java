package com.tarun.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Emp_Master")
public class EmployeeDTO {
	@Id
	@Column(name = "emp_id", length = 3, nullable = false)
	private int id;
	
	@Column(name = "emp_name", length = 10, nullable = false, unique = true)
	private String name;
	
	@Column(name = "emp_salary", scale = 5, precision = 4, nullable = false)
	private double salary;
	
	@Type(type = "yes_no")
	@Column(name = "emp_attend", nullable = false)
	private boolean attend;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dob;
	
	@Transient
	private double bonus = 1000;

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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isAttend() {
		return attend;
	}

	public void setAttend(boolean attend) {
		this.attend = attend;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", name=" + name + ", salary=" + salary + ", attend=" + attend + ", dob=" + dob
				+ ", bonus=" + bonus + "]";
	}
}
