package za.co.solms.ejb3.friends;

public interface FriendDAO 
{
  public long create(String name, String email, String tel, 
                     Address address);
  
  public Friend find(long id);
  
  public void update(Friend friend);
  
  public void delete(Friend friend);
}
