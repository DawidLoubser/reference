#include <stdio.h>
#include <stdlib.h>

typedef struct ParabolaStruct
{
  double a, b, c;
} Parabola;  

typedef struct PointStruct
{
  double x, y;
} Point;

Point calcTurningPoint(Parabola p)
{
  Point turningPoint;
  turningPoint.x = -p.b/(2*p.a);
  turningPoint.y = p.a*turningPoint.x*turningPoint.x + p.b*turningPoint.x + p.c;
  return turningPoint;
}

int main(int argc, char *argv[])
{
  Parabola parabola = {0,0,0};
  if (argc == 4)  
    {
      parabola.a = atof(argv[1]);   
      parabola.b = atof(argv[2]);   
      parabola.c = atof(argv[3]);   
    }
  else
    {
      printf("Enter parabola coefficients, a b c: ");
      scanf("%lf %lf %lf", &parabola.a, &parabola.b, &parabola.c);
    }
    
  Point turningPoint = calcTurningPoint(parabola);
  
  printf("turning point of %fx^2 + %fx + %f = (%f,%f)\n",
         parabola.a, parabola.b, parabola.c, 
         turningPoint.x, turningPoint.y);
         
  return 0;
}
