package com.tarun.demo2.persistent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import javax.persistence.Column;

@Entity
@Table(name = "orders_mstr")
public class OrderPersistent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id", precision = 2, nullable = false, insertable = true, updatable = true)
	private int id;
	
	@Column(name = "order_name", length = 15, unique = true, nullable = false, insertable = true, updatable = true)
	private String name;
	
	@Column(name = "order_payment", precision = 7, scale = 3, nullable = false, insertable = true, updatable = true)
	private double payment;
	
	@Type(type = "yes_no")
	@Column(name = "order_confirm", nullable = false, insertable = true, updatable = true)
	private boolean confirm;

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

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	@Override
	public String toString() {
		return "OrderPersistent Records [id=" + id + ", name=" + name + ", payment=" + payment + ", confirm=" + confirm + "]";
	}
}
