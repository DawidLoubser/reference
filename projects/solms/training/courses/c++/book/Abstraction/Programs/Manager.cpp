\begin{verbatim}
#include "Manager.h"

Manager::Manager(const string& name, const string& idNo, const double& salary,
                 const string& statusSymbol)
  : Employee1(name, idNo, salary), _statusSymbol(statusSymbol) {}

string Manager::statusSymbol() {return _statusSymbol;}
\end{verbatim}
