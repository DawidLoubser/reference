<![CDATA[
/*
 * Order.java
 *
 * Created on April 15, 2003, 6:32 PM
 */

package za.co.solmstraining.j2me.commerce;

/**
 *
 * @author  ernst
 */
public class Order
{
    
    /** Creates a new instance of Order */
    public Order(String clientName, String creditCardNumber, 
                 String creditCardType, String deliveryDate, 
                 ShoppingBasket shoppingBasket)
    {
        this.shoppingBasket = shoppingBasket;
        this.clientName = clientName; 
        this.creditCardNumber = creditCardNumber; 
        this.creditCardType = creditCardType; 
        this.deliveryDate = deliveryDate; 
    }
    
    /** Getter for property clientName.
     * @return Value of property clientName.
     *
     */
    public String getClientName()
    {
        return this.clientName;
    }
    
    /** Setter for property clientName.
     * @param clientName New value of property clientName.
     *
     */
    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }
    
    /** Getter for property creditCardNumber.
     * @return Value of property creditCardNumber.
     *
     */
    public String getCreditCardNumber()
    {
        return this.creditCardNumber;
    }
    
    /** Setter for property creditCardNumber.
     * @param creditCardNumber New value of property creditCardNumber.
     *
     */
    public void setCreditCardNumber(String creditCardNumber)
    {
        this.creditCardNumber = creditCardNumber;
    }
    
    /** Getter for property creditCardType.
     * @return Value of property creditCardType.
     *
     */
    public String getCreditCardType()
    {
        return this.creditCardType;
    }
    
    /** Setter for property creditCardType.
     * @param creditCardType New value of property creditCardType.
     *
     */
    public void setCreditCardType(String creditCardType)
    {
        this.creditCardType = creditCardType;
    }
    
    /** Getter for property deliveryDate.
     * @return Value of property deliveryDate.
     *
     */
    public String getDeliveryDate()
    {
        return this.deliveryDate;
    }
    
    /** Setter for property deliveryDate.
     * @param deliveryDate New value of property deliveryDate.
     *
     */
    public void setDeliveryDate(String deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }
    
    /** Getter for property shoppingBasket.
     * @return Value of property shoppingBasket.
     *
     */
    public ShoppingBasket getShoppingBasket()
    {
        return this.shoppingBasket;
    }
    
    public String toString()
    {
        String str = "";
        java.util.Vector lineItems = shoppingBasket.getLineItems();
        java.util.Enumeration enumerator = lineItems.elements();
        while(enumerator.hasMoreElements())
            str += ((LineItem)enumerator.nextElement()).toString() + "\n";
        return clientName + "\n" + creditCardNumber + " - " + creditCardType + " \n " + str;
    }

    /**The client's name + surname string */
    private String clientName;
    /**The client's credit card number */    
    private String creditCardNumber;
    /**The client's credit card type */
    private String creditCardType;
    /**Delivery date of order */
    private String deliveryDate;
    /** Holds value of property shoppingBasket. */
    private ShoppingBasket shoppingBasket;
    
}
]]>
