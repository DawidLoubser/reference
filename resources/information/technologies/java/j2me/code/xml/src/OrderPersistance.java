<![CDATA[
/*
 * OrderPersistance.java
 *
 * Created on April 16, 2003, 12:12 PM
 */

/**
 *
 * @author  ernst
 */
package za.co.solmstraining.j2me.xml;
import za.co.solmstraining.j2me.commerce.*;
import java.io.*;
import org.kxml.kdom.*;
import org.kxml.parser.*;
import javax.microedition.rms.*;

public class OrderPersistance
{
    
    public void saveOrder(Order order)
    {
        Node node = oxmlg.getXMLFromOrder(order);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(baos);
        try
        {
            node.writeChildren(new org.kxml.io.XmlWriter(osw));
            byte[] record = baos.toByteArray();
            rs = RecordStore.openRecordStore("orders", true);
            id = rs.addRecord(record, 0, record.length );
        }
        catch(Exception excp)
        {
            System.err.println("Agge nee");
           /*Keep in mind this is not going to help much on the
            device since it will not be running in a console*/
        }
        
    }
    
    public Order openOrder()
    {
        XmlParser xmlp = null;
        node = new org.kxml.kdom.Node();
        
        try
        {
            rs = RecordStore.openRecordStore("orders", true);
            System.out.println("Inside openOrder trying to get record. Nr records:" + rs.getNumRecords());
            if (rs.getNumRecords() < 1)
                return null;
            RecordEnumeration iter = rs.enumerateRecords(null, null,false);
            if (iter.hasNextElement())
            {
                
                byte[] record = iter.nextRecord();
                System.out.println("Byte array record to String:\n ---- \n " + new String(record) + "\n\n\n   ---- \n\n");
                ByteArrayInputStream bais = new ByteArrayInputStream(record);
                System.out.println(bais);
                xmlp = new XmlParser(new InputStreamReader(bais));
                xmlp.skip();
                System.out.println("node before parse: " + node);
                node.parse(xmlp);
                System.out.println("node after parse: " + node);
                //node = oxmlg.cleanNode(node);
                return oxmlg.getOrderFromNode(node);
            }
            
        }
        catch(Exception excp)
        {
            excp.printStackTrace();
            System.err.println("You should really not handle exceptions in this way");
        }
        
        return null;
    }
    
    private Node node;
    private OrderXMLGenerator oxmlg = new OrderXMLGenerator();
    private int id;
    private RecordStore rs;
}
]]>
