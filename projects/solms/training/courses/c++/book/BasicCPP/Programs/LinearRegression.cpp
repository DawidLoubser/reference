\begin{verbatim}
//                 LinearRegression.cpp
//---------------------------------------------------------------
// Asks for a collection of 2-dimensional data points
// and then fits the "best" straight line through these
// data points using the linear regression algorithm

#include <iostream>

using namespace std;

void readDataPoints(double*& x, double*& y, int& numDataPoints)
{
  cout << "Enter number of data points: ";  cin >> numDataPoints;

  x = new double[numDataPoints];
  y = new double[numDataPoints];

  for (int i=0; i<numDataPoints; ++i)
  {
    cout << "x[" << (i+1) << "] y[" << (i+1) << "] = ";
    cin >> x[i] >> y[i];
  }
}

//----------------------------------------------------------------

void linearRegression (double x[], double y[], const int numDataPoints,
                       double& slope, double& y_intercept)
{
  double sum_x=0, sum_x2=0, sum_y=0, sum_xy=0;

  for (int i=0; i<numDataPoints; ++i)
  {
    sum_x  += x[i];
    sum_y  += y[i];
    sum_x2 += x[i]*x[i];
    sum_xy += x[i]*y[i];
  }

  slope = (sum_x*sum_y - numDataPoints*sum_xy)
             / (sum_x*sum_x - numDataPoints*sum_x2);

  y_intercept = (sum_y - slope*sum_x) / numDataPoints;
}

//----------------------------------------------------------------

int main()
{
  int numDataPoints;

  double *x, *y;

  readDataPoints(x, y, numDataPoints);

  double slope, y_intercept;

  linearRegression(x, y, numDataPoints, slope, y_intercept);

  char* sign = " + ";
  if (y_intercept < 0)
    sign = " - ";

  cout << "Straight line fit obtained from linear regression: "
        << " y = " << slope << "x " << sign << y_intercept << endl;

  delete[] x;
  delete[] y;

  char k; cin >> k;
  
  return 0;
}
\end{verbatim}
