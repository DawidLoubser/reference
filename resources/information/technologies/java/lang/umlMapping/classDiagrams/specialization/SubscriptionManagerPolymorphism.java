public class SubscriptionManager
{				
   public TransactionConfirmation raiseSubscriptionFees(Client client, Account acc)
   {
      ...
      acc.debit(fee);
      ...
   }
}	
