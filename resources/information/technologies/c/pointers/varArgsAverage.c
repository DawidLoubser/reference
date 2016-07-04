#include <stdio.h>
#include <stdarg.h>

double average(int numArgs, ...);

int main()
{
  printf("average(2, 1.1, 2.2) = %f\n", average(2, 1.1, 2.2));
  
  printf("average(5, 1.1, 1.5, 2.0, 2.1, 7.3) = %f\n", 
        average(5, 1.1, 1.5, 2.0, 2.1, 7.3));
  
  return 0;
}

double average(int numArgs, ...)
{
  va_list arg_ptr;            /* declare a variable of type */
                              /* arguments-list pointer */
  
  va_start(arg_ptr, numArgs); /* initialize arguments pointer */

  double sum = 0;
  int i;
  for (i=0; i<numArgs; ++i)
    sum += va_arg(arg_ptr, double);
  
  va_end(arg_ptr);            /* request clean-up. */
  
  return sum/numArgs;
}
