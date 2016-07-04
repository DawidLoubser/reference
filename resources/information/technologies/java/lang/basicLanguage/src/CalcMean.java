import java.io.*;

public class CalcMean
{

  /** Calculates the average of the two given numbers */
  public static double mean(double a, double b)
  {
    double result = (a + b) / 2;

    return result;
  }


  public static void main(String[] args) throws IOException
  {
    BufferedReader kbd
      = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Enter x: ");
    double x = Double.parseDouble(kbd.readLine());

    System.out.print("Enter y: ");
    double y = Double.parseDouble(kbd.readLine());

    double m = mean(x,y);

    System.out.println("The mean is " + m);
  }
  
}