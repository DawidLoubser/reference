\begin{verbatim}
#ifndef Employee1_H
#define Employee1_H

#include <iostream>

#include "Person.h"

using namespace std;

class Employee1: public Person
{
  public:
    Employee1(const string& name, const string& idNo,
              const double& salary);

    double salary() const;

    void salary(const double& newSalary);

    string toString() const;

  //friends:
    friend ostream& operator<< (ostream& os, const Employee1& e);

  private:
    double _salary;
};
#endif
\end{verbatim}
