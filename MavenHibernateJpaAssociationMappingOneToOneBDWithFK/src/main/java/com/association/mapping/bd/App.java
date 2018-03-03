package com.association.mapping.bd;

import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.association.mapping.bd.connection.HibernateUtil;
import com.association.mapping.bd.persistent.dto.CountryPersistentDTO;
import com.association.mapping.bd.persistent.dto.PresidentPersistentDTO;

public class App 
{
	public void addRecord(String cname, String pname) {
		Session session = null;
    	Transaction tx = null;
    	
    	CountryPersistentDTO cpDTO = new CountryPersistentDTO();
    	cpDTO.setCname(cname);
    	
    	PresidentPersistentDTO ppDTO = new PresidentPersistentDTO();
    	ppDTO.setPname(pname);
    	
    	// sets the bi-directional association
    	cpDTO.setPresident(ppDTO);
    	ppDTO.setCountry(cpDTO);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.persist(cpDTO);
    		session.persist(ppDTO);
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
	public void showRecordsUnidirectional() {
    	Session session = null;
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		List<CountryPersistentDTO> cplist = session.createQuery("FROM CountryPersistentDTO").list();
    		for(CountryPersistentDTO cpDTO : cplist) {
    			System.out.println("Country Id Is :: "+cpDTO.getCid());
    			System.out.println("Country Name Is :: "+cpDTO.getCname());
    			System.out.println("President Id Is :: "+cpDTO.getPresident().getPid());
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
	public void showRecordsBidirectional() {
    	Session session = null;
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		List<PresidentPersistentDTO> pplist = session.createQuery("FROM PresidentPersistentDTO").list();
    		for(PresidentPersistentDTO ppDTO : pplist) {
    			System.out.println("President Id Is :: "+ppDTO.getPid());
    			System.out.println("President Name Is :: "+ppDTO.getPname());
    			System.out.println("Country Id Is :: "+ppDTO.getCountry().getCid());
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
    		List<CountryPersistentDTO> cplist = session.createQuery("FROM CountryPersistentDTO").list();
    		for(CountryPersistentDTO cpDTO : cplist) {
    			System.out.println("Country Id Is :: "+cpDTO.getCid());
    			System.out.println("Country Name Is :: "+cpDTO.getCname());
    			PresidentPersistentDTO ppDTO = cpDTO.getPresident();
    			System.out.println("President Id Is :: "+ppDTO.getPid());
        		System.out.println("President Name Is :: "+ppDTO.getPname());
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
	
	 public void getRecordByCountryId(int cid) {
			Session session = null;
	    	Transaction tx = null;
	    	
	    	// open and get session
	    	session = HibernateUtil.getSessionFactory().openSession();
	    	try {
	    		tx = session.beginTransaction();
	    		CountryPersistentDTO cpDTO = session.get(CountryPersistentDTO.class, cid);
	    		if(cpDTO != null) {
	    			System.out.println(cpDTO);
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
	
	 public void getRecordByPresidentId(int pid) {
			Session session = null;
	    	Transaction tx = null;
	    	
	    	// open and get session
	    	session = HibernateUtil.getSessionFactory().openSession();
	    	try {
	    		tx = session.beginTransaction();
	    		PresidentPersistentDTO ppDTO = session.get(PresidentPersistentDTO.class, pid);
	    		if(ppDTO != null) {
	    			System.out.println(ppDTO);
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
    		System.out.println("2. Show Records Unidirectional");
    		System.out.println("3. Show Records Bidirectional");
    		System.out.println("4. Show Both Entity Records");
    		System.out.println("5. Search Record Unidirectional");
    		System.out.println("6. Search Record Bidirectional");
    		System.out.println("7. Exit");
    		System.out.print("Enter The Choice :: ");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Country Name :: ");
    			String cname = sc.next();
    			
    			System.out.print("Enter President Name :: ");
    			String pname = sc.next();
    			
    			App addition = new App();
    			addition.addRecord(cname, pname);
    			if(addition != null) {
    				System.out.println("Record Inserted Successfully");
    			}
    			else {
    				System.out.println("Unable To Insert Record");
    			}
    		}
    		else if(choice == 2) {
    			App showUD = new App();
    			showUD.showRecordsUnidirectional();
    			if(showUD == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 3) {
    			App showBD = new App();
    			showBD.showRecordsBidirectional();
    			if(showBD == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 4) {
    			App showBE = new App();
    			showBE.showBothEntityRecords();
    			if(showBE == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 5) {
    			System.out.println("Enter Country Id :: ");
    			int cid = sc.nextInt();
    			
    			App searchUD = new App();
    			searchUD.getRecordByCountryId(cid);
    		}
    		else if(choice == 6) {
    			System.out.println("Enter President Id :: ");
    			int pid = sc.nextInt();
    			
    			App searchBD = new App();
    			searchBD.getRecordByPresidentId(pid);
    		}
    		else {
    			System.out.println("Good Byeeee......");
    			return;
    		}
		}
	}
}
