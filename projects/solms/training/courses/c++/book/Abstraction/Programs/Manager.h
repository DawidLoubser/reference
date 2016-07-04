\begin{verbatim}
#ifndef Manager_H
#define Manager_H

#include "Employee1.h"

class Manager: public Employee1
{
  public:
    Manager(const string& name, const string& idNo, const double& salary,
            const string& statusSymbol);

    string statusSymbol();

  private:
    string _statusSymbol;
};
#endif
\end{verbatim}
