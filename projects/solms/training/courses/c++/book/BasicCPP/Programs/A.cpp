\begin{verbatim}
#include "A.h"
#include <iostream>

using namespace std;

void A::f()
{
  cout << "I am a's f()" << endl;
}  

void f(A& a)
{
  cout << "Hi there: a's k =" << a.k << endl;
}

\end{verbatim}
