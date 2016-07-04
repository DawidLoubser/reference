package za.co.solmstcd.exercises;
/**
 * Employee class
 */

public class Employee
{	
	
	private final EmployeeRole role;

	/**
	 * Constructor Employee
	 * takes an argument of the Enum
	 * EmployeeRole
	 * @param role
	 */
	public Employee(EmployeeRole role) {
		this.role = role;		
	}	
	
	public String toString()
	{
		return "This employee is a: "+ role.name();
	}

	public EmployeeRole getRole() {
		return role;
	}
}
