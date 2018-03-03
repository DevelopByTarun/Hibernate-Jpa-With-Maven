package com.association.mapping.ud.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "student_manytomany_ud_jt_mstr")
public class StudentPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "student_id_gene")
	@TableGenerator (
			name = "student_id_gene",
			table = "STUDENTID_MANYTOMANY_UD_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "student_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "student_name", length = 14, unique = true, nullable = false, insertable = true, updatable = false)
	private String name;
	
	@Lob
	private String remarks;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "student_subject_mtm",
			joinColumns = { @JoinColumn(name = "student_id") },
			inverseJoinColumns = { @JoinColumn(name = "subject_id") }
		)
	private Collection<SubjectPersistentDTO> subjects = new ArrayList<SubjectPersistentDTO>();

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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Collection<SubjectPersistentDTO> getSubjects() {
		return subjects;
	}

	public void setSubjects(Collection<SubjectPersistentDTO> subjects) {
		this.subjects = subjects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StudentPersistentDTO))
			return false;
		StudentPersistentDTO other = (StudentPersistentDTO) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (subjects == null) {
			if (other.subjects != null)
				return false;
		} else if (!subjects.equals(other.subjects))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentPersistentDTO [id=" + id + ", name=" + name + ", remarks=" + remarks + ", subjects=" + subjects
				+ "]";
	}
}
