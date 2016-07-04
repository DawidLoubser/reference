#include <math.h>
#include <stdio.h>
#include <stdlib.h>

const double pi = 3.1415926;

int main(int argc, char *argv[])
{
  if (argc != 3)
  {
    printf("Usage: toPolar xCoordinate yCoordinate\n");
    return -1;
  }
  
  double x = atof(argv[1]);  /* note: argv[0] is the command which */
  double y = atof(argv[2]);  /* launched the application.          */
  
  double radius = sqrt(x*x+y*y);
  
  double theta = asin(x/radius);
  
  double thetaInDegrees = theta * 180 / pi;
  
  printf("polar coordinates: radius=%f, theta=%f (%f degrees)\n", 
         radius, theta, thetaInDegrees);
  
  return 0;
}
