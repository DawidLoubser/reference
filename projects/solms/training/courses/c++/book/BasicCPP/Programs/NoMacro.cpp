\begin{verbatim}
//                NoMacro.cpp
//-------------------------------------
#include <iostream>

using namespace std;

template <class T>
inline T sqr(const T& x) {return x*x;}

template <class T>
inline T& max(T& x, T& y)
{
  if (x > y)
    return x;
  else
    return y;
}

int main()
{
  cout << "sqr(2+1) = " << sqr(2+1) << endl;

  double a=2.1, b=1.7;
  cout << "max(a,b) = " << max(a,b) << endl;

  char k; cin >> k;
  
  return 0;
}

\end{verbatim}