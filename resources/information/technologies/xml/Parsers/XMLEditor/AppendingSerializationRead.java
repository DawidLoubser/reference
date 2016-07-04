package docs.components.XML.Parsers.XMLEditor;

\begin{verbatim}
import java.io.*;

public class AppendingSerializationRead
{
  public static void main(String[] args) throws IOException, ClassNotFoundException
  {
    ObjectInputStream in 
      = new ObjectInputStream(new FileInputStream("tmp"));
    Person p = (Person)in.readObject();  
    in.close();
    
    System.out.println(p);
  }
}
\end{verbatim}
