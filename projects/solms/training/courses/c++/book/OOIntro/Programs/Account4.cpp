\begin{verbatim}
#include <iostream>

using namespace std;

class Account
{
  public:
    Account(): _balance(50) {}

    Account(double balance)
    {
      _balance = balance;
    }

    ~Account()
    {
      cout << "I, " << this << ", am destroyed." << endl;
    }

    void credit(double amount) {_balance += amount;}
    void debit(double amount) {_balance -= amount;}
    double balance() {return _balance;}

  private:
    double _balance;
};

void f()
{
  cout << "Entered f()." << endl;
  Account account;
  account.credit(1e6);
  cout << "About to leave f()" << endl;
}

int main()
{
  f();

  Account acc1;
  cout << "Created acc1, entering block." << endl;
  {
    Account acc2;
    cout << "Created acc2 in block,leaving block." << endl;
  }

  char c; cin >> c;
  
  return 0;
}
\end{verbatim}
