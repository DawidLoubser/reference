\begin{verbatim}
// FILE: Rational.CPP
#include "Rational.h"
// CONSTRUCTORS
// ============

template <class T>
Rational<T>::Rational (): _numer(0), _denom(1) {};

template <class T>
Rational<T>::Rational (const T& numer,
                       const T& denom)
  : _numer(numer), _denom(denom)
{
#if EXCEPTIONHANDLING
  if (denom == 0)
    throw DivideByZero("Rational(numer,denom) => denom=0");
#endif
  if (_denom < 0)
    {_numer = -_numer;  _denom = -_denom;}

  T common = gcd(_numer, _denom);

  if (common > 1)
    {_numer /= common;  _denom /= common;}
};

template <class T>
Rational<T>::Rational (const Rational<T>& r)
  : _numer(r._numer), _denom(r._denom) {};

template <class T>
Rational<T>::Rational (const T& k)
  : _numer(k), _denom(1) {};

// QUERY FUNCTIONS
// ===============

template <class T>
inline T Rational<T>::numerator   () const
  {return _numer;};

template <class T>
inline T Rational<T>::denominator () const
  {return _denom;};

// OTHER CONSTANT MEMBER FUNCTIONS
// ===============================

template <class T>
Rational<T> Rational<T>::power (const long n) const
{
  if (n==0)
    return Rational<T>(1,1);
  else
    {
      Rational<T> result(*this);
      for (int i=2; i<=labs(n); i++)
        result *= *this;
      if (n<0)
      {
        T dummy = result._denom;
        result._denom = result._numer;
        result._numer = dummy;
      }
      return result;
    }
}

template <class T>
inline double Rational<T>::power (const double y) const
{return pow(*this,y);}

// ASSIGNMENT OPERATORS
// ====================

template <class T>
inline Rational<T>& Rational<T>::operator=  (const Rational<T> r)
{
  _numer = r._numer; _denom = r._denom;
  return *this;
}

template <class T>
inline Rational<T>& Rational<T>::operator= (const T& k)
{
  _numer = k; _denom = (T)1;
  return *this;
}

// ARITHMETIC OPERATORS
// ====================

template <class T>
inline Rational<T> Rational<T>::operator+
             (const Rational<T>& r) const
{
  return Rational<T>(_numer*r._denom
           + r._numer*_denom, _denom*r._denom);
}

template <class T>
inline Rational<T> Rational<T>::operator+
             (const T& k) const
{
  return Rational<T>(_numer+k*_denom,_denom);
}

template <class T>
inline Rational<T> Rational<T>::operator-
             (const Rational<T>& r) const
{ return *this + (-r); }

template <class T>
inline Rational<T> Rational<T>::operator-
             (const T& k) const
{
  return Rational<T>(_numer-k*_denom,_denom);
}

template <class T>
inline Rational<T> Rational<T>::operator*
        (const Rational<T>& r) const
{ return Rational<T>(_numer*r._numer, _denom*r._denom);}

template <class T>
inline Rational<T> Rational<T>::operator*
             (const T& k) const
{
  return Rational<T>(_numer*k,_denom);
}

template <class T>
inline Rational<T> Rational<T>::operator/
        (const Rational<T>& r) const
{ return Rational<T>(_numer*r._denom, _denom*r._numer);}

template <class T>
inline Rational<T>& Rational<T>::operator+=
     (const Rational<T>& r)
       {*this = *this + r; return *this;}

template <class T>
inline Rational<T>& Rational<T>::operator-=
     (const Rational<T>& r)
       {*this = *this - r; return *this;}

template <class T>
inline Rational<T>& Rational<T>::operator*=
     (const Rational<T>& r)
       {*this = *this * r; return *this;}

template <class T>
inline Rational<T>& Rational<T>::operator/=
     (const Rational<T>& r)
       {*this = *this / r; return *this;}

template <class T>
inline Rational<T>& Rational<T>::operator+=
     (const T& k)
       {*this = *this + k; return *this;}

template <class T>
inline Rational<T>& Rational<T>::operator-=
     (const T& k)
       {*this = *this - k; return *this;}

