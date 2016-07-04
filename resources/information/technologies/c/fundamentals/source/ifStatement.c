#include <stdio.h>

int main(int argc, char *argv[])
{
  int k;
  
  printf("Enter k: ");
  scanf("%d", &k);
  
  if (k == 3)
    {
      printf("k is equal to 3.\n");
      ++k;
    }
  else    
    printf("k is not equal to 3.\n");
    
  printf("Value of k after if: %d\n", k);  

  double x = -0.2;
  
  if (x)
    printf("if clause evaluated to true, clause value = %f\n", x);
    
  x = 12.3;
  printf("%8.4f\n", x);
    
  return 0;
}
