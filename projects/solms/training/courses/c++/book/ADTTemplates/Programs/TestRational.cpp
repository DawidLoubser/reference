\begin{verbatim}
// FILE: TestRationl.CPP

#include <iostream>

#include "ErrHandl.h"

#include "Rational.h"
#include "Rational.cpp"

using namespace std;

int main()
{
  Rational<long> r1(3,9);      cout << "r1 = " << r1 << endl;
  Rational<long> r2(13,2);     cout << "r2 = " << r2 << endl;

  // Now using default constructor, addition and assigment operators
  Rational<long> r3 = r1 + r2;
  cout << "r3 = r1+r2 = " << r3 << endl; // output stream operator
  cout << "r1*r2 = " << r1*r2 << endl; // multiplying 2 rational nos
  cout << "r1/r2 = " << r1/r2 << endl;

  cout << "-r1 = " << -r1 << endl;               // unary minus
  cout << "r1>r2  -> " << (r1>r2)  << endl;      // relational operators
  cout << "r1<=r2 -> " << (r1<=r2) << endl;

  double x = r1;     // using type conversion operator (implicitely)
  cout.precision(15);
  cout << "x = " << x << endl;

  cout << "r1 + 2 = " << (r1 + 2.1) << endl; // using member operator
  cout << "2 + r1 = " << ((long)2 + r1) << endl; // using global operator
  cout << "r1++ = " << (r1++) << endl;     // post-incrementing
  cout << "r1 = " << r1 << endl;
  cout << "++r1 = " << (++r1) << endl;     // pre-incrementing
  cout << "r1^5  = " << r1.power((long)3) << endl;  // power method
  cout << "r1^-0.5 = " << r1.power((double)-0.5) << endl;

  cout << "r1 < 2.3 = " << (r1<2.3) << endl;

  Rational<long> r4 = -17;
  cout << "fabs(" << r4 << ") = " << fabs(r4) << endl;

  cout << "Type in rational number r=n/m: n m = ";
  try
  {
    cin >> r1;
    cout << "Read in rational number, r1 = " << r1 << endl;
  }
  catch (DivideByZero error)
  {
    cout << "*** ERROR ***: " << error.source << endl;
  }
  char k; cin >> k;
  
  return 0;
}
\end{verbatim}
