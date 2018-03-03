package com.association.mapping.bd.persistent.dto;

public class EmployeePersistentDTO {
	
	private int eid;
	
	private String name;
	
	private AddressPersistentDTO address;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressPersistentDTO getAddress() {
		return address;
	}

	public void setAddress(AddressPersistentDTO address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "EmployeePersistentDTO [eid=" + eid + ", name=" + name + ", address=" + address + "]";
	}
}
