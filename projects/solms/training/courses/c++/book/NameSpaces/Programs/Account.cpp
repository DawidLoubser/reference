\begin{verbatim}
#include "Account.h"
#include <iostream>

using namespace std;

SolmsTraining::finance::Account::Account()
  : _balance(50) {}

SolmsTraining::finance::Account::Account(double initialBalance)
  : _balance(initialBalance) {}

SolmsTraining::finance::Account::~Account()
{
  cout << "I, " << this << ", am destroyed." << endl;
}

void SolmsTraining::finance::Account::credit(double amount)
{
  _balance += amount;
}

void SolmsTraining::finance::Account::debit(double amount)
{
  _balance -= amount;
}

double SolmsTraining::finance::Account::balance() {return _balance;}
\end{verbatim}
