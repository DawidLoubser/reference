\begin{verbatim}
#include "Person.h"

#include <string.h>

Person::Person(const string& name, const string& idNo)
         : _name(name), _idNo(idNo) {}

string Person::name() const {return _name;}

string Person::idNo() const {return _idNo;}

string Person::toString() const
{
  return _name + ": " + _idNo;
}

// Implementation of friend functions:

ostream& operator<< (ostream& os, const Person& p)
{
  os << p._name << ": " << p._idNo;
  return os;
}
\end{verbatim}
