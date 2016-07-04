\begin{verbatim}
//                    TOPOLAR.CPP
//-------------------------------------------------------------
//       Converts rectangular to polar coordinates

#include <iostream.h>   // for cout and cin
#include <stdlib.h>     // for exit()
#include <math.h>       // for sin, acos, sqrt and atof

//--------------------------------------------------------------

void ToPolar (const double x, const double y, const double z,
                    double& r, double& theta, double& phi)
{
       // x, y and z do not changed and are sent as a variable
       // (a local copy is made)
       // r, theta and phi are to be changed and are hence sent
       // via reference (the same memory area is used as in the
       // calling program)

  r = sqrt(x*x+y*y+z*z);

  if (r != 0)
    theta = acos(z/r);
  else
    theta = 0;

  if ((theta != 0) && (r != 0))
    phi   = acos(x/(r*sin(theta)));
  else
    phi = 0;
}

//--------------------------------------------------------------

void ReadCoordinates(double& x, double& y, double& z)
{
  cout << "Enter x coordinate : ";  cin >> x;
  cout << "Enter y coordinate : ";  cin >> y;
  cout << "Enter z coordinate : ";  cin >> z;
}

void main(int argc, char* argv[])
{
        // argc holds the no of command line parameters and
        // the array of strings argv[] holds the command line
        // parameters itself. The name of the program is
        // passed in argv[0]

  double x, y, z;

  cout << "::" << argv[0] << "::" << endl;

  switch (argc-1)
  {
    case 0:  ReadCoordinates(x, y, z);  // when no command line
             break;                     // parameters, read x,y,z

    case 3:  x = atof(argv[1]);  // converts first string to double
             y = atof(argv[2]);
             z = atof(argv[3]);
             break;

    default: cout << "*** ERROR *** : invalid number of command "
                  << "line parameters." << endl;
             exit(0);
  }

  double r, theta, phi;

  ToPolar(x,y,z,r,theta,phi);  // calculate polar coordinates

  cout << "(" << x << "," << y << "," << z << ")  => " << r
       << " r  + " << theta << " theta + " << phi << " phi." << endl;
}
\end{verbatim}
