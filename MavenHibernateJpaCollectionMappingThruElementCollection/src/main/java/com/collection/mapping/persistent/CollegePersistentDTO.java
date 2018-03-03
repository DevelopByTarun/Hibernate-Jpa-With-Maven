package com.collection.mapping.persistent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;

@Entity
@Table(name = "college_collectionmap_mstr")
public class CollegePersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "college_id_gene")
	@TableGenerator (
			name = "college_id_gene",
			table = "COLLEGEID_COLLECTIONMAP_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "college_id", precision = 4, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "college_name", length = 21, unique = true, nullable = false, insertable = true, updatable = false)
	private String name;
	
	@ElementCollection
	@CollectionTable(name ="College_Students_Mapping_Mstr", joinColumns = @JoinColumn(name ="college_id"))
	private List<StudentEmbeddableDTO> collegeStudents = new ArrayList<StudentEmbeddableDTO>();

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

	public List<StudentEmbeddableDTO> getCollegeStudents() {
		return collegeStudents;
	}

	public void setCollegeStudents(List<StudentEmbeddableDTO> collegeStudents) {
		this.collegeStudents = collegeStudents;
	}

	@Override
	public String toString() {
		return "CollegePersistentDTO [id=" + id + ", name=" + name + ", collegeStudents=" + collegeStudents + "]";
	}
}
