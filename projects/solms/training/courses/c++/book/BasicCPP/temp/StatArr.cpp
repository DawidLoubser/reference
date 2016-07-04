\begin{verbatim}
//                    STATARR.CPP
//------------------------------------------------------
//                  Standard arrays

#include <iostream.h>

void main()
{
  const int nrows = 3;
  const int ncols = 3;

  double M[nrows][ncols];

  cout << "Enter (" << nrows << "x" << ncols << ") matrix M:" << endl;

  for (int nr=0; nr<nrows; nr++)
  {
    for (int nc=0; nc<ncols; nc++)
    {
      cout << "M[" << nr+1 << "," << nc+1 << "] = ";
      cin >> M[nr][nc];
    }
  }
  cout << "sizeof(M) = " << sizeof(M) << endl;
      // returns size of 9 double precision
      // floating point variables.
}
\end{verbatim}
