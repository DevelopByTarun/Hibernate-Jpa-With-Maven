package com.component.mapping;

import java.util.ArrayList;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.component.mapping.persistent.dto.EmployeePersistentDTO;
import com.component.mapping.persistent.dto.EmployeeDetailsDTO;
import com.component.mapping.connection.HibernateUtil;

public class App 
{
	public static int addEmployee(EmployeePersistentDTO epd)
	{
		EmployeePersistentDTO epDTO = null;
		Session session = null;
		Transaction trans = null;
		int status = 0;
		
		epDTO = new EmployeePersistentDTO();
		epDTO.setName(epd.getName());
		epDTO.setEmpDetails(epd.getEmpDetails());
		epDTO.setCity(epd.getCity());
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			trans = session.beginTransaction();
			status = (Integer) session.save(epDTO);
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
	public static ArrayList<EmployeePersistentDTO> fetchEmployee()
	{
		ArrayList<EmployeePersistentDTO> list = null;
		Session session = null;
		Transaction trans = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			trans = session.beginTransaction();
			list = (ArrayList<EmployeePersistentDTO>) session.createQuery("FROM EmployeePersistentDTO").list();
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
    			
    			System.out.println("Enter Designation :: ");
    			String dg = sc.next();
    			
    			System.out.println("Enter Company :: ");
    			String cp = sc.next();
    			
    			System.out.println("Enter Salary :: ");
    			double sal = sc.nextDouble();
    			
    			System.out.println("Enter City :: ");
    			String ct = sc.next();
    			
    			EmployeeDetailsDTO edd = new EmployeeDetailsDTO();
    			edd.setDesignation(dg);
    			edd.setCompany(cp);
    			edd.setSalary(sal);
    			
    			EmployeePersistentDTO epd = new EmployeePersistentDTO();
    			epd.setName(nm);
    			epd.setEmpDetails(edd);
    			epd.setCity(ct);
    			
    			int status = 0;
    			try {
    				status = App.addEmployee(epd);
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
    			ArrayList<EmployeePersistentDTO> aList = App.fetchEmployee();
    			if(aList != null) {
    				for(EmployeePersistentDTO list : aList) {
        				System.out.println("Id Is :: "+list.getId());
        				System.out.println("Name Is :: "+list.getName());
        				System.out.println("Details Is :: "+list.getEmpDetails());
        				System.out.println("City Is :: "+list.getCity());
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
