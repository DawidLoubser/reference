package za.co.solms.finance;

public class CreditCard implements java.io.Serializable
{
  public CreditCard(String cardType,
                    String cardNumber,
                    String cardHolderName,
                    java.util.Date expiryDate)
  {
    type = cardType;
    number = cardNumber;
    holderName = cardHolderName;
    this.expiryDate = (java.util.Date)expiryDate.clone();
  }
  
  public String getType() {return type;}
  
  public String getNumber() {return number;}
  
  public String getCardHolderName() {return holderName;}
  
  public java.util.Date getExpiryDate() 
  {
    return (java.util.Date)expiryDate.clone();
  }  
  
  public boolean isValid(java.util.Date date)
  {
    return date.before(expiryDate);
  }  

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append(type).append(": ").append(number);
    result.append(" [").append(holderName).append("] ");
    result.append("expiryDate: ");
    result.append(dateFormat.format(expiryDate));
    
    return result.toString();
  }  
  
  private String type, number, holderName;
  private java.util.Date expiryDate;
  
  private static final java.text.DateFormat dateFormat
    = new java.text.SimpleDateFormat("MM/yy");
}
