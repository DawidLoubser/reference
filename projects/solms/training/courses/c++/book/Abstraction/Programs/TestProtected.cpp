\begin{verbatim}
#include <iostream>

using namespace std;

class A
{
  public:
    A() : secretCode(1234) {}

  protected:
    int secretCode;

  friend ostream& operator<< (ostream& os, const A& a);
};

ostream& operator<< (ostream& os, const A& a)
{
  os << "I am an A. My secret is " << a.secretCode;

  return os;
}

class B: public A
{
  public:
    int& publishAsSecret()
    {
      return secretCode;
    }
};

int main()
{
  B* b = new B();
  A* a = b;

  cout << "a = " << (*a) << endl;

  int& theSecret = b->publishAsSecret();
  theSecret = 666;

  cout << "a = " << (*a) << endl;

  char c; cin >> c;
  
  return 0;
}
\end{verbatim}
