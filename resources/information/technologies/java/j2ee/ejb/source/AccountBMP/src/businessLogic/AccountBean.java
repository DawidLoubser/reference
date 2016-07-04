package za.co.solms.finance;

import javax.ejb.*;
import java.sql.*;
import java.sql.*;
import java.util.*;

public class AccountBean implements javax.ejb.EntityBean
{
  public String getId()
                  throws java.rmi.RemoteException
  {return id;}
  
  public String getName() 
                  throws java.rmi.RemoteException
  {return name;}
  
  public void setName(String newName) 
                  throws java.rmi.RemoteException
  {name = newName;}
  
  public double getBalance() 
                  throws java.rmi.RemoteException
  {return balance;}
  
  public void credit(double amount)
                  throws java.rmi.RemoteException
  {
    balance += amount;
  }
  
  public void debit(double amount)
                  throws java.rmi.RemoteException,
                         InsufficientFundsException
  {
    if (amount > balance)
      throw new InsufficientFundsException
        ("available funds: " + balance);
    balance -= amount;
  }
  
  public void setEntityContext(EntityContext entityContext)
  {
    this.entityContext = entityContext;
  }
  
  public void unsetEntityContext()
  {
    entityContext = null;
  }
  
  public String ejbCreate(String id) throws javax.ejb.CreateException
  {
    return ejbCreate(id, null);
  }  
    
  public String ejbCreate(String id, String name) throws javax.ejb.CreateException
  {
    if ((id == null) || (id.length() == 0))
      throw new javax.ejb.CreateException("Id must be supplied");
    this.id = id;
    this.name = name;
    
    try
    {
      dbConn = getConnection();
      
      String sqlStmt = "INSERT INTO AccountBMP VALUES (?, ?, ?)";
      
      PreparedStatement stmt = dbConn.prepareStatement(sqlStmt);
      stmt.setString(1, id);
      stmt.setString(2, name);
      stmt.setDouble(3, balance);
      
      stmt.executeUpdate();
      stmt.close();  
    }
    catch(java.sql.SQLException e)  
    {
      throw new CreateException(e.toString()); 
    }
    finally
    {
      if (dbConn != null)
      {
        try
        {
          dbConn.close();
        }
        catch (SQLException e) {}
      }
    }      
    return id;
  }

  public void ejbPostCreate(String id) {}
  
  public void ejbPostCreate(String id, String name) {}
  
  // We set the id as stored in the context. 
  // This will be used by the container when loading the bean instance.
  public void ejbActivate() 
  {
    id = (String)entityContext.getPrimaryKey();
    System.out.println("Account bean " + id + " activated.");
  }
  
  public void ejbPassivate() 
  {
    System.out.println("Account bean " + id + " passivated.");
    id = null;
  }
  
  public void ejbLoad() 
  {
    try
    {
      dbConn = getConnection();
      
      String sqlStmt = "SELECT id, name, balance FROM AccountBMP "
                     + "WHERE id = ?";

      PreparedStatement stmt = dbConn.prepareStatement(sqlStmt);
      stmt.setString(1, id);

      System.out.println("Loading bean via query: " + sqlStmt + ": " + id);
      
      ResultSet result = stmt.executeQuery();
      result.next();
      this.name = result.getString("name");  
      this.balance = result.getDouble("balance");  
      
      stmt.close();
    }
    catch (java.sql.SQLException e)
    {
      throw new EJBException(e);  
    }  
    finally
    {
      if (dbConn != null)
        try
        {
          dbConn.close();
        }
        catch (java.sql.SQLException ex) {}
    }    
    System.out.println("returning from load.");
  }
  
