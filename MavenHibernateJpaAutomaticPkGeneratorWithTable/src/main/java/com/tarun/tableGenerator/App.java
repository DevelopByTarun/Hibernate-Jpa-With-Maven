package com.tarun.tableGenerator;

import com.tarun.persistent.EmployeeAutomaticId;
import com.tarun.connection.HibernateUtil;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App 
{
    public void addRecord(String name, String city) {
    	Session session = null;
    	Transaction tx = null;
    	
    	EmployeeAutomaticId eai = new EmployeeAutomaticId();
    	eai.setName(name);
    	eai.setCity(city);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.save(eai);
    		tx.commit();
    	}
    	catch(HibernateException he) {
			if(tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
    }
	
	public void showRecords() {
		Session session = null;
    	Transaction tx = null;
    	
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		@SuppressWarnings("unchecked")
			List<EmployeeAutomaticId> list = session.createQuery("FROM EmployeeAutomaticId").list();
    		for(EmployeeAutomaticId eai : list) {
    			System.out.println("Id Is :: "+ eai.getId());
    			System.out.println("Name Is :: "+ eai.getName());
    			System.out.println("City Is :: "+ eai.getCity());
    			System.out.println("");
    		}
    		tx.commit();
    	}
    	catch(HibernateException he) {
			if(tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
	}
	
	public void showRecordById(int id) {
		Session session = null;
    	Transaction tx = null;
    	
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		EmployeeAutomaticId eai = session.get(EmployeeAutomaticId.class, id);
    		if(eai != null) {
    			System.out.println(eai);
    		}
    		else {
    			System.out.println("Unable To Show Records");
    		}
    		tx.commit();
    	}
    	catch(HibernateException he) {
			if(tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
	}
	
	@SuppressWarnings("unused")
	public static void main( String[] args ) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. Insert Record");
    		System.out.println("2. Show Record");
    		System.out.println("3. Search Record By Id");
    		System.out.println("4. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.println("Enter Name :: ");
    			String nm = sc.next();
    			
    			System.out.println("Enter City :: ");
    			String ct = sc.next();
    			
    			App addition = new App();
    			addition.addRecord(nm, ct);
    			if(addition != null) {
    				System.out.println("Record Inserted Successfully");
    			}
    			else {
    				System.out.println("Unable To Insert Record");
    			}
    		}
    		else if(choice == 2) {
    			App show = new App();
    			show.showRecords();
    			if(show == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 3) {
    			System.out.println("Enter Id :: ");
    			int i = sc.nextInt();
    			
    			App search = new App();
    			search.showRecordById(i);
    		}
    		else {
    			System.out.println("Good Byeeee......");
    			return;
    		}
		}
	}
}
