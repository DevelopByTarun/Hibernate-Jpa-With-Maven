package com.tarun.demo.persistent;

public class CompanyPersistent {
	int id;
	String name;
	String product;
	String address;
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
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "CompanyPersistentRecords [id=" + id + ", name=" + name + ", product=" + product + ", address=" + address + "]";
	}
}
