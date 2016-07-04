package za.co.solmstcd.exercises;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EmployeeClient {
	
	private static Map<EmployeeRole, List<Task>> table = 
	    new EnumMap<EmployeeRole, List<Task>>(EmployeeRole.class);

	public static void main(String[] args) {
		// prints out the employee roles and their associated tasks
		assignTasks();

		// Create employee
		// and assign role
		// and print out tasks
		Employee newEmp = new Employee(EmployeeRole.CATERER);
		System.out.println(newEmp);
		System.out.println("This employee has the following tasks:");
		
		printTasks(newEmp.getRole());				
	}

	/**
	 * Assign specific tasks to certain employee roles
	 * 
	 */
	
	private static void assignTasks() {
		for (EmployeeRole empRole : EmployeeRole.values()) {
			switch (empRole) {
			case MANAGER:
				populateMap(empRole, Task.MANAGEMENT, Task.SALES);

				break;

			case ADMINISTRATOR:
				populateMap(empRole, Task.DOCUMENTS, Task.MINUTES);
				break;

			case ANALYST:
				populateMap(empRole, Task.ANALYSIS, Task.DOCUMENTS,
						Task.PROGRAMMING);

				break;

			case CATERER:
				populateMap(empRole, Task.DRINKS, Task.FOOD);

				break;

			case DEVELOPER:
				populateMap(empRole, Task.DOCUMENTS, 
				    Task.PROGRAMMING, Task.ANALYSIS);
				break;
			}
		}
	}

	private static void populateMap(EmployeeRole role, Task... tasks) {
		table.put(role, createList(tasks));
	}

	private static List<Task> createList(Task... tasks) {
		List<Task> taskList = new ArrayList<Task>();

		for (Task t : tasks) {
			taskList.add(t);
		}
		return taskList;
	}
	
	public static void printTasks(EmployeeRole role)
	{
		System.out.println(table.get(role));		
	}
	
}
