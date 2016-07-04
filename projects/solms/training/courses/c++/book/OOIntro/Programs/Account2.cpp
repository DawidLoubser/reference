\begin{verbatim}
class Account
{
  public:
    Account(): _balance(50) {}

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
  Account acc1;
  Account acc2(200);

  cout << "acc1 balance = " << acc1.balance() << endl;
  cout << "acc2 balance = " << acc2.balance() << endl;

  acc1.credit(400);
  acc1.debit(123.45);

  acc2.debit(456);

  cout << "balance = " << acc1.balance() << endl;
  cout << "acc2 balance = " << acc2.balance() << endl;

  char c; cin >> c;

  return 0;
}
\end{verbatim}
