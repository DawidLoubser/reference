public class RandomNumberGeneratorTest
{
  public static void main(String[] args)
  {
    za.co.solmstraining.utils.random.RandomNumberGenerator rng
      = new za.co.solmstraining.utils.random.RandomNumberGenerator();
    for (int i=0; i<5; ++i)
      System.out.println(rng.nextInteger(1000));
    System.out.println("Getting encapsulated state of random number generator");
    za.co.solmstraining.utils.Memento rngMemento = rng.getState();
    System.out.println("Now another 5 random numbers.");
    for (int i=0; i<5; ++i)
      System.out.println(rng.nextInteger(1000));
    System.out.println("Resetting random number generator to memento state");
    rng.setState(rngMemento);
    for (int i=0; i<5; ++i)
      System.out.println(rng.nextInteger(1000));

  }
}
