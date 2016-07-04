package za.co.solms.shopping.cart;

import java.util.Collection;
import java.util.LinkedList;
import za.co.solms.ejb3.shopping.cart.CartContent;
import za.co.solms.ejb3.shopping.cart.ShoppingCart;
import za.co.solms.ejb3.shopping.products.Product;
import javax.ejb.Stateful;

/**
 * The stateful session bean realisation for the shopping cart.
 */
@Stateful 
public class ShoppingCartBean implements ShoppingCart
{
    public void add(Product product, int quantity) 
    {
      cartContent.add(product, quantity);    
    }

    public CartContent getCartContent() 
    {
      return cartContent;
    }

    public void clear() 
    {
      cartContent.clear();    
    }
    
    public Collection<Product> getProductsList() 
    {
      return productsList;
    }


    private CartContent cartContent = new CartContent();
    
    private static final Collection<Product> productsList = 
        new LinkedList<Product>();    
    
    static
    {
        productsList.add(new Product(
            "Programming in Java", 5950));
        productsList.add(new Product(
            "Business Analysis using UML", 5950));
        productsList.add(new Product(
            "Design Patterns", 4760));
        productsList.add(new Product(
            "Object-Oriented Analysis and Design using UML", 5950));
        productsList.add(new Product(
            "Architecture", 5950));
        productsList.add(new Product(
            "Enterprise Java Beans", 5950));
        productsList.add(new Product(
            "XML and Web Services via Java", 5950));
    }
}