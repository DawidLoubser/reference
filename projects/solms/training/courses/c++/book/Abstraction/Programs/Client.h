\begin{verbatim}
#ifndef Client_H
#define Client_H

#include "Person.h"
#include "Account.h"

class Client: public virtual Person
{
  public:
    Client(const string& name, const string& idNo, Account& account);

    Account& account() const;

  private:
    Account& _account;
};
#endif
\end{verbatim}