\begin{verbatim}
class Account
{
  public:
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
  Account acc1;
  cout << "balance = " << acc1.balance() << endl;

  acc1.credit(10000);
  cout << "balance = " << acc1.balance() << endl;

  char c; cin >> c;
  
  return 0;
}
\end{verbatim}
