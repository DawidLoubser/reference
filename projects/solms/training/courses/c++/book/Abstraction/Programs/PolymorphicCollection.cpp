\begin{verbatim}
#include <iostream>

#include "Person.h"
#include "Employee.h"

using namespace std;

//---------------------------------------------------------------------------
int main()
{
  const int numPersons = 3;
  Person** persons = new Person*[numPersons];
  persons[0] = new Person("Alfred", "588753287");
  persons[1] = new Employee("Pieter", "618732987", 196000);
  persons[2] = new Person("Petra", "8109894542424");

  for (int i=0; i<numPersons; ++i)
    cout << persons[i]->toString() << endl;

  for (int i=0; i<numPersons; ++i)
    delete persons[i];
  delete[] persons;

  char c; cin >> c;
  
  return 0;
}
\end{verbatim}
