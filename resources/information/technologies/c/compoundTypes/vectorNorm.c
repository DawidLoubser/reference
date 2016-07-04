#include <math.h>
#include <stdio.h>

float norm(float vec[], int length);

int main(int argc, char *argv[])
{
  int length;
  printf("vector length = "); scanf("%d", &length);
  
  float vec[length];
  
  int i;
  for (i=0; i<length; ++i)
  {
    printf("vec[%d] = ", i);
    scanf("%E", &vec[i]);
  }
  
  float vecNorm = norm(vec, length);  
  
  printf("The norm of the vector is %f\n", vecNorm);
  
  printf("out-of-bound array elements: %f, %f\n", vec[length], vec[length+1]);
  
  vec[length+1] =12.345;
  
  return 0;
}

float norm(float vec[], int length)  
{
  float sum = 0;
  int i;
  for (i=0; i<length; ++i)
    sum += vec[i]*vec[i]; 
  return sqrt(sum);
}
