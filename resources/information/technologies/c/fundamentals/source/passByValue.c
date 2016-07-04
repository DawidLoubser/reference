#include <stdio.h>

void f(int a, int b)
{
  printf("a, b at start of f = %d,%d\n", a, b);
  a = 2;
  b = 3;
  printf("a, b at end of f = %d,%d\n", a, b);
}  

int main(int argc, char *argv[])
{
  int a=1, b=1;
  printf("a, b in main before calling f = %d,%d\n", a, b);
  f(a,b);
  printf("a, b in main after calling f = %d,%d\n", a, b);
  return 0;
}
