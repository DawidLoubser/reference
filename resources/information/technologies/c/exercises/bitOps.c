#include <stdio.h>

int main(int argc, char *argv[])
{
  int k = 3;               /* 0000 0011 */
  int l = 13;              /* 0000 1101 */
  int m = 128;             /* 1000 0000 */
  int n = (k << 2) & l;    /* (0000 1100) & (0000 1101) = 0000 1100 = 12 */
  printf("%d\n", n);
  printf("%d\n", ~k);
  n = ((k >> 1) ^ l) | m;  /* 1000 1100 = 140 */
  printf("%d\n", n);
  
  return 0;
}
