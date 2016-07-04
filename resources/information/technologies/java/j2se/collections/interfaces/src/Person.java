 public class Person implements Comparable
 {
   public Person (String surName, String firstNames)
   {
     this.surName = surName;
     this.firstNames = firstNames;
   }
 
   public int compareTo (Object object)
   {
     Person person = (Person)object;
 
     int surNameComparison = surName.compareTo(person.surName);
 
     if (surNameComparison != 0)
       return surNameComparison;
     else
       return firstNames.compareTo(person.firstNames);
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof Person))
       return false;
     else
       return (compareTo(object) == 0);
   }
 
   public String toString()
   {
       return firstNames + " " + surName;
   }
 
   public int hashCode()
   {
     return surName.hashCode() + 2*firstNames.hashCode();
   }
 
   private String surName, firstNames;
 }


