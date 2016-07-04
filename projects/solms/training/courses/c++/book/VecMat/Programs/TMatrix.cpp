\begin{verbatim}
#include <iostream>

#include "Matrix.h"
#include "Vector.h"
#include "Rational.h"

#include "Vector.cpp"
#include "Matrix.cpp"
#include "Rational.cpp"

using namespace std;

int main()
{
  try
  {
    Matrix<double> A(4,4);
    A.random();
    Matrix<double> B = A;
    B.setoutputwidth(6); B.setoutputprecision(2);
    cout << "B = " << endl << B;

    Vector<double> v1(4); v1.fill(1.7);
    B.insertrow(1,v1);
    cout << "Inserted row 1: B = " << endl << B;
    B.removecol(2);
    cout << "Removed column 2: B = " << endl << B;
    B.swaprows(0,3);
    cout << "swapped rows 0 and 3: B = " << endl << B;
    A.fill(2);
    A=B.transpose();
    Matrix<double> I1(2,2); I1.setoutputwidth(3); I1.setoutputprecision(2);
    I1.random(8,-2);
    cout << "I1 = " << endl << I1;
    Matrix<double> I2=200.0 + 2.0*I1 / 10;
    cout << "I2 = " << endl << I2 << endl;

    Vector<double> v2(7);
    v1.random(); v2.random();
    cout << "v1 = " << v1 << endl;
    cout << "v2 = " << v2 << endl;
    cout << "Kronecker product: v1%v2 = " << endl << v1%v2;

    Rational<long> r(1,3);
    Matrix<Rational<long> > Mr1(3,3);
    for (long nr=0; nr<3; nr++)
      for (long nc=0; nc<3; nc++)
        Mr1[nr][nc] = r+(nr+1L)*Rational<long>(5,nc+1);
    cout << "Mr1 = " << endl << Mr1;
    Matrix<Rational<long> > Mr2 = Mr1*Mr1-Mr1/2;
    cout << "Mr2 = Mr1*Mr1-Mr1/2 = " << endl << Mr2;

  }
  catch (Exception error)
  {
    cout << "*** ERROR ***: " << error.source << endl;
  }
  return 0;
}
\end{verbatim}
