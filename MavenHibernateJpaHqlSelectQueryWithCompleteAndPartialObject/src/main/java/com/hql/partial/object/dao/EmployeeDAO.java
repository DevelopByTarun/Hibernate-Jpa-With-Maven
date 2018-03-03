package com.hql.partial.object.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.hql.partial.object.connection.HibernateUtil;
import com.hql.partial.object.persistent.dto.EmployeePersistentDTO;

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
	public static ArrayList<String> getEmployeesName() {
		ArrayList<String> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlSelectName = "select e.name FROM EmployeePersistentDTO e ORDER BY e.name DESC";
			Query query = session.createQuery(hqlSelectName);
			empList = (ArrayList<String>) query.list();
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
	public static ArrayList<Map<String, Integer>> getEmployeesNameAndSalary() {
		ArrayList<Map<String, Integer>> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlSelectNameAddress = "select new map(e.name, e.salary) FROM EmployeePersistentDTO e ORDER BY e.salary ASC";
			Query query = session.createQuery(hqlSelectNameAddress);
			empList = (ArrayList<Map<String, Integer>>) query.list();
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
	public static List<Object[]> getEmployeesNameSalaryAndAddress() {
		List<Object[]> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlSelectNameAdddressSalary = "select e.name, e.address, e.salary FROM EmployeePersistentDTO e";
			Query query = session.createQuery(hqlSelectNameAdddressSalary);
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
