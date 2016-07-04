#include <stdio.h>

void printAverage(float a, float b)
{
  printf("The average is %f\n", (a+b)/2);
}  

int main(int argc, char *argv[])
{
  float a, b;
  printf("Enter a b: ");
  scanf("%f %f", &a, &b);
  
  printAverage(a, b);
  
  return 0;
}
