/*
 * Created on May 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package za.co.solms.ejb3.shopping;

import java.util.Collection;
import java.util.LinkedList;

import za.co.solms.ejb3.finance.tax.TaxCalculator;
import za.co.solms.ejb3.shopping.Shopping;
import za.co.solms.ejb3.shopping.cart.CartContent;
import za.co.solms.ejb3.shopping.order.PurchaseOrder;
import za.co.solms.ejb3.shopping.products.Product;
import javax.persistence.PersistenceContext;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

/**
 * The stateful session bean for the shopping cart.
 *
 * @author fritz@solms.co.za
 */
@Stateful 
//@Interceptors ({za.co.solms.ejb3.comms.email.EMailInterceptor.class, 
//                za.co.solms.ejb3.logging.LoggingInterceptor.class})
@Interceptors (za.co.solms.ejb3.comms.email.EMailInterceptor.class)
public class ShoppingBean implements Shopping
{

    public void add(Product product, int quantity) 
    {
      cartContent.add(product, quantity);    
    }

    public String getCartContent() 
    {
      return cartContent.toString();
    }

    public void clear() 
    {
      cartContent.clear();    
    }
    
    public Collection<Product> getProductsList() 
    {
      return productsList;
    }
    
    
    /**
     * Using dependency injection to set the tax calculator to be used.
     */
    @EJB
    public void setTaxCalculator(TaxCalculator taxCalculator)
    {
        this.taxCalculator = taxCalculator;
    }
    
    /**
     * Creates the order and returns the order number.
     */
    public int buyCart()
    {
        logger.info("buying cart");
        PurchaseOrder order = createOrder();
        entityManager.persist(order);
        logger.info("Created order: " + System.getProperty("line.separator") 
              + order);
        return order.getId();
    }
    
    public double getOrderCost(int orderNo)
    {
        PurchaseOrder order 
          = entityManager.find(PurchaseOrder.class, new Integer(orderNo));
        return order.calculateTotalCost();
    }
    
    private PurchaseOrder createOrder()
    {
        PurchaseOrder order = new PurchaseOrder();
        
        for (CartContent.Entry entry: cartContent.getEntries())
            order.addLineItem(entry.getProduct().getName(), 
                              entry.getProduct().getPrice(),
                              entry.getQuantity()); 
        return order;
    }

    private CartContent cartContent = new CartContent();
    @EJB
    private TaxCalculator taxCalculator;
    
    private static final Collection<Product> productsList 
      = new LinkedList<Product>();
    
    static
    {
        productsList.add(new Product("Programming in Java", 5950));
        productsList.add(new Product("Business Analysis using UML", 5950));
        productsList.add(new Product("Design Patterns", 4760));
        productsList.add(
          new Product("Object-Oriented Analysis and Design using UML", 5950));
        productsList.add(new Product("Architecture", 5950));
        productsList.add(new Product("Enterprise Java Beans", 5950));
        productsList.add(new Product("XML and Web Services via Java", 5950));
    }
    
    private static final Logger logger = Logger.getLogger(ShoppingBean.class);

    @PersistenceContext  // injecting entity manager reference
    private EntityManager entityManager;
}
