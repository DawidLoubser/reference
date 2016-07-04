
public class Employee extends Person
{
  public Employee (String name, String idNumber, 
                   double salary, String jobDescription)
  {
    super(name, idNumber);
    this.salary = salary;
    this.jobDescription = jobDescription;
  }

  public Employee (String name, String idNumber, double salary)
  {
    this(name, idNumber, salary, "Employee");
  }
  
  public double getSalary() {return salary;}
  
  public String getJobDecription() {return jobDescription;}
  
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    
    result.append(", salary = R").append(salaryFormat.format(salary));
    
    result.append(" (").append(jobDescription).append(")");
    
    return result.toString();
  }  
  
  public void finalize() throws Throwable
  {
    jobDescription = null;
    super.finalize();
  }  
    
  private double salary;
  
  private String jobDescription;  
    
  private static java.text.DecimalFormat salaryFormat
    = new java.text.DecimalFormat("#,###,###.##");
}    

