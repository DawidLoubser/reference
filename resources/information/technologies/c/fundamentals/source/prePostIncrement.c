#include <stdio.h> 

int main(int argc, char *argv[])  
{
  int k = 3;
  
  printf("initial value of k: %d\n", k);
  
  int l = ++k;
  
  printf("l=++k;  ->  k=%d, l=%d\n", k, l);
  
  k = 3;
  l = k++;
  printf("l=k++;  ->  k=%d, l=%d\n", k, l);
  
  return 0;
}
