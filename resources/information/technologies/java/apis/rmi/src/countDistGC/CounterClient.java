
package rmi.countDistGC;

public class CounterClient extends Object
{
    public static void main(String[] args) throws Exception
    {
        CounterFactory counterFactory
          = (CounterFactory)java.rmi.Naming.lookup("/rmi/count/CounterFactory");

        Counter counter = counterFactory.getCounter();
        counter.increment();
        System.out.println("count = " + counter.getCount());
    }
}

