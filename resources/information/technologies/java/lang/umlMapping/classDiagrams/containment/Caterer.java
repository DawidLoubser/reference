public interface Caterer
{
  public Invoice processOrder(Order order);
  
  public class Order {...}
  
  public class Invoice {...}
}