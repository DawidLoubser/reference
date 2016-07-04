package za.co.solms.ejb3.friends;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote({FriendDAO.class})
public class FriendDAOBean implements FriendDAO
{
  public long create(String name, String email, String tel, 
                     Address address)
  {
    Friend friend = new Friend(name, email, tel, address);
    entityManager.persist(friend); 
    return friend.getId();
  }

  public Friend find(long id)
  {
    return entityManager.find(Friend.class, new Long(id));
  }
  
  public void update(Friend friend)
  {
    entityManager.merge(friend);
  }
  
  public void delete(Friend friend)
  {
    entityManager.remove(friend);
  }
    
  @PersistenceContext 
  private EntityManager entityManager;
}
