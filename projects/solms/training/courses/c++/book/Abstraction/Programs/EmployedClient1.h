\begin{verbatim}
#ifndef EmployedClient1_H
#define EmployedClient1_H

#include "Client1.h"
#include "Employee1.h"

class EmployedClient1: public Employee1, public Client1
{
  public:
    EmployedClient1(const string& name, const string& idNo,
                    const double& salary, Account& account);
};
#endif
\end{verbatim}
