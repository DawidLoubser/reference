\begin{verbatim}
#include <iostream>
#include <math.h>

using namespace std;

double root(const double& x, const int n=2)
{
  return pow(x,(1.0/n));
}

int main ()
{
  cout << "root(3.0)    = " <<  root(3.0)   << endl;
  cout << "root(3.0,3) = " << root(3.0,3) << endl;

  char c; cin >> c;
  
  return 0;
}
\end{verbatim}
