\begin{verbatim}
#include <iostream.h>
#include <math.h>

double root(const double& x, const int n=2)
{
  return pow(x,(1.0/n));
 }
 
 void main ()
 {
   cout << "root(3.0)    = " <<  root(3.0)   << endl; 
   cout << "root(3.0,3) = " << root(3.0,3) << endl;
 }
\end{verbatim}
