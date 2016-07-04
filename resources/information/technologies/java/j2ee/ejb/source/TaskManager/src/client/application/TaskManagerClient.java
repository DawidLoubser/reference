package za.co.solms.pm;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import java.util.*;

public class TaskManagerClient
{
  public void run()
  {
    try
    {
      TaskManagerRemote taskManager = connect();

      long t = new Date().getTime();

      String id = "TS:" + t;

      taskManager.addTask(id, "description of " + id, t + 5000,
                          new DevelopmentTask("high"));

      id = "TSa:" + (t+1);

      taskManager.addTask(id, "description of " + id, t + 5000,
                          new MaintenanceTask("Total mess. Try and salvage whatever"));

      TaskRemote task = taskManager.getTask(id);

      task.addTimeStretch(t-100,t+200,"-100 -> +200");
      task.addTimeStretch(t+200,t+400,"+200 -> +400");
      task.addTimeStretch(t+800,t+2000,"+800 -> +2000");

      System.out.println("task descr: " + task.getDescription());
      System.out.println("due date: " + task.getDueDate());

      System.out.println("timeSpentInPeriod: [t, t+1000]: "
        + task.timeSpentInPeriod(t, t+1000));

      id = "TSb:" + (t+1);

      taskManager.addTask(id, "description of " + id, t + 5000,
                          new MaintenanceTask("Total mess. Try and salvage whatever"));

      task = taskManager.getTask(id);

      task.addTimeStretch(t-100,t+200,"-100 -> +200");
      task.addTimeStretch(t+200,t+400,"+200 -> +400");
      task.addTimeStretch(t+800,t+2000,"+800 -> +2000");

      System.out.println("task descr: " + task.getDescription());
      System.out.println("due date: " + task.getDueDate());

      System.out.println("timeSpentInPeriod: [t, t+1000]: "
        + task.timeSpentInPeriod(t, t+1000));
    }
    catch (Exception e)
    {
      e.printStackTrace(System.out);
    }
  }

  public TaskManagerRemote connect()
  {
    try
    {
      InitialContext jndiContext = new InitialContext();

      System.out.println("Now looking up session bean " + jndiName + " ...");

      Object beanHomeRef = jndiContext.lookup(jndiName);

      System.out.println("got it");

      TaskManagerHome home =
        (TaskManagerHome)PortableRemoteObject.narrow
          (beanHomeRef, TaskManagerHome.class);

      return home.create();
    }
    catch (Exception e)
    {
      System.out.println("Could not connect to task manager.");
      e.printStackTrace(System.out);
      System.exit(0);
    }
    return null;
  }

  public static void main(String[] args)
  {
    new TaskManagerClient().run();
  }

  private static final String jndiName = "ejb/TaskManager";
  private static final java.text.DateFormat dateFormat
    = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
}
