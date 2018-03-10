package com.inheritance.mapping.joined.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "contract_employee_inheritance_mapping_joined")
@PrimaryKeyJoinColumn(name = "emp_id")
public class ContractEmployeePersistentDTO extends EmployeePersistentDTO {
	
	@Column(name = "emp_address", length = 15, nullable = true, insertable = true, updatable = true)
	private String address;
	
	@Column(name = "emp_phone", precision = 12, nullable = true, insertable = true, updatable = true)
	private long phone;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "ContractEmployeePersistentDTO [address=" + address + ", phone=" + phone + "]";
	}
}
