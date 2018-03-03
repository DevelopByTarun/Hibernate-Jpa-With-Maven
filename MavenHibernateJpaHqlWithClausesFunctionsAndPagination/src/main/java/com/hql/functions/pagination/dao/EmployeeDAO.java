package com.hql.functions.pagination.dao;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.hql.functions.pagination.connection.HibernateUtil;
import com.hql.functions.pagination.persistent.dto.EmployeePersistentDTO;

public class EmployeeDAO {
	
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<EmployeePersistentDTO> getAllEmployees() {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlSelect = "FROM EmployeePersistentDTO e";
			Query query = session.createQuery(hqlSelect);
			empList = (ArrayList<EmployeePersistentDTO>) query.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<EmployeePersistentDTO> pagination(int record1, int record2) {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlPagination = "FROM EmployeePersistentDTO e";
			Query query = session.createQuery(hqlPagination);
			query.setFirstResult(record1);
			query.setMaxResults(record2);
			empList = (ArrayList<EmployeePersistentDTO>) query.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<Integer> getEmployeeMinimunSalary() {
		ArrayList<Integer> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlMinSalary = "select min(e.salary) FROM EmployeePersistentDTO e";
			Query query = session.createQuery(hqlMinSalary);
			empList = (ArrayList<Integer>) query.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<Integer> getEmployeeMaximumSalary() {
		ArrayList<Integer> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlMaxSalary = "select max(e.salary) FROM EmployeePersistentDTO e";
			Query query = session.createQuery(hqlMaxSalary);
			empList = (ArrayList<Integer>) query.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<Integer> getEmployeesTotalSalary() {
		ArrayList<Integer> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlTotalSalary = "select sum(e.salary) FROM EmployeePersistentDTO e";
			Query query = session.createQuery(hqlTotalSalary);
			empList = (ArrayList<Integer>) query.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<Integer> getEmployeesAverageSalary() {
		ArrayList<Integer> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlAverageSalary = "select avg(e.salary) FROM EmployeePersistentDTO e";
			Query query = session.createQuery(hqlAverageSalary);
			empList = (ArrayList<Integer>) query.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<Integer> getEmployeeTotalId() {
		ArrayList<Integer> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlTotalId = "select count(e.id) FROM EmployeePersistentDTO e";
			Query query = session.createQuery(hqlTotalId);
			empList = (ArrayList<Integer>) query.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
}
