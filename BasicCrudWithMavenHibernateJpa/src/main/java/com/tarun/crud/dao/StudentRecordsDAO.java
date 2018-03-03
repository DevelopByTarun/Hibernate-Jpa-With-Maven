package com.tarun.crud.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tarun.crud.connection.HibernateUtil;
import com.tarun.crud.dto.StudentRecordsDTO;

public class StudentRecordsDAO {
		
		// add student records
		public static int addRecords(int i, String fn, String ln, int a, String c) {
			Session session = null;
			Transaction trans = null;
			int result = 0;
			
			StudentRecordsDTO srDTO = new StudentRecordsDTO();
			srDTO.setId(i);
			srDTO.setFirstName(fn);
			srDTO.setLastName(ln);
			srDTO.setAge(a);
			srDTO.setCity(c);
			
			// open and get connection
			session = HibernateUtil.getSessionFactory().openSession();
			try {
				trans = session.beginTransaction();
				result = (Integer) session.save(srDTO);
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
		
		// fetch student records
		public static List<StudentRecordsDTO> getRecords() {
			Session session = null;
			Transaction trans = null;
			
			// open and get connection
			session = HibernateUtil.getSessionFactory().openSession();
			trans = session.beginTransaction();
			
			@SuppressWarnings("unchecked")
			List<StudentRecordsDTO> srDTO = session.createQuery("FROM StudentRecordsDTO").list();
			trans.commit();
			
			// close connection
			session.close();
			return srDTO;
		}
		
		// update student records
		public void updateRecords(int id, String f, String l, int a, String c) {
			Session session = null;
			Transaction trans = null;
			Integer i = id;
			
			// open and get connection
			session = HibernateUtil.getSessionFactory().openSession();
			
			try {
				trans = session.beginTransaction();
				StudentRecordsDTO srDTO = session.get(StudentRecordsDTO.class, i);
				srDTO.setFirstName(f);
				srDTO.setLastName(l);
				srDTO.setAge(a);
				srDTO.setCity(c);
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
		
		// delete records
		public void deleteRecords(int id) {
			Session session = null;
			Transaction trans = null;
			
			// open and get connection
			session = HibernateUtil.getSessionFactory().openSession();
			
			try {
				trans = session.beginTransaction();
				StudentRecordsDTO srDTO = session.get(StudentRecordsDTO.class, id);
				session.delete(srDTO);
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
		
		// search records
		public void searchById(int id) {
			Session session = null;
			Transaction trans = null;
			
			// open and get connection
			session = HibernateUtil.getSessionFactory().openSession();
			
			try {
				trans = session.beginTransaction();
				StudentRecordsDTO srDTO = session.get(StudentRecordsDTO.class, id);
				if(srDTO != null) {
					System.out.println(srDTO);
				}
				else {
					System.out.println("Unable To Search Records....");
				}
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
}
