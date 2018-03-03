package com.association.mapping.ud.persistent.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "orders_manytoone_ud_jc_mstr")
public class OrdersPersistentDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "order_id_gene")
	@TableGenerator (
			name = "order_id_gene",
			table = "ORDERID_MANYTOONE_UD_TABLE_GENERATOR",
			pkColumnName = "myid",
			valueColumnName = "next",
			pkColumnValue = "id",
			allocationSize = 1
		)
	@Column(name = "order_id", precision = 2, nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(name = "order_itemName", length = 14, unique = true, nullable = false, insertable = true, updatable = false)
	private String itemName;
	
	@Column(name = "order_paymentMode", length = 14, nullable = false, insertable = true, updatable = false)
	private String paymentMode;
	
	@Column(name = "order_quantity", precision = 5, nullable = false, insertable = true, updatable = false)
	private int quantity;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private CustomerPersistentDTO customer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CustomerPersistentDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerPersistentDTO customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "OrdersPersistentDTO [id=" + id + ", itemName=" + itemName + ", paymentMode=" + paymentMode
				+ ", quantity=" + quantity + ", customer=" + customer + "]";
	}
}
