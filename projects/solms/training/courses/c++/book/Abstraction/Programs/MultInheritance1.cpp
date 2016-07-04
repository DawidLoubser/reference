\begin{verbatim}
#include <iostream>

#include "Account.h"
#include "Person.h"
#include "Client1.h"
#include "Employee1.h"
#include "Manager.h"
#include "EmployedClient1.h"

using namespace std;

//---------------------------------------------------------------------------
int main()
{
  Account account;
  EmployedClient1* employedClient
    = new EmployedClient1("Peter", "546464389672", 190000, account);

  cout << employedClient->salary() << endl;
  Account& acc = employedClient->account();
  acc.credit(700);
  cout << acc.balance() << endl;

  Employee1* e = employedClient;

/*
  Person* p = employedClient;  // Compiler error: cannot convert
                               // EmployedClient* to Person*
*/

/*
  cout << employedClient->name(); // Compiler error: Person::name
                                  // and Person::name ambiguous
*/

  Manager* manager = new Manager("Charles", "5753223678", 300000, "BMW 740");

  Person* p = manager;

  cout << manager->name() << endl;

  char c; cin >> c;

  return 0;
}
\end{verbatim}
