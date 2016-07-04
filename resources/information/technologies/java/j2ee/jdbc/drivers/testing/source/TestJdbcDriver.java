<![CDATA[ import java.sql.*;
 
 // MySQL users:   root, no password
 //                noname, no password
 
 public class TestJdbcDriverSimple
 {
   public static void main(String[] args)
   {
     String dbUrl = "jdbc:mysql://localhost/test:3306";
     String user = "jboss";
     String password = "stc007";
     try
     {
       Class.forName("org.gjt.mm.mysql.Driver");
       Connection connection = DriverManager.getConnection
                                                (dbUrl, user, password);
 
       Statement statement = connection.createStatement();
 
       try
       {
         statement.executeUpdate("CREATE TABLE PriceList"
           + " (id CHAR(6) NOT NULL PRIMARY KEY, name CHAR(30), price DECIMAL)");
       }
       catch (SQLException ex)
       {
         System.out.println("table already exists");
       }
 
       try
       {
         statement.executeUpdate("INSERT INTO PriceList"
           + " (id, name, price) VALUES ('908399', 'Jam', '12.34')");
       }
       catch (SQLException ex)
       {
         System.out.println("record for that primary key already exists");
       }
 
       ResultSet result = statement.executeQuery(
                        "SELECT name, price " +
                        "FROM PriceList");
       while (result.next())
         System.out.println(result.getString("name") + " => " +
                            result.getDouble("price"));

        statement.close();
     }
     catch (Exception e) {e.printStackTrace();}
   }
 }
