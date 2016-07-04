\begin{verbatim}
#include <iostream>
#include "Account.h"

using namespace std;

int main()
{
  Account acc1;
  Account acc2(1000);

  acc1.credit(250);
  acc2.debit(100);

  cout << "balance of acc1: " << acc1.balance() << endl;
  cout << "balance of acc2: " << acc2.balance() << endl;

  char c; cin >> c;
  
  return 0;
}

\end{verbatim}
