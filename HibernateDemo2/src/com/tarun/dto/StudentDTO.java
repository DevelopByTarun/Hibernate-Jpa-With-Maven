package com.tarun.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import java.util.Date;

import javax.persistence.Column;


@Entity
@Table(name = "Student_Master")
public class StudentDTO {
	
	@Id
	@Column(name = "std_id", length = 4, nullable = false)
	private int id;
	
	@Column(name = "std_name", length = 10, unique = true, nullable = false)
	private String name;
	
	@Column(name = "std_styfund", precision = 3, scale = 6, nullable = false)
	private double styfund;
	
	@Type(type = "yes_no")
	@Column(name = "std_attendence", nullable = false)
	private boolean attendence;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "std_dob", nullable = false)
	private Date dob;
	
	@Transient
	private int donation;

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

	public double getStyfund() {
		return styfund;
	}

	public void setStyfund(double styfund) {
		this.styfund = styfund;
	}

	public boolean isAttendence() {
		return attendence;
	}

	public void setAttendence(boolean attendence) {
		this.attendence = attendence;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getDonation() {
		return donation;
	}

	public void setDonation(int donation) {
		this.donation = donation;
	}

	@Override
	public String toString() {
		return "Record Found [id=" + id + ", name=" + name + ", styfund=" + styfund + ", attendence=" + attendence
				+ ", dob=" + dob + ", donation=" + donation + "]";
	}
}