template <class T>
inline Rational<T>& Rational<T>::operator*=
     (const T& k)
       {*this = *this * k; return *this;}

template <class T>
inline Rational<T>& Rational<T>::operator/=
     (const T& k)
       {*this = *this / k; return *this;}

template <class T>
inline double Rational<T>::operator+  (const double& x) const
{return (double)*this+x;}

template <class T>
inline double Rational<T>::operator-  (const double& x) const
{return (double)*this-x;}
template <class T>

inline double Rational<T>::operator*  (const double& x) const
{return (double)*this * x;}
template <class T>

inline double Rational<T>::operator/  (const double& x) const
{return (double)*this/x;}


// UNARY OPERATORS
// ===============

template <class T>
inline Rational<T>& Rational<T>::operator++ ()
{*this+=1;  return *this;}

template <class T>
inline Rational<T> Rational<T>::operator++ (int)
{Rational<T> old(*this); *this+=1;  return old;}

template <class T>
inline Rational<T>& Rational<T>::operator-- ()
{*this-=1;  return *this;}

template <class T>
inline Rational<T> Rational<T>::operator-- (int)
{Rational<T> old(*this); *this-=1;  return old;}

template <class T>
inline Rational<T> Rational<T>::operator- () const
{
  Rational<T> r(*this);
  r._numer = -r._numer;
  return r;
}

template <class T>
inline Rational<T> Rational<T>::operator+ () const
{ return *this; }

// RELATIONAL OPERATORS
// =====================

template <class T>
inline int Rational<T>::operator==
        (const Rational<T>& r) const
{ return ((_numer == r._numer) && (_denom == r._denom));}

template <class T>
inline int Rational<T>::operator!=
        (const Rational<T>& r) const
{ return (!(*this == r)); }

template <class T>
inline int Rational<T>::operator>
        (const Rational<T>& r) const
{ return ((double)*this > (double)r);}

template <class T>
inline int Rational<T>::operator<
        (const Rational<T>& r) const
{ return ((double)*this < (double)r);}

template <class T>
inline int Rational<T>::operator>=
        (const Rational<T>& r) const
{ return ((double)*this >= (double)r);}

template <class T>
inline int Rational<T>::operator<=
        (const Rational<T>& r) const
{ return ((double)*this <= (double)r);}

template <class T>
inline int Rational<T>::operator==
        (const T& k) const
{ return (_numer == k);}

template <class T>
inline int Rational<T>::operator!=
        (const T& k) const
{ return (_numer != k);}

template <class T>
inline int Rational<T>::operator>
        (const T& k) const
{ return ((double)*this > k);}

template <class T>
inline int Rational<T>::operator<
        (const T& k) const
{ return ((double)*this < k);}

template <class T>
inline int Rational<T>::operator>=
        (const T& k) const
{ return ((double)*this >= k);}

template <class T>
inline int Rational<T>::operator<=
        (const T& k) const
{ return ((double)*this <= k);}

template <class T>
inline int Rational<T>::operator==
        (const double& x) const
{ return ((double)*this == x);}

template <class T>
inline int Rational<T>::operator!=
        (const double& x) const
{ return ((double)*this != x);}

template <class T>
inline int Rational<T>::operator>
        (const double& x) const
{ return ((double)*this > x);}

template <class T>
inline int Rational<T>::operator<
        (const double& x) const
{ return ((double)*this < x);}

template <class T>
inline int Rational<T>::operator>=
        (const double& x) const
{ return ((double)*this >= x);}

template <class T>
inline int Rational<T>::operator<=
        (const double& x) const
{ return ((double)*this <= x);}

// TYPE CONVERSIONS
// ================
template <class T>
inline Rational<T>::operator double () const
{ return (double)_numer/(double)_denom; }

// PRIVATE METHODS
// ===============

template <class T>
T Rational<T>::gcd (T a, T b) const
{
  if (a < 0) a = -a;
  if (b < 0) b = -b;

  while (b > 0)
  {
    T m = a % b;
    a = b;
    b = m;
  }
  return a;
}
\end{verbatim}
