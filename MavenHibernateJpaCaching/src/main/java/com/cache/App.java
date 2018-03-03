package com.cache;

import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.cache.persistent.dto.EmployeePersistentDTO;
import com.cache.connection.HibernateUtil;

public class App 
{
	public static int addEmployee(EmployeePersistentDTO epd) {
		EmployeePersistentDTO epDTO = null;
		Session session = null;
		Transaction tx = null;
		int status = 0;
		
		epDTO = new EmployeePersistentDTO();
		epDTO.setName(epd.getName());
		epDTO.setSalary(epd.getSalary());
		epDTO.setAddress(epd.getAddress());
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			status = (Integer) session.save(epDTO);
			tx.commit();
		}
		catch(HibernateException he) {
			if(tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return status;
	}
	
	public void secondLevelCache(int id) throws HibernateException {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		EmployeePersistentDTO epDTO1 = session1.get(EmployeePersistentDTO.class, id);
		if(epDTO1 != null) {
			System.out.println(epDTO1);
		}
		else {
			System.out.println("Record Not Found....");
		}
//		if we close connection this time function throw an error of session close and 
//		do not execute session2 i.e we close session1 and session2 at the end
//		session1.close();
		
		Session session2 = HibernateUtil.getSessionFactory().openSession(); // open connection
		EmployeePersistentDTO epDTO2 = session1.get(EmployeePersistentDTO.class, id);
		if(epDTO2 != null) {
			System.out.println(epDTO2);
		}
		else {
			System.out.println("Record Not Found....");
		}
		
		session1.close();
		session2.close();	
	}
	
	@SuppressWarnings("rawtypes")
	public void secondLevelQueryCache(int id) {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		String hqlSelectById1 = "FROM EmployeePersistentDTO e where e.id = ?";
		Query query1 = session1.createQuery(hqlSelectById1);
		query1.setParameter(0, id);
		query1.setCacheable(true);
		EmployeePersistentDTO epDTO1 = (EmployeePersistentDTO) query1.uniqueResult();
		if(epDTO1 != null) {
			System.out.println(epDTO1);
		}
		else {
			System.out.println("Record Not Found....");
		}
//		if we close connection this time function throw an error of session close and 
//		do not execute session2 i.e we close session1 and session2 at the end
//		session1.close();
		
		Session session2 = HibernateUtil.getSessionFactory().openSession(); // open connection
		String hqlSelectById2 = "FROM EmployeePersistentDTO e where e.id = ?";
		Query query2 = session2.createQuery(hqlSelectById2);
		query2.setParameter(0, id);
		query2.setCacheable(true);
		EmployeePersistentDTO epDTO2 = (EmployeePersistentDTO) query2.uniqueResult();
		if(epDTO2 != null) {
			System.out.println(epDTO2);
		}
		else {
			System.out.println("Record Not Found....");
		}
		
		session1.close();
		session2.close();
	}
	
	@SuppressWarnings("resource")
	public static void main( String[] args ) {
		Scanner sc = new Scanner(System.in);
    	while(true) {
    		System.out.println("");
    		System.out.println("1. Insert Employee Record");
    		System.out.println("2. Second Level Cache");
    		System.out.println("3. Second Level Query Cache");
    		System.out.println("4. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Employee Name :: ");
    			String name = sc.next();
    			
    			System.out.print("Enter Employee Address :: ");
    			String address = sc.next();
    			
    			System.out.print("Enter Employee Salary :: ");
    			double salary = sc.nextDouble();
    			
    			EmployeePersistentDTO epDTO = new EmployeePersistentDTO();
    			epDTO.setName(name);
    			epDTO.setSalary(salary);
    			epDTO.setAddress(address);
    			
    			int status = 0;
    			status = App.addEmployee(epDTO);
    			if(status > 0) {
    				System.out.println("Record Inserted Successfully");
    			}
    			else {
    				System.out.println("Unable To Insert Record");
    			}
    		}
    		else if(choice == 2) {
    			System.out.println("Enter Employee Id :: ");
    			int id = sc.nextInt();
    			    			
    			App slCache = new App();
    			slCache.secondLevelCache(id);
    		}
    		else if(choice == 3) {
    			System.out.println("Enter Employee Id :: ");
    			int id = sc.nextInt();
    			
    			App slQueryCache = new App();
    			slQueryCache.secondLevelQueryCache(id);
    		}
    		else {
    			System.out.println("Goodbye....!!");
    			return;
    		}
    	}
	}
}
