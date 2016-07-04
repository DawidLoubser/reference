\begin{verbatim}
#include <iostream>

#include "Account.h"
#include "Person.h"
#include "Client.h"
#include "Employee.h"
#include "EmployedClient.h"

using namespace std;

int main()
{
  Account account;
  EmployedClient* employedClient
    = new EmployedClient("Peter", "546464389672", 190000, account);

  cout << employedClient->salary() << endl;
  Account& acc = employedClient->account();
  acc.credit(700);
  cout << acc.balance() << endl;

  cout << employedClient->name() << endl;

  Person* p = employedClient;
  cout << p->idNo() << endl;

  char c; cin >> c;
  
  return 0;
}
\end{verbatim}
