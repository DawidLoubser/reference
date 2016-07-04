package za.co.solms.math.algebra.linear;

public interface Scalar extends java.lang.Cloneable
{
  public void assign(Scalar arg);

  public Scalar add(Scalar arg);

  public Scalar multiply(Scalar arg);

  public Scalar divideBy(Scalar arg);

  public Scalar clone();
}