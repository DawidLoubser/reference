public class Employee extends Person
{
  public Employee (String name, String idNumber, 
                   double salary, String employer)
  {
    super(name, idNumber);
    this.salary = salary;
    this.employer = employer;
  }
  
  public void speak()
  {
      System.out.println("Thank you for supporting " + employer 
          + ", my name is " + getName() + " - how can I help you?");
  }
  
  public void doWork( WorkRequest w )
  {
      // ...
  }
 
  public double getSalary()
  {
    return salary;
  }
 
  public String getEmployer()
  {
    return employer;
  }
 
   private double salary;
   private String employer;
}