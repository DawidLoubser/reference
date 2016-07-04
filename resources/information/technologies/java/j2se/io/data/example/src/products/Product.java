package products;

/** Represents a simple product. For the purpose of this exercise,
 * this product is immutable (cannot change after creation).
 */
public class Product
{
    /** Constructor */
    public Product(String name, long serialNo, double price)
    throws IllegalArgumentException
    {
        // We do not want to allow a null name
        if (name == null) 
            throw new IllegalArgumentException("Invalid Name");
        this.name = name;
        this.serialNo = serialNo;
        this.price = price;
    }
    
    /** Obtain the product name */
    public String getName()
    {
        return name;
    }

    /** Obtain the product serial number */
    public long getSerialNo()
    {
        return serialNo;
    }
    
    /** Obtain the product price */
    public double getPrice()
    {
        return price;
    }
    
    /** Equality test. Another product is considered equal if
     * the name and serial number match.
     */
    public boolean equals(Object o)
    {
        if (o instanceof Product)
        {
            Product other = (Product)o;
            return other.getName().equals( getName() ) &&
                (other.getSerialNo() == getSerialNo());
        }
        else
        {
            return false;
        }
    }
    
    /** Hash code uses a basic combination of product name and
     * serial number.
     */
    public int hashCode()
    {
        return getName().hashCode() * (int)getSerialNo();
    }
    
    /** Simple String representation */
    public String toString()
    {
        return getName() + " (#" + getSerialNo() + ") costs R" + getPrice();
    }
    
    private String name;
    private long serialNo;
    private double price;
}