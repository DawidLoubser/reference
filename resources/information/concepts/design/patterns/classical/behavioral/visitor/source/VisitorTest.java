class Employee
{
  public Employee(String name, double salary)
  {
    this.name = name;
    this.salary = salary;
  }
  public double getSalary()
  {
    return salary;
  }
  public void accept(EmployeeVisitor visitor)
  {
    visitor.visit(this);
  }
  public String toString()
  {
    return name + " :  " + salary;
  }
  private String name;
  private double salary;
}
//---------------------------------------------------------------

class Manager extends Employee
{
  public Manager(String name, double salary, double carAllowance)
  {
    super(name, salary);
    this.carAllowance = carAllowance;
  }
  public double getCarAllowance()
  {
    return carAllowance;
  }

  public void accept(EmployeeVisitor visitor)
  {
    visitor.visit(this);
  }

  public String toString()
  {
    return super.toString() + " (" + carAllowance + ")";
  }
  private double carAllowance;
}
//----------------------------------------------------------------

class Developer extends Employee
{
  public Developer(String name, double salary, double computerAllowance)
  {
    super(name, salary);
    this.computerAllowance = computerAllowance;
  }
  public double getComputerAllowance()
  {
    return computerAllowance;
  }
	
  public void accept(EmployeeVisitor visitor)
  {
    visitor.visit(this);
  }
	
  public String toString()
  {
    return super.toString() + " (" + computerAllowance + ")";
  }
  private double computerAllowance;
}
//---------------------------------------------------------------

interface EmployeeVisitor
{
  public void visit(Employee o);

  public void visit(Manager m);

  public void visit(Developer m);
}
//----------------------------------------------------------------

class TotalSalaryCalculator implements EmployeeVisitor
{
  public void visit(Employee employee)
  {
    totalSalary += employee.getSalary();
  }
  public void visit(Manager manager)
  {
    totalSalary += manager.getSalary();
    totalSalary += manager.getCarAllowance();
  }
  public void visit(Developer developer)
  {
    totalSalary += developer.getSalary();
    totalSalary += developer.getComputerAllowance();
  }

  public double getTotalSalary() {return totalSalary;}

  private double totalSalary = 0;
}
//----------------------------------------------------------------

public class VisitorTest
{
  public void run()
  {
    Employee[] employees = new Employee[6];
    employees[0] = new Employee("Peter", 100000);
    employees[1] = new Manager("Tandi",  100000, 32000);
    employees[2] = new Employee("Jannie", 100000);
    employees[3] = new Manager("Ahmed", 100000, 27000);
    employees[4] = new Developer("Li", 100000, 6000);
    employees[5] = new Employee("Jill", 100000);

    TotalSalaryCalculator totalSalaryCalculator
      = new TotalSalaryCalculator();

    for (int i=0; i<employees.length; ++i)
      employees[i].accept(totalSalaryCalculator);
    
    System.out.println("totalSalary = " + totalSalaryCalculator.getTotalSalary());  
  }    

  public static void main(String[] args)
  {
    new VisitorTest().run();
  }
}
