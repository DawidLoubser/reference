package za.co.solms.example;

/** Defines the services offered by a library: Used by the
 * books checkout counter, or a self-service kiosk. */
public interface Library
{
  /** Checks out an item to a library member.
   * @param checkout Information regarding the 
   * member and the book to be checked out
   * @return A receipt which indicates when the book 
   * should be returned
   * @throws InvalidMemberException If an invalid member 
   * tries to checkout items
   * @throws CheckoutLimitExceededException If the mamximum
   * number of items allowable are already checked out.
   * @throws OutstandingPaymentException If fees or fines are
   * outstanding, no further items may be borrowed. */
  public Receipt checkOut( CheckoutRequest checkout )
  throws InvalidMemberException, CheckoutLimitExceededException, 
  OutstandingPaymentException;
  
  /** Returns an item back to the library */
  public void checkIn( CheckInRequest checkin );
}
