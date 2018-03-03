package com.association.mapping.bd.persistent.dto;

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
@Table(name = "president_onetoone_bd_mstr")
public class PresidentPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "president_id_gene")
	@TableGenerator (
			name = "president_id_gene",
			table = "PRESIDENTID_ONETOONE_BD_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "president_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int pid;
	
	@Column(name = "president_name", length = 14, unique = true, nullable = false, insertable = true, updatable = false)
	private String pname;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id")
	private CountryPersistentDTO country;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public CountryPersistentDTO getCountry() {
		return country;
	}

	public void setCountry(CountryPersistentDTO country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "PresidentPersistentDTO [pid=" + pid + ", pname=" + pname + "]";
	}
}
