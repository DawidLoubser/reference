#include <math.h>
#include <stdio.h>

double myFunc(double x)
{
  return 3*x + sqrt(x);
}  

int main(int argc, char *argv[])
{
  double (*f)(double x); /* declaring a function pointer varable, f */
  
  double x = 2;
  
  f = myFunc;
  double y = f(x);
  
  printf("f(%f) = %f\n", x, y);
  
  f = sqrt;
  y = f(x);
  
  printf("f(%f) = %f\n", x, y);
  
  return 0;
}
