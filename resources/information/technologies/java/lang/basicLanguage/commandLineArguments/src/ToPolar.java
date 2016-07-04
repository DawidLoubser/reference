public class ToPolar
{
   public static void main(String[] args)
   {
     if (args.length != 2)
     {
       System.out.println("ERROR: must supply 2 command line arguments.");
       System.out.println("Usage: java toPolar <x-coordinate> <y-coordinate>.");
       System.out.println("Example: java toPolar 1.7 2.3 <ENTER>");
       System.exit(0);
     }
 
     double x = 0;
     try
     {
       x = Double.parseDouble(args[0]);
     }
     catch (NumberFormatException e)
     {
       System.out.println("ERROR: invalid number format for x-coordinate.");
       System.exit(0);
     }
 
     double y = 0;
     try
     {
       y = Double.parseDouble(args[1]);
     }
     catch (NumberFormatException e)
     {
       System.out.println("ERROR: invalid number format for y-coordinate.");
       System.exit(0);
     }
 
     double r = Math.sqrt(x*x + y*y);
 
     double theta = 0;
     if (r != 0)
       theta = Math.acos(x/r);
 
     // Convert from radians to degrees
     theta *= 180/Math.PI;
 
     System.out.println("r = "  + r);
     System.out.println("theta = "  + theta);
   }
}
