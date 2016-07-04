package za.co.solms.javaBeans;

import java.beans.PropertyChangeListener;

/**
 * Specifies a component that acs tas as the source of 
 * property changes events in the publish-subscribe 
 * (also called "Observer") design pattern.
 * 
 * @author fritz@solms.co.za, dawidl@solms.co.za
 */
public interface Observable
{
  /** Add an observer. Receives all property change events */
  public void addObserver(
      PropertyChangeListener observer);

  /** Add an observer for the specified property */
  public void addObserver(String propertyName,
      PropertyChangeListener observer)
      throws NoSuchPropertyException;

  /**
   * Removes an observer. Will no longer receive property 
   * change events.
   */
  public void removeObserver(
      PropertyChangeListener observer);

  /**
   * Removes an observer only from receiving the given property's 
   * change events.
   */
  public void removeObserver(String propertyName,
      PropertyChangeListener observer)
      throws NoSuchPropertyException;

  public class NoSuchPropertyException extends
      Exception
  {
    public NoSuchPropertyException(String name)
    {
      this.name = name;
    }

    public String getName()
    {
      return name;
    }

    private String name;

    private static final long serialVersionUID = 200605051056L;
  }
}
