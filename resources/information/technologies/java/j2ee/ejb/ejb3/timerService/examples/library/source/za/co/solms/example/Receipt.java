package za.co.solms.example;

/** A receipt issued after successful checkout of item(s)
 * from the library. */
public class Receipt implements java.io.Serializable
{
  public java.util.Date getReturnDate()
  {
    return returnDate;
  }

  public void setReturnDate( java.util.Date returnDate )
  {
    this.returnDate = returnDate;
  }
  
  // TODO: Extra info
  // ...
  
  private java.util.Date returnDate;
}
