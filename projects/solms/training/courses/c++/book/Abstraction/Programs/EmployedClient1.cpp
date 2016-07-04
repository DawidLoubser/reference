\begin{verbatim}
#include "EmployedClient1.h"

EmployedClient1::EmployedClient1(const string& name, const string& idNo,
                                 const double& salary, Account& account)
  : Employee1(name, idNo, salary), Client1(name, idNo, account) {}
\end{verbatim}
