package com.tarun.app;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tarun.dto.EmployeeDTO;

public class Demo {
	
	static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public static void add() {
		EmployeeDTO employee = new EmployeeDTO();	// transient state
		employee.setId(102);
		employee.setName("varun");
		employee.setSalary(23105.20);
		employee.setAttend(true);
		employee.setDob(new Date());
		Session session = sessionFactory.openSession();	// open connection
		Transaction trans = session.beginTransaction();
		session.save(employee);	// for record insertion
		trans.commit();
		session.close();	// connection close
		System.out.println("Recorded Added Successfully....");
	}
	
	public static void find() {
		Session session = sessionFactory.openSession();	// open connection
		EmployeeDTO employee = session.get(EmployeeDTO.class, 101);
		if(employee != null) {
			System.out.println(employee);
		}
		else {
			System.out.println("No Record Found....");
		}
		session.close();  // connection close
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		add();	
		find();
	}
}
