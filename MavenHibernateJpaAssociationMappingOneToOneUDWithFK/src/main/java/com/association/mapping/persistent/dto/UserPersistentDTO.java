package com.association.mapping.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name = "user_onetoone_ud_mstr")
public class UserPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "user_id_gene")
	@TableGenerator (
			name = "user_id_gene",
			table = "USERID_ONETOONE_UD_PK_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "user_id", precision = 4, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "user_name", length = 11, unique = true, nullable = false, insertable = true, updatable = false)
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vehicle_name")
	private VehiclePersistentDTO userVehicle;

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

	public VehiclePersistentDTO getUserVehicle() {
		return userVehicle;
	}

	public void setUserVehicle(VehiclePersistentDTO userVehicle) {
		this.userVehicle = userVehicle;
	}

	@Override
	public String toString() {
		return "UserPersistentDTO [id=" + id + ", name=" + name + ", userVehicle=" + userVehicle + "]";
	}
}
