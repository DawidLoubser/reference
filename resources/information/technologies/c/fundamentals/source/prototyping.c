#include <stdio.h>

long factorial(long n);

int main(int argc, char *argv[])
{
  long n;
  printf("Enter n: "); scanf("%ld", &n);
  
  printf("n! = %ld\n", factorial(n));
  
  return 0;
}

long factorial(long n)
{
  if (n < 0) return -1;  /* error code */
  
  if (n == 0) return 1;
  
  return n * factorial(n-1);  /* recursion (function calls itself) */
}
