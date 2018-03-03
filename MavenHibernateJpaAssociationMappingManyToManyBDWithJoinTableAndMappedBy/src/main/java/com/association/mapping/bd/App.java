package com.association.mapping.bd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.association.mapping.bd.persistent.dto.AuthorPersistentDTO;
import com.association.mapping.bd.persistent.dto.BookPersistentDTO;
import com.association.mapping.bd.connection.HibernateUtil;

public class App 
{
    public void addRecord(String aname1, String aname2, String aname3, String aname4, String bname1, String bname2, String bname3, String bname4) {
    	Session session = null;
    	Transaction tx = null;
    	Collection<AuthorPersistentDTO> authList = null;
    	
    	BookPersistentDTO bpDTO1 = new BookPersistentDTO();
    	bpDTO1.setBname(bname1);
    	bpDTO1.setAuthors(authList);
    	
    	BookPersistentDTO bpDTO2 = new BookPersistentDTO();
    	bpDTO2.setBname(bname2);
    	bpDTO2.setAuthors(authList);
    	
    	BookPersistentDTO bpDTO3 = new BookPersistentDTO();
    	bpDTO3.setBname(bname3);
    	bpDTO3.setAuthors(authList);
    	
    	BookPersistentDTO bpDTO4 = new BookPersistentDTO();
    	bpDTO4.setBname(bname4);
    	bpDTO4.setAuthors(authList);
    	
    	Collection<BookPersistentDTO> bookList1 = new ArrayList<BookPersistentDTO>();
    	bookList1.add(bpDTO1);
    	bookList1.add(bpDTO2);
    	
    	Collection<BookPersistentDTO> bookList2 = new ArrayList<BookPersistentDTO>();
    	bookList2.add(bpDTO4);
    	bookList2.add(bpDTO3);
    	bookList2.add(bpDTO2);
    	
    	Collection<BookPersistentDTO> bookList3 = new ArrayList<BookPersistentDTO>();
    	bookList3.add(bpDTO3);
    	bookList3.add(bpDTO4);
    	
    	Collection<BookPersistentDTO> bookList4 = new ArrayList<BookPersistentDTO>();
    	bookList4.add(bpDTO2);
    	bookList4.add(bpDTO4);
    	bookList4.add(bpDTO1);
    	bookList4.add(bpDTO3);
    	
    	AuthorPersistentDTO apDTO1 = new AuthorPersistentDTO();
    	apDTO1.setAname(aname1);
    	apDTO1.setBooks(bookList1);
    	
    	AuthorPersistentDTO apDTO2 = new AuthorPersistentDTO();
    	apDTO2.setAname(aname2);
    	apDTO2.setBooks(bookList2);
    	
    	AuthorPersistentDTO apDTO3 = new AuthorPersistentDTO();
    	apDTO3.setAname(aname3);
    	apDTO3.setBooks(bookList3);
    	
    	AuthorPersistentDTO apDTO4 = new AuthorPersistentDTO();
    	apDTO4.setAname(aname4);
    	apDTO4.setBooks(bookList4);
    	
    	authList = new ArrayList<AuthorPersistentDTO>();
    	authList.add(apDTO1);
    	authList.add(apDTO2);
    	authList.add(apDTO3);
    	authList.add(apDTO4);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.save(apDTO1);
    		session.save(apDTO3);
    		session.save(apDTO4);
    		session.save(apDTO2);
    		session.save(bpDTO1);
    		session.save(bpDTO2);
    		session.save(bpDTO3);
    		session.save(bpDTO4);
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
       		List<AuthorPersistentDTO> authList = session.createQuery("FROM AuthorPersistentDTO").list();
       		for(AuthorPersistentDTO apDTO : authList) {
       			System.out.println("Author Id Is :: "+apDTO.getAid());
       			System.out.println("Author Name Is :: "+apDTO.getAname());
       			Collection<BookPersistentDTO> bookList = apDTO.getBooks();
       			for(BookPersistentDTO bpDTO : bookList) {
       				System.out.println("Book Id Is :: "+bpDTO.getBid());
           			System.out.println("Book Name Is :: "+bpDTO.getBname());
       			}
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
   	public void showJoinTableRecords() {
       	Session session = null;
       	
       	// open and get session
       	session = HibernateUtil.getSessionFactory().openSession();
       	try {
       		List<AuthorPersistentDTO> authList = session.createQuery("FROM AuthorPersistentDTO").list();
       		for(AuthorPersistentDTO apDTO : authList) {
       			System.out.println("Author Id Is :: "+apDTO.getAid());
       			Collection<BookPersistentDTO> bookList = apDTO.getBooks();
       			for(BookPersistentDTO bpDTO : bookList) {
       				System.out.println("Book Id Is :: "+bpDTO.getBid());
       			}
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
    		AuthorPersistentDTO apDTO = session.get(AuthorPersistentDTO.class, id);
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
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main( String[] args ) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. Insert Record");
    		System.out.println("2. Show Both Entity Records");
    		System.out.println("3. Show Join Table Records");
    		System.out.println("4. Show Record By Id");
    		System.out.println("5. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter First Author Name :: ");
    			String aname1 = sc.next();
    			
    			System.out.print("Enter Second Author Name :: ");
    			String aname2 = sc.next();
    			
    			System.out.print("Enter Third Author Name :: ");
    			String aname3 = sc.next();
    			
    			System.out.print("Enter Fourth Author Name :: ");
    			String aname4 = sc.next();
    			
       			System.out.print("Enter First Book Name :: ");
    			String bname1 = sc.next();
    			
    			System.out.print("Enter Second Book Name :: ");
    			String bname2 = sc.next();
    			
    			System.out.print("Enter Third Book Name :: ");
    			String bname3 = sc.next();
    			
    			System.out.print("Enter Fourth Book Name :: ");
    			String bname4 = sc.next();
    			
    			App addition = new App();
    			addition.addRecord(aname1, aname2, aname3, aname4, bname1, bname2, bname3, bname4);
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
    			App showJT = new App();
    			showJT.showJoinTableRecords();
    			if(showJT == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 4) {
    			System.out.println("Enter Author Id :: ");
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
