package com.association.mapping.ud;

import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.association.mapping.ud.persistent.dto.StudentPersistentDTO;
import com.association.mapping.ud.persistent.dto.AddressPersistentDTO;
import com.association.mapping.ud.connection.HibernateUtil;

public class App 
{
    public void addRecord(String name, String country) {
    	Session session = null;
    	Transaction tx = null;
    	
    	AddressPersistentDTO apDTO = new AddressPersistentDTO();
    	apDTO.setCountry(country);
    	
    	StudentPersistentDTO spDTO = new StudentPersistentDTO();
    	spDTO.setName(name);
    	
    	// sets the uni-directional association
    	spDTO.setAddress(apDTO);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.persist(spDTO);
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
	
    @SuppressWarnings("unchecked")
  	public void showBothEntityRecords() {
      	Session session = null;
      	
      	// open and get session
      	session = HibernateUtil.getSessionFactory().openSession();
      	try {
      		List<StudentPersistentDTO> splist = session.createQuery("FROM StudentPersistentDTO").list();
      		for(StudentPersistentDTO spDTO : splist) {
      			System.out.println("Student Id Is :: "+spDTO.getId());
      			System.out.println("Student Name Is :: "+spDTO.getName());
      			AddressPersistentDTO apDTO = spDTO.getAddress();
      			System.out.println("Address Id Is :: "+apDTO.getId());
      			System.out.println("Country Is :: "+apDTO.getCountry());
      			System.out.println("");
      		}
      	}
      	catch(HibernateException he) {
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
    		StudentPersistentDTO spDTO = session.get(StudentPersistentDTO.class, id);
    		if(spDTO != null) {
    			System.out.println(spDTO);
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
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main( String[] args ) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. Insert Record");
    		System.out.println("2. Show Both Entity Records");
    		System.out.println("3. Search Record By Id");
    		System.out.println("4. Exit");
    		System.out.print("Enter The Choice :: ");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Student Name :: ");
    			String name = sc.next();
    			
    			System.out.print("Enter Student Country :: ");
    			String county = sc.next();
    			
    			App addition = new App();
    			addition.addRecord(name, county);
    			if(addition != null) {
    				System.out.println("Record Inserted Successfully");
    			}
    			else {
    				System.out.println("Unable To Insert Record");
    			}
    		}
    		else if(choice == 2) {
    			App showBE = new App();
    			showBE.showBothEntityRecords();
    			if(showBE == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 3) {
    			System.out.println("Enter Student Id :: ");
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
