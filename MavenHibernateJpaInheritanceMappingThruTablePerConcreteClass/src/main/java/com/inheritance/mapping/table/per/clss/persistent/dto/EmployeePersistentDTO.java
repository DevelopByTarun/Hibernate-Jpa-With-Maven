package com.inheritance.mapping.table.per.clss.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Table(name = "employee_inheritance_mapping_table_per_clss")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EmployeePersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE , generator="emp_id_generator")
	@TableGenerator(
			name = "emp_id_generator",
			table = "EMPLOYEEID_INHERITANCE_MAPPING_TABLE_PER_CLASS_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "course",
			allocationSize = 1
			)
	@Column(name = "emp_id", precision = 2, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "emp_name", length = 15, nullable = true, insertable = true, updatable = true)
	private String name;

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

	@Override
	public String toString() {
		return "EmployeePersistentDTO [id=" + id + ", name=" + name + "]";
	}
}
