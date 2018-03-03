package com.association.mapping.ud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.association.mapping.ud.persistent.dto.StudentPersistentDTO;
import com.association.mapping.ud.persistent.dto.SubjectPersistentDTO;
import com.association.mapping.ud.connection.HibernateUtil;

public class App 
{
    public void addRecord(String sname1, String remark1, String sname2, String remark2, String subName1, String subName2, String subName3, String subName4) {
    	Session session = null;
    	Transaction tx = null;
    	
    	SubjectPersistentDTO spDTO1 = new SubjectPersistentDTO();
    	spDTO1.setName(subName1);
    	
    	SubjectPersistentDTO spDTO2 = new SubjectPersistentDTO();
    	spDTO2.setName(subName2);
    	
    	SubjectPersistentDTO spDTO3 = new SubjectPersistentDTO();
    	spDTO3.setName(subName3);
    	
    	SubjectPersistentDTO spDTO4 = new SubjectPersistentDTO();
    	spDTO4.setName(subName4);
    	
    	Collection<SubjectPersistentDTO> subList1 = new ArrayList<SubjectPersistentDTO>();
    	subList1.add(spDTO1);
    	subList1.add(spDTO2);
    	subList1.add(spDTO3);
    	
    	Collection<SubjectPersistentDTO> subList2 = new ArrayList<SubjectPersistentDTO>();
    	subList2.add(spDTO1);
    	subList2.add(spDTO2);
    	subList2.add(spDTO3);
    	subList2.add(spDTO4);
    	
    	StudentPersistentDTO studDTO1 = new StudentPersistentDTO();
    	studDTO1.setName(sname1);
    	studDTO1.setRemarks(remark1);
    	studDTO1.setSubjects(subList2);
    	
    	StudentPersistentDTO studDTO2 = new StudentPersistentDTO();
    	studDTO2.setName(sname2);
    	studDTO2.setRemarks(remark2);
    	studDTO2.setSubjects(subList1);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.persist(studDTO1);
    		session.persist(studDTO2);
    		tx.commit();
    	}
    	catch(HibernateException he) {
			if(tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
    }
	
    @SuppressWarnings("unchecked")
   	public void showBothEntityRecords() {
       	Session session = null;
       	
       	// open and get session
       	session = HibernateUtil.getSessionFactory().openSession();
       	try {
       		List<StudentPersistentDTO> studList = session.createQuery("FROM StudentPersistentDTO").list();
       		for(StudentPersistentDTO spDTO : studList) {
       			System.out.println("Student Id Is :: "+spDTO.getId());
       			System.out.println("Student Name Is :: "+spDTO.getName());
       			System.out.println("Student Remark Is :: "+spDTO.getRemarks());
       			Collection<SubjectPersistentDTO> subList = spDTO.getSubjects();
       			for(SubjectPersistentDTO subDTO : subList) {
       				System.out.println("Subject Id Is :: "+subDTO.getId());
           			System.out.println("Subject Name Is :: "+subDTO.getName());
       			}
       			System.out.println("");
       		}
       	}
       	catch(HibernateException he) {
   			he.printStackTrace();
   		}
   		finally {
   			// close connection
   			session.close();
   		}
    }
    
    @SuppressWarnings("unchecked")
   	public void showJoinTableRecords() {
       	Session session = null;
       	
       	// open and get session
       	session = HibernateUtil.getSessionFactory().openSession();
       	try {
       		List<StudentPersistentDTO> studList = session.createQuery("FROM StudentPersistentDTO").list();
       		for(StudentPersistentDTO spDTO : studList) {
       			System.out.println("Student Id Is :: "+spDTO.getId());
       			Collection<SubjectPersistentDTO> subList = spDTO.getSubjects();
       			for(SubjectPersistentDTO subDTO : subList) {
       				System.out.println("Subject Id Is :: "+subDTO.getId());
       			}
       			System.out.println("");
       		}
       	}
       	catch(HibernateException he) {
   			he.printStackTrace();
   		}
   		finally {
   			// close connection
   			session.close();
   		}
    }
    
    public void getRecordById(int id) {
		Session session = null;
    	Transaction tx = null;
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		StudentPersistentDTO spDTO = session.get(StudentPersistentDTO.class, id);
    		if(spDTO != null) {
    			System.out.println(spDTO);
    		}
    		else {
    			System.out.println("Unable To Show Records");
    		}
    		tx.commit();
    	}
    	catch(HibernateException he) {
			if(tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		}
		finally {
			// close connection
			session.close();
		}
	}
	
	@SuppressWarnings({"resource", "unused"})
	public static void main( String[] args ) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. Insert Record");
    		System.out.println("2. Show Both Entity Records");
    		System.out.println("3. Show Join Table Records");
    		System.out.println("4. Show Record By Id");
    		System.out.println("5. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter First Student Name :: ");
    			String sname1 = sc.next();
    			
    			System.out.print("Enter First Student Remark :: ");
    			String remark1 = sc.next();
    			
    			System.out.print("Enter Second Student Name :: ");
    			String sname2 = sc.next();
    			
    			System.out.print("Enter Second Student Remark :: ");
    			String remark2 = sc.next();
    			
    			System.out.print("Enter First Subject Name :: ");
    			String subName1 = sc.next();
    			
    			System.out.print("Enter Second Subject Name :: ");
    			String subName2 = sc.next();
    			
    			System.out.print("Enter Third Subject Name :: ");
    			String subName3 = sc.next();
    			
    			System.out.print("Enter Fourth Subject Name :: ");
    			String subName4 = sc.next();
    			
    			App addition = new App();
    			addition.addRecord(sname1, remark1, sname2, remark2, subName1, subName2, subName3, subName4);
    			if(addition != null) {
    				System.out.println("Record Inserted Successfully");
    			}
    			else {
    				System.out.println("Unable To Insert Record");
    			}	
    		}
    		else if(choice == 2) {
    			App showBE = new App();
    			showBE.showBothEntityRecords();
    			if(showBE == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 3) {
    			App showJT = new App();
    			showJT.showJoinTableRecords();
    			if(showJT == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 4) {
    			System.out.println("Enter Student Id :: ");
    			int id = sc.nextInt();
    			
    			App search = new App();
    			search.getRecordById(id);
    		}
    		else {
    			System.out.println("Good Byeeee......");
    			return;
    		}
		}
	}
}
