#include <stdio.h>

long factorial(long n)
{
  if (n < 0) return -1;  /* error code */
  
  if (n == 0) return 1;
  
  return n * factorial(n-1);  /* recursion (function calls itself) */
}

long factorial2(long n)
{
  if (n < 0) return -1;  /* error code */
  
  long product = 1, i;
  
  for (i=n; i>0; --i)
    product *= i;
  
  return product;  
}

int main(int argc, char *argv[])
{
  long n;
  printf("Enter n: "); scanf("%ld", &n);
  
  printf("n! = %ld\n", factorial(n));
  
  return 0;
}
