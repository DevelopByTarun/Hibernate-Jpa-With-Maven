package com.hql.partial.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.hql.partial.object.persistent.dto.EmployeePersistentDTO;
import com.hql.partial.object.dao.EmployeeDAO;

public class App 
{
    @SuppressWarnings("resource")
	public static void main( String[] args ) {
    	Scanner sc = new Scanner(System.in);
    	while(true) {
    		System.out.println("");
    		System.out.println("1. Insert Employee Record");
    		System.out.println("2. Show All Employees Records");
    		System.out.println("3. Show Employee Name From Employee Records");
    		System.out.println("4. Show Employee Name And Salary From Employee Records");
    		System.out.println("5. Show Employee Name, Salary And Address From Employee Records");
    		System.out.println("6. Exit");
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
    			ArrayList<String> empList = EmployeeDAO.getEmployeesName();
    			if(empList != null && empList.size() > 0) {
    				for(String name: empList) {
    					System.out.println(name);
    				}
    			}
    			else {
    				System.out.println("Unable To Show Employees Name");
    			}
    		}
    		else if(choice == 4) {
    			ArrayList<Map<String, Integer>> empList = EmployeeDAO.getEmployeesNameAndSalary();
    			if(empList != null && empList.size() > 0) {
    				for(Map<String, Integer> map : empList) {
    					System.out.println(map);
    				}
    			}
    			else {
    				System.out.println("Unable To Show Employees Name And Salary");
    			}
    		}
    		else if(choice == 5) {
    			List<Object[]> empList = EmployeeDAO.getEmployeesNameSalaryAndAddress();
    			if(empList != null && empList.size() > 0) {
    				for(int i = 0; i < empList.size(); i++) {
    					Object[] data = empList.get(i);
    					System.out.println("Employee Name Is :: "+data[0]);
    					System.out.println("Employee Address Is :: "+data[1]);
    					System.out.println("Employee Salary Is :: "+data[2]);
    					System.out.println("");
    				}
    			}
    			else {
    				System.out.println("Unable To Show Employees Name, Address And Salary");
    			}
    		}
    		else {
    			System.out.println("Goodbye....!!");
    			return;
    		}
    	}
    }
}
