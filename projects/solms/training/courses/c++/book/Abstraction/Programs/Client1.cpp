\begin{verbatim}
#include "Client1.h"

Client1::Client1(const string& name, const string& idNo, Account& account)
         : Person(name, idNo), _account(account) {}

Account& Client1::account() const {return _account;}
\end{verbatim}
