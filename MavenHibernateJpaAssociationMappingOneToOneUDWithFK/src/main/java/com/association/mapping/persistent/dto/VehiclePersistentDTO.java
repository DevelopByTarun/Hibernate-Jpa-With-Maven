package com.association.mapping.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "vehicle_onetoone_ud_mstr")
public class VehiclePersistentDTO {
	
	@Id
	@Column(name = "vehicle_name", length = 16, nullable = false, insertable = true, updatable = false)
	private String name;
	
	@Column(name = "vehicle_type", length = 11, nullable = false, insertable = true, updatable = false)
	private String type;
	
	@Column(name = "vehicle_brand", length = 16, nullable = false, insertable = true, updatable = false)
	private String brand;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", type=" + type + ", brand=" + brand + "]";
	}
}
