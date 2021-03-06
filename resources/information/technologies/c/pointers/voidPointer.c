#include <stdio.h>

int main (int argc, char *argv[])
{
  int k = 3;
  float f = 3;
  
  printf("k = %d\n", k);
  printf("f = %f\n", f);
  
  void *void_p = &k;
  
  printf("after setting void_p = &k:\n");
  int intValue = *((int*)void_p); /* dereferencing void pointer  */
                                  /* after casting it to an int* */
  printf("*((int*)void_p) = %x\n", intValue);
  float floatValue = *((float*)void_p); /* dereferencing void pointer   */
                                       /* after casting it to a float* */  
  printf("*((float*)void_p) = %f\n", floatValue);  
  
  void_p = &f;
  
  printf("after setting void_p = &f:\n");
  printf("*((int*)void_p) = %d\n", *((int*)void_p));
  printf("*((float*)void_p) = %f\n", *((float*)void_p));  
  
  return 0;
}
