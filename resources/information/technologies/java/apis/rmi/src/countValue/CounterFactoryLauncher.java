
package rmi.countValue;

public class CounterFactoryLauncher extends Object
{
    public CounterFactoryLauncher() {}

    public void launch()
                throws java.rmi.RemoteException, java.net.MalformedURLException
    {
        java.rmi.Naming.rebind("/rmi/count/CounterFactory", new CounterFactoryImpl());
    }

    public static void main(String[] args)
    {
        try
        {
          new CounterFactoryLauncher().launch();

          System.out.println("CounterFactory launched.");
        }
        catch (Exception e) {e.printStackTrace();}
    }
}

