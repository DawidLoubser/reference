package za.co.solms.clustering.test;

public class ClusteringTestBean
             implements javax.ejb.SessionBean,
                        ClusteringTestServices
{
  public long run(long n)
  {
	  System.out.println("called the bean!!");
    java.util.Date start = new java.util.Date();
    java.util.Random rng = new java.util.Random();
    double sum = 0;
    for (long i=0; i<n; ++i)
        sum += Math.sin(rng.nextDouble()) + Math.cos(rng.nextDouble());
    java.util.Date end = new java.util.Date();
    return end.getTime() - start.getTime();
  }

  public void ejbCreate() throws javax.ejb.CreateException {}
  public void ejbRemove() {}
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void setSessionContext(javax.ejb.SessionContext context) {}
}

