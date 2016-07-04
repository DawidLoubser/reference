#include <stdio.h>
#include <malloc.h>

int main(int argc, char* argv[])
{
  int numElements = 20;
  
  double* vec = (double*)malloc(numElements*sizeof(double));
  
  double* iter = vec;
  
  printf("vec:\n");
  int i;
  for (i=0; i<numElements; ++i)
  {
    *iter = i; /* set value of element to which pointer points */ 
    printf("%f ", *iter);
    ++iter;    /* adding sizeof(double) to address of pointer */
  }
  printf("\n\nvec = %d\n", vec);

  printf("\niter = %d\n", iter);
  
  printf("vec - iter = %d\n", vec-iter);
  
  free(vec);
  
  return 0;
}
