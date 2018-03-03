package com.crud.named.ntv.query.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Column;

@NamedNativeQueries({
	
	@NamedNativeQuery(
			name = "forUpdate",
			query = "update employee_hql_named_native_query_mstr e set e.employee_name = :name, e.employee_salary = :salary where e.employee_id = :id",
			resultClass = EmployeePersistentDTO.class
			),
	@NamedNativeQuery(
			name = "forDelete",
			query = "delete e from employee_hql_named_native_query_mstr e where e.employee_id = :id",
			resultClass = EmployeePersistentDTO.class
			),
	@NamedNativeQuery(
			name = "forSelectAll",
			query = "select * from employee_hql_named_native_query_mstr e",
			resultClass = EmployeePersistentDTO.class
			),
	@NamedNativeQuery(
			name = "forEmployeeById",
			query = "select * from employee_hql_named_native_query_mstr e where e.employee_id = :id",
			resultClass = EmployeePersistentDTO.class
			),
	@NamedNativeQuery(
			name = "forSorting",
			query = "select * from employee_hql_named_native_query_mstr e where e.employee_salary >= :amt ORDER BY e.employee_salary DESC",
			resultClass = EmployeePersistentDTO.class
			)
})

@Entity
@Table(name = "employee_hql_named_native_query_mstr")
public class EmployeePersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE , generator="emp_id_generator")
	@TableGenerator(
			name = "emp_id_generator",
			table = "EMPLOYEEID_HQL_NAMED_NATIVE_QUERY_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "course",
			allocationSize = 1
			)
	@Column(name = "employee_id", precision = 2, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "employee_name", length = 15, unique = true, nullable = false, insertable = true, updatable = true)
	private String name;
	
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeePersistentDTO [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
}
