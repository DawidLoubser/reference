\begin{verbatim}
#include "Account.h"
#include <iostream>

using namespace SolmsTraining::finance;

using namespace std;

Account::Account(): _balance(50) {}

Account::Account(double initialBalance): _balance(initialBalance) {}

Account::~Account()
{
  cout << "I, " << this << ", am destroyed." << endl;
}

void Account::credit(double amount)
{
  _balance += amount;
}

void Account::debit(double amount)
          throw (InsufficientFunds)
{
  if (amount > _balance)
    throw InsufficientFunds(this, _balance);
  _balance -= amount;
}

double Account::balance() {return _balance;}
\end{verbatim}
