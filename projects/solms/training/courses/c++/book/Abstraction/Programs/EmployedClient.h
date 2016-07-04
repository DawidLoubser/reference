\begin{verbatim}
#ifndef EmployedClient_H
#define EmployedClient_H

#include "Client.h"
#include "Employee.h"

class EmployedClient: public virtual Employee, public virtual Client
{
  public:
    EmployedClient(const string& name, const string& idNo,
                   const double& salary, Account& account);
};

#endif
\end{verbatim}
