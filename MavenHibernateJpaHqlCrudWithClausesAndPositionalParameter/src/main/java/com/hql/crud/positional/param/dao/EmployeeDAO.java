package com.hql.crud.positional.param.dao;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hql.crud.positional.param.connection.HibernateUtil;
import com.hql.crud.positional.param.persistent.dto.EmployeePersistentDTO;

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
	public static int updateEmployee(EmployeePersistentDTO epDTO) {
		Session session = null;
		Transaction tx = null;
		int status = 0;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			String hqlUpdate = "update EmployeePersistentDTO e set e.name = ?, e.salary = ? where e.id = ?";
			Query query = session.createQuery(hqlUpdate);
			query.setParameter(0, epDTO.getName());
			query.setParameter(1, epDTO.getSalary());
			query.setParameter(2, epDTO.getId());
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
			String hqlDelete = "delete from EmployeePersistentDTO e where e.id = ?";
			Query query = session.createQuery(hqlDelete);
			query.setParameter(0, id);
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
			String hqlSelectById = "FROM EmployeePersistentDTO e where e.id = ?";
			Query query = session.createQuery(hqlSelectById);
			query.setParameter(0, id);
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
	public static ArrayList<EmployeePersistentDTO> sortEmployeesByGTESalaries(double amount) {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hqlGTESalary = "FROM EmployeePersistentDTO e where e.salary <= ?";
			Query query = session.createQuery(hqlGTESalary);
			query.setParameter(0, amount);
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
}
