package za.co.solms.pm;

public class MaintenanceTask extends TaskType
{
  public MaintenanceTask(String problemDescription)
  {
    this.problemDescription = problemDescription;
  }

  public String toString()
  {
    return "Development task. Problem: " + problemDescription;
  }

  public String getProblemDescription() {return problemDescription;}

  public void setProblemDescription(String newProblemDescription)
  {
    problemDescription = newProblemDescription;
  }

  private String problemDescription;
}
