\begin{verbatim}
#ifndef Employee_H
#define Employee_H

#include <iostream>

#include "Person.h"

using namespace std;

class Employee: public virtual Person
{
  public:
    Employee(const string& name, const string& idNo,
             const double& salary);

    double salary() const;

    void salary(const double& newSalary);

    string toString() const;

  //friends:
    friend ostream& operator<< (ostream& os, const Employee& e);

  private:
    double _salary;
};
#endif
\end{verbatim}
