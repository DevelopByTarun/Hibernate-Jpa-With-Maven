package com.association.mapping.bd.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

@Entity
@Table(name = "employees_onetomanymanytoone_bd_mstr")
public class EmployeesPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_id_gene")
	@TableGenerator (
			name = "employee_id_gene",
			table = "EMPLOYEEID_ONETOMANYMANYTOONE_BD_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "employee_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int eid;
	
	@Column(name = "employee_name", length = 14, unique = true, nullable = false, insertable = true, updatable = false)
	private String ename;
	
	@Column(name = "employee_mobileNo", precision = 12, nullable = false, insertable = true, updatable = false)
	private long mobileNo;
	
	@ManyToOne
	private DepartmentPersistentDTO department;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public DepartmentPersistentDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentPersistentDTO department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "EmployeesPersistentDTO [eid=" + eid + ", ename=" + ename + ", mobileNo=" + mobileNo + "]";
	}
}
