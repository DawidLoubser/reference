\begin{verbatim}
#include <iostream>
#include "Account.h"

using namespace std;

void debitServiceFees(Account* acc)
{
  acc->debit(45);
}

void run()
{
  int numAccounts = 5;
  Account** accounts = new Account*[numAccounts];

  for(int i=0; i<numAccounts; ++i)
    accounts[i] = new Account();

  for(int i=0; i<numAccounts; ++i)
    debitServiceFees(accounts[i]);

  // Because the accounts were created on the heap,
  // they have to be deleted manually:
  for(int i=0; i<numAccounts; ++i)
    delete accounts[i];
  delete[] accounts;
}

int main()
{
  run();

  char c; cin >> c;
  
  return 0;
}
\end{verbatim}
