package com.collection.mapping.persistent;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentEmbeddableDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "student_id", precision = 4, nullable = false, insertable = true, updatable = false)
	private int studId;
	
	@Column(name = "student_name", length = 11, nullable = false, insertable = true, updatable = false)
	private String studName;

	public int getStudId() {
		return studId;
	}

	public void setStudId(int studId) {
		this.studId = studId;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	@Override
	public String toString() {
		return "[studId=" + studId + ", studName=" + studName + "]";
	}
}
