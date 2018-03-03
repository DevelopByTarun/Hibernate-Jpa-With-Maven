package com.tarun.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tarun.connection.HibernateUtil;
import com.tarun.dto.EmployeeDTO;

public class EmployeeDAO {
	
	// add employee record
	public static int addEmployeeRecords(int i, String fn, String ln, int a, double s) {
		Session session = null;
		Transaction trans = null;
		int result = 0;
		
		EmployeeDTO empDTO = new EmployeeDTO();
		empDTO.setId(i);
		empDTO.setFirstname(fn);
		empDTO.setLastname(ln);
		empDTO.setAge(a);
		empDTO.setSalary(s);
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			trans = session.beginTransaction();
			result = (Integer) session.save(empDTO);
			trans.commit();
		}
		catch(HibernateException he) {
			if(trans != null) {
				trans.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
		return result;
	}
	
	// fetch employee records
	@SuppressWarnings("unchecked")
	public static ArrayList<EmployeeDTO> getEmployeeRecords() {
		ArrayList<EmployeeDTO> list = null;
//		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		Session session = null;
		Transaction trans = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			trans = session.beginTransaction();
			list = (ArrayList<EmployeeDTO>) session.createQuery("FROM EmployeeDTO").list();
			trans.commit();
		}
		catch(HibernateException he) {
			if(trans != null) {
				trans.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
		return list;
	}
	
	// update employee records
	public void mergeEmployeeRecords(int id, String fn, String ln, int a, double s) {
		Session session = null;
		Transaction trans = null;
		EmployeeDTO empDTO = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			trans = session.beginTransaction();
			empDTO = session.get(EmployeeDTO.class, id);
			empDTO.setFirstname(fn);
			empDTO.setLastname(ln);
			empDTO.setAge(a);
			empDTO.setSalary(s);
			session.merge(empDTO);
			trans.commit();
		}
		catch(HibernateException he) {
			if(trans != null) {
				trans.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
	}
	
	// delete employee records
	public void deleteEmployeeRecords(int id) {
		Session session = null;
		Transaction trans = null;
		EmployeeDTO empDTO = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			trans = session.beginTransaction();
			empDTO = session.get(EmployeeDTO.class, id);
			session.delete(empDTO);
			trans.commit();
		}
		catch(HibernateException he) {
			if(trans != null) {
				trans.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
	}
	
	// search employee records by id
		public static EmployeeDTO getEmployeeRecordsById(int id) {
//			EmployeeDTO empDTO = new EmployeeDTO();
			EmployeeDTO empDTO = null;
			Session session = null;
			Transaction trans = null;
			
			// open and get connection
			session = HibernateUtil.getSessionFactory().openSession();
			try {
				trans = session.beginTransaction();
				empDTO = session.get(EmployeeDTO.class, id);
				trans.commit();
			}
			catch(HibernateException he) {
				if(trans != null) {
					trans.rollback();
				}
				he.printStackTrace();
			}
			finally {
				// close connection
				session.close();
			}
			return empDTO;
		}
}
