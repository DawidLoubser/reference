import java.sql.*;

public class TestMySQL
{
  public static void main(String[] args)
  {
    String dbUrl = "jdbc:mysql://localhost/test:3306";
    String user = "";
    String password = "";
    
    /* Get Connection */
    Connection connection = null;
    try
    {
      Class.forName("org.gjt.mm.mysql.Driver");
      connection = DriverManager.getConnection(dbUrl, user, password);
    }
    catch (Exception e)
    {
      System.out.println("Could not establish DB connection: " + e);
    }        

    try
    {
      /* Create Tables */
      Statement statement = connection.createStatement();      
      String sqlStmt = "CREATE TABLE Courses(code CHAR(6) PRIMARY KEY NOT NULL, " 
                             + " name VARCHAR(255), duration SMALLINT)";
      try
      {                                       
        statement.executeUpdate(sqlStmt);
      }
      catch (java.sql.SQLException e)
      {
        System.out.println(e.getMessage());
      }    
      
      String[][] courses = {{"XML_1", "XML", "3"},
                    {"EJB-1", "Enterprise Java Beans", "5"},
                    {"UML-1", "Object-Oriented Analysis and Design using UML", "5"},
                    {"Java-2", "Advanced Java", "5"},
                    {"CORBA-1", "CORBA", "4"}};
      
      /* Insert some data */
      for (int course=0; course<courses.length; ++course)
      {  
        sqlStmt = "INSERT INTO Courses (code, name, duration) " +
                                 "VALUES ('" + courses[course][0] + "', '" 
                                             + courses[course][1] + "', '" 
                                             + courses[course][2] + "')";
        try
        {                                       
          statement.executeUpdate(sqlStmt);
        }
        catch (java.sql.SQLException e)
        {
          System.out.println(e.getMessage());
        }    
      }  
      
      /* Query some data */
      sqlStmt = "SELECT name, duration FROM Courses WHERE duration=5";
      ResultSet result = statement.executeQuery(sqlStmt);
      while (result.next())
        System.out.println(result.getString("name") + " => " +
                           result.getInt("duration"));

      statement.close();
    }
    catch (Exception e) {e.printStackTrace();}
    finally
    {
      /* Always close connections */
      try {connection.close();} catch (SQLException ignored) {}
    }  
  }
}
