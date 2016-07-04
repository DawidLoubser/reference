public class AccountTest
 {
   public static void main(String[] args)
   {
     // Create an account
     Account acc1 = new Account("A111");
     // Use it
     acc1.credit(200);
     acc1.debit(50);
     // Display it's state
     System.out.println("balance for " + acc1.getAccountNumber()
                         + " = " + acc1.getBalance());
 
     // Create another account
     Account acc2 = new Account("A112", 5000);
     // Use it
     acc2.debit( 1234.56 );
     // Display it's state by asking the account itself to
     // provide a string suitable for this purpose
     System.out.println( acc2.toString() );
   }
}
