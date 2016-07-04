
class Point
{
  public Point() {x=0; y=0;}

  public Point(double xCoordinate, double yCoordinate)
  {
    x = xCoordinate;
    y = yCoordinate;
  }

  public double getX() {return x;}

  public double getY() {return y;}

  public void setX(double xCoordinate) {x = xCoordinate;}
  public void setY(double yCoordinate) {y = yCoordinate;}

  public String toString() {return "(" + x + "," + y + ")";}

  private double x, y;
}

class Parabola3
{
  public Parabola3(double quadraticCoefficient,
                  double linearCoefficient,
                  double constant)
  {
    a = quadraticCoefficient;
    b = linearCoefficient;
    c = constant;
  }

  public void calcTurningPoint(Point turningPoint)
  {
    double x = -b/(2*a);
    double y = a*a*x + b*x + c;
    turningPoint.setX(x);
    turningPoint.setY(y);
  }

  private double a, b, c;
}

public class Parabola3Test
{
  public static void main(String[] args)
  {
    Parabola3 para = new Parabola3(4, 2, 3);

    Point turningPoint = new Point();
    para.calcTurningPoint(turningPoint);

    System.out.println("turning point = " + turningPoint);  	
  }
} 

