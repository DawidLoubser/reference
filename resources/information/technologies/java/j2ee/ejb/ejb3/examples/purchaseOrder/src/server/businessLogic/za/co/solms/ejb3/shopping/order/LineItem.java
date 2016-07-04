package za.co.solms.ejb3.shopping.order;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author fritz@solms.co.za
 *
 */
@Entity
public class LineItem implements Serializable 
{
	public int getId() {return id;}
  
  public void setId(int id) {this.id = id;}
  
  public String getProductName() {return productName;}
  
  public void setProductName(String productName) 
  {
      this.productName = productName;
  }
  
  public double getUnitPrice() {return unitPrice;}
  
  public void setUnitPrice(double unitPrice) 
  {
      this.unitPrice = unitPrice;
  }
  
  public int getQuantity() {return quantity;}
  
  public void setQuantity(int quantity)
  {
      this.quantity = quantity;
  }
  
  public double calculateTotal() {return quantity*unitPrice;}
  
  @ManyToOne
  @JoinColumn(name="order_id")  // specifies the foreign key column within the LineItem table
  public PurchaseOrder getPurchaseOrder()
  {
      return purchaseOrder;
  }
  
  public void setPurchaseOrder(PurchaseOrder purchaseOrder) 
  {
      this.purchaseOrder = purchaseOrder;
  }
  
  private String productName;
  private double unitPrice;
  private int quantity, id;
  private PurchaseOrder purchaseOrder;
	private static final long serialVersionUID = 1L;
}
