package com.association.mapping.ud;

import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.association.mapping.ud.persistent.dto.OrdersPersistentDTO;
import com.association.mapping.ud.persistent.dto.CustomerPersistentDTO;
import com.association.mapping.ud.connection.HibernateUtil;

public class App 
{
    public void addRecord(String cname, String itemName1, String paymentMode1, int quantity1, String itemName2, String paymentMode2, int quantity2) {
    	Session session = null;
    	Transaction tx = null;
    	
    	CustomerPersistentDTO cpDTO = new CustomerPersistentDTO();
    	cpDTO.setCname(cname);
    	
    	OrdersPersistentDTO opDTO1 = new OrdersPersistentDTO();
    	opDTO1.setItemName(itemName1);
    	opDTO1.setPaymentMode(paymentMode1);
    	opDTO1.setQuantity(quantity1);
    	opDTO1.setCustomer(cpDTO);
    	
    	OrdersPersistentDTO opDTO2 = new OrdersPersistentDTO();
    	opDTO2.setItemName(itemName2);
    	opDTO2.setPaymentMode(paymentMode2);
    	opDTO2.setQuantity(quantity2);
    	opDTO2.setCustomer(cpDTO);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.persist(opDTO1);
    		session.persist(opDTO2);
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
       		List<OrdersPersistentDTO> olist = session.createQuery("FROM OrdersPersistentDTO").list();
       		for(OrdersPersistentDTO opDTO : olist) {
       			System.out.println("Order Id Is :: "+opDTO.getId());
       			System.out.println("Order Item Name Is :: "+opDTO.getItemName());
       			System.out.println("Order Payment Mode Is :: "+opDTO.getPaymentMode());
       			System.out.println("Order Quantity Is :: "+opDTO.getQuantity());
       			System.out.println("Customer Id Is :: "+opDTO.getCustomer().getCid());
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
       		List<OrdersPersistentDTO> olist = session.createQuery("FROM OrdersPersistentDTO").list();
       		for(OrdersPersistentDTO opDTO : olist) {
       			System.out.println("Order Id Is :: "+opDTO.getId());
       			System.out.println("Order Item Name Is :: "+opDTO.getItemName());
       			System.out.println("Order Payment Mode Is :: "+opDTO.getPaymentMode());
       			System.out.println("Order Quantity Is :: "+opDTO.getQuantity());
       			CustomerPersistentDTO cpDTO = opDTO.getCustomer();
       			System.out.println("Customer Id Is :: "+cpDTO.getCid());
       			System.out.println("Customer Name Is :: "+cpDTO.getCname());
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
    		System.out.println("3. Show Records Unidirectional");
    		System.out.println("4. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Order Item Name1 :: ");
    			String itemName1 = sc.next();
    			
    			System.out.print("Enter Order Payment Mode1 :: ");
    			String paymentMode1 = sc.next();
    			
    			System.out.print("Enter Order Quantity1 :: ");
    			int quantity1 = sc.nextInt();
    			
    			System.out.print("Enter Order Item Name2 :: ");
    			String itemName2 = sc.next();
    			
    			System.out.print("Enter Order Payment Mode2 :: ");
    			String paymentMode2 = sc.next();
    			
    			System.out.print("Enter Order Quantity2 :: ");
    			int quantity2 = sc.nextInt();
    			
    			System.out.print("Enter Customer Name :: ");
    			String cname = sc.next();
    			
    			App addition = new App();
    			addition.addRecord(cname, itemName1, paymentMode1, quantity1, itemName2, paymentMode2, quantity2);
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
    			App showUD = new App();
    			showUD.showRecordsUnidirectional();
    			if(showUD == null) {
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

