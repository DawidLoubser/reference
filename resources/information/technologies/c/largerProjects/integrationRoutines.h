#ifndef integrationRoutines_h
#define integrationRoutines_h

double simpsonIntegrate(double (*f)(double x),
                        const double a, const double b, 
                        const int numIntervals);

#endif
