package com.inheritance.mapping.single.table;

import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.inheritance.mapping.single.table.connection.HibernateUtil;
import com.inheritance.mapping.single.table.persistent.dto.EmployeePersistentDTO;
import com.inheritance.mapping.single.table.persistent.dto.RegularEmployeePersistentDTO;
import com.inheritance.mapping.single.table.persistent.dto.ContractEmployeePersistentDTO;

public class App 
{
	public void addEmployee(String name, double salary, String designation, String address, long phone) {
		Session session = null;
		Transaction tx = null;
		
		EmployeePersistentDTO epDTO = new EmployeePersistentDTO();
		epDTO.setName(name);
		
		RegularEmployeePersistentDTO repDTO = new RegularEmployeePersistentDTO();
		repDTO.setSalary(salary);
		repDTO.setDesignation(designation);
		
		ContractEmployeePersistentDTO cepDTO = new ContractEmployeePersistentDTO();
		cepDTO.setAddress(address);
		cepDTO.setPhone(phone);
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			session.save(epDTO);
			session.save(repDTO);
			session.save(cepDTO);
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
	}
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main( String[] args ) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("");
			System.out.println("1. Add Record");
			System.out.println("2. Exit");
			System.out.println("Enter You Choice");
			int choice = sc.nextInt();
			if(choice == 1) {
				System.out.print("Enter Employee Name :: ");
				String nm = sc.next();
				
				System.out.print("Enter Employee Salary :: ");
				double sal = sc.nextDouble();
				
				System.out.print("Enter Employee Designation :: ");
				String desig = sc.next();
				
				System.out.print("Enter Employee Address :: ");
				String addr = sc.next();
				
				System.out.print("Enter Employee Phone :: ");
				long ph = sc.nextLong();
				
				App addition = new App();
				addition.addEmployee(nm, sal, desig, addr, ph);
				if(addition != null) {
					System.out.println("Record Added Successfully");
				}
				else {
					System.out.println("Unable To Insert Record");
				}
			}
			else {
				System.out.println("Good Bye....");
			}
		}
	}
}
