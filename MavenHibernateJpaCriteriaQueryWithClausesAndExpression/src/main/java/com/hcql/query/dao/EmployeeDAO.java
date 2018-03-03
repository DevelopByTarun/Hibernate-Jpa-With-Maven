package com.hcql.query.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.hcql.query.connection.HibernateUtil;
import com.hcql.query.persistent.dto.EmployeePersistentDTO;

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
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public static ArrayList<EmployeePersistentDTO> getAllEmployees() {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(EmployeePersistentDTO.class);
			
			// FROM EmployeePersistentDTO e
			empList = (ArrayList<EmployeePersistentDTO>) criteria.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public static ArrayList<EmployeePersistentDTO> sortEmployeesByGTESalaries(double amount) {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(EmployeePersistentDTO.class);
			
			// FROM EmployeePersistentDTO e where e.salary >= :amount
			criteria.add(Restrictions.ge("salary", amount));
			
			empList = (ArrayList<EmployeePersistentDTO>) criteria.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public static ArrayList<EmployeePersistentDTO> sortEmployeesByLTESalaries(double amount) {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(EmployeePersistentDTO.class);
			
			// FROM EmployeePersistentDTO e where e.salary <= :amount
			criteria.add(Restrictions.le("salary", amount));
			
			empList = (ArrayList<EmployeePersistentDTO>) criteria.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public static ArrayList<EmployeePersistentDTO> sortEmployeesByBetweenGTLTSalaries(double amount1, double amount2) {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(EmployeePersistentDTO.class);
			
			// FROM EmployeePersistentDTO e where e.salary >= :amount1 and e.salary <= :amount2
			criteria.add(Restrictions.between("salary", amount1, amount2));
			
			empList = (ArrayList<EmployeePersistentDTO>) criteria.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public static ArrayList<EmployeePersistentDTO> sortNameOrderByDescending() {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(EmployeePersistentDTO.class);
			
			// select e.name FROM EmployeePersistentDTO e order by desc
			criteria.addOrder(Order.desc("name"));
			
			empList = (ArrayList<EmployeePersistentDTO>) criteria.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public static ArrayList<EmployeePersistentDTO> sortSalaryOrderByAscending() {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(EmployeePersistentDTO.class);
			
			// select e.salary FROM EmployeePersistentDTO e order by asc
			criteria.addOrder(Order.asc("salary"));
			
			empList = (ArrayList<EmployeePersistentDTO>) criteria.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public static ArrayList<EmployeePersistentDTO> criteriaPagination(int record1, int record2) {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(EmployeePersistentDTO.class);
			criteria.setFirstResult(record1);
			criteria.setMaxResults(record2);
			empList = (ArrayList<EmployeePersistentDTO>) criteria.list();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			session.close();
		}
		return empList;
	}
	
	@SuppressWarnings({"unchecked", "deprecation"})
	public static ArrayList<EmployeePersistentDTO> sortNameWithLike() {
		ArrayList<EmployeePersistentDTO> empList = null;
		Session session = null;
		
		// open and get connection
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(EmployeePersistentDTO.class);
			
			criteria.add(Restrictions.or(Restrictions.like("name", "a%"), Restrictions.like("name", "t%")));
			
			empList = (ArrayList<EmployeePersistentDTO>) criteria.list();
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
