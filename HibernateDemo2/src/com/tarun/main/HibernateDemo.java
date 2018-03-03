package com.tarun.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tarun.dto.StudentDTO;

import java.util.Date;
import java.util.Scanner;

public class HibernateDemo {
	
	SessionFactory sessionFactory;
	
	HibernateDemo() {
		load();
	}
	
	void load() {
		Configuration config = new Configuration().configure();
		sessionFactory = config.buildSessionFactory();
	}
	
	public void addRecord() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Id :: ");
		int ids = sc.nextInt();
		
		System.out.println("Enter Name :: ");
		String nm = sc.next();
		
		System.out.println("Enter Styfund :: ");
		double sf = sc.nextDouble();
		
		System.out.println("Enter Attendence :: ");
		boolean attend = sc.nextBoolean();
		
		System.out.println("Enter Donation :: ");
		int donate = sc.nextInt();
		
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(ids);
		studentDTO.setName(nm);
		studentDTO.setStyfund(sf);
		studentDTO.setAttendence(attend);
		studentDTO.setDob(new Date());
		studentDTO.setDonation(donate);
		
		Session session = sessionFactory.openSession();	// open connection
		Transaction trans = session.beginTransaction();
		session.save(studentDTO);	// insert record
		
//		studentDTO.setStyfund(studentDTO.getStyfund() + 500);
//		studentDTO.setAttendence(attend);    // manually updated
		
		trans.commit();  	// saving transaction
		session.close();   // close connection
		System.out.println("Recorded Added Successfully....");
		
		sc.close();   // scanner close
	}	
	
	void searchRecord(int id) {
		Integer i = id;
		Session session = sessionFactory.openSession();	// open connection
		StudentDTO studentDTO = session.load(StudentDTO.class, i);
		if(studentDTO != null) {
			System.out.println(studentDTO);
		}
		else {
			System.out.println("Record Not Found....");
		}
		session.close();	// close connection
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HibernateDemo hd = new HibernateDemo();
		
		// call for insert record
//		hd.addRecord();
		
		// call for search record
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Id :: ");
		Integer is = sc.nextInt();
		hd.searchRecord(is);
		sc.close();		// scanner close
	}
}
