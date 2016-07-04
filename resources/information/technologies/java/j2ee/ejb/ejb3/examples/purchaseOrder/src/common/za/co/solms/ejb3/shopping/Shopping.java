package za.co.solms.ejb3.shopping;

import java.util.Collection;

import za.co.solms.ejb3.shopping.products.Product;

import javax.ejb.Remote;

/**
 * @author fritz
 *
 * Business interface for the shopping cart statefull session bean.
 */
@Remote public interface Shopping 
{
  public void add(Product product, int quantity);
  
  public String getCartContent();
  
  public Collection<Product> getProductsList();
  
  public double getOrderCost(int orderNo);

  public int buyCart();
    
  public void clear();
}
