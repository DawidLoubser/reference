\begin{verbatim}
#include "Client.h"

using SolmsTraining::finance::Account;
using SolmsTraining::clients::Client;

Client::Client (const string& name, const string& idNo, Account& account)
         : Person(name, idNo), _account(account) {}

Account& Client::account() const
{
  return _account;
}
\end{verbatim}
