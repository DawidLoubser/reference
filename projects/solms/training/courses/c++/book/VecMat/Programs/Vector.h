\begin{verbatim}
#include <iostream>
#include <cstdlib>  // for srand()
#include <assert.h>
#include <math.h>

#include "ErrHandl.h"

using namespace std;

#ifndef __VECTOR_H
#define __VECTOR_H


template <class T> class Vector
{
  public:
    Vector ();
    Vector (const Vector<T>& v);
    Vector (const long lngth);
   ~Vector ();

    void resize (const long lngth);

    T& operator[] (const long i) const;  // element access

    long length    () const;  // returns length of vector

    int  outputprecision    () const;  // returns output precision
    int  outputwidth        () const;  // returns output width
    void setoutputprecision (const int precisn);
    void setoutputwidth     (const int wdth);

    void fill      (const T& x);   // set all v_i=x
    void unit      (const long i); // set to unit vector e_i

    void random (const T& upper=1, const T& lower=0);

    Vector<T> insert (const Vector<T>& v, const long before) const;
    Vector<T> slice  (const long istart, const long noelements,
                      const long stride=1) const;

    Vector<T> func (T (*f)(const T&)) const;  // returns f(vec)
    Vector<T> func (T (*f)(T)) const;  // returns f(vec)

    T    sumels    () const;       // sum of all elements
    T    mean      () const;       // arithmetic
    T    norm      () const;       // Euclidean norm
    T    norm      (const double& p) const;  // p-norm => (sum(|v_i|^p))^(1/p)
    T    maxel      () const;      // largest element
    T    minel      () const;      // smallest element
    T    maxabsel   () const;      // largest abs(element)
    T    minabsel   () const;      // smallest abs(element)
    long maxindx    () const;      // index of largest element
    long minindx    () const;      // index of smallest element
    long maxabsindx () const;      // index of largest abs
    long minabsindx () const;      // index of smallest abs
    void normalize  ();            // so that vec*vec=1
    void swap       (const long i, const long j);
    void quickSort  (long low=0, long high=-1);

    Vector<T> hadamardproduct (const Vector<T>& v) const;
                            // element for element multiplication

    Vector<T>& operator= (const Vector<T>& v);
    Vector<T>  operator+ (const Vector<T>& v) const;
    Vector<T>  operator- (const Vector<T>& v) const;
    T          operator* (const Vector<T>& v) const; // dot product
    Vector<T>  operator+ (const T& x) const;
    Vector<T>  operator- (const T& x) const;
    Vector<T>  operator* (const T& x) const;
    Vector<T>  operator/ (const T& x) const;

    void operator+= (const Vector<T>& v);
    void operator-= (const Vector<T>& v);
    void operator+= (const T& x);
    void operator-= (const T& x);
    void operator*= (const T& x);
    void operator/= (const T& x);

    Vector<T> operator+ () const;   // unary +
    Vector<T> operator- ();         // unary -
/*
    friend ostream&  operator<< (ostream& os, const Vector<T>& v);
    friend istream&  operator>> (istream& is,       Vector<T>& v);
    friend Vector<T> operator*  (const T& x,  const Vector<T>& v);
    friend Vector<T> operator+  (const T& x,  const Vector<T>& v);
    friend Vector<T> operator-  (const T& x,  const Vector<T>& v);
*/
  private:
    T* _v;
    long _length;
    int _precision, _width;
    static const int _defaultprecision;
    static const int _defaultwidth;

    long partitionSmallerLarger (long low, long high, long ipivot);
};

template <class T>
ostream& operator<< (ostream& os, const Vector<T>& v);

template <class T>
istream& operator>> (istream& is, Vector<T>& v);

template <class T>
Vector<T> operator* (const T& x, const Vector<T>& v);

template <class T>
Vector<T> operator+ (const T& x, const Vector<T>& v);

template <class T>
Vector<T> operator- (const T& x, const Vector<T>& v);


// With the Matrix class we define a global operator
/*     Matrix<T> operator% (const Vector<T>&, const Vector<T>&);  */
// which computes the Kronecker product between 2 vectors
// and returns the result as a matrix.

#endif
\end{verbatim}
