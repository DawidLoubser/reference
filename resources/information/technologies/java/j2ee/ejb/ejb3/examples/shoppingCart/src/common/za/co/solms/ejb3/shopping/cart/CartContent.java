package za.co.solms.ejb3.shopping.cart;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import za.co.solms.ejb3.shopping.products.Product;

/**
 * A value object representing the content of a shopping cart, 
 i.e. entries of quantities of various products.
 */
public class CartContent implements Serializable
{
    public CartContent(){}
    
    public void add(Product product, int quantity)
    {
        if (entries.containsKey(product))
        {
            logger.info("Product already in cart: updating...");
            entries.get(product).add(quantity);
            if (entries.get(product).getValue() == 0)
                entries.remove(product);
        }        
        else
        {
          logger.info("Product not yet in cart: adding ...");
            
          if (quantity > 0)  
            entries.put(product, new Count(quantity));
        }  
    }
    
    public void clear() {entries.clear();}
    
    public String toString()
    {
      String s = "Cart:\n";
      for ( Product product : entries.keySet() )
      {
          s += entries.get(product) + " of ";
          s += product + " at ";
          s += priceFormatter.format(product.getPrice()) + "\n";
      }
      return s;
    }
    
    private class Count implements Serializable
    {
        public Count(){}
        
        public Count(int num){value = num;}
        
        public void increment() {++value;}
        
        public void add(int n) {value += n;}
        
        public int getValue() {return value;}
        
        public String toString()
        {
            return Integer.toString(value);
        }
        
        private int value = 0;
        
        private static final long serialVersionUID = 87328763287632L;
    }

    private Map<Product,Count> entries = 
        new HashMap<Product, Count>();
        
    private static final DecimalFormat priceFormatter = 
        new DecimalFormat("R#####0.00");
        
    private static final Logger logger = 
        Logger.getLogger(CartContent.class);
}