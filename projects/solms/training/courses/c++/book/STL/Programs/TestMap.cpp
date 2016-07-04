\begin{verbatim}
#include <iostream>
#include <map>
#include <set>
#include <string>

#include <stdlib.h>

#include "Account.h"
#include "Person.h"

using SolmsTraining::finance::Account;

using namespace std;

struct PersonComparator
{
  bool operator()(const Person * const p1, const Person * p2)
  {
    return p1->name() < p2->name();
  }
};

int main()
{
  Person* p = new Person("Jacky de Lill", "6142354");

  set<Person*, PersonComparator> persons;
  persons.insert(new Person("Jack Hill", "6541654"));
  persons.insert(p);
  persons.insert(new Person("Jaco Mill", "6323254"));

  map<Person*, Account*> clientsAccounts;

  int accNo = 101;
  for (set<Person*, PersonComparator>::iterator iter = persons.begin();
       iter != persons.end(); ++iter)
  {
    const string accNo("Acc:");
    cout << "X" << accNo << endl;
    clientsAccounts[*iter] = new Account(accNo, 1000);
  }

  clientsAccounts[p]->credit(2000);

  for (set<Person*, PersonComparator>::iterator iter = persons.begin();
       iter != persons.end(); ++iter)
          cout << "bal: " << clientsAccounts[*iter]->balance() << endl;

  for (map<Person*, Account*>::iterator iter = clientsAccounts.begin();
       iter != clientsAccounts.end(); ++iter)
  {
    Person* p = iter->first;
    Account* acc = iter->second;
    cout << *p << "  =>  " << *acc <<  endl;
  }
  return 0;
}
\end{verbatim}
