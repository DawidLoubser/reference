<![CDATA[
package za.co.solmstraining.j2me.persistence;

import java.io.*;
import java.util.*;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStoreException;

public class BirthdayRepository
{
  public BirthdayRepository(RecordStore store)
  {
    this.store = store;
  }

  public Vector getBirthdays()
                  throws RecordStoreException
  {
    RecordEnumeration iter
      = store.enumerateRecords(null, null, false);

    return getBirthdays(iter);
  }

  public Vector getBirthdays(Date date)
                  throws RecordStoreException
  {
    RecordEnumeration iter
      = store.enumerateRecords
          (new BirthdayOnDateFilter(date), null, false);

    return getBirthdays(iter);
  }

  public Vector getBirthdays(Date onOrAfter, Date before)
                 throws RecordStoreException
  {
    RecordEnumeration iter
      = store.enumerateRecords
          (new BirthdayBetweenDatesFilter(onOrAfter, before),
            null, false);

    return getBirthdays(iter);
  }

  public Vector getBirthdays(String name)
                 throws RecordStoreException
  {
    RecordEnumeration iter
      = store.enumerateRecords
          (new NameFilter(name), null, false);

    return getBirthdays(iter);
  }

  public void addBirthday(Birthday birthday)
                   throws RecordStoreException
  {
    byte[] data = toByteArray(birthday);

    int recordId = store.addRecord(data, 0, data.length);
    birthday.setRecordId(recordId);
  }

  public void updateBirthday(Birthday birthday)
                 throws RecordStoreException
  {
    byte[] data = toByteArray(birthday);
    store.setRecord(birthday.getRecordId(), data, 0, data.length);
  }

  public void deleteBirthday(Birthday birthday)
                 throws RecordStoreException
  {
    store.deleteRecord(birthday.getRecordId());
  }

  private Vector getBirthdays(RecordEnumeration iter)
                 throws RecordStoreException
  {
    Vector birthdays = new Vector(iter.numRecords());
    while (iter.hasNextElement())
    {
      int recordId = iter.nextRecordId();
      birthdays.addElement
       (createBirthday(store.getRecord(recordId), recordId));
    }
    return birthdays;
  }

  private Birthday createBirthday(byte[] data, int recordId)
  {
    String name = "";
    Date birthDate = new Date();
    DataInputStream in = null;
    try
    {
      in = new DataInputStream
            (new ByteArrayInputStream(data));

      birthDate.setTime(in.readLong());
      name = in.readUTF();
    }
    catch (IOException e)
    {
      throw new IllegalArgumentException
        ("Could not create BirthDate from byte[].");
    }
    return new Birthday(name, birthDate, recordId);
  }

  private byte[] toByteArray(Birthday birthday)
  {
    try
    {
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(bout);
      out.writeLong(birthday.getDate().getTime());
      out.writeUTF(birthday.getName());
      byte[] record = bout.toByteArray();
      out.close();
      bout.close();

      return record;
    }
    catch (Exception e) {return null;}
  }

  private class BirthdayOnDateFilter implements RecordFilter
  {
    public BirthdayOnDateFilter(Date date)
    {
      this.date = removeTime(date);
    }

    private Date removeTime(Date date)
    {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.set(Calendar.HOUR,0);
      cal.set(Calendar.MINUTE,0);
      cal.set(Calendar.SECOND,0);
      cal.set(Calendar.MILLISECOND, 0);
      return cal.getTime();
    }

    public boolean matches(byte[] recordData)
    {
      try
      {
        DataInputStream in
          = new DataInputStream(new ByteArrayInputStream(recordData));
        Date recordDate = new Date(in.readLong());
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(recordDate);
        cal2.setTime(date);

        return ((cal1.get(Calendar.DAY_OF_MONTH)
                  == cal2.get(Calendar.DAY_OF_MONTH)) &&
                    (cal1.get(Calendar.MONTH)
                      == cal2.get(Calendar.MONTH)));
       }
       catch (Exception e)
       {
         throw new RuntimeException();
       }
    }
    private Date date;
  }

  private class NameFilter implements RecordFilter
  {
    public NameFilter(String name) {this.name = name;}

    public boolean matches(byte[] recordData)
    {
      return name.equals(createBirthday(recordData,-1).getName());
    }
    private String name;
  }

  private class BirthdayBetweenDatesFilter implements RecordFilter
  {
    public BirthdayBetweenDatesFilter(Date onOrAfter, Date before)
    {
      this.onOrAfter = onOrAfter;
      this.before = before;
    }

    public boolean matches(byte[] recordData)
    {
      try
      {
        DataInputStream in
          = new DataInputStream(new ByteArrayInputStream(recordData));
        return ((in.readLong() >= onOrAfter.getTime())
          && (in.readLong() < before.getTime()));
       }
       catch (Exception e)
       {
         throw new RuntimeException();
       }
    }

    private Date before, onOrAfter;
  }

  private RecordStore store;
}


]]>
