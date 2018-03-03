package com.tarun.demo;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tarun.demo.connection.HibernateUtil;
import com.tarun.demo.persistent.CompanyPersistent;

public class App 
{
	public void addCompanyRecords(String n, String p, String a ) {
    	Session session = null;
    	Transaction tx = null;
    	
    	CompanyPersistent cp = new CompanyPersistent();
		cp.setName(n);
		cp.setProduct(p);
		cp.setAddress(a);
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			session.save(cp);
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
	
	public void updateCompanyRecords(int i, String n, String p, String a) {
		Session session = null;
		Transaction tx = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			CompanyPersistent cp = session.get(CompanyPersistent.class, i);
			cp.setName(n);
			cp.setProduct(p);
			cp.setAddress(a);
			session.update(cp);
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
	
	public void deleteCompanyRecords(int id) {
		Session session = null;
		Transaction tx = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			tx = session.beginTransaction();
			CompanyPersistent cp = session.get(CompanyPersistent.class, id);
			session.delete(cp);
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
	
	public void showCompanyRecords() {
		Session session = null;
		Transaction tx = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<CompanyPersistent> list = (List<CompanyPersistent>) session.createQuery("FROM CompanyPersistent").list();
			for(CompanyPersistent cp : list) {
				System.out.println("Id Is :: "+cp.getId());
				System.out.println("Name Is :: "+cp.getName());
				System.out.println("Product Is :: "+cp.getProduct());
				System.out.println("Address Is :: "+cp.getAddress());
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
	
	public void showCompanyRecordsById(int ids) {
		Session session = null;
		Transaction tx = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			CompanyPersistent cp = session.get(CompanyPersistent.class, ids);
			if(cp != null) {
				System.out.println(cp);
			}
			else {
				System.out.println("Unable To Search Records");
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
    		System.out.println("2. Update Record");
    		System.out.println("3. Delete Record");
    		System.out.println("4. Show Record");
    		System.out.println("5. Search Record By Id");
    		System.out.println("6. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.println("Enter Name :: ");
    			String nm = sc.next();
    			
    			System.out.println("Enter Product :: ");
    			String pd = sc.next();
    			
    			System.out.println("Enter Address :: ");
    			String ad = sc.next();
    			
    			App addition = new App();
    			addition.addCompanyRecords(nm, pd, ad);
    			if(addition != null) {
    				System.out.println("Records Inserted Successfully");
    			}
    			else {
    				System.out.println("Unable To Insert Records");
    			}
    		}
    		else if(choice == 2) {
    			System.out.println("Enter Id :: ");
    			int i = sc.nextInt();
    			
    			System.out.println("Enter Name :: ");
    			String nm = sc.next();
    			
    			System.out.println("Enter Product :: ");
    			String pd = sc.next();
    			
    			System.out.println("Enter Address :: ");
    			String ad = sc.next();
    			
    			App updt = new App();
    			updt.updateCompanyRecords(i, nm, pd, ad);
    			if(updt != null) {
    				System.out.println("Records Updated Successfully");
    			}
    			else {
    				System.out.println("Unable To Update Records");
    			}
    		}
    		else if(choice == 3) {
    			System.out.println("Enter Id :: ");
    			int i = sc.nextInt();
    			
    			App delet = new App();
    			delet.deleteCompanyRecords(i);
    			if(delet != null) {
    				System.out.println("Records Deleted Successfully");
    			}
    			else {
    				System.out.println("Unable To Delete Records");
    			}
    		}
    		else if(choice == 4) {
    			App show = new App();
    			show.showCompanyRecords();
    			if(show != null) {
    				System.out.println(show);
    			}
    			else {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 5) {
    			System.out.println("Enter Id :: ");
    			int i = sc.nextInt();
    			
    			App search = new App();
    			search.showCompanyRecordsById(i);
    		}
    		else {
    			System.out.println("Good Byeeee....");
    			return;
    		}
		}
	}
}
