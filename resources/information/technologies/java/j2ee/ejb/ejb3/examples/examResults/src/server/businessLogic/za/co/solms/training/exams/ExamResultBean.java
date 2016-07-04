/*
 * Created on Jun 6, 2005
 *
 */
package za.co.solms.training.exams;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.persistence.Entity;
import javax.persistence.GeneratorType;
import javax.persistence.Id;

import org.jboss.aspects.security.SecurityDomain;

/**
 * @author fritz
 *
 */
@Entity 
@SecurityDomain("other")
public class ExamResultBean
{
  protected ExamResultBean() {}
  
  public ExamResultBean(String name, double result)
  {
    this.name = name;
    this.result = result;
  }
  
  @PermitAll
  public String getName() {return name;}
  protected void setName(String nm) {name = nm;}

  @RolesAllowed({"student"})
  public double getResult() {return result;}
  @RolesAllowed({"lecturer"})
  public void setResult(double rslt) {result = rslt;}
  
  @Id(generate = GeneratorType.AUTO)
  public long getId() {return id;}
  protected void setId(long id) {this.id = id;}

  private String name;
  private double result;
  private long id;
}
