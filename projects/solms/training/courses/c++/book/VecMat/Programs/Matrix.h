\begin{verbatim}
#ifndef __MATRIX_H
#define __MATRIX_H

#include <math.h>
#include <iostream>
#include <cstdlib>

#include "Vector.h"
#include "ErrHandl.h"

using namespace std;

template <class T> class Matrix
{
  public:
    Matrix ();
    Matrix (const Matrix<T>& M);
    Matrix (const long nrows, const long ncols);
   ~Matrix ();

    long rows         () const;
    long cols         () const;

    int  outputwidth        () const;
    int  outputprecision    () const;
    void setoutputwidth     (const int precisn);
    void setoutputprecision (const int wdth);

    void random (const T& upper=1, const T& lower=0);
    void fill   (const T& x);
    void unit   ();

    void setcol    (const long ncol, const Vector<T>& v);
    void setrow    (const long nrow, const Vector<T>& v);
    void insertrow (const long beforerow, const Vector<T>& v);
    void insertcol (const long beforecol, const Vector<T>& v);
    void removerow (const long nrow);
    void removecol (const long ncol);
    void swaprows  (const long r1, const long r2);
    void swapcols  (const long c1, const long c2);
    void setdiag   (const Vector<T>& v);
    void setdiag   (const T& x);

    Vector<T> diag  () const;
    T         trace () const;

    Matrix<T> transpose () const;
    Matrix<T> hadamamardproduct (const Matrix<T>& M) const;

    Matrix<T>& operator=  (const Matrix<T>& M);
    Vector<T>& operator[] (const long nrow)    const;
    Vector<T>  operator() (const long ncol)    const;

    Matrix<T>  operator+  (const Matrix<T>& M) const;
    Matrix<T>  operator-  (const Matrix<T>& M) const;
    Matrix<T>  operator*  (const Matrix<T>& M) const;
    Matrix<T>  operator%  (const Matrix<T>& M) const;
    Matrix<T>  operator+  (const Vector<T>& v) const;
    Matrix<T>  operator-  (const Vector<T>& v) const;
    Matrix<T>  operator+  (const T& x)         const;
    Matrix<T>  operator-  (const T& x)         const;
    Matrix<T>  operator*  (const T& x)         const;
    Matrix<T>  operator/  (const T& x)         const;
    Vector<T>  operator*  (const Vector<T>& v) const;
    void       operator+= (const Matrix<T>& M);
    void       operator-= (const Matrix<T>& M);
    void       operator*= (const Matrix<T>& M);
    void       operator%= (const Matrix<T>& M);
    void       operator+= (const Vector<T>& v);
    void       operator-= (const Vector<T>& v);
    void       operator+= (const T& x)        ;
    void       operator-= (const T& x)        ;
    void       operator*= (const T& x)        ;
    void       operator/= (const T& x)        ;
/*
    friend Matrix<T> operator% (const Vector<T>&,const Vector<T>&);
    friend Matrix<T> operator+ (const T& x,const Matrix<T>& M);
    friend Matrix<T> operator- (const T& x,const Matrix<T>& M);
    friend Matrix<T> operator* (const T& x,const Matrix<T>& M);
    friend Vector<T> operator* (const Vector<T>& v, Matrix<T> M);

    friend istream& operator>> (istream& is,       Matrix<T>& M);
    friend ostream& operator<< (ostream& os, const Matrix<T>& M);
*/
  private:
    Vector<T>*       _M;
    long             _nrows, _ncols;
    int              _precision, _width;
    static const int _defaultprecision;
    static const int _defaultwidth;

};

// Initializing Static Class Constants
template<class T> const int Matrix<T>::_defaultprecision = 3;
template<class T> const int Matrix<T>::_defaultwidth     = 9;

template <class T>
Matrix<T> operator% (const Vector<T>& v1,const Vector<T>& v2);

template <class T>
inline Matrix<T> operator+ (const T& x,const Matrix<T>& M);

template <class T>
Matrix<T> operator- (const T& x,const Matrix<T>& M);

template <class T>
inline Matrix<T> operator* (const T& x,const Matrix<T>& M);

template <class T>
Vector<T> operator* (const Vector<T>& v,const Matrix<T>& M);

template <class T>
istream& operator>> (istream& is, Matrix<T>& M);

template <class T>
ostream& operator<< (ostream& os, const Matrix<T>& M);

#endif
\end{verbatim}
