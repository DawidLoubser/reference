\begin{verbatim}
//                NOMACRO.CPP
//-------------------------------------
#include <iostream.h>

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

void main()
{
  cout << "sqr(k) = " << sqr(2+1) << endl;

  double a=2.1, b=1.7;
  cout << "max(a,b) = " << max(a,b) << endl;
}
\end{verbatim}
