\begin{verbatim}
#ifndef AccountH
#define AccountH

#include <iostream>
#include <string>

using namespace std;

namespace SolmsTraining
{
 namespace finance
 {
  class Account
  {
    public:
      Account(const string& accNo);
      Account(const string& accNo, const double& initialBalance);

      ~Account();

      void credit(const double& amount);
      void debit(const double& amount);

      double balance() const;

      string accNo() const;

    private:
      double _balance;
      string _accNo;
  };
 }
}

ostream& operator<< (ostream& os, const SolmsTraining::finance::Account& acc);
#endif
\end{verbatim}
