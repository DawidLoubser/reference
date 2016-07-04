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
