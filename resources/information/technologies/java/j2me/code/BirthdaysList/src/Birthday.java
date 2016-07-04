<![CDATA[
package za.co.solmstraining.j2me.persistence;

import java.util.*;
import java.io.*;

public class Birthday
{
  public Birthday(String name, Date birthDate)
  {
    this(name, birthDate, -1);
  }

  public Birthday(String name, Date birthDate, int recordId)
  {
    this.name = name;
    this.birthDate = birthDate;
    this.recordId = recordId;
  }

  public void setRecordId(int recordId)
  {
    this.recordId = recordId;
  }

  public String getName() {return name;}

  public Date getDate() {return birthDate;}

  public int getRecordId() {return recordId;}

  public void setName(String newName) {name = newName;}

  public void setDate(Date newDate) {birthDate = newDate;}

  public String toString()
  {
    calendar.setTime(birthDate);
    return Integer.toString(calendar.get(Calendar.DAY_OF_MONTH))
      + '/' + Integer.toString(calendar.get(Calendar.MONTH))
        + '/' + Integer.toString(calendar.get(Calendar.YEAR))
          + ": " + name;
  }

  private String name;
  private Date birthDate;
  private int recordId;

  private static final Calendar calendar
    = Calendar.getInstance();
}
]]>
