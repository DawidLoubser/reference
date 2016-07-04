import java.io.*;
 
import java.util.StringTokenizer;
 
public class MatrixProduct
{
   public static void main(String[] args) throws IOException
   {
     System.out.println("Enter matrix 1:");
     double[][] mat1 = readMatrix();
     System.out.println("/nEnter matrix 2:");
     double[][] mat2 = readMatrix();
 
     double[][] product = multiply(mat1, mat2);
 
     System.out.println("/nThe product is:/n" + toString(product));
   }
 
   public static double[][] readMatrix() throws IOException
   {
       BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 
       System.out.print("no of rows = ");
       int nRows = Integer.parseInt(in.readLine());
       System.out.print("no of columns = ");
       int nCols = Integer.parseInt(in.readLine().trim());
 
       double[][] mat = new double[nRows][nCols];
 
       System.out.println("Enter rows of matrix with spaces between elements:");
       for (int nRow=0; nRow&lt;nRows; ++nRow)
       {
         System.out.println("Enter row " + nRow + ":");
         String line = in.readLine();
         StringTokenizer tokenizer = new StringTokenizer(line, " ");
         for (int nCol=0; nCol&lt;nCols; ++nCol)
           mat[nRow][nCol] = Double.parseDouble(tokenizer.nextToken());
       }
     return mat;
   }
 
   public static double[][] multiply(double[][] A, double[][] B)
   {
     if (A.length == 0)
       throw new IllegalArgumentException("A has zero rows");
     if (B.length == 0)

       throw new IllegalArgumentException("B has zero rows");
     if (A[0].length != B.length)
       throw new IllegalArgumentException
         ("Incorrect dimensions for multiplication");
 
     double[][] C = new double[A.length][B[0].length];
 
     for (int nr1=0; nr1&lt;A.length; ++nr1)
       for (int nc2=0; nc2&lt;B[0].length; ++nc2)
       {
         double sum = 0;
         for (int k=0; k&lt;B.length; ++k)
           sum += A[nr1][k]*B[k][nc2];
         C[nr1][nc2] = sum;
       }
     return C;
   }
 
   public static String toString(double[][] A)
   {
     if (A.length == 0) return new String("Empty matrix");
     if (A[0].length == 0) return new String("Empty matrix");
     StringBuffer result = new StringBuffer();
 
     for (int r=0; r&lt;A.length; ++r)
     {
       for (int c=0; c&lt;A[0].length-1; ++c)
         result.append(A[r][c]).append(", ");
       result.append(A[r][A[0].length-1] + "/n");
     }
     return result.toString();
   }
}
