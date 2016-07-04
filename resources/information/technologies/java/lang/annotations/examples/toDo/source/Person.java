package za.co.solms.annotations;

@ToDo(taskType=ToDo.TaskType.DOCUMENTATION, 
      importance=ToDo.Importance.CRITICAL,
      reasons={"Jane bugs me all the time","Boss threatens me"})
public class Person
{
  @ToDo(taskDescription="Change firstNames to String[]",
        taskType=ToDo.TaskType.REFACTORIZATION, 
        importance=ToDo.Importance.VERY_IMPORTANT,
        reasons={"Matching on persons fail regularly","Boss threatens me"})
  @Deprecated
  public Person(String firstNames, String surNames)
  {
    this.firstNames =  firstNames;
    this.surName = surName;
  }

  @ToDo(taskDescription="Change to separate methods",
        taskType=ToDo.TaskType.REFACTORIZATION, 
        importance=ToDo.Importance.VERY_IMPORTANT,
        reasons={"Matching on persons fail regularly","Boss threatens me"})
  public String getName() {return firstNames + " " + surName;}

  private String firstNames, surName;
}