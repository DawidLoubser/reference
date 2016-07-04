\begin{verbatim}
#include <iostream>

#include "Person.h"
#include "Employee1.h"

using namespace std;

//---------------------------------------------------------------------------
int main()
{
  Person* p1 = new Person("Jack", "6811125657102");
  Employee1 * e1 = new Employee1("Jill", "6910825655105", 210000);

  cout << "p1's name: " << p1->name() << endl;
  cout << "e1's name: " << e1->name() << endl;
  cout << "e1's salary: " << e1->salary() << endl;

  Person* p2 = e1;

  cout << "p2's name: " << p2->name() << endl;

  char c; cin >> c;
  
  return 0;
}
\end{verbatim}
