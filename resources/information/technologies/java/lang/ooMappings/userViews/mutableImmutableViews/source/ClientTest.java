public class ClientTest
{
  public static void main(String[] args) throws Exception
  {
    Client.Mutable client = new ClientImpl( "Joe Bloggs" );
    client.setAccount( new AccountImpl("A123") );
    
    client.getAccount().credit( 500.00 );
  }
}
