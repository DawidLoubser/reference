#include <stdio.h>
#include <stdlib.h>

double average(double vec[], int length)
{
  double sum = 0;
  int i;
  for (i=0; i<length; ++i)
    sum += vec[i];
  return sum/length;
}    

int main(int argc, char* argv[])
{
  double vec[argc-1]; /* the first command-line argument
                     is the application itself */
  int i; 
  for (i=1; i<argc; ++i)
    vec[i-1] = atof(argv[i]);  /* Converting text arguments to double */
  
  double avg = average(vec, argc-1);  
  
  printf("average: %f\n", avg);
    
  return 0;  
}
