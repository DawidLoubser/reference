\begin{verbatim}
#include <iostream>

using namespace std;

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

int main()
{
  double a=5.1, b=1.23;
  cout << "a, b = " << a << ", " << b << endl;
  cout << "Max(a,b) = " << Max(a,b) << endl << endl;

  int v[5];
  v[0]=9; v[1]=2; v[2]=11; v[3]=7; v[4]=8;
  cout << "v = [";
  for (int i=0; i<4; ++i)
    cout << v[i] << ", ";
  cout << v[4] << "]" << endl;  

  cout << "Max(v,4) = " << Max(v,4) << endl;

  char k; cin >>k;
  
  return 0;
}
\end{verbatim}
