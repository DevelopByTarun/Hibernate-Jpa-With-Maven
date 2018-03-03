package com.component.mapping.hbjpa.persistent.dto;

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
@Table(name = "student_component_mstr")
public class StudentPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "student_id_gene")
	@TableGenerator (
			name = "student_id_gene",
			table = "STUDENTID_COMPONENTMAP_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "student_id", precision = 2, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "student_name", length = 11, unique = true, nullable = false, insertable = true, updatable = false)
	private String name;
	
	@Embedded	// embeddable class StudentAddressDTO
	private StudentAddressDTO localAddress;
	
	@Embedded	// embeddable class StudentAddressDTO then attribute overrides
	@AttributeOverrides({
		@AttributeOverride(
			name = "city", 
			column = @Column(name = "student_permanent_city", length = 11, nullable = false, insertable = true, updatable = false)
			),
		@AttributeOverride(
			name = "pincode", 
			column = @Column(name = "student_permanent_pincode", length = 8, nullable = false, insertable = true, updatable = false)
				)
	})
	private StudentAddressDTO permanentAddress;

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

	public StudentAddressDTO getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(StudentAddressDTO localAddress) {
		this.localAddress = localAddress;
	}

	public StudentAddressDTO getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(StudentAddressDTO permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
}
