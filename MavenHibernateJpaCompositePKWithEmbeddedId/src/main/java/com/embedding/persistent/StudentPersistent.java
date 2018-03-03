package com.embedding.persistent;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;

@Entity
@Table(name = "student_embedding_mstr")
public class StudentPersistent {
	
	@Column(name = "student_embedding_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Embedded	// embeddable class NameEmbeddablePersistent
	@EmbeddedId	// for create composite primary key
	private NameEmbeddablePersistent name;
	
	@Column(name = "student_embedding_city", length = 15, unique = true, nullable = false, insertable = true, updatable = false)
	private String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NameEmbeddablePersistent getName() {
		return name;
	}

	public void setName(NameEmbeddablePersistent name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "StudentPersistent Records [id=" + id + ", name=" + name + ", city=" + city + "]";
	}
}
