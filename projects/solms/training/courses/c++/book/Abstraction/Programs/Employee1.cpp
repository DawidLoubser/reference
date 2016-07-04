\begin{verbatim}
#include "Employee1.h"
#include <stdlib.h>

Employee1::Employee1(const string& name, const string& idNo,
                     const double& salary)
  :Person(name, idNo), _salary(salary){}

double Employee1::salary() const {return _salary;}

void Employee1::salary(const double& newSalary)
{
  _salary = newSalary;
}

string Employee1::toString() const
{
  char* s_salary = new char[24];
  sprintf(s_salary, "%f", _salary);
  return Person::toString() + " (salary = " + s_salary + ")";
}

ostream& operator<< (ostream& os, const Employee1& e)
{
  os << e.name() << ": " << e.idNo() << "(salary = "
     << e._salary << ")";
  return os;
}
\end{verbatim}
