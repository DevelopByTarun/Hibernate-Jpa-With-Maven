package com.association.mapping.ud.persistent.dto;

public class AuthorPersistentDTO {
	
	private int aid;
	
	private String name;
	
	private String email;

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "AuthorPersistentDTO [aid=" + aid + ", name=" + name + ", email=" + email + "]";
	}
}
