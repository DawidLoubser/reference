#include <stdio.h>

int main(int argc, char *argv[])
{
  int i,j,k;

  i=0;j=0;k=3; 
  while (((i<=k) && (k!=j)))
  {
    j += i%2;
    printf("%d,%d,%d\n", i, j, k);
    ++i; 
    k-=j; 
    printf("%d\n", j);
  }
  return 0;
}
