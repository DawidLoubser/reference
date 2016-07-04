#include "integrationRoutines.h"

#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>


double myFunc(double x)
{
  return 2.3*sin(x/5) + x*x;
}

int main(int argc, char *argv[])
{
  printf("Simpson integration:\n");
  
  int numIntervals;
  printf("number of intervals = "); scanf("%d", &numIntervals);
  
  double a, b;
  printf("integration boundaries: a, b = ");
  scanf("%lf %lf", &a, &b);
  
  double integral_a_b = simpsonIntegrate(myFunc, a, b, numIntervals);
  
  printf("Integral_%f^%f f(x) dx = %f\n", a, b, integral_a_b);
  
  return 0;
}
