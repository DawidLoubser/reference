package za.co.solms.javaBeans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;

/**
 * Provides observability support to subclasses (typically Java Bean
 * implementations)
 * 
 * @author fritz@solms.co.za
 * 
 */
public abstract class ObservableBase implements
    Observable
{
  protected ObservableBase()
  {
    propertyChangeSupport = new PropertyChangeSupport(
        getThisHandle());
  }

  /**
   * Provides polymorphic access to the this handle. 
   * This method must be overridden by EVERY 
   * subclass at any level of the specialization hierarchy,
   * i.e., if Person is a subclass of ObservableBase and 
   * Employee is a subclass
   * of Person it must be overridden in both, Person and 
   * Employee.
   * 
   * @return the this handle for the object from whom 
   *        the this handle is requested.
   */
  protected abstract Object getThisHandle();

  /**
   * Returns a set of all the objects properties, irrespective 
   * of whether they are defined in the object's class or in 
   * one of the superclasses of the object's class.
   * 
   * @return all properties published by the object.
   */
  public abstract Set<String> getProperties();

  /**
   * Adds an observer on the object's state.
   */
  public void addObserver(
      PropertyChangeListener observer)
  {
    propertyChangeSupport
        .addPropertyChangeListener(observer);
  }

  /**
   * Adds an observer for a particular property of the 
   * object's state.
   */
  public void addObserver(String propertyName,
      PropertyChangeListener observer)
      throws NoSuchPropertyException
  {
    if (getProperties().contains(propertyName))
      propertyChangeSupport.addPropertyChangeListener(
          propertyName, observer);
    else
      throw new NoSuchPropertyException(propertyName);
  }

  /**
   * Removes the provided observer on the object's state.
   */
  public void removeObserver(
      PropertyChangeListener observer)
  {
    propertyChangeSupport
        .removePropertyChangeListener(observer);
  }

  /**
   * Removes the provided observer for a particular property 
   * of the object's state.
   */
  public void removeObserver(String propertyName,
      PropertyChangeListener observer)
      throws NoSuchPropertyException
  {
    if (getProperties().contains(propertyName))
      propertyChangeSupport
          .removePropertyChangeListener(propertyName,
              observer);
    else
      throw new NoSuchPropertyException(propertyName);
  }

  /**
   * The property change support facilitating convenient 
   * event distribution accross observers.
   */
  protected PropertyChangeSupport propertyChangeSupport;
}
