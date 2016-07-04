package za.co.solms.ejb3.friends;

import javax.persistence.*;

@Entity
@Table(name="FRIENDS")
public class Friend implements java.io.Serializable
{
  public Friend(String name) 
  {
    setName( name );
  }
  
  public Friend(String name, String email, String tel, 
                Address address) 
  {
    setName( name );
    setEmailAddress( emailAddress );
    setTelephoneNo( telephoneNo );
    setAddress( address );
  }
  
  public Friend() {}
  
  
  public long getId() {return id;}
  public void setId(long id) {this.id = id;}
  
  public String getName() {return name;}
  public void setName (String name) {this.name = name;}
  
  
  public String getEmailAddress() {return emailAddress;}
  public void setEmailAddress (String email) {emailAddress = email;}
  
  
  public String getTelephoneNo() {return telephoneNo;}
  public void setTelephoneNo (String tel) {telephoneNo = tel;}

  /**
   * Safe update which respects composition relationship for embedded class.
   */
  public void updateAddress(Address address)
  {
    this.address.setStreet(address.getStreet());
    this.address.setSuburb(address.getSuburb());
  }
  
  /**
   * Safe query which respects composition relationship for embedded class.
   */
  public Address getAddress()
  {
    return (Address)address.clone();
  }
  
  public String toString()
  {
    return name + ": (" + emailAddress + "," + telephoneNo + ")";
  }


  @Id @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  
  private String name;
  
  @Column(name="EMAIL")
  private String emailAddress;
  
  @Column(name="TEL")
  private String telephoneNo;
  
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name="street", 
        column = @Column(name = "STREET_ADDRESS")),
    @AttributeOverride(name="suburb",
        column = @Column(name="SUBURB"))
  })
  private Address address; // embedded class
}
