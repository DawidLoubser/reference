#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

double** readMatrix(int* nRows, int* nCols)
{
  printf("Enter numRows numCols: ");
  scanf("%d %d", nRows, nCols);
  
  double** mat = malloc((*nRows) * sizeof(double*));
  
  int nr, nc;
  for (nr=0; nr<*nRows; ++nr)
  {
    mat[nr] = malloc((*nCols) * sizeof(double));
    
    for (nc=0; nc<*nCols; ++nc)
    {
      printf("mat[%d][%d] = ", nr, nc);
      scanf("%lf", &mat[nr][nc]);
    }    
  }
  return mat;
}

double** matrixProduct(const double * const * const mat1, 
                       const int nRowsMat1, const int nColsMat1, 
                       const double * const * const mat2, 
                       const int nRowsMat2, const int nColsMat2,
                       int* nRowsProduct, int* nColsProduct)
{
  if (nColsMat1 != nRowsMat2)
  {
    printf("Error: invalid dimensions in matrix multiplication.");
    exit(-1);
  }
  
  *nRowsProduct = nRowsMat1;
  *nColsProduct = nColsMat2;

  double** product = malloc((*nRowsProduct) * sizeof(double*));
  
  int nr, nc;
  for (nr=0; nr<(*nRowsProduct); ++nr)
  {
    product[nr] = malloc((*nColsProduct) * sizeof(double));
    
    for (nc=0; nc<(*nColsProduct); ++nc)
    {
      double sum = 0;
      int i;
      for (i=0; i<nRowsMat2; ++i)
        sum += mat1[nr][i] * mat2[i][nc];
        
      product[nr][nc] = sum;  
    }
  }   
  return product;  
}

void printMatrix(const double * const * const mat, 
                 const int nRows, const int nCols)
{
  int nr, nc;
  for (nr=0; nr<nRows; ++nr)
  {
    for (nc=0; nc<nCols; ++nc)
      printf("%15.6f", mat[nr][nc]);
      
    printf("\n");  
  }
}    

void delete(double** mat, int nRows)
{
  int nr;
  for (nr=0; nr<nRows; ++nr)
    free(mat[nr]);
  free(mat);
}    

int main(int argc, char *argv[])
{
  int *nRowsA = malloc(sizeof(int));
  int *nColsA = malloc(sizeof(int));
  double** A = readMatrix(nRowsA, nColsA);
  
  int *nRowsB = malloc(sizeof(int));
  int *nColsB = malloc(sizeof(int));
  double** B = readMatrix(nRowsB, nColsB);
  
  int *nRowsC = malloc(sizeof(int));
  int *nColsC = malloc(sizeof(int));
  double** C = matrixProduct((const double * const * const)A, 
                             (const int)*nRowsA, (const int)*nColsA, 
                             (const double * const * const)B, 
                             (const int)*nRowsB, (const int)*nColsB,
                             nRowsC, nColsC);
  
  printf("Product matrix:\n");
  printMatrix((const double * const * const)C, 
              (const int)*nRowsC, (const int)*nColsC);
  
  delete(A, *nRowsA);
  delete(B, *nRowsB);
  delete(C, *nRowsC);
  return 0;
}
