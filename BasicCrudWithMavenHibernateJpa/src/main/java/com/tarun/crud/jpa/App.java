package com.tarun.crud.jpa;

import java.util.List;
import java.util.Scanner;

import com.tarun.crud.dao.StudentRecordsDAO;
import com.tarun.crud.dto.StudentRecordsDTO;

public class App 
{
    @SuppressWarnings("unused")
	public static void main( String[] args ) {
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	while(true) {
    		System.out.println("1. Insert Record");
    		System.out.println("2. Update Record");
    		System.out.println("3. Delete Record");
    		System.out.println("4. Show Record");
    		System.out.println("5. Search Record By Id");
    		System.out.println("6. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Id :: ");
    			int id = sc.nextInt();
    			
    			System.out.print("Enter Firstname :: ");
    			String firstname = sc.next();
    			
    			System.out.print("Enter Lastname :: ");
    			String lastname = sc.next();
    			
    			System.out.print("Enter Age :: ");
    			int age = sc.nextInt();
    			
    			System.out.print("Enter City :: ");
    			String city = sc.next();
    			
    			int result = StudentRecordsDAO.addRecords(id, firstname, lastname, age, city);
    			if(result > 0) {
    				System.out.println("Record Inserted Successfully....");
    			}
    			else {
    				System.out.println("Unable To Insert Records....");
    			}
    		}
    		else if(choice == 2) {
    			System.out.print("Enter Id :: ");
    			int ids = sc.nextInt();
    			
    			System.out.print("Enter Firstname :: ");
    			String fn = sc.next();
    			
    			System.out.print("Enter Lastname :: ");
    			String ln = sc.next();
    			
    			System.out.print("Enter Age :: ");
    			int ag = sc.nextInt();
    			
    			System.out.print("Enter City :: ");
    			String ct = sc.next();
    				
    			StudentRecordsDAO srDAO = new StudentRecordsDAO();
    			srDAO.updateRecords(ids, fn, ln, ag, ct);
    			if(srDAO != null) {
    				System.out.println("Record Updated Successfully....");
    			}
    			else {
    				System.out.println("Unable To Update Records....");
    			}
    		}
    		else if(choice == 3) {
    			System.out.print("Enter Id :: ");
    			int ids = sc.nextInt();
    			
    			StudentRecordsDAO srDAO = new StudentRecordsDAO();
    			srDAO.deleteRecords(ids);
    			if(srDAO != null) {
    				System.out.println("Record Deleted Successfully....");
    			}
    			else {
    				System.out.println("Unable To Delete Records....");
    			}
    		}
    		else if(choice == 4) {
    			List<StudentRecordsDTO> list = StudentRecordsDAO.getRecords();
    			for(StudentRecordsDTO srd : list) {
    				System.out.println("Id Is :: "+srd.getId());
    				System.out.println("Firstname Is :: "+srd.getFirstName());
    				System.out.println("Lastname Is :: "+srd.getLastName());
    				System.out.println("Age Is :: "+srd.getAge());
    				System.out.println("City Is :: "+srd.getCity());
    				System.out.println("");
    			}
    		}
    		else if(choice == 5) {
    			System.out.print("Enter Id :: ");
    			int i = sc.nextInt();
    			
    			StudentRecordsDAO srDAO = new StudentRecordsDAO();
    			srDAO.searchById(i);
    		}
    		else {
    			System.out.println("Goodbye....!!");
    			return;
    		}
    	}
    }
}
