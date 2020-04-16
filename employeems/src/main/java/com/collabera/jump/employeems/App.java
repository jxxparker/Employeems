package com.collabera.jump.employeems;

import com.collabera.jump.ems.app.EmployeeManagementSystem;
import com.collabera.jump.ems.exceptions.EmployeeNotFoundException;
import com.collabera.jump.ems.exceptions.EmployeeWithNameNotFoundException;
import com.collabera.jump.ems.model.Employee;
import com.collabera.jump.ems.util.ScannerUtil;
import com.collabera.jump.ems.util.ScannerUtil.Result;
import com.collabera.jump.ems.util.ScannerUtil.TYPES;

import java.util.Scanner;

public class App {

	private static boolean exitFlag = true;

	public static void main(String[] args) {

		EmployeeManagementSystem ems = new EmployeeManagementSystem();

		// ScannerUtil scannerUtil = new ScannerUtil();

//		System.out.println("++++++++++++++++++++");
//		System.out.println("+++++++++EMS++++++++");
//		System.out.println("++++++++++++++++++++");
		
		System.out.println(
				" #####  ####### #       #          #    ######  ####### ######     #    \n" + 
				"#     # #     # #       #         # #   #     # #       #     #   # #   \n" + 
				"#       #     # #       #        #   #  #     # #       #     #  #   #  \n" + 
				"#       #     # #       #       #     # ######  #####   ######  #     # \n" + 
				"#       #     # #       #       ####### #     # #       #   #   ####### \n" + 
				"#     # #     # #       #       #     # #     # #       #    #  #     # \n" + 
				" #####  ####### ####### ####### #     # ######  ####### #     # #     # \n" + 
				"                                                                        \n" + 
				"");

		System.out.println(
				" _       __     __                             __           ________  ________\n" + 
				"| |     / /__  / /________  ____ ___  ___     / /_____     / ____/  |/  / ___/\n" + 
				"| | /| / / _ \\/ / ___/ __ \\/ __ `__ \\/ _ \\   / __/ __ \\   / __/ / /|_/ /\\__ \\ \n" + 
				"| |/ |/ /  __/ / /__/ /_/ / / / / / /  __/  / /_/ /_/ /  / /___/ /  / /___/ / \n" + 
				"|__/|__/\\___/_/\\___/\\____/_/ /_/ /_/\\___/   \\__/\\____/  /_____/_/  /_//____/  \n" + 
				"                                                                              ");

		do {
			System.out.println("Please choose an option to proceed:");

			System.out.println("1. CREATE");
			System.out.println("2. UPDATE");
			System.out.println("3. DELETE");
			System.out.println("4. SEARCH");
			System.out.println("5. EXIT");
			
			Employee emp = new Employee();
			
			Employee emp1 = new Employee();
			
			if( emp.equals(emp1))
			{
				System.out.println("They are same employee");
			}

			Result option = ScannerUtil.retryUntilSucceeds("Your input: ", TYPES.INT, 5);

			switch (option.getValueAsInt()) {
			case 1: {
				System.out.println("Please choose an option to proceed:");
				System.out.println("1. EMPLOYEE");
				System.out.println("2. MANAGER");
				option = ScannerUtil.retryUntilSucceeds("Your input: ", TYPES.INT, 2);
				// scanner.nextLine();
				switch (option.getValueAsInt()) {
				case 1:
					ems.createEmployeeWithInputs();
					break;
				case 2:
					System.out.println("Create Manager");
					ems.createEmployeeWithInputs();
					ems.createManagerWithInputs();
					break;
				default:
					System.out.println("Invalid Input");
					break;
				}

			}
				break;
			case 2:
				System.out.println(2);
				Result emID = ScannerUtil.retryUntilSucceeds("Enter the id of the employee you wish to find:", TYPES.INT, Integer.MAX_VALUE);
				Result fieldToUpdate = ScannerUtil.retryUntilSucceeds("Enter the field you want to update? \n1 - SSN \n2 - Email \n3 - Name \n4 - Age \n5 - DOB \n6 - Home Phone Number \n7 - Home Address \n8 - Work Address", TYPES.INT, 8);
				Result value = ScannerUtil.retryUntilSucceeds("Enter the new value for your field: ", TYPES.LINE, 0);
				switch (fieldToUpdate.getValueAsInt()) {
					case 1:
						ems.updateEmployee("emp_ssn", emID.getValueAsInt(), (String) value.getValue());
						break;
					case 2:
						ems.updateEmployee("emp_email_id", emID.getValueAsInt(), (String) value.getValue());
						break;
					case 3:
						ems.updateEmployee("emp_name", emID.getValueAsInt(), (String) value.getValue());
						break;
					case 4:
						ems.updateEmployee("emp_age", emID.getValueAsInt(), (String) value.getValue());
						break;
					case 5:
						ems.updateEmployee("emp_dob", emID.getValueAsInt(), (String) value.getValue());
						break;
					case 6:
						ems.updateEmployee("emp_phone_num", emID.getValueAsInt(), (String) value.getValue());
						break;
					case 7:
						ems.updateEmployee("emp_home_address", emID.getValueAsInt(), (String) value.getValue());
						break;
					case 8:
						ems.updateEmployee("emp_work_address", emID.getValueAsInt(), (String) value.getValue());
						break;
				}
				break;
			case 3:
				System.out.println(3);
				Result eID = ScannerUtil.retryUntilSucceeds("Enter the id of the employee you wish to find:", TYPES.INT, Integer.MAX_VALUE);
				ems.deleteEmployee(eID.getValueAsInt());
				break;
			case 4:
				System.out.println(4);
				Result searchValue = ScannerUtil.retryUntilSucceeds("Would you like to search by name or id? 1 - Id or 2-Name", TYPES.INT, 2);
				switch (searchValue.getValueAsInt()) {
					case 1:
						Result empID = ScannerUtil.retryUntilSucceeds("Enter the id of the employee you wish to find:", TYPES.INT, Integer.MAX_VALUE);
						try {
							Employee employ = ems.getEmployeeById(empID.getValueAsInt());
							System.out.println(employ);
						} catch (EmployeeNotFoundException e) {
							e.printStackTrace();
						}
						break;
					case 2:
						Result empName = ScannerUtil.retryUntilSucceeds("Enter employee name: ", TYPES.LINE, 0);
						try {
							Employee employ = ems.getEmployeeBynName((String) empName.getValue());
							System.out.println(employ);
						} catch (EmployeeWithNameNotFoundException e) {
							e.printStackTrace();
						}
						break;
				}
				break;
			case 5:
				exitFlag = false;
				ems.save();
				System.out.println("Thanks for using EMS!");
				break;
			default:
				break;
			}
		} while (exitFlag);

		ScannerUtil.close();

	}

}
