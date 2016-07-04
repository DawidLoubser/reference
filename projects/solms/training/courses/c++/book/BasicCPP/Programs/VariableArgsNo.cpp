\begin{verbatim}
#include <stdarg.h>
#include <iostream>

using namespace std;

double sum(int argCount, ...)  // header for variable argument list
{
  va_list ap;
  double result = 0;
  va_start(ap, argCount);

  while (argCount-- > 0)
    result += va_arg(ap, double);

  va_end(ap);

  return result;
}

int main()
{
  double s = sum(2, 1.2,2.1);
  cout << "sum = " << s << endl;

  s = sum(5, 1.3,2.1,3.3,2.1,2.2);
  cout << "sum = " << s << endl;

  char c; cin >> c;
  
  return 0;
}
\end{verbatim}
