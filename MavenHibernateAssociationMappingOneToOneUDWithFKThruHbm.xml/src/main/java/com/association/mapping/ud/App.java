package com.association.mapping.ud;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.association.mapping.ud.persistent.dto.BookPersistentDTO;
import com.association.mapping.ud.persistent.dto.AuthorPersistentDTO;
import com.association.mapping.ud.connection.HibernateUtil;

public class App 
{
    public void addRecord(String title, int aid, String name, String email) {
    	Session session = null;
    	Transaction tx = null;
    	
    	AuthorPersistentDTO apDTO = new AuthorPersistentDTO();
    	apDTO.setAid(aid);
    	apDTO.setName(name);
    	apDTO.setEmail(email);
    	
    	BookPersistentDTO bpDTO = new BookPersistentDTO();
    	bpDTO.setTitle(title);
    	bpDTO.setPublishedDate(new Date());
    	
    	// sets the uni-directional association
    	bpDTO.setAuthor(apDTO);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.persist(bpDTO);
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
   	public void showBookRecordsUnidirectional() {
       	Session session = null;
       	
       	// open and get session
       	session = HibernateUtil.getSessionFactory().openSession();
       	try {
       		List<BookPersistentDTO> bplist = session.createQuery("FROM BookPersistentDTO").list();
       		for(BookPersistentDTO bpDTO : bplist) {
       			System.out.println("Book Details :-");
       			System.out.println("Book Id Is :: "+bpDTO.getBid());
       			System.out.println("Book Title Is :: "+bpDTO.getTitle());
       			System.out.println("Book Published Date Is :: "+bpDTO.getPublishedDate());
       			System.out.println("Author Id Is :: "+bpDTO.getAuthor().getAid());
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
    
    @SuppressWarnings("unchecked")
   	public void showAuthorRecords() {
       	Session session = null;
       	
       	// open and get session
       	session = HibernateUtil.getSessionFactory().openSession();
       	try {
       		List<BookPersistentDTO> bplist = session.createQuery("FROM BookPersistentDTO").list();
       		for(BookPersistentDTO bpDTO : bplist) {
       			AuthorPersistentDTO apDTO = bpDTO.getAuthor();
       			System.out.println("Author Details :-");
       			System.out.println("Author Id Is :: "+apDTO.getAid());
       			System.out.println("Author Name Is :: "+apDTO.getName());
       			System.out.println("Author Email Is :: "+apDTO.getEmail());
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
    
    @SuppressWarnings("unchecked")
   	public void showBothEntityRecords() {
       	Session session = null;
       	
       	// open and get session
       	session = HibernateUtil.getSessionFactory().openSession();
       	try {
       		List<BookPersistentDTO> bplist = session.createQuery("FROM BookPersistentDTO").list();
       		for(BookPersistentDTO bpDTO : bplist) {
       			System.out.println("Book Details :-");
       			System.out.println("Book Id Is :: "+bpDTO.getBid());
       			System.out.println("Book Title Is :: "+bpDTO.getTitle());
       			System.out.println("Book Published Date Is :: "+bpDTO.getPublishedDate());
       			AuthorPersistentDTO apDTO = bpDTO.getAuthor();
       			System.out.println("Author Details :-");
       			System.out.println("Author Id Is :: "+apDTO.getAid());
       			System.out.println("Author Name Is :: "+apDTO.getName());
       			System.out.println("Author Email Is :: "+apDTO.getEmail());
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
    
    public void getRecordByBookId(int bid) {
		Session session = null;
    	Transaction tx = null;
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		BookPersistentDTO bpDTO = session.get(BookPersistentDTO.class, bid);
    		if(bpDTO != null) {
    			System.out.println(bpDTO);
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
    
    public void getRecordByAuthorId(int aid) {
		Session session = null;
    	Transaction tx = null;
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		AuthorPersistentDTO apDTO = session.get(AuthorPersistentDTO.class, aid);
    		if(apDTO != null) {
    			System.out.println(apDTO);
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
    
	@SuppressWarnings({ "resource", "unused" })
	public static void main( String[] args ) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. Insert Record");
    		System.out.println("2. Show Book Records Unidirectional");
    		System.out.println("3. Show Author Records");
    		System.out.println("4. Show Both Entity Records");
    		System.out.println("5. Search Record By Book Id");
    		System.out.println("6. Search Record By Author Id");
    		System.out.println("7. Exit");
    		System.out.print("Enter The Choice :: ");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Book Title :: ");
    			String title = sc.next();
    			
    			System.out.print("Enter Author Id :: ");
    			int id = sc.nextInt();
    			
    			System.out.print("Enter Author Name :: ");
    			String name = sc.next();
    			
    			System.out.print("Enter Author Email :: ");
    			String email = sc.next();
    		
    			App addition = new App();
    			addition.addRecord(title, id, name, email);
    			if(addition != null) {
    				System.out.println("Record Inserted Successfully");
    			}
    			else {
    				System.out.println("Unable To Insert Record");
    			}
    		}
    		else if(choice == 2) {
    			App showBookUD = new App();
    			showBookUD.showBookRecordsUnidirectional();
    			if(showBookUD == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 3) {
    			App showAuth = new App();
    			showAuth.showAuthorRecords();
    			if(showAuth == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 4) {
    			App showBoth = new App();
    			showBoth.showBothEntityRecords();
    			if(showBoth == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 5) {
    			System.out.println("Enter Book Id :: ");
    			int bid = sc.nextInt();
    			
    			App searchBID = new App();
    			searchBID.getRecordByBookId(bid);
    		}
    		else if(choice == 6) {
    			System.out.println("Enter Author Id :: ");
    			int aid = sc.nextInt();
    			
    			App searchAID = new App();
    			searchAID.getRecordByAuthorId(aid);
    		}
    		else {
    			System.out.println("Good Byeeee......");
    			return;
    		}
		}
	}
}


