package com.association.mapping.bd;

import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.association.mapping.bd.persistent.dto.EmployeePersistentDTO;
import com.association.mapping.bd.persistent.dto.AddressPersistentDTO;
import com.association.mapping.bd.connection.HibernateUtil;

public class App 
{
    public void addRecord(String name, String country) {
    	Session session = null;
    	Transaction tx = null;
    	
    	EmployeePersistentDTO epDTO = new EmployeePersistentDTO();
    	epDTO.setName(name);
    	
    	AddressPersistentDTO apDTO = new AddressPersistentDTO();
    	apDTO.setCountry(country);
    	
    	// sets the bi-directional association
    	epDTO.setAddress(apDTO);
    	apDTO.setEmployee(epDTO);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.persist(epDTO);
    		session.persist(apDTO);
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
       		List<EmployeePersistentDTO> eplist = session.createQuery("FROM EmployeePersistentDTO").list();
       		for(EmployeePersistentDTO epDTO : eplist) {
       			System.out.println("Employee Details :-");
       			System.out.println("Employee Id Is :: "+epDTO.getEid());
       			System.out.println("Employee Name Is :: "+epDTO.getName());
       			System.out.println("Address Id Is :: "+epDTO.getAddress().getAid());
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
       		List<AddressPersistentDTO> aplist = session.createQuery("FROM AddressPersistentDTO").list();
       		for(AddressPersistentDTO apDTO : aplist) {
       			System.out.println("Address Details:-");
       			System.out.println("Address Id Is :: "+apDTO.getAid());
       			System.out.println("Country Is :: "+apDTO.getCountry());
       			System.out.println("Employee Id Is :: "+apDTO.getEmployee().getEid());
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
       		List<EmployeePersistentDTO> eplist = session.createQuery("FROM EmployeePersistentDTO").list();
       		for(EmployeePersistentDTO epDTO : eplist) {
       			System.out.println("Employee Details:-");
       			System.out.println("Employee Id Is :: "+epDTO.getEid());
       			System.out.println("Employee Name Is :: "+epDTO.getName());
       			System.out.println("Address Id Is :: "+epDTO.getAddress().getAid());
       			AddressPersistentDTO apDTO = epDTO.getAddress();
       			System.out.println("Address Details :-");
       			System.out.println("Address Id Is :: "+apDTO.getAid());
       			System.out.println("Country Is :: "+apDTO.getCountry());
       			System.out.println("Employee Id Is :: "+apDTO.getEmployee().getEid());
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
	
    public void getRecordByEmployeeId(int eid) {
		Session session = null;
    	Transaction tx = null;
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		EmployeePersistentDTO epDTO = session.get(EmployeePersistentDTO.class, eid);
    		if(epDTO != null) {
    			System.out.println(epDTO);
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
    
    public void getRecordByAddressId(int aid) {
		Session session = null;
    	Transaction tx = null;
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		AddressPersistentDTO apDTO = session.get(AddressPersistentDTO.class, aid);
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
    		System.out.println("2. Show Records Unidirectional");
    		System.out.println("3. Show Records Bidirectional");
    		System.out.println("4. Show Both Entity Records");
    		System.out.println("5. Search Record By Employee Id");
    		System.out.println("6. Search Record By Address Id");
    		System.out.println("7. Exit");
    		System.out.print("Enter The Choice :: ");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Employee Name :: ");
    			String name = sc.next();
    			
    			System.out.print("Enter Country :: ");
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
    			App showBoth = new App();
    			showBoth.showBothEntityRecords();
    			if(showBoth == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 5) {
    			System.out.println("Enter Employee Id :: ");
    			int eid = sc.nextInt();
    			
    			App searchEID = new App();
    			searchEID.getRecordByEmployeeId(eid);
    		}
    		else if(choice == 6) {
    			System.out.println("Enter Address Id :: ");
    			int aid = sc.nextInt();
    			
    			App searchAID = new App();
    			searchAID.getRecordByAddressId(aid);
    		}
    		else {
    			System.out.println("Good Byeeee......");
    			return;
    		}
		}
	}
}
