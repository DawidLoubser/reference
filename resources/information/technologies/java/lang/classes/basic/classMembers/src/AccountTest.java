public class AccountTest
{
   public static void main(String[] args)
   {
     System.out.println("A: no of accounts = " + Account.getNumberOfInstances());
 
     Account a1 = new Account("A1");
     Account a2 = new Account("A2", 200);
 
     System.out.println("B: no of accounts = " + Account.getNumberOfInstances());
 
     a2 = null;
 
     System.out.println("C: no of accounts = " + Account.getNumberOfInstances());
 
     System.gc();
 
     System.out.println("D: no of accounts = " + Account.getNumberOfInstances());
 
   }
}
