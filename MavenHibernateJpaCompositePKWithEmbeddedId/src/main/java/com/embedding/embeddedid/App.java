package com.embedding.embeddedid;

import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.embedding.connection.HibernateUtil;
import com.embedding.persistent.StudentPersistent;
import com.embedding.persistent.NameEmbeddablePersistent;

public class App 
{
    public void addRecord(int id, String firstName, String lastName, String city) {
    	Session session = null;
    	Transaction tx = null;
    	
    	NameEmbeddablePersistent nep = new NameEmbeddablePersistent();
    	nep.setFirstName(firstName);
    	nep.setLastName(lastName);
    	
    	StudentPersistent sp = new StudentPersistent();
    	sp.setId(id);
    	sp.setName(nep);
    	sp.setCity(city);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.save(sp);
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
			List<StudentPersistent> list = session.createQuery("FROM StudentPersistent").list();
    		for(StudentPersistent sp : list) {
    			System.out.println("Id Is :: "+sp.getId());
    			System.out.println("Name Is :: "+sp.getName());
    			System.out.println("City Is :: "+sp.getCity());
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
    		StudentPersistent sp = session.get(StudentPersistent.class, id);
    		if(sp != null) {
    			System.out.println(sp);
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
    		System.out.println("2. Show Record");
    		System.out.println("3. Search Record By Id");
    		System.out.println("4. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.println("Enter Id :: ");
    			int id = sc.nextInt();
    			
    			System.out.println("Enter firstName :: ");
    			String firstName = sc.next();
    			
    			System.out.println("Enter lastName :: ");
    			String lastName = sc.next();
    			
    			System.out.println("Enter City :: ");
    			String city = sc.next();
    			
    			App addition = new App();
    			addition.addRecord(id, firstName, lastName, city);
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
