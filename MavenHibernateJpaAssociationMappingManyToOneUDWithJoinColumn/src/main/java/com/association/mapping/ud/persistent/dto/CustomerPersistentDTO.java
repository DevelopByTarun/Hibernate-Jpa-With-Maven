package com.association.mapping.ud.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "customer_manytoone_ud_jc_mstr")
public class CustomerPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "customer_id_gene")
	@TableGenerator (
			name = "customer_id_gene",
			table = "CUSTOMERID_MANYTOONE_UD_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 101
		)
	@Column(name = "customer_id", precision = 7, nullable = false, insertable = true, updatable = false)
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
		return "CustomerPersistentDTO [cid=" + cid + ", cname=" + cname + "]";
	}	
}
