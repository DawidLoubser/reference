package za.co.solms.training.exams;

import javax.naming.InitialContext;

import org.jboss.security.SecurityAssociation;
import org.jboss.security.SimplePrincipal;

/**
 * @author fritz@solms.co.za
 *
 */
public class ExamsManager
{
  public ExamsManager() {}
  
  public void run()
  {
    try
    {
      InitialContext context = new InitialContext();
      ExamResults examResults 
        = (ExamResults)context.lookup(ExamResults.class.getName());
      
      SecurityAssociation.setPrincipal(new SimplePrincipal("mrBean"));
      SecurityAssociation.setCredential("xx7".toCharArray());
      examResults.add("Paula Paulus", 50);
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args)
  {
    new ExamsManager().run();
  }
}
