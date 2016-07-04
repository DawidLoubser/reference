#include <stdio.h>

int main(int argc, char *argv[])
{
  double vec[3];
  
  vec[0] = 1.2;
  vec[1] = 2.3;
  vec[2] = vec[0] + vec[1];
  
  int n;
  n = (int)vec[2];
  double vec2[n];
  
  int i;
  for (i=0; i<n; ++i)
    vec2[i] = 0;
  
  return 0;
}
