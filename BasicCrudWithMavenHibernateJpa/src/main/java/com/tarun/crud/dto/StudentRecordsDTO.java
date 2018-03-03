package com.tarun.crud.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_records_mstr")
public class StudentRecordsDTO {
	@Id
	@Column(name = "student_id", length = 4, nullable = false)
	int id;
	
	@Column(name = "student_fname", length = 11, nullable = false, unique = true)
	String firstName;
	
	@Column(name = "student_lname", length = 11, nullable = false)
	String lastName;
	
	@Column(name = "student_age", length = 2, nullable = false)
	int age;
	
	@Column(name = "student_city", length = 15, nullable = false)
	String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Records Is :: [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", city=" + city + "]";
	}
}
