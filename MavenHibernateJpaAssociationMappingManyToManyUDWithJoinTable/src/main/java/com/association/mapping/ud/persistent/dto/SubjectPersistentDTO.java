package com.association.mapping.ud.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "subject_manytomany_ud_jt_mstr")
public class SubjectPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "subject_id_gene")
	@TableGenerator (
			name = "subject_id_gene",
			table = "SUBJECTID_MANYTOMANY_UD_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "subject_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "subject_name", length = 14, nullable = false, insertable = true, updatable = false)
	private String name;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SubjectPersistentDTO))
			return false;
		SubjectPersistentDTO other = (SubjectPersistentDTO) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubjectPersistentDTO [id=" + id + ", name=" + name + "]";
	}
}
