package products;
import java.io.*;

/** Product Management Application. This class serves as a user interface /
 * controller to the Inventory and Persistence classes.
 */
public class ProductApp
{
    
    /** Creates a new instance of the application.
     */
    public ProductApp(File storage)
    {        
        // Setup keyboard
        keyBoard = new BufferedReader( new InputStreamReader(System.in) );
        // Create business-logic objects
        inventory = new Inventory();
        persistence = new InventoryPersistence(storage);
    }
    
    
    /** Run (main menu loop) - this repeatedly prints the menu
     * (and dispatches option requests) until exit key pressed.
     */
    public void run()
    {
        String kbdOption = null; // Menu selection stored here
        do
        {
            try
            {
                // Show menu
                printMainMenu();
                
                // Get user option
                System.out.print(" >");
                kbdOption = this.keyBoard.readLine().toLowerCase();
                
                // Perform program action
                this.performAction(kbdOption);
            }
            catch (Exception e)
            {
                handleRunException(e);
            }
            
        } while (kbdOption != null && !kbdOption.equals("x"));
    }
    
    
    /** Displays the main menu to the standard output.
     */
    public void printMainMenu()
    {
        System.out.println();
        System.out.println("-- Product Manager -----------------------\n");
        System.out.println(" (n) Add new product");
        System.out.println(" (a) Show all products");
        System.out.println(" (l) Load from file");
        System.out.println(" (s) Save to file");
        System.out.println(" (x) Exit");
        System.out.println("------------------------------------------");
    }
    
    /** Performs the requested program action given a
     * user option
     */
    public void performAction( String option ) throws Exception
    {
        // Check if option is potentially useless
        if (option == null || option.equals("x"))
            return;
        
        /* Can't use a switch because String is not primitive,
         * so we're using a silly if/else matrix */
        if (option.equals("n"))
        {
            doAddNewProduct();
        }
        else if (option.equals("a"))
        {
            doListAll();
        }
        else if (option.equals("l"))
        {
            doLoadFromFile();
        }
        else if (option.equals("s"))
        {
            doSaveToFile();
        }
    }
    
    
    /** Adds a product to the inventory */
    void doAddNewProduct() throws Exception
    {
        inventory.add( getProductFromUser() );
    }
    
    
    /** Lists all products in the inventory */
    void doListAll()
    {
        System.out.println("-- ALL PRODUCTS --\n");
        System.out.println( inventory.toString() );
    }
    
    
    /** Loads products from file */
    void doLoadFromFile() throws IOException
    {
        inventory = persistence.load();
        if (inventory != null)
        {
            System.out.println(inventory.getProductCount() +  
                    " product(s) loaded from " + 
                    persistence.getStorageFile().getName() );
        }
    }
    
    
    /** Saves products to file */
    void doSaveToFile() throws IOException
    {
        int n = persistence.save( inventory );
        System.out.println(n + " product(s) saved to " + 
                persistence.getStorageFile().getName() );
    }
    
    
    /** This method constructs a Product from user input
     */
    public Product getProductFromUser() throws IOException, 
    NumberFormatException
    {
        System.out.println("-- NEW PRODUCT --");
        System.out.print(" Name       > ");
        String name = this.keyBoard.readLine();
        System.out.print(" Serial No  > ");
        String serialNo = this.keyBoard.readLine();
        System.out.print(" Price      > R");
        String price = this.keyBoard.readLine();
        
        long sn = Long.parseLong( serialNo );
        double pr = Double.parseDouble(price);
        
        // Create Product
        return new Product(name, sn, pr);
    }

    
    /** Method to handle an uncaught exception during 
     * program run()
     */
    private void handleRunException( Exception e )
    {
        // Display exception (will call toString() on e)
        System.out.println("** ERROR: " + e);
    }
    
    
    /** Starting point of app.
     * @param args the command line arguments: Pass a filename
     */
    public static void main(String[] args)
    {
        // Check for file parameter
        if (args.length < 1)
        {
            System.out.println("Usage: java ProductApp <file>");
            System.exit(1);
        }
        
        // Create instance of ourself, and run
        ProductApp app = new ProductApp( new File( args[0] ) );
        app.run();

    }
    
    private Inventory inventory;
    private InventoryPersistence persistence;
    private BufferedReader keyBoard;
}