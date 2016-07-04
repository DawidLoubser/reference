#include "integrationRoutines.h"

#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

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
  return (f(a) + f(b) + 2*sumEven + 4*sumOdd) * dx/3;
}
