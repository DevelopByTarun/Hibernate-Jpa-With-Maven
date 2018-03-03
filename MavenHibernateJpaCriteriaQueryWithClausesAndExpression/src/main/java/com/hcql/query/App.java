package com.hcql.query;

import java.util.ArrayList;
import java.util.Scanner;
import com.hcql.query.dao.EmployeeDAO;
import com.hcql.query.persistent.dto.EmployeePersistentDTO;

public class App 
{
    @SuppressWarnings("resource")
	public static void main( String[] args ) {
    	Scanner sc = new Scanner(System.in);
    	while(true) {
    		System.out.println("");
    		System.out.println("1. Insert Employee Record");
    		System.out.println("2. Show All Employees Records");
    		System.out.println("3. Sort Employee Records By GTE Salary");
    		System.out.println("4. Sort Employees Records By LTE Salaries");
    		System.out.println("5. Sort Employees Records Between GTLT Salaries");
    		System.out.println("6. Sort Name By Descending Order");
    		System.out.println("7. Sort Salary By Ascending Order");
    		System.out.println("8. Criteria Pagination");
    		System.out.println("9. Sort Name With Like With Or Condition");
    		System.out.println("10. Exit");
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
    		else if(choice == 3) {
    			System.out.print("Enter Amount :: ");
    			double amount = sc.nextInt();
    			
    			ArrayList<EmployeePersistentDTO> empList = EmployeeDAO.sortEmployeesByGTESalaries(amount);
    			if(empList != null && empList.size() > 0) {
    				for(EmployeePersistentDTO epDTO: empList) {
    					System.out.println(epDTO);
    				}
    			}
    			else {
    				System.out.println("Unable To Sort Salary For GTE");
    			}
    		}
    		else if(choice == 4) {
    			System.out.print("Enter Amount :: ");
    			double amount = sc.nextInt();
    			
    			ArrayList<EmployeePersistentDTO> empList = EmployeeDAO.sortEmployeesByLTESalaries(amount);
    			if(empList != null && empList.size() > 0) {
    				for(EmployeePersistentDTO epDTO: empList) {
    					System.out.println(epDTO);
    				}
    			}
    			else {
    				System.out.println("Unable To Sort Salary LTE");
    			}
    		}
    		else if(choice == 5) {
    			System.out.print("Enter Greater Than Equals Amount :: ");
    			double amount1 = sc.nextInt();
    			
    			System.out.print("Enter Less Than Equals Amount :: ");
    			double amount2 = sc.nextInt();
    			
    			ArrayList<EmployeePersistentDTO> empList = EmployeeDAO.sortEmployeesByBetweenGTLTSalaries(amount1, amount2);
    			if(empList != null && empList.size() > 0) {
    				for(EmployeePersistentDTO epDTO: empList) {
    					System.out.println(epDTO);
    				}
    			}
    			else {
    				System.out.println("Unable To Sort Salary Between Less Than And Grater Than");
    			}
    		}
    		else if(choice == 6) {
    			ArrayList<EmployeePersistentDTO> empList = EmployeeDAO.sortNameOrderByDescending();
    			if(empList != null && empList.size() > 0) {
    				for(EmployeePersistentDTO epDTO: empList) {
    					System.out.println(epDTO);
    				}
    			}
    			else {
    				System.out.println("Unable To Sort Name By Descending Order");
    			}
    		}
    		else if(choice == 7) {
    			ArrayList<EmployeePersistentDTO> empList = EmployeeDAO.sortSalaryOrderByAscending();
    			if(empList != null && empList.size() > 0) {
    				for(EmployeePersistentDTO epDTO: empList) {
    					System.out.println(epDTO);
    				}
    			}
    			else {
    				System.out.println("Unable To Sort Salary By Ascending Order");
    			}
    		}
    		else if(choice == 8) {
    			System.out.print("Enter Set First Result :: ");
    			int record1 = sc.nextInt();
    			
    			System.out.print("Enter Set Max Result :: ");
    			int record2 = sc.nextInt();
    			
    			ArrayList<EmployeePersistentDTO> empList = EmployeeDAO.criteriaPagination(record1, record2);
    			if(empList != null && empList.size() > 0) {
    				for(EmployeePersistentDTO epDTO: empList) {
    					System.out.println(epDTO);
    				}
    			}
    			else {
    				System.out.println("Unable To Sort Record By Pagination");
    			}
    		}
    		else if(choice == 9) {
    			ArrayList<EmployeePersistentDTO> empList = EmployeeDAO.sortNameWithLike();
    			if(empList != null && empList.size() > 0) {
    				for(EmployeePersistentDTO epDTO: empList) {
    					System.out.println(epDTO);
    				}
    			}
    			else {
    				System.out.println("Unable To Sort Name With Like Condition");
    			}
    		}
    		else {
    			System.out.println("Goodbye....!!");
    			return;
    		}
    	}
    }
}
