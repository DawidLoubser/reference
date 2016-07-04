package za.co.solms.javaBeans;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * An observable person.
 * 
 * @author fritz@solms.co.za
 * 
 */
public class Person extends ObservableBase implements
    Serializable, Observable
{
  public Person(String name, String idNo)
  {
    // String is immutable, hence we can just keep a reference to
    // the provided object as it cannot be changed anyway.
    this.name = name;
    this.idNo = idNo;
  }

  // Getters for querying bean properties
  // ======================
  public String getName()
  {
    return name;
  }

  public String getIdNo()
  {
    return idNo;
  }

  // Setters for modifying bean properties
  // =======================
  public void setName(String name)
  {
    String oldName = this.name;
    this.name = name;
    // State change notification
    propertyChangeSupport.firePropertyChange(
        NAME_PROPERTY, oldName, name);
  }

  public void setIdNo(String idNo)
  {
    String oldIdNo = this.idNo;
    this.idNo = idNo;
    // State change notification
    propertyChangeSupport.firePropertyChange(
        ID_NO_PROPERTY, oldIdNo, idNo);
  }

  public Set<String> getProperties()
  {
    return properties;
  }

  // A polymorphic service to get the this handle
  protected Person getThisHandle()
  {
    return this;
  }

  private String name, idNo;

  public static final String NAME_PROPERTY = "name";

  public static final String ID_NO_PROPERTY = "idNo";

  private static final Set<String> properties;

  static
  {
    Set<String> props = new TreeSet<String>();
    props.add(NAME_PROPERTY);
    props.add(ID_NO_PROPERTY);
    properties = Collections.unmodifiableSet(props);
  }

  private static final long serialVersionUID = 200605051059L;
}
