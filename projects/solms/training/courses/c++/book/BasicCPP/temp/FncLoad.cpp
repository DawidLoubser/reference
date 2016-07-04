\begin{verbatim}
#include <iostream.h>
                 
template <class T>
T Max(const T& x, const T& y)
{
  if (x > y)
    return x;
  else
    return y;
}

template <class T>
T Max(const T* const vec, const int length)
{
  T largest = vec[0];
  for (int i=1; i<length; i++)
    if (vec[i] > largest)
      largest = vec[i];
  return largest;
}

void main()
{
  double a=5.1, b=1.23;
  cout << "Max(a,b) = " << Max(a,b) << endl;

  int v[5];
  v[0]=9; v[1]=2; v[2]=11; v[3]=7;

  cout << "Max(v,4) = " << Max(v,4) << endl;
}
\end{verbatim}
