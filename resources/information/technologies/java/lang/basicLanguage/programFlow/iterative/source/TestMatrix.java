package za.co.solms.forEach;

public class TestMatrix
{
  public void run()
  {
    double[][] matrix = {{1.2, 1.5, 3}, {2.1, 2.7, 2.4}};

    for (double[] row:matrix)
    {
      for (double x:row)
        System.out.print(x + " ");
      System.out.println();  
    }
  }

  public static void main(String[] args)
  {
    new TestMatrix().run();
  }
}