
import java.util.Vector;

class PolyVector
{
  public static void main(String[] args)
  {
    Vector persons = new Vector();

    persons.addElement(new Person("Jane Cole", "781123 2235 087"));
    persons.addElement(new Employee("Rashad Naidoo", "680201 1178 023", 11000.0));
    persons.addElement(new Employee("Pieter Smit", "590821 3343 087", 9500.0,
                                    "lecturer"));
    persons.addElement(new Person("Benny Moloto", "601230 1218 078"));

    for (int i=0; i<persons.size(); ++i)
      System.out.println(persons.elementAt(i));
  }
}

