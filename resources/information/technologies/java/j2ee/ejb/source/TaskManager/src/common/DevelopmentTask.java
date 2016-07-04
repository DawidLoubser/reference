package za.co.solms.pm;

public class DevelopmentTask extends TaskType
{
  public DevelopmentTask(String riskFactor) {this.riskFactor = riskFactor;}

  public String toString()
  {
    return "development task with risk factor: " + riskFactor;
  }

  public String getRiskFactor() {return riskFactor;}

  public void setRiskFactor(String newRiskFactor)
  {
    riskFactor = newRiskFactor;
  }

  private String riskFactor;
}
