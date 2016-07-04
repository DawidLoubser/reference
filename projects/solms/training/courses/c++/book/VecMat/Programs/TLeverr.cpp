\begin{verbatim}
//************************************************************
//      Speed Testing Programm using Leverrier's method
//      ===============================================
#include <iostream>
#include <stdlib.h>
#include <time.h>

#include "Vector.h"
#include "Matrix.h"

using namespace std;

int main()
{
  int nd;

  cout << "Leverrier method for randm matrix:";
  cout << "Dimension of matrix = "; cin  >> nd;

  time_t t1, t2;
  t1 = time(NULL);

  Matrix<double> A(nd,nd), B(nd,nd);
  Vector<double> c(nd);
  double determ;

  A.random();   cout<<A;

  cout << endl << "starting ..." << endl;
  Leverr(A, c, B, determ);
  t2 = time(NULL);

  cout << "Inverse = " << endl << B;
  cout << "Coefficients of characteristic polynimial = " << c << endl;
  cout << "Determinant = " << determ << endl;
  cout << "Time required = " << difftime(t2,t1) << " seconds" << endl;
  
  return 0;
}
\end{verbatim}
