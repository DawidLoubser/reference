package za.co.solms.clustering.test;

public interface ClusteringTestHome extends javax.ejb.EJBHome
{
  ClusteringTest create() throws java.rmi.RemoteException,
                                 javax.ejb.CreateException;
}
