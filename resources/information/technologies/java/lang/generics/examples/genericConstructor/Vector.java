public class Vector    // Note: Vector class itself not parameterized
{
    public Vector() {}

    public <N extends Number> Vector(N[] elements)
    {
      // ...
    }
    
    private double[] elements;
}    