  public void ejbStore() 
  {
    try
    {
      dbConn = getConnection();
      
      String sqlStmt = "UPDATE AccountBMP SET name = ?, balance = ? "
                     + "WHERE id = ?"; 
      
      PreparedStatement stmt = dbConn.prepareStatement(sqlStmt);
      stmt.setString(1, name);
      stmt.setDouble(2, balance);
      stmt.setString(3, id);
      
      if (stmt.executeUpdate() != 1)
        throw new EJBException("Account could not be persisted to storage.");
      
      stmt.close();
    }
    catch (java.sql.SQLException e)
    {
      throw new EJBException(e);  
    }  
    finally
    {
      if (dbConn != null)
        try
        {
          dbConn.close();
        }
        catch (java.sql.SQLException ex) {}
    }
  }
  
  public void ejbRemove() 
  {
    try
    {
      dbConn = getConnection();
      
      String sqlStmt = "DELETE FROM AccountBMP where id = ?";
      
      PreparedStatement stmt = dbConn.prepareStatement(sqlStmt);
      stmt.setString(1, this.id);
      
      stmt.executeUpdate();
      stmt.close();
    }
    catch (java.sql.SQLException e)
    {
      throw new EJBException(e);  
    }  
    finally
    {
      if (dbConn != null)
        try
        {
          dbConn.close();
        }
        catch (java.sql.SQLException ex) {}
    }
  }
  
  // We also have to write the FINDER methods!!
  
  public String ejbFindByPrimaryKey(String id)
                   throws FinderException
  {
    try
    {
      dbConn = getConnection();
      
      String sqlStmt = "SELECT id FROM AccountBMP "
                     + "WHERE id = ?"; 
      
      PreparedStatement stmt = dbConn.prepareStatement(sqlStmt);
      stmt.setString(1, id);
      
      ResultSet result = stmt.executeQuery();
  
      if (!result.next())
        throw new ObjectNotFoundException();
      
      stmt.close();
    }
    catch (java.sql.SQLException e)
    {
      throw new EJBException(e);  
    }  
    finally
    {
      if (dbConn != null)
        try
        {
          dbConn.close();
        }
        catch (java.sql.SQLException ex) {}
    }
    return id;
  }

  public Collection ejbFindByName(String accountName)
                    throws FinderException
  {
    try
    {
      dbConn = getConnection();

      String sqlStmt = "SELECT id FROM AccountBMP "
                     + "WHERE name = ?";

      PreparedStatement stmt = dbConn.prepareStatement(sqlStmt);
      stmt.setString(1, accountName);

      ResultSet result = stmt.executeQuery();

      Collection accounts = new ArrayList();
      while (result.next())
        accounts.add(result.getString(1));

      stmt.close();
      return accounts;
    }
    catch (java.sql.SQLException e)
    {
      throw new EJBException(e);
    }
    finally
    {
      if (dbConn != null)
        try
        {
          dbConn.close();
        }
        catch (java.sql.SQLException ex) {}
    }
  }

  public Collection ejbFindAll()
                    throws FinderException
  {
    try
    {
      dbConn = getConnection();

      String sqlStmt = "SELECT id FROM AccountBMP";
      
      PreparedStatement stmt = dbConn.prepareStatement(sqlStmt);
      
      ResultSet result = stmt.executeQuery();
  
      Collection accounts = new ArrayList();
      while (result.next())
        accounts.add(result.getString(1));
      
      stmt.close();
      return accounts;
    }
    catch (java.sql.SQLException e)
    {
      throw new EJBException(e);  
    }  
    finally
    {
      if (dbConn != null)
        try
        {
          dbConn.close();
        }
        catch (java.sql.SQLException ex) {}
    }    
  }  
  
  // General service for getting a connection through the application server
  // (usually from a connection pool maintained by the app server).
  
  private Connection getConnection() throws EJBException
  {
    try
    {
      javax.naming.InitialContext jndiContext = new javax.naming.InitialContext();
      
      javax.sql.DataSource dataSource
        = (javax.sql.DataSource)jndiContext.lookup
            ("java:/comp/env/jdbc/MySQLtestDS");

      System.out.println("found data source.");  
    
      return dataSource.getConnection();
    }
    catch (Exception e)
    {
      throw new EJBException(e.toString());
    }    
  }

  private String id, name;
  private double balance = 0;

  private EntityContext entityContext;
  private Connection dbConn;
}
