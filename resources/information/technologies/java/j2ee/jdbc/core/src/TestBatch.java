import java.sql.*;

public class TestBatch
{
  public static void main(String[] args)
  {
    String dbUrl = "jdbc:mysql://localhost/test:3306";
    String user = "bond";
    String password = "007secret";

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
      Statement statement = connection.createStatement();
      
      String sqlStmt = "CREATE TABLE Courses(code CHAR(6) PRIMARY KEY " + 
                     + "NOT NULL, name VARCHAR(255), duration SMALLINT)";
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
      
      for (int course=0; course<courses.length; ++course)
      {  
        sqlStmt = "INSERT INTO Courses (code, name, duration) " +
                                 "VALUES ('" + courses[course][0] + "', '" 
                                             + courses[course][1] + "', '" 
                                             + courses[course][2] + "')";
                
        statement.addBatch(sqlStmt);
      }  

      try
      {                                       
        statement.executeBatch();
      }
      catch (java.sql.SQLException e)
      {
        System.out.println(e.getMessage());
      }    
      
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
      try {connection.close();} catch (SQLException ex) {}
    }  
  }
}
