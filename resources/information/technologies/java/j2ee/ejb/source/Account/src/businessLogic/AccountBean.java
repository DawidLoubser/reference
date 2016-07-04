package za.co.solms.finance;

import javax.ejb.EntityContext;

public abstract class AccountBean implements javax.ejb.EntityBean
{
  public abstract String getId();
  public abstract void setId(String newId);

  public abstract String getName();
  public abstract void setName(String newName);

  public abstract double getBalance();
  public abstract void setBalance(double newBalance);

  public void credit(double amount)
  {
    setBalance(getBalance() + amount);
  }

  public void debit(double amount)
                  throws InsufficientFundsException
  {
    double balance = getBalance();
    if (amount > balance)
      throw new InsufficientFundsException
        ("available funds: " + balance);
    balance -= amount;
    setBalance(balance);
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
    if ((id == null) || (id.length() == 0))
      throw new javax.ejb.CreateException("Id must be supplied");

    setId(id);
    return null;  // actual implementation supplied by Container
  }

  public void ejbPostCreate(String id) {}

  public void ejbActivate() {}
  public void ejbPassivate() {}

  public void ejbLoad() {}
  public void ejbStore() {}
  public void ejbRemove() {}


  protected EntityContext entityContext;
}
