\begin{verbatim}
#include "Vector.h"

//=========================================================
// Initializing Static Class Constants
//------------------------------------
template<class T> const int Vector<T>::_defaultprecision = 4;
template<class T> const int Vector<T>::_defaultwidth     = 7;
//=========================================================
// CONSTRUCTORS and DESTRUCTOR
//----------------------------
template <class T> Vector<T>::Vector ()
  : _v(NULL), _length(0),
    _precision(_defaultprecision), _width(_defaultwidth) {}
//---------------------------------------------------------
template <class T> Vector<T>::Vector (const Vector<T>& v)
  : _v(NULL), _length(0),_precision(_defaultprecision),
    _width(_defaultwidth)  {*this=v;}
//---------------------------------------------------------
template <class T> Vector<T>::Vector (const long lngth)
  : _precision(_defaultprecision), _width(_defaultwidth)
{
  #if EXCEPTIONHANDLING
    if (lngth<0) throw Exception("Vector(lngth) => lngth<0");
  #endif
  _v = new T[lngth];
  _length=lngth;
}
//---------------------------------------------------------
template <class T> inline Vector<T>::~Vector ()
{delete[] _v;}
//---------------------------------------------------------
template <class T> inline long Vector<T>::length () const
{return _length;}
//---------------------------------------------------------
template <class T> inline int Vector<T>::outputprecision () const
{return _precision;}
//---------------------------------------------------------
template <class T> inline int Vector<T>::outputwidth () const
{return _width;}
//---------------------------------------------------------
template <class T>
inline void Vector<T>::setoutputwidth (const int wdth)
{
  #if EXCEPTIONHANDLING
    if (wdth<=0)
      throw IllegalArguments("Vector::setoutputwidth(wdth)");
  #endif
  _width = wdth;
}
//---------------------------------------------------------
template <class T>
inline void Vector<T>::setoutputprecision (const int precisn)
{
  #if EXCEPTIONHANDLING
    if (precisn<=0)
      throw IllegalArguments("Vector::setoutputwidth(precisn)");
  #endif
  _precision = precisn;
}
//=========================================================
// ELEMENT ACCESS
//---------------
template <class T>
inline T& Vector<T>::operator[] (const long i) const
{
  #if EXCEPTIONHANDLING
    if ((i<0) || (i>=_length))
      throw Range("Vector::operator[]:");
  #endif
  return _v[i];
}
//=========================================================
// USEFUL METHODS
//---------------
template <class T>
void Vector<T>::fill (const T& x)
{for (long i=0; i<_length; i++) _v[i]=x;}
//---------------------------------------------------------
template <class T>
inline void Vector<T>::unit (const long i)
{
  #if EXCEPTIONHANDLING
    if ((i<0) || (i>=_length))
      throw Range("Vector::unit(i)");
  #endif
  fill(0);  _v[i]=1;
}
//---------------------------------------------------------

template <class T>
void Vector<T>::random (const T& upper, const T& lower)
{
  for (long i=0; i<_length; i++)
    _v[i] = (T)rand();
}

