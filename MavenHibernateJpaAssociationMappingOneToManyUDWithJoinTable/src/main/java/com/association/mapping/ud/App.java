package com.association.mapping.ud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.association.mapping.ud.persistent.dto.BankPersistentDTO;
import com.association.mapping.ud.persistent.dto.CustomersPersistentDTO;
import com.association.mapping.ud.connection.HibernateUtil;

public class App 
{
    public void addRecord(String name, String cname1, String cname2, String cname3, String cname4) {
    	Session session = null;
    	Transaction tx = null;
    	
    	CustomersPersistentDTO cpDTO1 = new CustomersPersistentDTO();
    	cpDTO1.setCname(cname1);
    	
    	CustomersPersistentDTO cpDTO2 = new CustomersPersistentDTO();
    	cpDTO2.setCname(cname2);
    	
    	CustomersPersistentDTO cpDTO3 = new CustomersPersistentDTO();
    	cpDTO3.setCname(cname3);
    	
    	CustomersPersistentDTO cpDTO4 = new CustomersPersistentDTO();
    	cpDTO4.setCname(cname4);
    	
    	Collection<CustomersPersistentDTO> cplist = new ArrayList<CustomersPersistentDTO>();
    	cplist.add(cpDTO1);
    	cplist.add(cpDTO2);
    	cplist.add(cpDTO3);
    	cplist.add(cpDTO4);
    	
    	BankPersistentDTO bpDTO = new BankPersistentDTO();
    	bpDTO.setName(name);
    	bpDTO.setCustomers(cplist);
    	
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
	public void showBothEntityRecords() {
    	Session session = null;
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		List<BankPersistentDTO> blist = session.createQuery("FROM BankPersistentDTO").list();
    		for(BankPersistentDTO bpDTO : blist) {
    			System.out.println("Bank Id Is :: "+bpDTO.getId());
    			System.out.println("Bank Name Is :: "+bpDTO.getName());
    			Collection<CustomersPersistentDTO> cplist = bpDTO.getCustomers();
    			for(CustomersPersistentDTO cpDTO : cplist) {
    				System.out.println("Customer Id Is :: "+cpDTO.getCid());
        			System.out.println("Customer Name Is :: "+cpDTO.getCname());
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
       		List<BankPersistentDTO> blist = session.createQuery("FROM BankPersistentDTO").list();
       		for(BankPersistentDTO bpDTO : blist) {
       			System.out.println("Bank Id Is :: "+bpDTO.getId());
       			Collection<CustomersPersistentDTO> cplist = bpDTO.getCustomers();
       			for(CustomersPersistentDTO cpDTO : cplist) {
       				System.out.println("Customer Id Is :: "+cpDTO.getCid());
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
    
	@SuppressWarnings({ "unused", "resource" })
	public static void main( String[] args ) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. Insert Record");
    		System.out.println("2. Show Both Entity Records");
    		System.out.println("3. Show Join Table Records");
    		System.out.println("4. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Bank Name :: ");
    			String bname = sc.next();
    			
    			System.out.print("Enter First Customer Name :: ");
    			String cname1 = sc.next();
    			
    			System.out.print("Enter Second Customer Name :: ");
    			String cname2 = sc.next();
    			
    			System.out.print("Enter Third Customer Name :: ");
    			String cname3 = sc.next();
    			
    			System.out.print("Enter Fourth Customer Name :: ");
    			String cname4 = sc.next();
    			
    			App addition = new App();
    			addition.addRecord(bname, cname1, cname2, cname3, cname4);
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
    		else {
    			System.out.println("Good Byeeee......");
    			return;
    		}
		}
	}
}
