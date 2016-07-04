package za.co.solms.ejb3.shopping.cart;

import java.util.Collection;
import za.co.solms.ejb3.shopping.products.Product;
import javax.ejb.Remote;

/** Business interface for a shopping cart. */
@Remote 
public interface ShoppingCart 
{
  public void add(Product product, int quantity);
  
  public CartContent getCartContent();
  
  public Collection<Product> getProductsList();
    
  public void clear();
}