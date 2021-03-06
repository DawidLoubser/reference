#include <stdio.h>

typedef struct RationalStruct
{
  long numerator, denominator;
} Rational;  

typedef struct ComplexStruct
{
  double real, imaginary;
} Complex;  

typedef union ScalarStruct
{
  short shortNumber;
  int intNumber;
  long longNumber;
  float floatNumber;
  double doubleNumber;
  Rational rationalNumber;
  Complex complexNumber;
} Scalar;  

int main(int argc, char *argv[])
{
  Scalar scalar;
  printf("sizeof(scalar) = %d bytes\n", sizeof(scalar));
  
  printf("initial values:\n");
  printf("scalar.intNumber = %d\n", scalar.intNumber);
  printf("scalar.rationalNumber = %ld/%ld\n", 
         scalar.rationalNumber.numerator,
         scalar.rationalNumber.denominator);
  printf("scalar.complexNumber = %f + i%f\n", 
         scalar.complexNumber.real,
         scalar.complexNumber.imaginary);
   
  scalar.rationalNumber.numerator = 2;
  scalar.rationalNumber.denominator = 3;
  
  printf("After initializing with rational number (fraction) 2/3:\n");
  printf("scalar.intNumber = %d\n", scalar.intNumber);
  printf("scalar.rationalNumber = %ld/%ld\n", 
         scalar.rationalNumber.numerator,
         scalar.rationalNumber.denominator);
  printf("scalar.complexNumber = %f + i%f\n", 
         scalar.complexNumber.real,
         scalar.complexNumber.imaginary);

  scalar.complexNumber.real = 1.5;
  scalar.complexNumber.imaginary = 2.3;
  
  printf("After initializing with complex number (fraction) 1.5 - i2.3:\n");
  printf("scalar.intNumber = %d\n", scalar.intNumber);
  printf("scalar.rationalNumber = %ld/%ld\n", 
         scalar.rationalNumber.numerator,
         scalar.rationalNumber.denominator);
  printf("scalar.complexNumber = %f + i%f\n", 
         scalar.complexNumber.real,
         scalar.complexNumber.imaginary);
  
         
  return 0;         
}  
