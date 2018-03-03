package com.association.mapping.ud.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "customers_onetomany_ud_jt_mstr")
public class CustomersPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "customers_id_gene")
	@TableGenerator (
			name = "customers_id_gene",
			table = "CUSTOMERSID_ONETOMANY_UD_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "customer_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int cid;
	
	@Column(name = "customer_name", length = 14, unique = true, nullable = false, insertable = true, updatable = false)
	private String cname;

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

	@Override
	public String toString() {
		return "CustomersPersistentDTO [cid=" + cid + ", cname=" + cname + "]";
	}
}
