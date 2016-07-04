import java.io.File;

/** Simple program to print a text-based directory tree for a given 
 * directory. It illustrates several aspects of working with files.
 * 
 * @author Solms TCD
 * @version 0.1 (Jan 13, 2005)
 */
public class DirTree
{
    /** Service to print a directory tree recursively */
    public void subTree( File root, String prefix)
    {
        System.out.println( prefix + root.getName());
        File[] children = root.listFiles();
        for (int i = 0; i < children.length; i++)
        {
            if ( children[i].isDirectory() )
            {
                dirCount++;
                subTree( children[i], prefix + "+ ");
            }
            else
            {
                fileCount++;
            }
        }
    }
    
    /** Main service (run program) */
    public static void main(String[] args)
    {
        // Did user provide a command-line argument?
        if (args.length == 0)
        {
            System.err.println("Usage: java DirTree <name>");
            System.exit(1);
        }
        
        // Create root File object, making sure it's a valid directory
        File root = new File( args[0] );        
        if (root.exists())
        {
            // Create an instance of this class (we could have rather made
            // the 'subTree()' method static, but this would be unsafe for 
            // concurrent usage (eg. two directories listed simultaneously)
            DirTree treeMaker = new DirTree();
            
            if (root.isDirectory())
            {
                // Call the recursive print service
                treeMaker.subTree(root, " + ");
            }
            else
            {
                // If the user provided a file, use the containing directory
                treeMaker.subTree(root.getParentFile(), " + ");
            }
            
            // Display totals
            System.out.println(treeMaker.getTotals());
        }
        else
        {
            System.err.println("Invalid directory: "+ args[0]);
            System.exit(1);
        }
    }
    
    /** Service to report on the total number of files and directories. */
    public String getTotals()
    {
        return fileCount + " file(s) in " + dirCount + " directories.";
    }
    
    // Keeps track of directory and file counts
    private int dirCount, fileCount;
}