package za.co.solmstcd.exercises;
	/**
	 * 	enum Employee Role is a list of constant
	 *  employee roles
	 */
	public enum EmployeeRole 
	{
		ADMINISTRATOR, DEVELOPER, ANALYST, 
		MANAGER, CATERER;
		
		public String toString() 
		{
			return this.name();
		}
		
		public EmployeeRole role() {
			return this;
		}
		

	}	
	

