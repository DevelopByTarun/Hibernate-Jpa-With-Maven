package com.hql.functions.pagination;

import java.util.ArrayList;
import java.util.Scanner;
import com.hql.functions.pagination.persistent.dto.EmployeePersistentDTO;
import com.hql.functions.pagination.dao.EmployeeDAO;

public class App 
{
    @SuppressWarnings("resource")
	public static void main( String[] args ) {
    	Scanner sc = new Scanner(System.in);
    	while(true) {
    		System.out.println("");
    		System.out.println("1. Insert Employee Record");
    		System.out.println("2. Show All Employees Records");
    		System.out.println("3. Show Pagination");
    		System.out.println("4. Show Minimun Employee Salary From Employee Records");
    		System.out.println("5. Show Maximun Employee Salary From Employee Records");
    		System.out.println("6. Show Total Salary Of Employees");
    		System.out.println("7. Show Average Salary Of Employees");
    		System.out.println("8. Show Total No Of Employees Id");
    		System.out.println("9. Exit");
    		System.out.println("Enter The Choice");
    		int choice = sc.nextInt();
    		if(choice == 1) {
    			System.out.print("Enter Employee Name :: ");
    			String name = sc.next();
    			
    			System.out.print("Enter Employee Address :: ");
    			String address = sc.next();
    			
    			System.out.print("Enter Employee Salary :: ");
    			double salary = sc.nextDouble();
    			
    			EmployeePersistentDTO epDTO = new EmployeePersistentDTO();
    			epDTO.setName(name);
    			epDTO.setSalary(salary);
    			epDTO.setAddress(address);
    			
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
    			ArrayList<EmployeePersistentDTO> empList = EmployeeDAO.getAllEmployees();
    			if(empList != null && empList.size() > 0) {
    				for(EmployeePersistentDTO epDTO: empList) {
    					System.out.println("Employee Id Is :: "+epDTO.getId());
    					System.out.println("Employee Name Is :: "+epDTO.getName());
    					System.out.println("Employee Salary Is :: "+epDTO.getSalary());
    					System.out.println("Employee Address Is :: "+epDTO.getAddress());
    					System.out.println("");
    				}
    			}
    			else {
    				System.out.println("Unable To Show Employees");
    			}
    		}
    		else if(choice == 3) {
    			System.out.print("Enter Set First Result :: ");
    			int record1 = sc.nextInt();
    			
    			System.out.print("Enter Set Max Result :: ");
    			int record2 = sc.nextInt();
    			
    			ArrayList<EmployeePersistentDTO> empList = EmployeeDAO.pagination(record1, record2);
    			if(empList != null && empList.size() > 0) {
    				for(EmployeePersistentDTO epDTO: empList) {
    					System.out.println(epDTO);
    				}
    			}
    			else {
    				System.out.println("Unable To Sort Record By Pagination");
    			}
    		}
    		else if(choice == 4) {
    			ArrayList<Integer> empList = EmployeeDAO.getEmployeeMinimunSalary();
    			if(empList != null && empList.size() > 0) {
    				System.out.println("Minimum Salary Is :: "+empList.get(0));
    			}
    			else {
    				System.out.println("Unable To Show Minimun Salary");
    			}
    		}
    		else if(choice == 5) {
    			ArrayList<Integer> empList = EmployeeDAO.getEmployeeMaximumSalary();
    			if(empList != null && empList.size() > 0) {
    				System.out.println("Maximum Salary Is :: "+empList.get(0));
    			}
    			else {
    				System.out.println("Unable To Show Maximum Salary");
    			}
    		}
    		else if(choice == 6) {
    			ArrayList<Integer> empList = EmployeeDAO.getEmployeesTotalSalary();
    			if(empList != null && empList.size() > 0) {
    				System.out.println("Total Salary Is :: "+empList.get(0));
    			}
    			else {
    				System.out.println("Unable To Show Total Salary");
    			}
    		}
    		else if(choice == 7) {
    			ArrayList<Integer> empList = EmployeeDAO.getEmployeesAverageSalary();
    			if(empList != null && empList.size() > 0) {
    				System.out.println("Average Salary Is :: "+empList.get(0));
    			}
    			else {
    				System.out.println("Unable To Show Average Salary");
    			}
    		}
    		else if(choice == 8) {
    			ArrayList<Integer> empList = EmployeeDAO.getEmployeeTotalId();
    			if(empList != null && empList.size() > 0) {
    				System.out.println("Total Id Is :: "+empList.get(0));
    			}
    			else {
    				System.out.println("Unable To Show Total Id");
    			}
    		}
    		else {
    			System.out.println("Goodbye....!!");
    			return;
    		}
    	}
    }
}
