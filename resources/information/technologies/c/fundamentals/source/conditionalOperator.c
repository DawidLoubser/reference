#include <stdio.h>

int main(int argc, char *argv[])
{
  int a, b, max;
  printf("Enter a b: ");
  scanf("%d %d", &a, &b);
  
  max = (a>b) ? a : b;
  
  printf("max = %d\n", max);
  
  return 0;
}
