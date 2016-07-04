\begin{verbatim}
#include <iostream.h>

#include "Person.h"
#include "Employee1.h"

#include <condefs.h>
USEUNIT("Person.cpp");
USEUNIT("Employee1.cpp");

void printPersonA(Person* person)
{
  cout << "printPersonA: " << person->toString() << endl;
}

void printPersonB(Person person)
{
  cout << "printPersonB: " << person.toString() << endl;
}

void main()
{
  Person* p1 = new Person("Jack", "6811125657102");
  Person* p2 = new Employee1("Jill", "6910825655105", 210000);

  printPersonA(p1);    printPersonA(p2);
  printPersonB(*p1);   printPersonB(*p2);

  cout << (*p1) << endl;
  cout << (*p2) << endl;

  char c; cin >> c;
}
\end{verbatim}