//---------------------------------------------------------
template <class T>    // inserts vector v into *this before element no before
Vector<T> Vector<T>::insert (const Vector<T>& v,
                             const long before) const
{
  #if EXCEPTIONHANDLING
    if ((before<0) || (before>_length))
      throw Range("Vector::insert(v,before)");
  #endif

  Vector<T> result(_length+v._length);

  for (long i=0; i<before; i++)
    result._v[i] = _v[i];
  for (long i=0; i<v._length; i++)
    result._v[before+i] = v._v[i];
  for (long i=before; i<_length; i++)
    result._v[v._length+i] = _v[i];
  return result;
}
//---------------------------------------------------------
template <class T>
Vector<T> Vector<T>::slice  (const long istart,
     const long noelements, const long stride) const
{
  long last = istart+(noelements-1)*stride;
  if ((istart<0) || (istart>=_length) || (last<0) ||
      (last>=_length)) throw Range("Vector::slice");

  Vector<T> result(noelements);
  long i=istart;
  for (long j=0; j<noelements; j++)
  {
    result._v[j] = _v[i];
    i += stride;
  }
  return result;
}
//---------------------------------------------------------
template <class T>            // returns f(vec)
Vector<T> Vector<T>::func (T (*f)(const T&)) const
{
  Vector<T> result(_length);
  for (long i=0; i<_length; i++)
    result._v[i] = f(_v[i]);
  return result;
}
//---------------------------------------------------------
template <class T>            // returns f(vec)
Vector<T> Vector<T>::func (T (*f)(T)) const
{
  Vector<T> result(_length);
  for (long i=0; i<_length; i++)
    result._v[i] = f(_v[i]);
  return result;
}
//---------------------------------------------------------
template <class T>            // sum of all elements
T Vector<T>::sumels () const
{
  T sum = 0;
  for (long i=0; i<_length; i++)
    sum += _v[i];
  return sum;
}
//---------------------------------------------------------
template <class T>           // arithmetic mean of elements
inline T Vector<T>::mean () const
{return sumels()/_length;}
//---------------------------------------------------------
template <class T>           // Euclidean norm
T Vector<T>::norm () const
{
  T sum=0;
  for (long i=0; i<_length; i++)
  {
    T x = _v[i];
    sum += x*x;
  }
  return sqrt(sum);
}
//---------------------------------------------------------
template <class T>          // so that vec*vec=1;
void Vector<T>::normalize ()
{
  T fact = norm();
  if (fact==(double)0)
    throw DivideByZero("Vector::normalize");
  for (long i=0; i<_length; i++)
    _v[i] /= fact;
}
//---------------------------------------------------------
template <class T>        // (sum(|v_i|^p))^(1/p)
T Vector<T>::norm (const double& p) const
{
  T sum=0;
  for (long i=0; i<_length; i++)
  {
    sum += pow(fabs(_v[i]),p);
  }
  return pow(sum,(double)1/p);
}
//---------------------------------------------------------
template <class T>     // returns index of largest element
long Vector<T>::maxindx () const
{
  #if EXCEPTIONHANDLING
    if (_v == NULL)
      throw IllegalCall("Vector::maxindx => NULL vector");
  #endif
  long indx=0;
  T max = _v[0];
  for (int i=1; i<_length; i++)
  {
    if (max < _v[i])
    {
      indx = i;
      max  = _v[i];
    }
  }
  return indx;
}
//---------------------------------------------------------
template <class T>     // returns index of smallest element
long Vector<T>::minindx () const
{
  #if EXCEPTIONHANDLING
    if (_v == NULL)
      throw IllegalCall("Vector::minindx => NULL vector");
  #endif
  long indx=0;
  T min = _v[0];
  for (int i=1; i<_length; i++)
  {
    if (min > _v[i])
    {
      indx = i;
      min  = _v[i];
    }
  }
  return indx;
}
//---------------------------------------------------------
template <class T>   // returns index of largest abs element
long Vector<T>::maxabsindx () const
{
  #if EXCEPTIONHANDLING
    if (_v == NULL)
      throw IllegalCall("Vector::maxabsindx => NULL vector");
  #endif
  long indx=0;
  T max = fabs(_v[0]), x;
  for (int i=1; i<_length; i++)
  {
    if (max < (x=fabs(_v[i])))
    {
      indx = i;
      max  = x;
    }
  }
  return indx;
}
//---------------------------------------------------------
template <class T> // returns index of smallest abs element
long Vector<T>::minabsindx () const
{
  #if EXCEPTIONHANDLING
    if (_v == NULL)
      throw IllegalCall("Vector::minabsindx => NULL vector");
  #endif
  long indx=0;
  T min = fabs(_v[0]), x;
  for (int i=1; i<_length; i++)
  {
    if (min > (x=fabs(_v[i])))
    {
      indx = i;
      min  = x;
    }
  }
  return indx;
}
//---------------------------------------------------------
template <class T>        // returns largest element
inline T Vector<T>::maxel () const
{return _v[maxindx()];}
//---------------------------------------------------------
template <class T>        // returns smallest element
inline T Vector<T>::minel () const
{return _v[minindx()];}
//---------------------------------------------------------
template <class T>        // returns largest abs element
inline T Vector<T>::maxabsel () const
{return fabs(_v[maxabsindx()]);}
//---------------------------------------------------------
template <class T>        // returns smallest abs element
inline T Vector<T>::minabsel () const
{return fabs(_v[minabsindx()]);}
//---------------------------------------------------------
template <class T>        // swaps elements i and j
inline void Vector<T>::swap (const long i, const long j)
{
  if (i!=j)
  {
    #if EXCEPTIONHANDLING
      if ((i<0) || (j<0) || (i>=_length) || (j>=_length))
        throw Range("Vector::swap");
    #endif
    T dummy = _v[i];
    _v[i]   = _v[j];
    _v[j]   = dummy;
  }
}
//---------------------------------------------------------
template <class T>
void Vector<T>::quickSort (long low, long high)
{
  if (high==-1)
    high=_length-1;
  if (low >= high)
    return;  // nothing to sort

  long ipivot = (low+high)/2;
  ipivot = partitionSmallerLarger (low,high,ipivot);
  if (low < ipivot)
    quickSort(low,ipivot-1);
  if (ipivot < high)
    quickSort(ipivot+1,high);
}
//---------------------------------------------------------
template <class T>
long Vector<T>::partitionSmallerLarger (long low, long high,
					long ipivot)
{
  if (ipivot != low)
    swap(low,ipivot);
  ipivot = low;
  low++;

  while (low <= high)
  {
    if (_v[low] <= _v[ipivot])
      ++low;
    else if (_v[high] > _v[ipivot])
           --high;
         else
           swap(low,high);
  }

  if (high != ipivot)
    swap(ipivot,high);
  return high;
}
//---------------------------------------------------------
template <class T>    // element for element multiplication
Vector<T> Vector<T>::hadamardproduct (const Vector<T>& v) const
{
  #if EXCEPTIONHANDLING
    if (_length != v._length)
      throw IllegalOperation("Vector::hadamardproduct");
  #endif
  Vector<T> result(v);
  for (long i=0; i<_length; i++)
    result._v[i] *= _v[i];

  return result;
}
//=========================================================
// Class Operators
//----------------
template <class T>      // assignment operator
Vector<T>& Vector<T>::operator= (const Vector<T>& v)
{
  if (this != &v)
   {
       if (_length != v._length)
         resize(v._length);
       for (long i=0; i<_length; i++)
        _v[i] = v._v[i];
   }
  return *this;
}
//---------------------------------------------------------
template <class T>      // vector addition
Vector<T> Vector<T>::operator+ (const Vector<T>& v) const
{
  #if EXCEPTIONHANDLING
    if (_length != v._length)
      throw IllegalOperation("Vector::operator+");
  #endif
  Vector<T> result(*this);
  for (long i=0; i<_length; i++)
    result._v[i] += v._v[i];
  return result;
}
//---------------------------------------------------------
template <class T>      // vector subtraction
Vector<T> Vector<T>::operator- (const Vector<T>& v) const
{
  #if EXCEPTIONHANDLING
    if (_length != v._length)
      throw IllegalOperation("Vector::operator-");
  #endif
  Vector<T> result(*this);
  for (long i=0; i<_length; i++)
    result._v[i] -= v._v[i];
  return result;
}
//---------------------------------------------------------
template <class T>      // Dot product
T Vector<T>::operator* (const Vector<T>& v) const
{
  #if EXCEPTIONHANDLING
    if (_length != v._length)
      throw IllegalOperation("Vector::operator*");
  #endif
  T result=0;
  for (long i=0; i<_length; i++)
    result += _v[i]*v._v[i];
  return result;
}
//---------------------------------------------------------
template <class T>
Vector<T> Vector<T>::operator+ (const T& x) const
{
  Vector<T> result(*this);
  for (long i=0; i<_length; i++)
    result._v[i] += x;
  return result;
}
//---------------------------------------------------------
template <class T>
Vector<T> Vector<T>::operator- (const T& x) const
{
  Vector<T> result(*this);
  for (long i=0; i<_length; i++)
    result._v[i] -= x;
  return result;
}
//---------------------------------------------------------
template <class T>
Vector<T> Vector<T>::operator* (const T& x) const
{
  Vector<T> result(*this);
  for (long i=0; i<_length; i++)
    result._v[i] *= x;
  return result;
}
//---------------------------------------------------------
template <class T>
Vector<T> Vector<T>::operator/ (const T& x) const
{
  Vector<T> result(*this);
  for (long i=0; i<_length; i++)
    result._v[i] /= x;
  return result;
}
//---------------------------------------------------------
template <class T>
inline void Vector<T>::operator+= (const Vector<T>& v)
{*this = *this + v;}
//---------------------------------------------------------
template <class T>
inline void Vector<T>::operator-= (const Vector<T>& v)
{*this = *this - v;}
//---------------------------------------------------------
template <class T>
inline void Vector<T>::operator+= (const T& x)
{*this = *this + x;}
//---------------------------------------------------------
template <class T>
inline void Vector<T>::operator-= (const T& x)
{*this = *this - x;}
//---------------------------------------------------------
template <class T>
inline void Vector<T>::operator*= (const T& x)
{*this = *this * x;}
//---------------------------------------------------------
template <class T>
inline void Vector<T>::operator/= (const T& x)
{*this = *this / x;}
//---------------------------------------------------------
template <class T>       // unary +
inline Vector<T> Vector<T>::operator+ () const
{return (*this);}
//---------------------------------------------------------
template <class T>       // unary -
Vector<T> Vector<T>::operator- ()
{
  Vector<T> result(_length);
  for (long i=0; i<_length; i++)
    result._v[i] = -_v[i];
  return result;
}
//=========================================================
// PRIVATE MEMBER FUNCTIONS
//-------------------------
template <class T> void Vector<T>::resize (const long lngth)
{
  #if EXCEPTIONHANDLING
    if (lngth<0)
      throw IllegalArguments("Vector::resize(lngth) => lngth<0");
  #endif
  delete[] _v;
  if (lngth>0)
   {
     _v = new T[lngth];
     _length = lngth;
   }
  else
   {
     _v = NULL;
     _length = 0;
   }
}
/*
//=========================================================
// FRIENDS OF VECTOR
//------------------
template <class T>
ostream& operator<< (ostream& os, const Vector<T>& v)
{
  os << "[ ";
  for (long i=0; i<v._length; i++)
  {
    os.precision(v._precision); os.width(v._width);
    os << v._v[i] << ' ';
  }
  return os << "]";
}
//---------------------------------------------------------
template <class T>
istream& operator>> (istream& is, Vector<T>& v)
{
  for (long i=0; i<v._length; i++)
    is >> v._v[i];
  return is;
}
//=========================================================
// Global Operators
//-----------------
template <class T>
Vector<T> operator* (const T& x, const Vector<T>& v)
{
  Vector<T>  result(v._length);
  for (long i=0; i<v._length; i++)
    result._v[i] = x*v._v[i];
  return result;
}
//---------------------------------------------------------
template <class T>
Vector<T> operator+ (const T& x, const Vector<T>& v)
{
  Vector<T>  result(v._length);
  for (long i=0; i<v._length; i++)
    result._v[i] = x+v._v[i];
  return result;
}
//---------------------------------------------------------
template <class T>
Vector<T> operator- (const T& x, const Vector<T>& v)
{
  Vector<T>  result(v._length);
  for (long i=0; i<v._length; i++)
    result._v[i] = x-v._v[i];
  return result;
}
*/
//=========================================================

