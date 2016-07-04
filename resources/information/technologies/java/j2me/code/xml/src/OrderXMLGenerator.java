<![CDATA[
/*
 * OrderXMLGenerator.java
 *
 * Created on April 15, 2003, 7:06 PM
 */

package za.co.solmstraining.j2me.xml;

import za.co.solmstraining.j2me.commerce.*;
import org.kxml.kdom.*;
import org.kxml.*;

/**
 *
 * @author  ernst
 */
public class OrderXMLGenerator
{
    public Node getXMLFromOrder(Order order)
    {
        //Create Node from order
        Node node = new Node();
        Element child = new Element();
        child.setName("order");
        Element name = new Element();
        name.setName("name");
        name.addChild(Xml.TEXT, order.getClientName());
        child.addChild(Xml.ELEMENT, name);
        Element creditcard = new Element();
        creditcard.setName("creditcard");
        Element ccnum = new Element();
        ccnum.setName("number");
        ccnum.addChild(Xml.TEXT, order.getCreditCardNumber());
        creditcard.addChild(Xml.ELEMENT, ccnum);
        Element cctype = new Element();
        cctype.setName("type");
        cctype.addChild(Xml.TEXT, order.getCreditCardType());
        creditcard.addChild(Xml.ELEMENT, cctype);
        child.addChild(Xml.ELEMENT, creditcard);
        Element date = new Element();
        date.setName("deliverydate");
        date.addChild(Xml.TEXT, order.getDeliveryDate().toString());
        child.addChild(Xml.ELEMENT, date);
        Element lineItemsElement = new Element();
        lineItemsElement.setName("lineitems");
        za.co.solmstraining.j2me.commerce.ShoppingBasket shoppingBasket = order.getShoppingBasket();
        java.util.Vector lineItems = shoppingBasket.getLineItems();
        LineItem lineItem = null;
        java.util.Enumeration iter = lineItems.elements();
        while (iter.hasMoreElements())
        {
            lineItem = (LineItem)iter.nextElement();
            Element currentLineItem = new Element();
            currentLineItem.setName("lineItem" + (lineItemsElement.getChildCount() + 1));
            currentLineItem.setAttribute(new Attribute("productcode", lineItem.getProductId()));
            currentLineItem.addChild(Xml.TEXT, lineItem.getQuantity() + "");
            lineItemsElement.addChild(Xml.ELEMENT, currentLineItem);
        }
        child.addChild(Xml.ELEMENT, lineItemsElement);
        
        node.addChild(Xml.ELEMENT, child);
        return node;
    }
    
    public Order getOrderFromNode(Node orderNode) throws Exception
    {
        removeStringChildren(orderNode);
        int x = 0;
        Object o = null;
        
        while( x < orderNode.getChildCount())
        {
            o = orderNode.getChild(x);
            if (o instanceof Element)
            {
                if (((Element)o).getName().equals("order"))
                {
                    Element orderElement = (Element)orderNode.getChild(x);
                    ++x;
                    removeStringChildren(orderElement);
                    Element elementName = (Element)orderElement.getChild(0);
                    String clientName = (String)elementName.getChild(0);
                    Element ccard = orderElement.getElement(1);
                    removeStringChildren(ccard);
                    String creditCardNumber = (String)((Element)ccard.getChild(0)).getChild(0);
                    String creditCardType = (String)((Element)ccard.getChild(1)).getChild(0);
                    Element date = orderElement.getElement(2);
                    String deliveryDate = (String)date.getChild(0);
                    ShoppingBasket shoppingBasket = new ShoppingBasket(new ProductList());
                    Element lineItemsElement = orderElement.getElement(3);
                    removeStringChildren(lineItemsElement);
                    
                    int i = 0;
                    while (i < lineItemsElement.getChildCount())
                    {
                        Element lineItem = (Element)lineItemsElement.getChild(i);
                        String productCode = ((Attribute)lineItem.getAttribute("productcode")).getValue();
                        String quantity = (String)lineItem.getChild(0);
                        shoppingBasket.addLineItem(productCode,  Integer.parseInt(quantity));
                        System.out.println("Line Item:    " + productCode + "  : " + quantity);
                        ++i;
                    }
                    
                    Order order = new Order(clientName, creditCardNumber, creditCardType, deliveryDate, shoppingBasket);
                    
                    return order;
                }
                else
                {
                    throw new Exception("the root ellement of this node is not an order");
                }
            }
            else
            {
                throw new Exception("the child is not an Element or a String");
            }
        }
        return null;
    }
    
    public void removeStringChildren(Node dirtyNode) throws Exception
    {
        for (int i = 0; i < dirtyNode.getChildCount(); ++i)
        {
            if ( dirtyNode.getChild(i) instanceof String )
                dirtyNode.removeChild(i);
            
        }
    }
    
}
]]>
