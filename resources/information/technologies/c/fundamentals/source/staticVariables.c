#include <stdio.h>

void testStatic()
{
  int i=0;
  static int j=0;
  printf("i, j = %d, %d\n", i, j);
  ++i;
  ++j;
}

int main(int argc, char *argv[])
{
  int k;
  for (k=0; k<10; ++k)
    testStatic();

  return 0;
}
