
package rmi.countValue;

public class CounterImpl implements Counter
{
    public CounterImpl() {}

    public void increment() {++count;}

    public int getCount() {return count;}

    public void finalize() throws Throwable
    {
        System.out.println("Counter's finalize() called.");
        super.finalize();
    }

    private int count = 0;
}

