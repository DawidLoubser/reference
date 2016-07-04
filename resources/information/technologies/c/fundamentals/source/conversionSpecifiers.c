#include <stdio.h>

int main(int argc, char *argv[])
{
  int score1, score2;
  
  printf("Enter score for team 1:");
  scanf("%d", &score1);
  
  printf("Enter score for team 2:");
  scanf("%d", &score2);
  
  float temp;
  printf("Enter current temperature:");
  scanf("%f", &temp);
  printf("temp:%f\n", temp);
  
  printf("The score is %d:%d and the temperature is %10.2f degrees celsius.",
         score1, score2, temp); 
  return 0;       
}
