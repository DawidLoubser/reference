\begin{verbatim}
// #define NDEBUG   // to comment out assert() statements for
                    // bound checking etc.

#include "Vector.h"
#include "Matrix.h"

//**************************************************************
template <class T>
void Leverr(const Matrix<T>& A, Vector<T>& c, Matrix<T>& B, T& determ)

// Leverrier's Method for obtaining the characteristic polynomial
// of a matrix A, its inverse B and its determinant determ.
// Characteristic polynomial P(e) has as roots eigenvalues of A:
// P(e) = e^n - c[0]*e^(n-1) - c[1]*e^(n-1) - ... - c[n-1]*e - c[n-1]
{
  if ((A.rows() != A.cols()) || (A.rows() != c.length())
   || (A.rows() != B.rows()) || (B.rows() != B.cols()))
      throw IllegalArguments();

  unsigned n = c.length();

  Matrix<T> BI(n,n);
  Matrix<T> D(n,n);
  Matrix<T> ID(n,n);
  ID.unit();

  B = A;
  c[0] = A.trace();

  char cdum;

  for (unsigned k=1; k<n; k++)
  {
    D    = A*(B - c[k-1]);  // A-x -> A - ID*x
    c[k] = D.trace()/(k+1);
    if (k == n-2)
      BI = D;
    B = D;
  }
  determ = -c[n-1];

  if (determ != 0.0)
    B = (ID*c[n-2] - BI)/determ;
  else
    B.fill(0.0);
}

\end{verbatim}
