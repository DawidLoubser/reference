
public class Person
{
  public Person (String name, String idNumber)
  {
    this.name = name;
    this.idNumber = idNumber;
  }

  public String toString()
  {
    return idNumber + ": " + name;
  }

  public String getName() {return name;}

  public String getIdNumber() {return idNumber;}

  public void finalize() throws Throwable
  {
    name = null;
    idNumber = null;
    super.finalize();
  }

  private String name, idNumber;
}

