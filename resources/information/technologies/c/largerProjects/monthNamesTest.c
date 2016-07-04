#include "monthNames.h"

int main(char * args[])
{
  int n = 1;
  printf("Enter month no: ");
  scanf("%d", &n);
  char* monthName = monthNames[n-1];
  printf("Month no %d is %s\n", n, monthName);
}
