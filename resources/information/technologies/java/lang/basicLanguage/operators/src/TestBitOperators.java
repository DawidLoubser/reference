class TestBitOperators
{
  public static void main(String[] args)
  {
    int i =   9;
    int j = -15;
    int k =  15;

    System.out.println(" i = " + i);
    System.out.println(" j = " + j);
    System.out.println(" k = " + k);
    System.out.println("~i    = " + (~i));
    System.out.println("i & j = " + (i & j));
    System.out.println("i | j = " + (i | j));
    System.out.println("i ^ j = " + (i ^ j));
    System.out.println("j << 2 = " + (j << 2));
    System.out.println("j >> 2 = " + (j >> 2));
    System.out.println("j >>> 2 = " + (j >>> 2));
    System.out.println("k << 2 = " + (k << 2));
    System.out.println("k >> 2 = " + (k >> 2));
    System.out.println("k >>> 2 = " + (k >>> 2));
  }
}
