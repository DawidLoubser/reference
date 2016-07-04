public class Person
{
  public Person (String name, String idNumber)
  {
    this.name = name;
    this.idNumber = idNumber;
  }
  
  public void speak()
  {
      System.out.println("Yo, my name is " + name);
  }
 
  public String getName() 
  {
    return name;
  }
 
  public String getIdNumber()
  {
    return idNumber;
  }

  private String name;
  private String idNumber;
}