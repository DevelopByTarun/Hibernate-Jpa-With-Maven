package com.collection.mapping;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.collection.mapping.persistent.dto.FolderPersistentDTO;
import com.collection.mapping.connection.HibernateUtil;
import com.collection.mapping.persistent.dto.FileCollectionDTO;

public class App 
{
    public void addRecord(String name, int fs1, String fn1, int fs2, String fn2, int fs3, String fn3) {
    	Session session = null;
    	Transaction tx = null;
    	
    	FileCollectionDTO fcDTO1 = new FileCollectionDTO();
    	fcDTO1.setSize(fs1);
    	fcDTO1.setName(fn1);
    	
    	FileCollectionDTO fcDTO2 = new FileCollectionDTO();
    	fcDTO2.setSize(fs2);
    	fcDTO2.setName(fn2);
    	
    	FileCollectionDTO fcDTO3 = new FileCollectionDTO();
    	fcDTO3.setSize(fs3);
    	fcDTO3.setName(fn3);
    	
    	HashSet<FileCollectionDTO> fileList = new HashSet<FileCollectionDTO>();
    	fileList.add(fcDTO1);
    	fileList.add(fcDTO2);
    	fileList.add(fcDTO3);
    	
    	FolderPersistentDTO fpDTO = new FolderPersistentDTO();
    	fpDTO.setName(name);
    	fpDTO.setFiles(fileList);
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		tx = session.beginTransaction();
    		session.persist(fpDTO);
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
	
    @SuppressWarnings({ "unchecked"})
	public void showRecords() {
    	Session session = null;
    	
    	// open and get session
    	session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		List<FolderPersistentDTO> flist = session.createQuery("FROM FolderPersistentDTO").list();
    		for(FolderPersistentDTO fpDTO : flist) {
    			System.out.println("Folder Id Is :: "+fpDTO.getId());
    			System.out.println("Folder Name Is :: "+fpDTO.getName());
    			Set<FileCollectionDTO> fileList = fpDTO.getFiles();
    			for(FileCollectionDTO fcDTO : fileList) {
    				System.out.println("File Size Is :: "+fcDTO.getSize());
        			System.out.println("File Name Is :: "+fcDTO.getName());
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
    		FolderPersistentDTO fpDTO = session.get(FolderPersistentDTO.class, id);
    		if(fpDTO != null) {
    			System.out.println(fpDTO);
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

	@SuppressWarnings({ "resource", "unused" })
	public static void main( String[] args ) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. Insert Record");
    		System.out.println("2. Show Record");
    		System.out.println("3. Search Record By Id");
    		System.out.println("4. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.println("Enter Folder Name :: ");
    			String fname = sc.next();
    			
    			System.out.println("Enter File Size No.1 :: ");
    			int fs1 = sc.nextInt();
    			
    			System.out.println("Enter File Name No.1 :: ");
    			String fn1 = sc.next();
    			
    			System.out.println("Enter File Size No.2 :: ");
    			int fs2 = sc.nextInt();
    			
    			System.out.println("Enter File Name No.2 :: ");
    			String fn2 = sc.next();
    			
    			System.out.println("Enter File Size No.3 :: ");
    			int fs3 = sc.nextInt();
    			
    			System.out.println("Enter File Name No.3 :: ");
    			String fn3 = sc.next();
    			
    			App add = new App();
    			add.addRecord(fname, fs1, fn1, fs2, fn2, fs3, fn3);
    			if(add != null) {
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
    			System.out.println("Enter Folder Id :: ");
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
