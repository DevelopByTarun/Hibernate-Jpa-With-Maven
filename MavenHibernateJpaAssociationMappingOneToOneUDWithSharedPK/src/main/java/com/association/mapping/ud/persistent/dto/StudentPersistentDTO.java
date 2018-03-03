package com.association.mapping.ud.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Table(name = "student_onetoone_ud_sharedpk_mstr")
public class StudentPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "student_id_gene")
	@TableGenerator (
			name = "student_id_gene",
			table = "STUDENTID_ONETOONE_UD_SHAREDPK_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "student_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "student_name", length = 14, unique = true, nullable = false, insertable = true, updatable = false)
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private AddressPersistentDTO address;

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

	public AddressPersistentDTO getAddress() {
		return address;
	}

	public void setAddress(AddressPersistentDTO address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "StudentPersistentDTO [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
}
