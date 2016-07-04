package za.co.solms.ejb3.shopping.order;

import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * An entity bean for a purchase order.
 * 
 * @author fritz@solms.co.za
 *
 */
@Entity
@Table(name="PURCHASE_ORDER")
public class PurchaseOrder implements Serializable 
{
  public int getId() {return id;}
  
  public void setId(int id) {this.id = id;}
  
  /*
   OneToMany relationship:
   -----------------------
     - Cascading delete for realizing composition (i.e. owner does not survive
       component.
     - Fetch type eager to fetch line items when fetching order (otherwise fetch
       on demand via fetch type lazy.
     - mappedBy specifies that this is a bi-directional mapping which is managed by the
       purchaseOrder property on the related entity bean, LineItem.  
  */     
  @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="purchaseOrder")
  public Collection<LineItem> getLineItems()
  {
      return lineItems;
  }
  
  public void setLineItems(Collection<LineItem> lineItems) 
  {
      this.lineItems = lineItems;
  }
  
  public void addLineItem(String productName, double unitPrice, int quantity)
  {
      LineItem lineItem = new LineItem();
      lineItem.setPurchaseOrder(this);
      lineItem.setProductName(productName);
      lineItem.setQuantity(quantity);
      lineItem.setUnitPrice(unitPrice);
      lineItems.add(lineItem);
  }
  
  public double calculateTotalCost()
  {
      double sum = 0;
      for (LineItem lineItem : lineItems)
          sum += lineItem.calculateTotal();
      return sum;
  }
  
  public String toString()
  {
    return "Order: no=" + id + ", number of line items = " + lineItems.size();   
  }
  
  private Collection<LineItem> lineItems = new ArrayList<LineItem>();
  private int id;
	private static final long serialVersionUID = 1L;
}
