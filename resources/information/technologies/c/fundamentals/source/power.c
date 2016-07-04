#include <stdio.h>

float power(float a, int k)
{
  float result = 1;  int i;
  
  if (k>0)
    for (i=0; i<k; ++i)
      result *= a;
  else
    for (i=k; i<0; ++i)
      result /= a;
  
  return result;
}          

int main(int argc, char *argv[])
{
  float x;
  printf("x = "); scanf("%f", &x);
  
  int n;
  printf("n = "); scanf("%d", &n);
  
  float xPn = power(x, n);
  
  printf("x^n = %f\n", xPn);
  
  return 0;
}
