\begin{verbatim}
#include <iostream>

using namespace std;

const float version = 2.11;

double y;

double f(const double x)
{
  double y = x*x;
  return y;
}

int main()
{
  double a;
  cout << "a = ";  cin >> a;
  y = f(a);

  if (y > 5)
  {
    double y = a*a - a;
    cout << "y = " << y << endl;
  }
  cout << "y = " << y << endl;

  char k; cin >> k;
  
  return 0;
}
\end{verbatim}
