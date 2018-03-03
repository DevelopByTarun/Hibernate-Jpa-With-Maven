package com.association.mapping.bd.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "book_manytomany_bd_jtmb_mstr")
public class BookPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "book_id_gene")
	@TableGenerator (
			name = "book_id_gene",
			table = "BOOKID_MANYTOMANY_BD_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "book_id", precision = 3, nullable = false, insertable = true, updatable = false)
	private int bid;
	
	@Column(name = "book_name", length = 14, nullable = false, insertable = true, updatable = false)
	private String bname;
	
	@ManyToMany(mappedBy = "books")
	private Collection<AuthorPersistentDTO> authors = new ArrayList<AuthorPersistentDTO>();

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Collection<AuthorPersistentDTO> getAuthors() {
		return authors;
	}

	public void setAuthors(Collection<AuthorPersistentDTO> authors) {
		this.authors = authors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + bid;
		result = prime * result + ((bname == null) ? 0 : bname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BookPersistentDTO))
			return false;
		BookPersistentDTO other = (BookPersistentDTO) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (bid != other.bid)
			return false;
		if (bname == null) {
			if (other.bname != null)
				return false;
		} else if (!bname.equals(other.bname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookPersistentDTO [bid=" + bid + ", bname=" + bname + "]";
	}
}
