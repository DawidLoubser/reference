#include "integrationRoutines.h"

#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

static double simpsonResult(double f_xMin, double f_xMax, 
                            double sumEven, double sumOdd, double stepSize)
{
  return (f_xMin + f_xMax + 2*sumEven + 4*sumOdd)*stepSize/3;
}  

double simpsonIntegrate(double (*f)(double x),
                        const double a, const double b, 
                        const int numIntervals)
{
  double dx = (b-a)/numIntervals;
  double x = a+dx;
  
  double sumEven = 0, sumOdd = f(x);
  
  int n;
  for (n=2; n<numIntervals; n+=2)
  {
    x += dx;
    sumEven += f(x);
    x += dx;
    sumOdd += f(x);
  }
  return simpsonResult(f(a),f(b),sumEven,sumOdd,dx);
}
