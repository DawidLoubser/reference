package za.co.solms.pm;

import java.util.Set;
import java.util.Date;

public interface TaskLocal extends javax.ejb.EJBLocalObject
{
  public String getId();
  public long getDueDate();
  public String getDescription();
  public TaskType getTaskType();
  public Set getTimeStretches();

  public void setDueDate(long dueDate);
  public void setDescription(String description);

  public long timeSpentInPeriod(long start, long end);

  public void addTimeStretch(long start, long end, String description);
}
