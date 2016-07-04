package products;

import java.io.*;
import java.util.*;

/** Responsible for performing I/O between a product inventory, and
 * a single binary file which represents it.
 */
public class InventoryPersistence
{
    /** Constructs a new inventory persistence */
    public InventoryPersistence(File storage) throws 
    IllegalArgumentException
    {
        if (storage == null)
            throw new IllegalArgumentException("Invalid file for storage");
        this.storage = storage;
    }
    
    /** Saves an inventory to the file system. Does not append to any
     * existing data (only the given inventory will be in the file).
     * @return int The number of products written to file */
    public int save( Inventory i ) throws IOException
    {
        FileOutputStream fout = new FileOutputStream(storage);
        DataOutputStream dout = new DataOutputStream(fout);
        Iterator products = i.listAll();
        int count = 0;
        try
        {
            while (products.hasNext())
            {
                Product p = (Product)products.next();
                dout.writeUTF( p.getName() );
                dout.writeLong( p.getSerialNo() );
                dout.writeDouble( p.getPrice() );
                count++;
            }
        }
        finally
        {
            dout.flush();
            dout.close();
        }
        return count;
    }
    
    /** Loads a new inventory from the file system */
    public Inventory load() throws IOException
    {
        FileInputStream fin = new FileInputStream(storage);
        DataInputStream din = new DataInputStream(fin);
        Inventory inventory = new Inventory();
        try
        {
            while (true)
            {
                String name = din.readUTF();
                long serialNo = din.readLong();
                double price = din.readDouble();
                try
                {
                    inventory.add( new Product(name, serialNo, price) );
                } catch (IllegalArgumentException e)
                {
                    System.err.println("Invalid product, ignoring - " + e);
                }
            }
        }
        catch (EOFException ignored){}
        finally
        {
            din.close();
        }
        return inventory;
    }
    
    /** Returns a reference to the file being used for storage */
    public File getStorageFile()
    {
        return storage;
    }
    
    // Where our products will be stored
    private File storage;
}