package com.crud.named.query.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Column;

@NamedQueries({
	
	@NamedQuery(
			name = "forUpdate",
			query = "update EmployeePersistentDTO e set e.name = :name, e.salary = :salary where e.id = :id"
			),
	@NamedQuery(
			name = "forDelete",
			query = "delete from EmployeePersistentDTO e where e.id = :id"
			),
	@NamedQuery(
			name = "forSelectAll",
			query = "FROM EmployeePersistentDTO e"
			),
	@NamedQuery(
			name = "forEmployeeById",
			query = "FROM EmployeePersistentDTO e where e.id = :id"
			),
	@NamedQuery(
			name = "forSorting",
			query = "FROM EmployeePersistentDTO e where e.salary >= :amt ORDER BY e.salary DESC"
			)
})

@Entity
@Table(name = "employee_hql_named_query_mstr")
public class EmployeePersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE , generator="emp_id_generator")
	@TableGenerator(
			name = "emp_id_generator",
			table = "EMPLOYEEID_HQL_NAMED_QUERY_GENERATOR",
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
