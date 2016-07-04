package za.co.solms.example.impl;

import java.util.*;
import javax.annotation.*;
import javax.ejb.*;
import javax.ejb.Timer;
import javax.jws.*;
import javax.persistence.*;
import za.co.solms.example.*;

@Stateless
@Remote({Library.class})
public class LibraryBean implements Library
{
  public Receipt checkOut(CheckoutRequest checkout)
  throws InvalidMemberException, CheckoutLimitExceededException,
  OutstandingPaymentException
  {
    // Check preconditions
    // ...
    
    // Perform business logic of checking out an item
    //...
    
    // Item must be returned 14 days from now
    Calendar returnDate = Calendar.getInstance();
    returnDate.add( Calendar.DATE, 14);
    
    // Schedule a timer to call back this on the return date
    createReturnTimer(checkout.getItem(), returnDate.getTime());
    
    // Create receipt
    Receipt receipt = new Receipt();
    receipt.setReturnDate( returnDate.getTime() );
    return receipt;    
  }
  
  
  public void checkIn(CheckInRequest checkin)
  {
    // Perform business logic
    // ...
    
    // Cancel timer
    cancelReturnTimer( checkin.getItem() );
  }
  
  
  /* Creates a timer that is set to call back on the given
   * returnDate, with the given item as info. */
  private void createReturnTimer( ItemInfo item, Date returnDate )
  {
    // Schedule timer for return date. As info, provide the item
    Timer timer = ejbContext.getTimerService().createTimer( 
        returnDate, item);
    
    // Persistently store a handle to the timer in database, with
    // borrowed item serial number as key
    ItemTimer timerInfo = new ItemTimer( 
        item.getSerialNumber(), timer.getHandle() );
    entityManager.merge( timerInfo );
  }
  
  
  /* Cancels the timer for the given item */
  private void cancelReturnTimer( ItemInfo item )
  {
    // Lookup the timer handle for the given item
    ItemTimer timerInfo = entityManager.find( 
        ItemTimer.class, item.getSerialNumber() );
    
    if (timerInfo != null)
    {
      try
      {
        // Use the timer handle to get the timer, and cancel it
        Timer timer = timerInfo.getTimerHandle().getTimer();
        timer.cancel();
      }
      catch (NoSuchObjectLocalException ignored)
      {
        // Timer no longer active (i.e. timeOut callback already occurred)
      }
      catch (NullPointerException ignored)
      {
        // Timer does not exist in the first place
      }
      finally
      {
        // Remove timer handle from database
        entityManager.remove( timerInfo );
      }
    }
  }
  
  
  /* Called by a timer, we assume to indicate that an item
   * has not yet been returned. */
  @Timeout
  private void timeOut( Timer timer )
  {
    ItemInfo item = (ItemInfo)timer.getInfo();
    System.out.println("** ALERT: Item not yet returned: " + item);
    
    // Perform business logic (e.g. allocate fine)
    //...
  }


  @Resource
  private EJBContext ejbContext;
  
  @PersistenceContext
  private EntityManager entityManager;
}