#include <stdio.h>

int main(int argc, char *argv[])
{
  int i,j,k;

  for (i=0,j=0,k=3; ((i<=k) && (k!=j)); ++i, k-=j, printf("%d\n", j))
  {
    j += i%2;
    printf("%d,%d,%d\n", i, j, k);
  }
  return 0;
}
