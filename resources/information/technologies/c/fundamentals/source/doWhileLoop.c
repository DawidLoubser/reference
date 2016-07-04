#include <stdio.h>

int main(int argc, char *argv[])
{
  int n0, n;
  printf("Enter initial loop index: ");
  scanf("%d", &n0);
  printf("Enter final loop index: ");
  scanf("%d", &n);
  
  printf("while loop:\n");
  int i=n0;
  while (i<=n)
  {
    printf("%d", i);
    ++i;
  }
  printf("\n");
  
  printf("do-while loop:\n");
  i=n0;
  do
  {
    printf("%d", i);
    ++i;
  } while (i<=n);
  printf("\n");
    
  return 0;
}
