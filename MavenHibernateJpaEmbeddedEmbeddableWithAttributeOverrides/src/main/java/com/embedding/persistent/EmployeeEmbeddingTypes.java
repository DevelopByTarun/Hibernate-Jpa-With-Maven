package com.embedding.persistent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

@Entity
@Table(name = "emp_embeddable_embedded_mstr")
public class EmployeeEmbeddingTypes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "emp_id_gene")
	@TableGenerator (
			name = "emp_id_gene",
			table = "EMPID_EMBEDDING_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "emp_embedding_id", precision = 4, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "emp_embedding_name", length = 11, unique = true, nullable = false, insertable = true, updatable = false)
	private String name;
	
	@Column(name = "emp_embedding_salary", precision = 8, scale = 2, nullable = false, insertable = true, updatable = false)
	private double salary;
	
	@Embedded	// embeddable class AddressEmbedding
	private AddressEmbedding homeAddress;
	
	@Embedded	// embeddable class AddressEmbedding then attribute overrides
	@AttributeOverrides({
		@AttributeOverride(
			name = "city", 
			column = @Column(name = "emp_embedding_office_city", length = 11, nullable = false, insertable = true, updatable = false)
			),
		@AttributeOverride(
			name = "state", 
			column = @Column(name = "emp_embedding_office_state", length = 11, nullable = false, insertable = true, updatable = false)
				),
		@AttributeOverride(
			name = "pincode", 
			column = @Column(name = "emp_embedding_office_pincode", length = 8, nullable = false, insertable = true, updatable = false)
				)
	})
	private AddressEmbedding officeAddress;

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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public AddressEmbedding getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(AddressEmbedding homeAddress) {
		this.homeAddress = homeAddress;
	}

	public AddressEmbedding getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(AddressEmbedding officeAddress) {
		this.officeAddress = officeAddress;
	}

	@Override
	public String toString() {
		return "EmployeeEmbeddingTypes [id=" + id + ", name=" + name + ", salary=" + salary + ", homeAddress="
				+ homeAddress + ", officeAddress=" + officeAddress + "]";
	}
}
