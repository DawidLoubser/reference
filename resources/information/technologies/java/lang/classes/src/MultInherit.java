
interface Account
{
  public void  debit(double amount);
  public void credit(double amount);
  public double getBalance();
}

interface Person
{
  public String getName();
}

interface Employee extends Person
{
  public double getSalary();
}

interface Client extends Person
{
  public Account getAccount();
}

interface EmployedClient extends Employee, Client {}

// Implementations:

class AccountImpl implements Account
{
  public AccountImpl() {}

  public AccountImpl(double initialBalance) {balance = initialBalance;}

  public void debit(double amount) {balance -= amount;}

  public void credit(double amount) {balance += amount;}

  public double getBalance() {return balance;}

  private double balance;
}

class PersonImpl implements Person
{
  public PersonImpl(String name) {this.name = name;}

  public String getName() {return name;}

  public String toString() {return name;}

  private String name;
}

class EmployeeImpl extends PersonImpl implements Employee
{
  public EmployeeImpl(String name, double salary)
  {
    super(name);
    this.salary = salary;
  }

  public double getSalary() {return salary;}

  private double salary;
}

class ClientImpl extends PersonImpl implements Client
{
  public ClientImpl(String name, double accountBalance)
  {
    super(name);
    account = new AccountImpl(accountBalance);
  }

  public Account getAccount() {return account;}

  private Account account;
}

class EmployedClientImpl extends EmployeeImpl implements EmployedClient
{
  public EmployedClientImpl(String name, double salary, double accountBalance)
  {
    super(name, salary);
    account = new AccountImpl(accountBalance); // code duplication
  }

  public Account getAccount() {return account;} // code duplication

  private Account account; // code duplication
}

// Client code:

public class MultInherit
{
  public static double calcTotalSalary(Employee[] employees)
  {
    double sum = 0;
    for (int i=0; i<employees.length; ++i)
      sum += employees[i].getSalary();
    return sum;
  }

  public static double calcTotalBalance(Client[] clients)
  {
    double sum = 0;
    for (int i=0; i<clients.length; ++i)
      sum += clients[i].getAccount().getBalance();
    return sum;
  }

  public static void main(String[] args)
  {
    Employee[] employees = new Employee[3];
    employees[0] = new EmployeeImpl("Peter", 8000);
    employees[1] = new EmployedClientImpl("Jane", 12000, -2000);
    employees[2] = new EmployeeImpl("Mbuyu", 11000);

    System.out.println("total salary = " + calcTotalSalary(employees));

    Client[] clients = new Client[4];
    clients[0] = new ClientImpl("Jan", 2500);
    clients[1] = new EmployedClientImpl("Ellen", 9000, 1000);
    clients[2] = new EmployedClientImpl("Li", 8500, -1000);
    clients[3] = new ClientImpl("Temba", -500);

    System.out.println("total accounts balance = " + calcTotalBalance(clients));
  }
}

