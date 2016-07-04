
package rmi.countValue;

public class CounterClient extends Object
{
    public static void main(String[] args) throws Exception
    {
        CounterFactory counterFactory
          = (CounterFactory)java.rmi.Naming.lookup("/rmi/count/CounterFactory");

        Counter counter = counterFactory.getCounter();
        System.out.println("counter value = " + counter.getCount());
        counter.increment();
        System.out.println("counter value = " + counter.getCount());
        System.out.println("factory's counter value = "
          + counterFactory.getCounter().getCount());
    }
}

