package com.tarun.persistent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "emp_automaticid_mstr")
public class EmployeeAutomaticId {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE , generator="emp_id_generator")
	@TableGenerator(
			name = "emp_id_generator",
			table = "EMPID_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "course",
			allocationSize = 1
			)
	@Column(name = "emp_id", precision = 2, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "emp_name", length = 11, nullable = false, insertable = true, updatable = false, unique = true)
	private String name;
	
	@Column(name = "emp_city", length = 11, nullable = false, insertable = true, updatable = false)
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "EmployeeAutomaticId Records [id=" + id + ", name=" + name + ", city=" + city + "]";
	}
}
