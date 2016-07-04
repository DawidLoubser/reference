\begin{verbatim}
// FILE: RATIONAL.H

#ifndef __RATIONAL_H
#define __RATIONAL_H

#include <math.h>
#include <stdexcept>
#include <iostream>

#include "ErrHandl.h"

using namespace std;

template <class T>
class Rational
{
  public:
    Rational ();
    Rational (const T& numer, const T& denom);
    Rational (const Rational<T>& r);
    Rational (const T& k);

    inline T numerator   () const;
    inline T denominator () const;

    Rational<T>   power (const long)   const;
    double power (const double) const;

    Rational<T>& operator= (const Rational<T> r);
    Rational<T>& operator= (const T& k);

    Rational<T>  operator+  (const Rational<T>& r) const;
    Rational<T>  operator-  (const Rational<T>& r) const;
    Rational<T>  operator*  (const Rational<T>& r) const;
    Rational<T>  operator/  (const Rational<T>& r) const;
    Rational<T>& operator+= (const Rational<T>& r);
    Rational<T>& operator-= (const Rational<T>& r);
    Rational<T>& operator*= (const Rational<T>& r);
    Rational<T>& operator/= (const Rational<T>& r);

    Rational<T>  operator+  (const T& k) const;
    Rational<T>  operator-  (const T& k) const;
    Rational<T>  operator*  (const T& k) const;
    Rational<T>  operator/  (const T& k) const;
    double       operator+  (const double& x) const;
    double       operator-  (const double& x) const;
    double       operator*  (const double& x) const;
    double       operator/  (const double& x) const;
    Rational<T>& operator+= (const T& k);
    Rational<T>& operator-= (const T& k);
    Rational<T>& operator*= (const T& k);
    Rational<T>& operator/= (const T& k);

    int operator== (const Rational<T>& r) const;
    int operator!= (const Rational<T>& r) const;
    int operator<  (const Rational<T>& r) const;
    int operator>  (const Rational<T>& r) const;
    int operator<= (const Rational<T>& r) const;
    int operator>= (const Rational<T>& r) const;

    int operator== (const double& x) const;
    int operator!= (const double& x) const;
    int operator<  (const double& x) const;
    int operator>  (const double& x) const;
    int operator<= (const double& x) const;
    int operator>= (const double& x) const;

    int operator== (const T& k) const;
    int operator!= (const T& k) const;
    int operator<  (const T& k) const;
    int operator>  (const T& k) const;
    int operator<= (const T& k) const;
    int operator>= (const T& k) const;

    Rational<T> operator+ () const;
    Rational<T> operator- () const;

    Rational<T>& operator++ ();
    Rational<T>& operator-- ();
    Rational<T>  operator++ (int);
    Rational<T>  operator-- (int); // decrement

    operator double () const;      // type conversion to double
  
  private:
    T _numer, _denom;

    T gcd (T a, T b) const;
};


template <class T>
ostream& operator<< (ostream& os, const Rational<T>& r)
{
  T numer = r.numerator();
  T denom = r.denominator();
  T non_frac = numer/denom;
  T frac_numer = numer - non_frac*denom;
  if (non_frac != 0) os << non_frac;
  if (frac_numer != 0)
    if (frac_numer > 0)
      os << " " << frac_numer << "/" << denom;
    else
      os << " " << -frac_numer << "/" << denom;
  return os;
}

template <class T>
istream& operator>> (istream& is, Rational<T>& r)
{
  T numer, denom;
  is >> numer >> denom;
  r = Rational<T>(numer,denom);
  return is;
}

template <class T>
Rational<T> operator+ (const T& k, const Rational<T>& r)
{return r+k;}

template <class T>
Rational<T> operator- (const T& k, const Rational<T>& r)
{return (-r)+k;}

template <class T>
Rational<T> operator* (const T& k, const Rational<T>& r)
{return r*k;}

template <class T>
Rational<T> operator/ (const T& k, const Rational<T>& r)
{return Rational<T>(r.denominator()*k,r.numerator());}

template <class T>
Rational<T> fabs (const Rational<T>& r)
{return Rational<T>(abs(r.numerator()),r.denominator());}

#endif
\end{verbatim}
