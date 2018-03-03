package com.association.mapping.ud.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "bank_onetomany_ud_jt_mstr")
public class BankPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "bank_id_gene")
	@TableGenerator (
			name = "bank_id_gene",
			table = "BANKID_ONETOMANY_UD_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "bank_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "bank_name", length = 14, unique = true, nullable = false, insertable = true, updatable = false)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "bank_customers",
			joinColumns = { @JoinColumn(name = "bank_id") },
			inverseJoinColumns = { @JoinColumn(name = "customer_id") }
		)
	private Collection<CustomersPersistentDTO> customers = new ArrayList<CustomersPersistentDTO>();

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

	public Collection<CustomersPersistentDTO> getCustomers() {
		return customers;
	}

	public void setCustomers(Collection<CustomersPersistentDTO> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "BankPersistentDTO [id=" + id + ", name=" + name + ", customers=" + customers + "]";
	}
}
