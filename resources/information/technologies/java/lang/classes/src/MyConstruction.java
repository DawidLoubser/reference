
package za.co.SolmsTraining.innerClass;

import za.co.SolmsTraining.math.functions.*;
import za.co.SolmsTraining.math.numeric.solvers.*;
public class MyConstruction
{
  public MyConstruction (double height, double beamLength)
  {
    this.height = height;
    this.beamLength = beamLength;
  }

  public double calculateStress()
  {
    class InnerFunction implements FunctionDoubleDouble
    {
      public double value(double x)
      {
        return height*Math.sin(x*x + Math.exp(-x))
               + Math.sqrt(x) - beamLength;
      }
    }
    InnerFunction f = new InnerFunction();

    return RootSolvers.biSection(f,0,height,1e-5,1000);
  }

  double height, beamLength;
}

