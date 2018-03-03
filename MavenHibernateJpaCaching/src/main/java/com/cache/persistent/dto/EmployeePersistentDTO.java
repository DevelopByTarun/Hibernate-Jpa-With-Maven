package com.cache.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.Id;
import javax.persistence.Cacheable;
import javax.persistence.Column;

@Entity
@Table(name = "employee_cache_mstr")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "employee_cache_mstr")
public class EmployeePersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE , generator="emp_id_generator")
	@TableGenerator(
			name = "emp_id_generator",
			table = "EMPLOYEEID_CACHE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "course",
			allocationSize = 1
			)
	@Column(name = "employee_id", precision = 2, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "employee_name", length = 15, unique = true, nullable = false, insertable = true, updatable = true)
	private String name;
	
	@Column(name = "employee_address", length = 15, nullable = false, insertable = true, updatable = true)
	private String address;
	
	@Column(name = "employee_salary", precision = 7, scale = 3, nullable = false, insertable = true, updatable = true)
	private double salary;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeePersistentDTO [id=" + id + ", name=" + name + ", address=" + address + ", salary=" + salary
				+ "]";
	}
}
