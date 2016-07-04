package za.co.solms.pm;

import java.util.Set;
import java.util.Date;
import java.util.Iterator;
import java.util.Collection;

public abstract class TaskBean implements javax.ejb.EntityBean
{
  public TaskBean() {}

  //-------------------------------------------------------------------
  //  Abstract accessor methods
  //-------------------------------------------------------------------

  public abstract String getId();
  public abstract long getDueDate();
  public abstract String getDescription();
  public abstract TaskType getTaskType(); // value object
  public abstract Set getTimeStretches(); // related entity beans

  public abstract void setId(String newId);
  public abstract void setDueDate(long dueDate);
  public abstract void setDescription(String description);
  public abstract void setTaskType(TaskType taskType);  // value object
  public abstract void setTimeStretches(Set timeStretches); // related entity beans

  //-------------------------------------------------------------------
  //  Selector methods
  //-------------------------------------------------------------------

  public abstract Collection ejbSelectTimeStretchesInPeriod
                                 (String taskId, long start, long end)
           throws javax.ejb.FinderException;

  //-------------------------------------------------------------------
  //  Other business methods
  //-------------------------------------------------------------------

  public long timeSpentInPeriod( long start, long end)
  {
    long totalTimeSpentInPeriod = 0;
    try
    {
      Collection relevantTimeStretches
        = ejbSelectTimeStretchesInPeriod(getId(), start, end);

      Iterator iter = relevantTimeStretches.iterator();
      while (iter.hasNext())
      {
        TimeStretchLocal timeStretch = (TimeStretchLocal)iter.next();
        totalTimeSpentInPeriod += timeStretch.timeSpentInPeriod(start, end);
      }
    }
    catch (javax.ejb.FinderException e) {}

    return totalTimeSpentInPeriod;
  }

  public void addTimeStretch(long start, long end, String description)
  {
    try
    {
      javax.naming.Context namingContext = new javax.naming.InitialContext();

      TimeStretchLocalHome timeStretchHome = (TimeStretchLocalHome)
        namingContext.lookup("java:comp/env/ejb/TimeStretch");

      String id = Long.toString(new Date().getTime());
                     // id == creation time stamp

      TimeStretchLocal timeStretch
        = timeStretchHome.create(id, start, end, description);

      getTimeStretches().add(timeStretch);
    }
    catch (Exception e)
    {
      throw new javax.ejb.EJBException(e);
    }
  }
  //-------------------------------------------------------------------
  //  Create and postCreate methods
  //-------------------------------------------------------------------

  public String ejbCreate(String id, String description, long dueDate,
                          TaskType taskType)
                                     throws javax.ejb.CreateException
  {
    setId(id);
    setDescription(description);
    setDueDate(dueDate);
    setTaskType(taskType);

    return id;
  }

  public void ejbPostCreate(String id, String description, long dueDate,
                            TaskType taskType) {}

  //-------------------------------------------------------------------
  //  Further methods required by entity beans
  //-------------------------------------------------------------------

  public void ejbRemove() {}
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void ejbLoad() {}
  public void ejbStore() {}

  public void setEntityContext(javax.ejb.EntityContext context)
  {
    this.context = context;
  }

  public void unsetEntityContext() {context = null;}

  protected javax.ejb.EntityContext context;
}

