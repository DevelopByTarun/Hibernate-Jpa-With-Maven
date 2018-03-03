package com.association.mapping;

import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.association.mapping.persistent.dto.UserPersistentDTO;
import com.association.mapping.persistent.dto.VehiclePersistentDTO;
import com.association.mapping.connection.HibernateUtil;

public class App 
{
    public void addRecord(String vname, String vtype, String vbrand, String uname) {
    	Session session = null;
    	Transaction tx = null;
    	
    	VehiclePersistentDTO  vpDTO = new VehiclePersistentDTO ();
    	vpDTO.setName(vname);
    	vpDTO.setType(vtype);
    	vpDTO.setBrand(vbrand);
    	
    	UserPersistentDTO upDTO = new UserPersistentDTO();
    	upDTO.setName(uname);
    	
    	// sets the uni-directional association
    	upDTO.setUserVehicle(vpDTO);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.persist(upDTO);
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
    		List<UserPersistentDTO> uplist = session.createQuery("FROM UserPersistentDTO").list();
    		for(UserPersistentDTO upDTO : uplist) {
    			System.out.println("User Id Is :: "+upDTO.getId());
    			System.out.println("User Name Is :: "+upDTO.getName());
    			VehiclePersistentDTO vpDTO = upDTO.getUserVehicle();
    			System.out.println("Vehicle Name Is :: "+vpDTO.getName());
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
    		List<UserPersistentDTO> uplist = session.createQuery("FROM UserPersistentDTO").list();
    		for(UserPersistentDTO upDTO : uplist) {
    			System.out.println("User Id Is :: "+upDTO.getId());
    			System.out.println("User Name Is :: "+upDTO.getName());
    			VehiclePersistentDTO vpDTO = upDTO.getUserVehicle();
    			System.out.println("Vehicle Name Is :: "+vpDTO.getName());
        		System.out.println("Vehicle Type Is :: "+vpDTO.getType());
        		System.out.println("Vehicle Brand Is :: "+vpDTO.getBrand());
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
    		UserPersistentDTO upDTO = session.get(UserPersistentDTO.class, id);
    		if(upDTO != null) {
    			System.out.println(upDTO);
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
    		System.out.println("3. Show Both Entity Records");
    		System.out.println("4. Search Record By Id");
    		System.out.println("5. Exit");
    		System.out.print("Enter The Choice :: ");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Vehicle Name :: ");
    			String vname = sc.next();
    			
    			System.out.print("Enter Vehicle Type :: ");
    			String vtype = sc.next();
    			
    			System.out.print("Enter Vehicle Brand :: ");
    			String vbrand = sc.next();
    			
    			System.out.print("Enter User Name :: ");
    			String uname = sc.next();
    			
    			App addition = new App();
    			addition.addRecord(vname, vtype, vbrand, uname);
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
    			App showBE = new App();
    			showBE.showBothEntityRecords();
    			if(showBE == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 4) {
    			System.out.println("Enter User Id :: ");
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
