import java.sql.*;

public class TestGetMetaData
{
  public static void main(String[] args)
  {
    String dbUrl = "jdbc:mysql://localhost/test:3306";
    String user = "fritz";
    String password = "007";
    try
    {
      Class.forName("org.gjt.mm.mysql.Driver");
      Connection connection = 
        DriverManager.getConnection(dbUrl, user, password);
      Statement statement = connection.createStatement();
      String sqlStmt = "SELECT * FROM Courses";      
      ResultSet result = statement.executeQuery(sqlStmt);
      ResultSetMetaData metaData = result.getMetaData();
      int numColumns = metaData.getColumnCount();
      
      for (int nc=1; nc<=numColumns; ++nc)
      {
        System.out.println(metaData.getColumnName(nc) + " " +
          metaData.getColumnTypeName(nc));
      }
      statement.close();
    }
    catch (Exception e) { e.printStackTrace(); }
    finally
    {
      connection.close();
    }  
  }
}
