package com.embedding.persistent;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NameEmbeddablePersistent implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "student_embedding_firstname", length = 11, nullable = false, insertable = true, updatable = false)
	private String firstName;
	
	@Column(name = "student_embedding_lastname", length = 11, nullable = false, insertable = true, updatable = false)
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "[firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
