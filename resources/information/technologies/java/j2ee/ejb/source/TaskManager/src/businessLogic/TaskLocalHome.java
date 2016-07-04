package za.co.solms.pm;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

import java.util.Collection;
import java.util.Date;

public interface TaskLocalHome extends javax.ejb.EJBLocalHome
{
  public TaskLocal create(String id, String description, long dueDate,
                          TaskType taskType)
                                     throws CreateException;

  public TaskLocal findByPrimaryKey(String id)
                                 throws FinderException;

  public Collection findAll() throws FinderException;

  public Collection findDueBefore(long dateTime) throws FinderException;
}
