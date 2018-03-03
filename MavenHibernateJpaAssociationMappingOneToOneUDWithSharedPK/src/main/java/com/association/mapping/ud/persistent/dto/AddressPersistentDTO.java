package com.association.mapping.ud.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "address_onetoone_ud_sharedpk_mstr")
public class AddressPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "address_id_gene")
	@TableGenerator (
			name = "address_id_gene",
			table = "ADDRESSID_ONETOONE_UD_SHAREDPK_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "address_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "address_country", length = 14, unique = true, nullable = false, insertable = true, updatable = false)
	private String country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", country=" + country + "]";
	}
}
