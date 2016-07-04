import java.util.*;
 
 public class TreeSetOfPersons
 {
   public static void main(String[] args)
   {
     Set sortedPersons = new TreeSet();
 
     sortedPersons.add(new Person("Smith", "Peter"));
     sortedPersons.add(new Person("Khumalo", "Tandi"));
     sortedPersons.add(new Person("Motaung","Alfred Johannes"));
     sortedPersons.add(new Person("Pieterse", "Francois"));
     sortedPersons.add(new Person("Ali", "Mohammed"));
     sortedPersons.add(new Person("Smith", "Jenny"));
     sortedPersons.add(new Person("Smith", "Jenny Ellen"));
 
     Iterator iter = sortedPersons.iterator();
     while(iter.hasNext())
       System.out.println( iter.next() );
   }
 }