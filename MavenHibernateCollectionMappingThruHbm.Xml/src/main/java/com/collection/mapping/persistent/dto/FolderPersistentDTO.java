package com.collection.mapping.persistent.dto;

import java.util.HashSet;
import java.util.Set;

public class FolderPersistentDTO {
	
	private int id;
	
	private String name;
	
	private Set<FileCollectionDTO> files = new HashSet<FileCollectionDTO>();

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

	public Set<FileCollectionDTO> getFiles() {
		return files;
	}

	public void setFiles(Set<FileCollectionDTO> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "FolderPersistentDTO [id=" + id + ", name=" + name + ", files=" + files + "]";
	}
}
