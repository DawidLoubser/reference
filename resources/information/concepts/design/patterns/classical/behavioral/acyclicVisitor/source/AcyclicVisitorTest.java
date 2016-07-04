<![CDATA[
abstract class Employee
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
  
  public abstract void accept(EmployeeVisitor visitor);
  
  public String toString()  
  {
    return name + " :  " + salary;
  }
  
  private String name;
  private double salary;
}    

//-------------------------------------------------------------------


class ContractWorker extends Employee
{
  public ContractWorker(String name, double salary)
  {
    super(name, salary);
  }  

  /**
   * @throws ClassCastException if the visitor is not a
   *         ContractWorkerVisitor.
   */   
  public void accept(EmployeeVisitor v)
  {
    ContractWorkerVisitor visitor = (ContractWorkerVisitor)v;
    visitor.visit(this);
  }  
  
  public String toString() 
  {
    return "Contract worker: " + super.toString();
  }
}  
//-------------------------------------------------------------------

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
  
  public int getLeave() {return 22;}
  
  /**
   * @throws ClassCastException if the visitor is not a
   *         ManagerVisitor.
   */   
  public void accept(EmployeeVisitor v)
  {
    ManagerVisitor visitor = (ManagerVisitor)v;
    visitor.visit(this);
  }    
  
  public String toString() 
  {
    return super.toString() + " (" + carAllowance + ")";
  }
  
  private double carAllowance;
}  

//-------------------------------------------------------------------

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
  
  public int getLeave() {return 20;}
  
  public int getStudyLeave() {return 10;}
 
  /**
   * @throws ClassCastException if the visitor is not a
   *         DeveloperVisitor.
   */   
  public void accept(EmployeeVisitor v)
  {
    DeveloperVisitor visitor = (DeveloperVisitor)v;
    visitor.visit(this);
  }    
  
  public String toString() 
  {
    return super.toString() + " (" + computerAllowance + ")";
  }
  
  private double computerAllowance;
}  

//-------------------------------------------------------------------

interface EmployeeVisitor {}  

//-------------------------------------------------------------------

interface ContractWorkerVisitor extends EmployeeVisitor
{
  public void visit(ContractWorker m);
}
//-------------------------------------------------------------------

interface ManagerVisitor extends EmployeeVisitor
{
  public void visit(Manager m);
}
//-------------------------------------------------------------------

interface DeveloperVisitor extends EmployeeVisitor
{
  public void visit(Developer m);
}  

//-------------------------------------------------------------------

class TotalSalaryCalculator implements ContractWorkerVisitor,
                                       ManagerVisitor,
                                       DeveloperVisitor
{
  public void visit(ContractWorker contractWorker) 
  {
    totalSalary += contractWorker.getSalary();  
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

//-------------------------------------------------------------------

class TotalLeaveCalculator implements ManagerVisitor,
                                      DeveloperVisitor
{
  public void visit(Manager manager) 
  {
    totalLeave += manager.getLeave();
  }
  public void visit(Developer developer) 
  {
    totalLeave += developer.getLeave();
    totalLeave += developer.getStudyLeave();
  }
  
  public double getTotalLeave() {return totalLeave;}
  
  private double totalLeave = 0;
}  

//-------------------------------------------------------------------

public class AcyclicVisitorTest
{
  public void testTotalSalaryCalculator()
  {
    Employee[] employees = new Employee[6];
    employees[0] = new ContractWorker("Peter", 100000);
    employees[1] = new Manager("Tandi",  100000, 32000);
    employees[2] = new ContractWorker("Jannie", 100000);
    employees[3] = new Manager("Ahmed", 100000, 27000);
    employees[4] = new Developer("Li", 100000, 6000);
    employees[5] = new ContractWorker("Jill", 100000);

    TotalSalaryCalculator totalSalaryCalculator 
      = new TotalSalaryCalculator();

    for (int i=0; i<employees.length; ++i)
      employees[i].accept(totalSalaryCalculator);
    
    System.out.println("totalSalary = " + totalSalaryCalculator.getTotalSalary());  
  }    

  public void testTotalLeaveCalculator()
  {
    Employee[] employees = new Employee[3];
    employees[0] = new Manager("Tandi",  100000, 32000);
    employees[1] = new Manager("Ahmed", 100000, 27000);
    employees[2] = new Developer("Li", 100000, 6000);

    TotalLeaveCalculator totalLeaveCalculator 
      = new TotalLeaveCalculator();

    for (int i=0; i<employees.length; ++i)
      employees[i].accept(totalLeaveCalculator);
    
    System.out.println("totalLeave = " 
      + totalLeaveCalculator.getTotalLeave());  
  }    

  public static void main(String[] args)
  {
    AcyclicVisitorTest test = new AcyclicVisitorTest();
    
    test.testTotalSalaryCalculator();
    
    test.testTotalLeaveCalculator();
  }
}
]]>
