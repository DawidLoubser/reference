/*
 * Created on May 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package za.co.solms.shopping.cart;

import java.util.Collection;
import java.util.LinkedList;

import za.co.solms.ejb3.finance.tax.TaxCalculator;
import za.co.solms.ejb3.shopping.cart.CartContent;
import za.co.solms.ejb3.shopping.cart.Invoice;
import za.co.solms.ejb3.shopping.cart.ShoppingCart;
import za.co.solms.ejb3.shopping.products.Product;

import javax.annotation.EJB;
import javax.ejb.Stateful;
import javax.ejb.Interceptor;
import javax.ejb.Interceptors;
import javax.ejb.AroundInvoke;

import org.apache.log4j.Logger;

/**
 * @author fritz
 *
 * The stateful session bean for the shopping cart.
 */
@Stateful 
@Interceptor (za.co.solms.ejb3.logging.LoggingInterceptor.class)
//@Interceptors ({@Interceptor (za.co.solms.ejb3.comms.email.EMailInterceptor.class),
//                @Interceptor (za.co.solms.ejb3.logging.LoggingInterceptor.class)})
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
    
    /**
     * Using dependency injection to set the tax calculator to be used.
     */
    @EJB(beanName= "za.co.solms.ejb3.finance.tax.TaxCalculatorBean")
    public void setTaxCalculator(TaxCalculator taxCalculator)
    {
        this.taxCalculator = taxCalculator;
    }
    
    public String buyCart()
    {
        logger.info("buying cart");
        Invoice invoice = new Invoice(cartContent.getTotalCost(), 
                                      taxCalculator.calculateTax(cartContent.getTotalCost()));
        return invoice.toString();
    }

    private CartContent cartContent = new CartContent();
    private TaxCalculator taxCalculator;
    
    private static final Collection<Product> productsList = new LinkedList<Product>();
    
    static
    {
        productsList.add(new Product("Programming in Java", 5950));
        productsList.add(new Product("Business Analysis using UML", 5950));
        productsList.add(new Product("Design Patterns", 4760));
        productsList.add(new Product("Object-Oriented Analysis and Design using UML", 5950));
        productsList.add(new Product("Architecture", 5950));
        productsList.add(new Product("Enterprise Java Beans", 5950));
        productsList.add(new Product("XML and Web Services via Java", 5950));
    }
    
    private static final Logger logger = Logger.getLogger(ShoppingCartBean.class);
}