template <class T>
ostream& operator<< (ostream& os, const Vector<T>& v)
{
  os << "[ ";
  for (long i=0; i<v.length(); i++)
  {
    os.precision(v.outputprecision()); os.width(v.outputwidth());
    os << v[i] << ' ';
  }
  return os << "]";
}
//---------------------------------------------------------
template <class T>
istream& operator>> (istream& is, Vector<T>& v)
{
  for (long i=0; i<v.length(); i++)
    is >> v[i];
  return is;
}
//=========================================================
// Global Operators
//-----------------
template <class T>
Vector<T> operator* (const T& x, const Vector<T>& v)
{
  Vector<T>  result(v.length());
  for (long i=0; i<v.length(); i++)
    result[i] = x*v[i];
  return result;
}
//---------------------------------------------------------
template <class T>
Vector<T> operator+ (const T& x, const Vector<T>& v)
{
  Vector<T>  result(v.length());
  for (long i=0; i<v.length(); i++)
    result[i] = x+v[i];
  return result;
}
//---------------------------------------------------------
template <class T>
Vector<T> operator- (const T& x, const Vector<T>& v)
{
  Vector<T>  result(v.length());
  for (long i=0; i<v.length(); i++)
    result[i] = x-v[i];
  return result;
}
\end{verbatim}
