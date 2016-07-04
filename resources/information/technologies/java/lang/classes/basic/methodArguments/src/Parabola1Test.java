class Parabola1
{
   public Parabola1(double quadraticCoefficient,
                            double linearCoefficient,
                            double constant)
   {
     a = quadraticCoefficient;
     b = linearCoefficient;
     c = constant;
   }
 
   public void calcTurningPoint(double x, double y)
   {
     x = -b/(2*a);
     y = a*a*x + b*x + c;
 
     System.out.println("in calcTurningPoint:");
     System.out.println("x = " + x);
     System.out.println("y = " + y);
   }
 
   private double a, b, c;
}
 
public class Parabola1Test
{
   public static void main(String[] args)
   {
     Parabola1 para = new Parabola1(4, 2, 3);
 
     double x=0, y=0;
 
     para.calcTurningPoint(x, y);
 
 
     System.out.println("/nback in main:");
     System.out.println("x = " + x);
     System.out.println("y = " + y);
   }
}
