import java.util.Date;

interface Available
{
  public void borrow();
}

interface OnLoan
{
  public void returnVideo();

  public Date getDueDate();
}

class Video
{
  public Video(String name, String id)
  {
    this.name = name;
    this.id = id;
  }

  public String toString()
  {
    return name + " (" + id + ") " + state.toString();
  }

  private abstract class _State
  {
    public void expire()
    {
      current = false;
    }

    private current = true;
  }

  private class _Available extends _State implements Available
  {
    public void borrow() throws NotCurrentStateException
    {
      synchronized(Video.this)
      {
        if (current == false)
          throw new NotCurrentStateException();

        Date now = new Date();
        Date dueDate = new Date(now.getTime() + 24L*60*60*1000);
        state.expire();
        state = new _OnLoan(dueDate);
      }
    }

    public String toString()
    {
      return "is available";
    }
  }

  private class _OnLoan extends _State implements OnLoan
  {
    public _OnLoan(Date dueDate)
    {
      this.dueDate = dueDate;
    }

    public void returnVideo() throws NotCurrentStateException
    {
      synchronized(Video.this)
      {
        if (current == false)
          throw new NotCurrentStateException();
        state.expire();
        state = new _Available();
      }
    }

    public Date getDueDate() throws NotCurrentStateException
    {
      if (current == false)
        throw new NotCurrentStateException();
      return dueDate;
    }

    public String toString() throws NotCurrentStateException
    {
      if (current == false)
        throw new NotCurrentStateException();
      return "is on loan. To be returned by "
        + dateFormat.format(dueDate);
    }

    private Date dueDate;
  }

  public Available isAvailable() throws ClassCastException
  {
    return (Available)state;
  }

  public OnLoan isOnLoan() throws ClassCastException
  {
    return (OnLoan)state;
  }

  private String name, id;
  private _State state = new _Available();
  private static final java.text.DateFormat dateFormat
    = new java.text.SimpleDateFormat("HH:mm 'on' dd-MM-yyyy");
}

public class ExposedStateTest
{
  public static void main(String[] args)
  {
    Video video = new Video("A Fish Called Wanda", "Fishy12");

    System.out.println(video);

    try
    {
      video.isAvailable().borrow();
    }
    catch (ClassCastException e)
    {
      System.out.println("Sorry, not available.");
    }

    System.out.println(video);

    try
    {
      video.isOnLoan().returnVideo();
    }
    catch (ClassCastException e)
    {
      System.out.println("How the hell have you got that video???");
    }

    System.out.println(video);
  }
}
