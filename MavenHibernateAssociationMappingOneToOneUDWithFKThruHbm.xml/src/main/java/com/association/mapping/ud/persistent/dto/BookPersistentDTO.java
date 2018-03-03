package com.association.mapping.ud.persistent.dto;

import java.util.Date;

public class BookPersistentDTO {
	
	private int bid;
	
	private String title;
	
	private Date publishedDate;
	
	private AuthorPersistentDTO author;

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public AuthorPersistentDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorPersistentDTO author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "BookPersistentDTO [bid=" + bid + ", title=" + title + ", publishedDate=" + publishedDate + ", author="
				+ author + "]";
	}
}
