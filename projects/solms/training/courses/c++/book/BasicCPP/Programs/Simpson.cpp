\begin{verbatim}
#include <iostream>
#include <math.h>

using namespace std;

double f(const double& x) {return exp(x*x);}

double Simpson (double (*func)(const double&),
                const double& a, const double& b, const int nintvl)
{
  double dx = (b-a)/nintvl;
  double x  = a+dx;

  double sumeven = 0;
  double sumodd  = func(x);

  for (int n=2; n<nintvl; n=n+2)
  {
    x += dx;
    sumeven += func(x);
    x += dx;
    sumodd += func(x);
  }
  return (func(a) + func(b) + 2*sumeven + 4*sumodd)*dx/3;
}

int main()
{
  int nintvl;
  double a, b;

  cout << "Enter no of intervals = ";            cin  >> nintvl;
  cout << "Enter integration range a b : ";    cin  >> a >> b;

  cout << "Simpson: "   << Simpson(f,a,b,nintvl) << endl;

  char k; cin>>k;
  
  return 0;
}
\end{verbatim}
