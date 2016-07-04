package za.co.solms.pm;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

import java.util.Collection;

public interface TimeStretchLocalHome extends javax.ejb.EJBLocalHome
{
  public TimeStretchLocal create(String id, long start, long end,
                                  String description)
                     throws CreateException;

  public TimeStretchLocal findByPrimaryKey(String id)
                                 throws FinderException;

  public Collection findAll() throws FinderException;
}

