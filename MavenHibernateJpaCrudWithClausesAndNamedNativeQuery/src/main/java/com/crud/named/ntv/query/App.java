package com.crud.named.ntv.query;

import java.util.ArrayList;
import java.util.Scanner;

import com.crud.named.ntv.query.dao.EmployeeDAO;
import com.crud.named.ntv.query.persistent.dto.EmployeePersistentDTO;

public class App 
{
    @SuppressWarnings("resource")
	public static void main( String[] args ) {
    	Scanner sc = new Scanner(System.in);
    	while(true) {
    		System.out.println("");
    		System.out.println("1. Insert Employee Record");
    		System.out.println("2. Update Employee Record");
    		System.out.println("3. Delete Employee Record");
    		System.out.println("4. Show All Employees Records");
    		System.out.println("5. Search Employee Record By Id");
    		System.out.println("6. Sort Employee Records By GTE Salary");
    		System.out.println("7. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Employee Name :: ");
    			String name = sc.next();
    			
    			System.out.print("Enter Employee Salary :: ");
    			double salary = sc.nextDouble();
    			
    			EmployeePersistentDTO epDTO = new EmployeePersistentDTO();
    			epDTO.setName(name);
    			epDTO.setSalary(salary);
    			
    			int status = 0;
    			status = EmployeeDAO.addEmployee(epDTO);
    			if(status > 0) {
    				System.out.println("Record Inserted Successfully");
    			}
    			else {
    				System.out.println("Unable To Insert Record");
    			}
    		}
    		else if(choice == 2) {
    			System.out.print("Enter Employee Name :: ");
    			String name = sc.next();
    			
    			System.out.print("Enter Employee Salary :: ");
    			double salary = sc.nextDouble();
    			
    			System.out.print("Enter Employee Id :: ");
    			int id = sc.nextInt();
    			
    			EmployeePersistentDTO epDTO = new EmployeePersistentDTO();
    			epDTO.setName(name);
    			epDTO.setSalary(salary);
    			epDTO.setId(id);
    			
    			int status = 0;
    			status = EmployeeDAO.updateEmployee(epDTO);
    			if(status > 0) {
    				System.out.println("Record Updated Successfully");
    			}
    			else {
    				System.out.println("Unable To Update Record");
    			}
    		}
    		else if(choice == 3) {
    			System.out.print("Enter Employee Id :: ");
    			int id = sc.nextInt();
    			
    			int status = 0;
    			status = EmployeeDAO.deleteEmployee(id);
    			if(status > 0) {
    				System.out.println("Record Deleted Successfully");
    			}
    			else {
    				System.out.println("Unable To Delete Record");
    			}
    		}
    		else if(choice == 4) {
    			ArrayList<EmployeePersistentDTO> empList = EmployeeDAO.getAllEmployees();
    			if(empList != null && empList.size() > 0) {
    				for(EmployeePersistentDTO epDTO: empList) {
    					System.out.println("Employee Id Is :: "+epDTO.getId());
    					System.out.println("Employee Name Is :: "+epDTO.getName());
    					System.out.println("Employee Salary Is :: "+epDTO.getSalary());
    					System.out.println("");
    				}
    			}
    			else {
    				System.out.println("Unable To Show Employees");
    			}
    		}
    		else if(choice == 5) {
    			System.out.print("Enter Employee Id :: ");
    			int id = sc.nextInt();
    			
    			EmployeePersistentDTO epDTO = EmployeeDAO.getEmployeeById(id);
    			if(epDTO != null) {
    				System.out.println("Employee Name Is :: "+epDTO.getName());
					System.out.println("Employee Salary Is :: "+epDTO.getSalary());
    			}
    			else {
    				System.out.println("Unable To Show Employee");
    			}
    		}
    		else if(choice == 6) {
    			System.out.print("Enter Amount :: ");
    			double amount = sc.nextInt();
    			
    			ArrayList<EmployeePersistentDTO> empList = EmployeeDAO.sortEmployeesByGTESalaries(amount);
    			if(empList != null && empList.size() > 0) {
    				for(EmployeePersistentDTO epDTO: empList) {
    					System.out.println(epDTO);
    				}
    			}
    			else {
    				System.out.println("Unable To Show Employees");
    			}
    		}
    		else {
    			System.out.println("Goodbye....!!");
    			return;
    		}
    	}
    }
}
