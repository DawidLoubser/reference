package za.co.solms.training.exams;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.aspects.security.SecurityDomain;

/**
 * @author fritz@solms.co.za
 *
 */
@Stateless
@SecurityDomain("other")
public class ExamResultsBean implements ExamResults
{
  
	@RolesAllowed({"lecturer"})
  public long add(String studentName, double result)
  {
     ExamResultBean examResult 
      = new ExamResultBean(studentName, result);
    entityManager.persist(examResult);
    return examResult.getId();
  }

  @PersistenceContext  // injecting entity manager reference
  private EntityManager entityManager;
}
