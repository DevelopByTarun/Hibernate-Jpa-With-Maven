package com.embedding.types;

import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.embedding.persistent.EmployeeEmbeddingTypes;
import com.embedding.persistent.AddressEmbedding;
import com.embedding.connection.HibernateUtil;

public class App 
{
    public void addRecord(String name, double salary, String hmCity, String hmState, String hmPincode, String ofCity, String ofState, String ofPincode) {
    	Session session = null;
    	Transaction tx = null;
    	
    	AddressEmbedding homeAddress = new AddressEmbedding();
    	homeAddress.setCity(hmCity);
    	homeAddress.setState(hmState);
    	homeAddress.setPincode(hmPincode);
    	
    	AddressEmbedding officeAddress = new AddressEmbedding();
    	officeAddress.setCity(ofCity);
    	officeAddress.setState(ofState);
    	officeAddress.setPincode(ofPincode);
    	
    	EmployeeEmbeddingTypes eet = new EmployeeEmbeddingTypes();
    	eet.setName(name);
    	eet.setSalary(salary);
    	eet.setHomeAddress(homeAddress);
    	eet.setOfficeAddress(officeAddress);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.save(eet);
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
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		@SuppressWarnings("unchecked")
			List<EmployeeEmbeddingTypes> list = session.createQuery("FROM EmployeeEmbeddingTypes").list();
    		for(EmployeeEmbeddingTypes eet : list) {
    			System.out.println("Id Is :: "+eet.getId());
    			System.out.println("Name Is :: "+eet.getName());
    			System.out.println("Salary Is :: "+eet.getSalary());
    			System.out.println("Home Address Is :: "+eet.getHomeAddress());
    			System.out.println("Office Address Is :: "+eet.getOfficeAddress());
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
	
	public void getRecordById(int id) {
		Session session = null;
    	Transaction tx = null;
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		EmployeeEmbeddingTypes eet = session.get(EmployeeEmbeddingTypes.class, id);
    		if(eet != null) {
    			System.out.println(eet);
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
    			
    			System.out.println("Enter Salary :: ");
    			double sal = sc.nextDouble();
    			
    			System.out.println("Enter Home City :: ");
    			String hmCity = sc.next();
    			
    			System.out.println("Enter Home State :: ");
    			String hmState = sc.next();
    			
    			System.out.println("Enter Home Pincode :: ");
    			String hmPincode = sc.next();
    			
    			System.out.println("Enter Office City :: ");
    			String ofCity = sc.next();
    			
    			System.out.println("Enter Office State :: ");
    			String ofState = sc.next();
    			
    			System.out.println("Enter Office Pincode :: ");
    			String ofPincode = sc.next();
    			
    			App addition = new App();
    			addition.addRecord(nm, sal, hmCity, hmState, hmPincode, ofCity, ofState, ofPincode);
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
    			int id = sc.nextInt();
    			
    			App search = new App();
    			search.getRecordById(id);
    		}
    		else {
    			System.out.println("Good Byeeee......");
    			return;
    		}
		}
	}
}
