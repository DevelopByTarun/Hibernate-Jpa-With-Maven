package com.association.mapping.bd.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "dept_onetomanymanytoone_bd_mstr")
public class DepartmentPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "dept_id_gene")
	@TableGenerator (
			name = "dept_id_gene",
			table = "DEPTID_ONETOMANYMANYTOONE_BD_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "dept_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int did;
	
	@Column(name = "dept_name", length = 14, unique = true, nullable = false, insertable = true, updatable = false)
	private String dname;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "dept_employees",
			joinColumns = { @JoinColumn(name = "dept_id") },
			inverseJoinColumns = { @JoinColumn(name = "employee_id") }
		)
	private Set<EmployeesPersistentDTO> employees = new HashSet<EmployeesPersistentDTO>();

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Set<EmployeesPersistentDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeesPersistentDTO> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "DepartmentPersistentDTO [did=" + did + ", dname=" + dname + ", employees=" + employees + "]";
	}
}
