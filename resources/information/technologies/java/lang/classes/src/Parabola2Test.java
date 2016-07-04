
class Point
{
  public Point(double xCoordinate, double yCoordinate)
  {
    x = xCoordinate;
    y = yCoordinate;
  }

  public double getX() {return x;}

  public double getY() {return y;}

  public String toString() {return "(" + x + "," + y + ")";}

  private double x, y;
}

class Parabola2
{
  public Parabola2(double quadraticCoefficient,
                  double linearCoefficient,
                  double constant)
  {
    a = quadraticCoefficient;
    b = linearCoefficient;
    c = constant;
  }

  public Point getTurningPoint()
  {
    double x = -b/(2*a);
    double y = a*a*x + b*x + c;
    return  new Point(x,y);
  }

  private double a, b, c;
}

public class Parabola2Test
{
  public static void main(String[] args)
  {
    Parabola2 para = new Parabola2(4, 2, 3);

    Point turningPoint = para.getTurningPoint();

    System.out.println("turning point = " + turningPoint);  	
  }
} 

