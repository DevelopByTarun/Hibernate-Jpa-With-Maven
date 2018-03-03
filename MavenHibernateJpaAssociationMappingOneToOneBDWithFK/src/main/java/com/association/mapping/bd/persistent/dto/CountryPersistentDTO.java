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
@Table(name = "country_onetoone_bd_mstr")
public class CountryPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "country_id_gene")
	@TableGenerator (
			name = "country_id_gene",
			table = "COUNTRYID_ONETOONE_BD_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "country_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int cid;
	
	@Column(name = "country_name", length = 14, unique = true, nullable = false, insertable = true, updatable = false)
	private String cname;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "president_id")
	private PresidentPersistentDTO president;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public PresidentPersistentDTO getPresident() {
		return president;
	}

	public void setPresident(PresidentPersistentDTO president) {
		this.president = president;
	}

	@Override
	public String toString() {
		return "CountryPersistentDTO [cid=" + cid + ", cname=" + cname + ", president=" + president + "]";
	}
}
