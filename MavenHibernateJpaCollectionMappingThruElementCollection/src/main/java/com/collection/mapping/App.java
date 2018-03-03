package com.collection.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.collection.mapping.persistent.CollegePersistentDTO;
import com.collection.mapping.persistent.StudentEmbeddableDTO;
import com.collection.mapping.connection.HibernateUtil;

public class App 
{
    public void addRecord(String name, int si1, String sn1, int si2, String sn2, int si3, String sn3) {
    	Session session = null;
    	Transaction tx = null;
    	
    	StudentEmbeddableDTO seDTO1 = new StudentEmbeddableDTO();
    	seDTO1.setStudId(si1);
    	seDTO1.setStudName(sn1);
    	    	
    	StudentEmbeddableDTO seDTO2 = new StudentEmbeddableDTO();
    	seDTO2.setStudId(si2);
    	seDTO2.setStudName(sn2);
    	
    	StudentEmbeddableDTO seDTO3 = new StudentEmbeddableDTO();
    	seDTO3.setStudId(si3);
    	seDTO3.setStudName(sn3);
    	
    	ArrayList<StudentEmbeddableDTO> studentsList = new ArrayList<StudentEmbeddableDTO>();
    	studentsList.add(seDTO1);
    	studentsList.add(seDTO2);
    	studentsList.add(seDTO3);
    	
    	CollegePersistentDTO cpDTO = new CollegePersistentDTO();
    	cpDTO.setName(name);
    	cpDTO.setCollegeStudents(studentsList);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.persist(cpDTO);
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
    
    @SuppressWarnings({ "unchecked", "unused" })
	public void showRecords() {
    	Session session = null;
    	Transaction tx = null;
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		List<CollegePersistentDTO> clist = session.createQuery("FROM CollegePersistentDTO").list();
    		for(CollegePersistentDTO cpDTO : clist) {
    			System.out.println("College Id Is :: "+cpDTO.getId());
    			System.out.println("College Name Is :: "+cpDTO.getName());
    			List<StudentEmbeddableDTO> studList = cpDTO.getCollegeStudents();
    			for(StudentEmbeddableDTO seDTO : studList) {
    				System.out.println("Student Id Is :: "+seDTO.getStudId());
        			System.out.println("Student Name Is :: "+seDTO.getStudName());
    			}
    			System.out.println("");
    		}
//    		tx.commit();
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
    
    public void getRecordById(int id) {
		Session session = null;
    	Transaction tx = null;
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		CollegePersistentDTO cpDTO = session.get(CollegePersistentDTO.class, id);
    		if(cpDTO != null) {
    			System.out.println(cpDTO);
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
	
	@SuppressWarnings("unused")
	public static void main( String[] args ) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. Insert Record");
    		System.out.println("2. Show Record");
    		System.out.println("3. Search Record By Id");
    		System.out.println("4. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.println("Enter College Name :: ");
    			String nm = sc.next();
    			
    			System.out.println("Enter Student Id No.1 :: ");
    			int si1 = sc.nextInt();
    			
    			System.out.println("Enter Student Name No.1 :: ");
    			String sn1 = sc.next();
    			
    			System.out.println("Enter Student Id No.2 :: ");
    			int si2 = sc.nextInt();
    			
    			System.out.println("Enter Student Name No.2 :: ");
    			String sn2 = sc.next();
    			
    			System.out.println("Enter Student Id No.3 :: ");
    			int si3 = sc.nextInt();
    			
    			System.out.println("Enter Student Name No.3 :: ");
    			String sn3 = sc.next();
    			
    			App addition = new App();
    			addition.addRecord(nm, si1, sn1, si2, sn2, si3, sn3);
    			if(addition != null) {
    				System.out.println("Record Inserted Successfully");
    			}
    			else {
    				System.out.println("Unable To Insert Record");
    			}
    		}
    		else if(choice == 2) {
    			App show = new App();
    			show.showRecords();
    			if(show == null) {
    				System.out.println("Unable To Show Records");
    			}
    		}
    		else if(choice == 3) {
    			System.out.println("Enter College Id :: ");
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
