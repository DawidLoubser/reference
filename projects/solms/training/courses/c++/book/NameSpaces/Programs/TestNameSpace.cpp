\begin{verbatim}
#include <iostream>

#include "Client.h"
#include "Account.h"

using namespace std;

int main()
{
  using SolmsTraining::clients::Client;

  SolmsTraining::finance::Account* acc
    = new SolmsTraining::finance::Account();

  acc->credit(380);

  Client* client1 = new Client("Abduhl", "6754236754", *acc);

  cout << client1->name() << " has a balance of R"
       << client1->account().balance() << endl;

  char c; cin >> c;
  
  return 0;
}
\end{verbatim}
