package za.co.stc.finance;

public abstract class Asset implements Valuable
{
  public Asset(String id) {this.id = id;}

  public String toString() {return id;}

  public String getId() {return id;}

  private String id;
}
