public class SubscriptionManager
{
  public TransactionConfirmation raiseSubscriptionFee
           (Client client, Account account)
  {
    ...
    account.debit(client.getTotalSubscriptionFee(period));
    ...
  }
}    					
