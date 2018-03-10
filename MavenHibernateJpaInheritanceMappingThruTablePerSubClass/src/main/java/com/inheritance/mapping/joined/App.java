package com.inheritance.mapping.joined;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.inheritance.mapping.joined.connection.HibernateUtil;
import com.inheritance.mapping.joined.persistent.dto.EmployeePersistentDTO;
import com.inheritance.mapping.joined.persistent.dto.RegularEmployeePersistentDTO;
import com.inheritance.mapping.joined.persistent.dto.ContractEmployeePersistentDTO;

public class App 
{
	public void addEmployee() {
		Session session = null;
		Transaction tx = null;
		
		EmployeePersistentDTO epDTO = new EmployeePersistentDTO();
		epDTO.setName("tarun");
		
		RegularEmployeePersistentDTO repDTO = new RegularEmployeePersistentDTO();
		repDTO.setName("varun");
		repDTO.setSalary(45244.33);
		repDTO.setDesignation("backend");
		
		ContractEmployeePersistentDTO cepDTO = new ContractEmployeePersistentDTO();
		cepDTO.setName("amit");
		cepDTO.setAddress("delhi");
		cepDTO.setPhone(46346234);
		
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

	@SuppressWarnings("unused")
	public static void main( String[] args ) {
		
		App call = new App();
		call.addEmployee();
		if(call != null) {
			System.out.println("Record Added Successfully");
		}
		else {
			System.out.println("Unable To Add Record");
		}
	}
}
