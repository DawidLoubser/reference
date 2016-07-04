
package za.co.SolmsTraining.innerClass;

public class InnerClassTest
{
  public static void main(String[] args)
  {
    double height = 1, beamLength = 1.3;

    MyConstruction construction = new MyConstruction (height, beamLength);

    System.out.println("stress = " + construction.calculateStress());
  }
}

