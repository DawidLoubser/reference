
import java.io.*;

public class BufferCopy
{
    public static void main(String[] args)
    {
        // Check if user provided two filenames
        if (args.length != 2)
        {
            System.out.println("Usage: java Copy <fromFile> <toFile>");
            System.exit(1);
        }

        // Create input stream from source file
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(args[0]);
        }
        catch (IOException e)
        {
            System.out.println("ERROR: Could not read " + args[0]);
            System.exit(1);
        }

        // Create output stream to destination file
        FileOutputStream out = null;
        try
        {
            out = new FileOutputStream(args[1]);
        }
        catch (IOException e)
        {
            System.out.println("ERROR: Could not write to " + args[1]);
            System.exit(1);
        }

        // Chain buffers to low-level streams to improve throughput
        BufferedInputStream bin = new BufferedInputStream(in, 32000);
        BufferedOutputStream bout = new BufferedOutputStream(out, 32000);

        // Copy byte-for-byte until end of file is read (-1 byte)
        try
        {
            int bte = bin.read();
            while (bte != -1)
            {
                bout.write(bte);
                bte = bin.read();
            }
        }
        catch (EOFException e1) {}
        catch (IOException e2)
        {
            System.out.println("ERROR: During copy: " + e2);
            e2.printStackTrace();
        }
        finally
        {
            // Streams must always be closed
            try { bin.close(); } catch (IOException e) {}
            try { bout.close();} catch (IOException e) {}
        }
    }
}