package com.association.mapping.bd.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "author_manytomany_bd_jtmb_mstr")
public class AuthorPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "author_id_gene")
	@TableGenerator (
			name = "author_id_gene",
			table = "AUTHORID_MANYTOMANY_BD_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "author_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int aid;
	
	@Column(name = "author_name", length = 14, nullable = false, insertable = true, updatable = false)
	private String aname;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "author_book_mtm",
			joinColumns = { @JoinColumn(name = "author_id") },
			inverseJoinColumns = { @JoinColumn(name = "book_id") }
		)
	private Collection<BookPersistentDTO> books = new ArrayList<BookPersistentDTO>();

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public Collection<BookPersistentDTO> getBooks() {
		return books;
	}

	public void setBooks(Collection<BookPersistentDTO> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aid;
		result = prime * result + ((aname == null) ? 0 : aname.hashCode());
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AuthorPersistentDTO))
			return false;
		AuthorPersistentDTO other = (AuthorPersistentDTO) obj;
		if (aid != other.aid)
			return false;
		if (aname == null) {
			if (other.aname != null)
				return false;
		} else if (!aname.equals(other.aname))
			return false;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AuthorPersistentDTO [aid=" + aid + ", aname=" + aname + ", books=" + books + "]";
	}
}
