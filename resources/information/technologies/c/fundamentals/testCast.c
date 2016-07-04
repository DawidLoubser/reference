#include <stdio.h> 

int main(int argc, char *argv[])  
{
  double x = 2/3;
  printf("x = %f\n", x);
  
  double y = (double)2/3;
  printf("y = %f\n", y);
  
  int k=2,l=3;
  float result = k/(float)l;  /* explicit up-cast*/
  printf("%d/%d = %f\n", k, l, result);
  
  float kFloat = k;           /* implicit up-cast */
  result = kFloat/l;
  printf("%d/%d = %f\n", k, l, result);
  
  result = (int)result;       /* explicit down-cast */
  printf("After down-casting to an integer: %f\n", result);
  
  result = (float)2/3;
  int resultInt = result;
  printf("After down-casting to an integer: %f\n", resultInt);
  
  return 0;
}
