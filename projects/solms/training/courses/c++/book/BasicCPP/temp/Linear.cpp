\begin{verbatim}
//                    LINEAR.CPP
//---------------------------------------------------------------
// Asks for the number of data points and then uses least-squares
// to fit the "best" straight line through these points

#include <iostream.h>

void ReadDataPoints (double*& x, double*& y, int& nodatapoints)
{
  cout << "Enter number of data points : ";  cin  >> nodatapoints;

  x = new double[nodatapoints];
  y = new double[nodatapoints];

  for (int i=0; i<nodatapoints; i++)
  {
    cout << "x[" << i+1 << "]  y[" << i+1 << "]  =  ";
    cin  >> x[i] >> y[i];
  }
}

//---------------------------------------------------------------

void LinearRegression (double x[], double y[], const int nodatapoints,
                       double& slope, double& y_intercept)
{
  double sum_x=0, sum_x2=0, sum_y=0, sum_xy=0;

  for (int i=0; i<nodatapoints; i++)
  {
    sum_x  += x[i];
    sum_y  += y[i];
    sum_x2 += x[i]*x[i];
    sum_xy += x[i]*y[i];
  }

  slope = (sum_x*sum_y - nodatapoints*sum_xy)
          / (sum_x*sum_x - nodatapoints*sum_x2);

  y_intercept = (sum_y - slope*sum_x) / nodatapoints;
}

//---------------------------------------------------------------

void main()
{
  int nodatapoints;
  double *x, *y;

  ReadDataPoints (x, y, nodatapoints);

  double slope, y_intercept;

  LinearRegression (x, y, nodatapoints, slope, y_intercept);

  cout << "Linear regression results: " << endl;
  cout << "y(x) = " << slope << "x + " << y_intercept << endl;

  delete[] x;
  delete[] y;
}

\end{verbatim}
