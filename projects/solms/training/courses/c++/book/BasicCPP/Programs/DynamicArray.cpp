\begin{verbatim}
//                   DynamicArray.cpp
//---------------------------------------------------------
//                Dynamic Memory Arrays

#include <iostream>

using namespace std;

int main()
{
  int nrows, ncols;

  cout << "Enter number of rows : ";
  cin >> nrows;
  cout << "Enter number of columns : ";
  cin >> ncols;

  double **M = NULL;  // it is usually a good idea to initialize
                      // pointers to NULL.

  int nr, nc;

  M = new double*[nrows];
  for (nr=0; nr<nrows; nr++)
    M[nr] = new double[ncols];

  cout << "Now enter the elements of the (" << nrows << "x"
       << ncols << ") matrix M :" << endl;

  for (nr=0; nr<nrows; nr++)
  {
    for (nc=0; nc<ncols; nc++)
    {
      cout << "M[" << (nr+1) << "," << (nc+1) << "] = ";
      cin >> M[nr][nc];
    }
  }

  cout << "sizeof(M)    = " << sizeof(M)
       << endl;  // returns size of a pointer variable
  cout << "sizeof(M[1]) = " << sizeof(M[1])
       << endl;  // returns size of a pointer variable
  cout << "sizeof(M[1][1]) = " << sizeof(M[1][1])
       << endl;  // returns size of a double precision
                 // floating point variable

  for (nr=0; nr<nrows; nr++)
    delete[] M[nr];

  delete[] M;

  char k; cin >> k;
  
  return 0;
}
\end{verbatim}
