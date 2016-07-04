package za.co.solms.pm;

import java.util.*;

public class TaskManagerBean implements javax.ejb.SessionBean
{
  public TaskManagerBean() {}

  public void addTask(String id, String description, long dueDate,
                      TaskType taskType)
                throws java.rmi.RemoteException
  {
    try
    {
      javax.naming.Context namingContext = new javax.naming.InitialContext();

      TaskLocalHome taskHome = (TaskLocalHome)
        namingContext.lookup("java:comp/env/ejb/Task");

      taskHome.create(id, description, dueDate, taskType);
    }
    catch (Exception e)
    {
      throw new javax.ejb.EJBException(e);
    }
  }

  public TaskRemote getTask(String id) throws java.rmi.RemoteException
  {
    try
    {
      javax.naming.Context namingContext = new javax.naming.InitialContext();

      TaskHome taskHome = (TaskHome)namingContext.lookup("ejb/Task");

      return taskHome.findByPrimaryKey(id);
    }
    catch (Exception e)
    {
      throw new javax.ejb.EJBException(e);
    }
  }

  //-------------------------------------------------------------------
  //  Further methods required by session beans
  //-------------------------------------------------------------------

  public void ejbCreate() throws javax.ejb.CreateException {}
  public void ejbRemove() {}
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void ejbPostCreate() {}

  public void setSessionContext(javax.ejb.SessionContext context)
  {
    this.context = context;
  }

  public void unsetSessionContext() {context = null;}

  protected javax.ejb.SessionContext context;
}

