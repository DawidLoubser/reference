package za.co.solms.pm;

public abstract class TimeStretchBean implements javax.ejb.EntityBean
{
  public TimeStretchBean() {}

  public abstract String getId();
  public abstract long getStart();
  public abstract long getEnd();
  public abstract String getDescription();
  public abstract TaskRemote getTask();

  public abstract void setId(String id);
  public abstract void setStart(long dateTime);
  public abstract void setEnd(long dateTime);
  public abstract void setDescription(String description);
  public abstract void setTask(TaskRemote task);

  public long timeSpentInPeriod(long start, long end)
  {
    start = Math.max(start, getStart());
    end   = Math.min(end, getEnd());

    return end - start;
  }

  public String ejbCreate(String id, long start, long end, String description)
                                     throws javax.ejb.CreateException
  {
    setId(id);
    setStart(start);
    setEnd(end);
    setDescription(description);

    return id;
  }

  public void ejbPostCreate(String id, long start, long end,
                            String description) {}

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
