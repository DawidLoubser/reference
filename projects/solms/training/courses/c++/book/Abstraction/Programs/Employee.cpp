\begin{verbatim}
#include "Employee.h"
#include <stdlib.h>

Employee::Employee(const string& name, const string& idNo,
                   const double& salary)
  :Person(name, idNo), _salary(salary){}

double Employee::salary() const {return _salary;}

void Employee::salary(const double& newSalary)
{
  _salary = newSalary;
}

string Employee::toString() const
{
  char* s_salary = new char[24];
  sprintf(s_salary, "%f", _salary);
  return Person::toString() + " (salary = " + s_salary + ")";
}

ostream& operator<< (ostream& os, const Employee& e)
{
  os << e.name() << ": " << e.idNo() << "(salary = "
     << e._salary << ")";
  return os;
}
\end{verbatim}
