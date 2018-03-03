package com.component.mapping.hbjpa;

import java.util.ArrayList;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.component.mapping.hbjpa.persistent.dto.StudentPersistentDTO;
import com.component.mapping.hbjpa.connection.HibernateUtil;
import com.component.mapping.hbjpa.persistent.dto.StudentAddressDTO;

public class App 
{
	public static int addStudent(StudentPersistentDTO spd)
	{
		StudentPersistentDTO spDTO = null;
		Session session = null;
		Transaction trans = null;
		int status = 0;
		
		spDTO = new StudentPersistentDTO();
		spDTO.setName(spd.getName());
		spDTO.setLocalAddress(spd.getLocalAddress());
		spDTO.setPermanentAddress(spd.getPermanentAddress());
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			trans = session.beginTransaction();
			status = (Integer) session.save(spDTO);
			trans.commit();
		}
		catch(HibernateException he) {
			if(trans != null) {
				trans.rollback();
			}
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<StudentPersistentDTO> fetchStudent()
	{
		ArrayList<StudentPersistentDTO> list = null;
		Session session = null;
		Transaction trans = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			trans = session.beginTransaction();
			list = (ArrayList<StudentPersistentDTO>) session.createQuery("FROM StudentPersistentDTO").list();
			trans.commit();
		}
		catch(HibernateException he) {
			if(trans != null) {
				trans.rollback();
			}
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return list;
	}
	
	public static void main( String[] args ) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. Insert Record");
    		System.out.println("2. Show Record");
    		System.out.println("3. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.println("Enter Name :: ");
    			String nm = sc.next();
    			
    			System.out.println("Enter Local City :: ");
    			String lct = sc.next();
    			
    			System.out.println("Enter Local Pincode :: ");
    			String lpc = sc.next();
    			
    			System.out.println("Enter Permanent City :: ");
    			String pct = sc.next();
    			
    			System.out.println("Enter Permanent Pincode :: ");
    			String ppc = sc.next();
    			
    			StudentAddressDTO localAddress = new StudentAddressDTO();
    			localAddress.setCity(lct);
    			localAddress.setPincode(lpc);
    			
    			StudentAddressDTO permanentAddress = new StudentAddressDTO();
    			permanentAddress.setCity(pct);
    			permanentAddress.setPincode(ppc);
    			
    			StudentPersistentDTO spDTO = new StudentPersistentDTO();
    			spDTO.setName(nm);
    			spDTO.setLocalAddress(localAddress);
    			spDTO.setPermanentAddress(permanentAddress);
    			
    			int status = 0;
    			try {
    				status = App.addStudent(spDTO);
    				if(status > 0) {
    					System.out.println("Record Inserted Successfully");
    				}
    				else {
    					System.out.println("Unable To Insert Record");
    				}
    			}
    			catch(HibernateException he) {
    				he.printStackTrace();
    			}
    		}
    		else if(choice == 2) {
    			ArrayList<StudentPersistentDTO> aList = App.fetchStudent();
    			if(aList != null) {
    				for(StudentPersistentDTO list : aList) {
        				System.out.println("Id Is :: "+list.getId());
        				System.out.println("Name Is :: "+list.getName());
        				System.out.println("Local Address Is :: "+list.getLocalAddress());
        				System.out.println("Permanent Address Is :: "+list.getPermanentAddress());
        				System.out.println("");
        			}
    			}
    			else {
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





