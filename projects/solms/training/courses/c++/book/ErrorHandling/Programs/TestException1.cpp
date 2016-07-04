\begin{verbatim}
#include <iostream>
#include "Account.h"

using namespace SolmsTraining::finance;

using namespace std;

int main()
{
  Account* myAccount = new Account();
  myAccount->credit(2000);

  Account* myUnclesAccount = new Account(3000);

  while (true)
  {
    try
    {
      myAccount->debit(350);
      cout << "Debited my account. Balance: " << myAccount->balance() << endl;
    }
    catch (InsufficientFunds exception)
    {
      cout << "my Account only has R"
           << exception.availableFunds() << " left."
           << " Debited uncle instead." << endl;
      try
      {
        myUnclesAccount->debit(350);
      }
      catch (InsufficientFunds e)
      {
        cout << "Out of luck. Uncle broke too." << endl;
        return -1;
      }
    }
  }
}
\end{verbatim}
