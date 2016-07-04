\begin{verbatim}
#include <iostream.h>
#include <string.h>

void main()
{
  char str[20] = "This is a string";
  char* pchar = str;
  cout << pchar << endl;

  int ivec[5] = {1, 2, 3, 4, 5};
  int* pvec = ivec;
  cout << pvec << endl;
  cout << (*pvec) << endl;
}
\end{verbatim}
