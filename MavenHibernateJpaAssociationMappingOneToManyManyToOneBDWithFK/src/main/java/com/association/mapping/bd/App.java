package com.association.mapping.bd;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.association.mapping.bd.persistent.dto.DepartmentPersistentDTO;
import com.association.mapping.bd.persistent.dto.EmployeesPersistentDTO;
import com.association.mapping.bd.connection.HibernateUtil;

public class App 
{
    public void addRecord(String dname, String ename1, long emob1, String ename2, long emob2, String ename3, long emob3) {
    	Session session = null;
    	Transaction tx = null;
    	DepartmentPersistentDTO dpDTO = null;
    	
    	EmployeesPersistentDTO epDTO1 = new EmployeesPersistentDTO();
    	epDTO1.setEname(ename1);
    	epDTO1.setMobileNo(emob1);
    	epDTO1.setDepartment(dpDTO);
    	
    	EmployeesPersistentDTO epDTO2 = new EmployeesPersistentDTO();
    	epDTO2.setEname(ename2);
    	epDTO2.setMobileNo(emob2);
    	epDTO2.setDepartment(dpDTO);
    	
    	EmployeesPersistentDTO epDTO3 = new EmployeesPersistentDTO();
    	epDTO3.setEname(ename3);
    	epDTO3.setMobileNo(emob3);
    	epDTO3.setDepartment(dpDTO);
    	
    	Set<EmployeesPersistentDTO> empList = new HashSet<EmployeesPersistentDTO>();
    	empList.add(epDTO1);
    	empList.add(epDTO2);
    	empList.add(epDTO3);
    	
    	dpDTO = new DepartmentPersistentDTO();
    	dpDTO.setDname(dname);
    	dpDTO.setEmployees(empList);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.persist(dpDTO);
    		session.persist(epDTO1);
    		session.persist(epDTO2);
    		session.persist(epDTO3);
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
    		List<DepartmentPersistentDTO> dlist = session.createQuery("FROM DepartmentPersistentDTO").list();
    		for(DepartmentPersistentDTO dpDTO : dlist) {
    			System.out.println("Department Id Is :: "+dpDTO.getDid());
    			System.out.println("Department Name Is :: "+dpDTO.getDname());
    			Set<EmployeesPersistentDTO> epList = dpDTO.getEmployees();
    			for(EmployeesPersistentDTO epDTO : epList) {
    				System.out.println("Employee Id Is :: "+epDTO.getEid());
        			System.out.println("Employee Name Is :: "+epDTO.getEname());
        			System.out.println("Employee Mobile No Is :: "+epDTO.getMobileNo());
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
    
	@SuppressWarnings({ "resource"})
	public static void main( String[] args ) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. Insert Record");
    		System.out.println("2. Show Both Entity Records");
    		System.out.println("3. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Department Name :: ");
    			String dname = sc.next();
    			
    			System.out.print("Enter First Employee Name :: ");
    			String ename1 = sc.next();
    			
    			System.out.print("Enter First Employee Mobile No :: ");
    			long emob1 = sc.nextLong();
    			
    			System.out.print("Enter Second Employee Name :: ");
    			String ename2 = sc.next();
    			
    			System.out.print("Enter Second Employee Mobile No :: ");
    			long emob2 = sc.nextLong();
    			
    			System.out.print("Enter Third Employee Name :: ");
    			String ename3 = sc.next();
    			
    			System.out.print("Enter Third Employee Mobile No :: ");
    			long emob3 = sc.nextLong();
    			
    			App addition = new App();
    			addition.addRecord(dname, ename1, emob1, ename2, emob2, ename3, emob3);
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
    		else {
    			System.out.println("Good Byeeee......");
    			return;
    		}
		}
	}
}
