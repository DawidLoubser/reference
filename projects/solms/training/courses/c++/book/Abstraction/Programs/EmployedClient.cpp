\begin{verbatim}
#include "EmployedClient.h"

EmployedClient::EmployedClient(const string& name, const string& idNo,
                               const double& salary, Account& account)
  : Employee(name, idNo, salary), Client(name, idNo, account),
    Person(name, idNo) {}
\end{verbatim}
