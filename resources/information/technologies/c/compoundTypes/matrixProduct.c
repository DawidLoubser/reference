#include <stdio.h>

int main(int argc, char *argv[])
{
  /* Read in dimensions of first matrix. */  
  int nRows1, nCols1;
  printf("mat1: no of rows, columns = ");
  scanf("%d %d", &nRows1, &nCols1);
  double mat1[nRows1][nCols1];
  
  /* Read in first matrix */  
  int nr, nc;
  for (nr=0; nr<nRows1; ++nr)
    for (nc=0; nc<nCols1; ++nc)
      {
        printf("mat[%d][%d] = ", nr, nc);
        scanf("%lf", &mat1[nr][nc]);
      }
      
  /* Read in dimensions of second matrix. */    
  int nCols2, nRows2;
  printf("mat2: no of rows, columns = ");
  scanf("%d %d", &nRows2, &nCols2);
  double mat2[nRows2][nCols2];
  
  /* Read in second matrix. */
  for (nr=0; nr<nRows2; ++nr)
    for (nc=0; nc<nCols2; ++nc)
      {
        printf("mat2[%d][%d] = ", nr, nc);
        scanf("%lf", &mat2[nr][nc]);
      }

  /* Check the dimensions. */
  if (nCols1 != nRows2)
  {
    printf("*** ERROR ***: illegal matrix dimensions in matrixProduct.");
    return -1;
  }
  
  /* declare result matrix of correct size. */
  double result[nRows1][nCols2];

  /* Calculate matrix product. */
  for (nr=0; nr<nRows1; ++nr)
  {
    for (nc=0; nc<nCols2; ++nc)
    {
      double sum = 0;
      int n;
      for (n=0; n<nCols1; ++n)
        sum += mat1[nr][n] * mat2[n][nc];
      result[nr][nc] = sum;
    }
  }
  
  printf("\nmatrix product:\n");  
  for (nr=0; nr<nRows1; ++nr)
  {
    for (nc=0; nc<nCols2; ++nc)
      printf("%10.4lf ", result[nr][nc]);
    printf("\n");  
  }    
  return 0;
}
