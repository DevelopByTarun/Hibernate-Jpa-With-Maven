package com.tarun.crud;

import com.tarun.dao.EmployeeDAO;
import com.tarun.dto.EmployeeDTO;

import java.util.List;
import java.util.Scanner;

public class App 
{
    @SuppressWarnings("unused")
	public static void main( String[] args ) {
    	@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
    	while(true) {
    		System.out.println("1. Insert Record");
    		System.out.println("2. Show Record");
    		System.out.println("3. Update Record");
    		System.out.println("4. Delete Record");
    		System.out.println("5. Search Record By Id");
    		System.out.println("6. Exit");
    		System.out.println("Enter The Choice");
    		int choice = scanner.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Id :: ");
    			int id = scanner.nextInt();
    			
    			System.out.print("Enter Firstname :: ");
    			String fname = scanner.next();
    			
    			System.out.print("Enter Lastname :: ");
    			String lname = scanner.next();
    			
    			System.out.print("Enter Age :: ");
    			int age = scanner.nextInt();
    			
    			System.out.print("Enter Salary :: ");
    			double sal = scanner.nextDouble();
    			    			
    			int result = EmployeeDAO.addEmployeeRecords(id, fname, lname, age, sal);
    			if(result > 0) {
    				System.out.println("Record Inserted Successfully....");
    			}
    			else {
    				System.out.println("Unable To Insert Records....");
    			} 
    		}
    		else if(choice == 2) {
    			List<EmployeeDTO> list = EmployeeDAO.getEmployeeRecords();
    			for(EmployeeDTO al: list) {
    				System.out.println("Id Is :: "+al.getId());
    				System.out.println("Firstname Is :: "+al.getFirstname());
    				System.out.println("Lastname Is :: "+al.getLastname());
    				System.out.println("Age Is :: "+al.getAge());
    				System.out.println("Salary Is :: "+al.getSalary());
    				System.out.println("");
    			}
    		}
    		else if(choice == 3) {
    			System.out.print("Enter Id :: ");
    			int id = scanner.nextInt();
    			
    			System.out.print("Enter Firstname :: ");
    			String fname = scanner.next();
    			
    			System.out.print("Enter Lastname :: ");
    			String lname = scanner.next();
    			
    			System.out.print("Enter Age :: ");
    			int age = scanner.nextInt();
    			
    			System.out.print("Enter Salary :: ");
    			double sal = scanner.nextDouble();
    			
    			EmployeeDAO empDAO = new EmployeeDAO();
    			empDAO.mergeEmployeeRecords(id, fname, lname, age, sal);
    			if(empDAO != null) {
    				System.out.println("Record Updated Successfully....");
    			}
    			else {
    				System.out.println("Unable To Update Records....");
    			}
    		}
    		else if(choice == 4) {
    			System.out.print("Enter Id :: ");
    			int id = scanner.nextInt();
    			
    			EmployeeDAO empDAO = new EmployeeDAO();
    			empDAO.deleteEmployeeRecords(id);
    			if(empDAO != null) {
    				System.out.println("Record Deleted Successfully....");
    			}
    			else {
    				System.out.println("Unable To Delete Records....");
    			}
    		}
    		else if(choice == 5) {
    			System.out.print("Enter Id :: ");
    			int id = scanner.nextInt();
    			
    			EmployeeDTO empDTO = EmployeeDAO.getEmployeeRecordsById(id);
    			if(empDTO != null) {
    				System.out.println("Firstname Is :: "+empDTO.getFirstname());
    				System.out.println("Lastname Is :: "+empDTO.getLastname());
    				System.out.println("Age Is :: "+empDTO.getAge());
    				System.out.println("Salary Is :: "+empDTO.getSalary());
    			}
    			else {
    				System.out.println("Unable To Search Records....");
    			}
    		}
    		else {
    			System.out.println("Goodbye....nd fuck....uuuu");
    			return;
    		}
    	}
    }
}
