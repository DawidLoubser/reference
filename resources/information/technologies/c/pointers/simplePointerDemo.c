#include <stdio.h>

int main(int argc, char *argv[])
{
  int k = 3;
  
  int *pk = &k; /* Declares a pointer and initializes */
               /* it to point to the address of k.    */
              
  printf("pointer value, pk= %x\n", pk);
  printf("derefencing the pointer: *pk=%d\n", *pk);
  
  *pk = 5;
  printf("after *k=5: k=%d\n", k);
  
  return 0;
}
