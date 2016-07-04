package za.co.solms.pm;

public interface TimeStretchLocal extends javax.ejb.EJBLocalObject
{
  public String getId();
  public long getStart();
  public long getEnd();
  public String getDescription();

  public void setStart(long dateTime);
  public void setEnd(long dateTime);
  public void setDescription(String description);

  public long timeSpentInPeriod(long start, long end);
}

