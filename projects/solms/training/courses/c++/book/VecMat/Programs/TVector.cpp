\begin{verbatim}
#include <iostream>
#include <math.h>

#include "Vector.h"
#include "Rational.h"

#include "Vector.cpp"
#include "Rational.cpp"

using namespace std;

int main()
{
  Vector<double> v1(4);
  v1[0]=-1;  v1[1]=-4;  v1[2]=3; v1[3]=0.1;
          // using element access operator
  cout << "v1 = " << v1 << endl;  // using output stream operator

  Vector<double> v2 = v1.func(exp);
  v2.setoutputprecision(8); v2.setoutputwidth(10);
  cout << "v2 = exp(v1) = " << v2 << endl;

  Vector<double> v3 = v1 + v2;     // vector addition and assignment
  cout << "v1+v2 = " << v3 << endl;
  cout << "v1*v2 = " << v1*v2 << endl;   // dot product
  cout << "v1*2  = " << v1*2 << endl;     // using member function
  cout << "2.0-v1  = " << 2.0-v1 << endl;     // using global function

  cout << "v1.insert(v2,2)" << v1.insert(v2,2) << endl;

  cout << v1.maxel() << " " << v1.minel() << " "
       << v1.maxabsel() << " " << v1.minabsel() << endl;

  v1.normalize();
  cout << "v1 = " << v1 << " => " << v1.norm() << endl;

  v1.unit(2);
  cout << "v1.unit(2) = e3 = " << v1 << endl;

  Vector<int> v4(20);
  for (int i=0; i<20; i++)
    v4[i] = i;
  cout << "v4 = " << v4 << endl;
  cout << "v4.slice(5,6) = " << v4.slice(5,6) << endl;
  cout << "v4.slice(3,4,2) = " << v4.slice(3,4,2) << endl;

  cout << "-v4 = " << -v4 << endl;  // unary minus

  Vector<Rational<long> > vr1(3), vr2(3);
  vr1[0] = Rational<long>(1,1);    vr2[0] = Rational<long>(17,2);
  vr1[1] = Rational<long>(1,2);    vr2[1] = Rational<long>(-21,4);
  vr1[2] = Rational<long>(1,3);    vr2[2] = Rational<long>(3,7);

  vr1.setoutputwidth(4);  vr2.setoutputwidth(4);
  cout << "vr1 =" << vr1 << "     vr2=" << vr2 << endl;
  cout << "(2*vr1+vr2)*vr1 = " << (*(new Rational<long>(2))*vr1+vr2)*vr1
       << "  (dot product)" << endl;

  try
  {
    Vector<int> v5(-1);
  }
  catch (Exception error)
  {
    cout << "*** ERROR ***: " << error.source << endl;
  }

  v1.random(29);
  cout << "v1 = " << v1 << endl;
  cout << "Enter vector v1: ";  cin >> v1;
  cout << "v1 = " << v1 << endl;

  Vector<double> vlong(100);
  vlong.random();
  cout << "long vector before sorting: " << vlong << endl;
  vlong.quickSort();
  cout << "long vector after sorting: " << vlong << endl;

  return 0;
}
\end{verbatim}
