\begin{verbatim}
#include "Account.h"

SolmsTraining::finance::Account::Account(const string& accNo)
  : _balance(50), _accNo(accNo) {}

SolmsTraining::finance::Account::Account
        (const string& accNo, const double& initialBalance)
  : _balance(initialBalance), _accNo(accNo) {}

SolmsTraining::finance::Account::~Account()
{
  cout << "I, " << this << ", am destroyed." << endl;
}

void SolmsTraining::finance::Account::credit(const double& amount)
{
  _balance += amount;
}

void SolmsTraining::finance::Account::debit(const double& amount)
{
  _balance -= amount;
}

double SolmsTraining::finance::Account::balance() const {return _balance;}

string SolmsTraining::finance::Account::accNo() const {return _accNo;}

ostream& operator<< (ostream& os, const SolmsTraining::finance::Account& acc)
{
  os << "Account: " << acc.accNo() << " => " << "balance = "<< acc.balance();
  return os;
}
\end{verbatim}
