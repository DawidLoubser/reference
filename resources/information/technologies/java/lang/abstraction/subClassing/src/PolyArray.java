class PolyArray
{
   public static void main(String[] args)
   {
     Person[] persons = new Person[4];
 
     persons[0] = new Person("Jane Cole", "781123 2235 087");
     persons[1] = new Employee("Rashad Naidoo", "680201 1178 023", 11000.0, "ABC Training");
     persons[2] = new Employee("Pieter Smit", "590821 3343 087", 9500.0, "CNA");
     persons[3] = new Person("Benny Moloto", "601230 1218 078");
 
     for (int i=0; i < persons.length; ++i)
     {
       persons[i].speak();
     }
   }
}