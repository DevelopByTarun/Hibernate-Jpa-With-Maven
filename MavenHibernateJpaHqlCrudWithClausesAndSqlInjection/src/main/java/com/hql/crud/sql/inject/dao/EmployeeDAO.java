package com.hql.crud.sql.inject.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.hql.crud.sql.inject.persistent.dto.EmployeePersistentDTO;
import com.hql.crud.sql.inject.connection.HibernateUtil;

public class EmployeeDAO {
	
	public static int addEmployee(EmployeePersistentDTO epd) {
		EmployeePersistentDTO epDTO = null;
		Session session = null;
		Transaction tx = null;
		int status = 0;
		
		epDTO = new EmployeePersistentDTO();
		epDTO.setName(epd.getName());
		epDTO.setSalary(epd.getSalary());
		
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
	
	@SuppressWarnings("rawtypes")
	public static int updateEmployee(String name, double salary, int id) {
		Session session = null;
		Transaction tx = null;
		int status = 0;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			String hqlUpdate = "update EmployeePersistentDTO e set e.name = '"+name+"', e.salary = '"+salary+"' where e.id = '"+id+"'";
			Query query = session.createQuery(hqlUpdate);
			status = query.executeUpdate();
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
	
	@SuppressWarnings("rawtypes")
	public static int deleteEmployee(int id) {
		Session session = null;
		Transaction tx = null;
		int status = 0;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			String hqlDelete = "delete from EmployeePersistentDTO e where e.id = '"+id+"'";
			Query query = session.createQuery(hqlDelete);
			status = query.executeUpdate();
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
	
	@SuppressWarnings("rawtypes")
	public static EmployeePersistentDTO getEmployeeById(int id) {
		EmployeePersistentDTO employee = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlSelectById = "FROM EmployeePersistentDTO e where e.id = '"+id+"'";
			Query query = session.createQuery(hqlSelectById);
			employee = (EmployeePersistentDTO) query.uniqueResult();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return employee;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<EmployeePersistentDTO> sortEmployeesByGTESalaries(int amount) {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlGTESalary = "FROM EmployeePersistentDTO e where e.salary >= '"+amount+"'";
			Query query = session.createQuery(hqlGTESalary);
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
	public static ArrayList<EmployeePersistentDTO> sortEmployeesOrderBy() {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlOrderBy = "FROM EmployeePersistentDTO e order by e.name DESC";
			Query query = session.createQuery(hqlOrderBy);
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
	public static List<Object[]> sortEmployeesGroupBy() {
		List<Object[]> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlGroupBy = "select SUM(e.salary), e.name FROM EmployeePersistentDTO e group by e.name";
			Query query = session.createQuery(hqlGroupBy);
			empList = query.list();
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
