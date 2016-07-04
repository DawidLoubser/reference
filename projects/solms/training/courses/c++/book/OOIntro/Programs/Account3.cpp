\begin{verbatim}
class Account
{
  public:
    Account(double balance)
    {
      _balance = balance;
    }

    void credit(double amount) {_balance += amount;}
    void debit(double amount) {_balance -= amount;}
    double balance() {return _balance;}

  private:
    double _balance;
};

#include <iostream>

using namespace std;

int main()
{
  Account acc1;  // CAUSES COMPILER ERROR:
                 // Could not find Account::Account()
  return 0;               
}
\end{verbatim}
