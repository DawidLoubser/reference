\begin{verbatim}
#include "Matrix.h"

//=========================================================
// CONSTRUCTORS and DESTRUCTOR
// ---------------------------
template <class T>
Matrix<T>::Matrix (): _M(NULL), _nrows(0), _ncols(0),
  _precision(_defaultprecision), _width(_defaultwidth) {}
//---------------------------------------------------------
template <class T>
Matrix<T>::Matrix (const Matrix<T>& M): _M(NULL),
    _nrows(0), _ncols(0), _precision(_defaultprecision),
    _width(_defaultwidth)  {*this=M;}
//---------------------------------------------------------
template <class T>
Matrix<T>::Matrix (const long nrows, const long ncols)
  : _precision(_defaultprecision), _width(_defaultwidth)
{
  #if EXCEPTIONHANDLING
    if ((nrows<0) || (ncols<0))
      throw IllegalArguments("Matrix(nrows,ncols) => nrows<0 or ncols<0");
  #endif
  _M = new Vector<T>[nrows];
  for (long nr=0; nr<nrows; nr++)
    _M[nr].resize(ncols);
  _nrows = nrows;  _ncols = ncols;
}
//---------------------------------------------------------
template <class T>
Matrix<T>::~Matrix () {delete[] _M;}
//=========================================================
// QUERY FUNCTIONS
// ---------------
template <class T>
long Matrix<T>::rows () const {return _nrows;}
//---------------------------------------------------------
template <class T>
long Matrix<T>::cols () const {return _ncols;}
//---------------------------------------------------------
template <class T>
int Matrix<T>::outputwidth () const {return _width;}
//---------------------------------------------------------
template <class T>
int Matrix<T>::outputprecision () const {return _precision;}
//=========================================================
// OTHER MEMBER FUNCTIONS
// ----------------------
template <class T>
void Matrix<T>::setoutputwidth (const int wdth)
{_width=wdth;}
//---------------------------------------------------------
template <class T>
void Matrix<T>::setoutputprecision (const int precisn)
{_precision = precisn;}
//---------------------------------------------------------
template <class T>
void Matrix<T>::random (const T& upper, const T& lower)
{
  for (long nr=0; nr<_nrows; nr++)
    _M[nr].random(lower,upper);
}
//---------------------------------------------------------
template <class T>
void Matrix<T>::fill (const T& x)
{
  for (long nr=0; nr<_nrows; nr++)
    _M[nr].fill(x);
}
//---------------------------------------------------------
template <class T>
void Matrix<T>::unit ()
{
  #if EXCEPTIONHANDLING
    if (_nrows != _ncols)
      throw IllegalOperation("Matrix::unit() => Matrix not square");
  #endif
  for (long nr=0; nr<_nrows; nr++)
    _M[nr].unit(nr);
}
//---------------------------------------------------------
template <class T>
void Matrix<T>::setcol (const long ncol, const Vector<T>& v)
{
  #if EXCEPTIONHANDLING
    if ((ncol<0) || (ncol>=_ncols))
      throw Range("Matrix::setcol(ncol)");
  #endif
  for (long nr=0; nr<_nrows; nr++)
    _M[nr][ncol] = v[nr];
}
//---------------------------------------------------------
template <class T>
inline void Matrix<T>::setrow  (const long nrow, const Vector<T>& v)
{
  #if EXCEPTIONHANDLING
    if ((nrow<0) || (nrow>=_nrows))
      throw Range("Matrix::setrow(nrow)");
  #endif
  _M[nrow]=v;
}
//---------------------------------------------------------
template <class T>
void Matrix<T>::insertrow (const long beforerow, const Vector<T>& v)
{
  #if EXCEPTIONHANDLING
    if ((beforerow<0) || (beforerow>_nrows))
      throw Range("Matrix::insertrow(beforerow,v)");
  #endif
  Matrix<T> dummy(_nrows+1,_ncols);
  for (long nr=0; nr<beforerow; nr++)
    dummy._M[nr] = _M[nr];
  dummy._M[beforerow] = v;
  for (long nr=beforerow+1; nr<=_nrows; nr++)
    dummy._M[nr] = _M[nr-1];
  *this = dummy;
}
//---------------------------------------------------------
template <class T>
void Matrix<T>::insertcol (const long beforecol, const Vector<T>& v)
{
  #if EXCEPTIONHANDLING
    if ((beforecol<0) || (beforecol>_ncols))
      throw Range("Matrix::insertcol(beforecol,v)");
  #endif
  Matrix<T> dummy(_nrows,_ncols+1);
  for (long nc=0; nc<beforecol; nc++)
    for (long nr=0; nr<_nrows; nr++)
      dummy._M[nr][nc] = _M[nr][nc];
  for (long nr=0; nr<_nrows; nr++)
    dummy._M[nr][beforecol] = v[nr];
  for (long nc=beforecol+1; nc<=_ncols; nc++)
    for (long nr=0; nr<_nrows; nr++)
      dummy._M[nr][nc] = _M[nr][nc-1];
  *this = dummy;
}
//---------------------------------------------------------
template <class T>
void Matrix<T>::removerow (const long nrow)
{
  #if EXCEPTIONHANDLING
    if ((nrow<0) || (nrow>=_nrows))
      throw Range("Matrix::removerow(nrow)");
  #endif
  Matrix<T> dummy(_nrows-1,_ncols);
  for (long nr=0; nr<nrow; nr++)
    dummy._M[nr] = _M[nr];
  for (long nr=nrow+1; nr<_nrows; nr++)
    dummy._M[nr-1] = _M[nr];
  *this = dummy;
}
//---------------------------------------------------------
template <class T>
void Matrix<T>::removecol (const long ncol)
{
  #if EXCEPTIONHANDLING
    if ((ncol<0) || (ncol>_ncols))
      throw Range("Matrix::removecol(ncol)");
  #endif
  Matrix<T> dummy(_nrows,_ncols-1);
  for (long nc=0; nc<ncol; nc++)
    for (long nr=0; nr<_nrows; nr++)
      dummy._M[nr][nc] = _M[nr][nc];
  for (long nc=ncol+1; nc<_ncols; nc++)
    for (long nr=0; nr<_nrows; nr++)
      dummy._M[nr][nc-1] = _M[nr][nc];
  *this = dummy;
}
//---------------------------------------------------------
template <class T>
void Matrix<T>::setdiag (const Vector<T>& v)
{
  #if EXCEPTIONHANDLING
    if (_nrows != _ncols)
      throw IllegalOperation("Matrix::setdiag() => Matrix not square");
  #endif
  for (long nr=0; nr<_nrows; nr++)
    _M[nr][nr] = v[nr];
}
//---------------------------------------------------------
template <class T>
void Matrix<T>::setdiag (const T& x)
{
  #if EXCEPTIONHANDLING
    if (_nrows != _ncols)
      throw IllegalOperation("Matrix::setdiag() => Matrix not square");
  #endif
  for (long nr=0; nr<_nrows; nr++)
    _M[nr][nr] = x;
}
//---------------------------------------------------------
template <class T>
Vector<T> Matrix<T>::diag () const
{
  #if EXCEPTIONHANDLING
    if (_nrows != _ncols)
      throw IllegalOperation("Matrix::diag() => Matrix not square");
  #endif
  Vector<T> vdiag(_nrows);
  for (long nr=0; nr<_nrows; nr++)
    vdiag[nr] = _M[nr][nr];
  return vdiag;
}
//---------------------------------------------------------
template <class T>
T Matrix<T>::trace () const
{
  #if EXCEPTIONHANDLING
    if (_nrows != _ncols)
      throw IllegalOperation("Matrix::diag() => Matrix not square");
  #endif
  T sum=0;
  for (long nr=0; nr<_nrows; nr++)
    sum += _M[nr][nr];
  return sum;
}
//---------------------------------------------------------
template <class T>
void Matrix<T>::swaprows (const long r1, const long r2)
{
  if (r1 != r2)
  {
    #if EXCEPTIONHANDLING
      if ((r1<0) || (r1>=_nrows) || (r2<0) || (r2>=_nrows))
        throw Range("Matrix::swaprows(r1,r2)");
    #endif
    Vector<T> dummy = _M[r1];
    _M[r1] = _M[r2];
    _M[r2] = dummy;
  }
}
//---------------------------------------------------------
template <class T>
void Matrix<T>::swapcols (const long c1, const long c2)
{
  if (c1 != c2)
  {
    #if EXCEPTIONHANDLING
      if ((c1<0) || (c1>=_ncols) || (c2<0) || (c2>=_ncols))
        throw Range("Matrix::swapcols(c1,c2)");
    #endif
    for (long nr=0; nr<_nrows; nr++)
    {
      T dummy = _M[nr][c1];
      _M[nr][c1] = _M[nr][c2];
      _M[nr][c2] = dummy;
    }
  }
}
//---------------------------------------------------------
template <class T>
Matrix<T> Matrix<T>::transpose () const
{
  Matrix<T> result(_ncols,_nrows);
  for (long nr=0; nr<_nrows; nr++)
    for (long nc=0; nc<_ncols; nc++)
      result._M[nc][nr] = _M[nr][nc];
  return result;
}
//---------------------------------------------------------
template <class T>
Matrix<T> Matrix<T>::hadamamardproduct (const Matrix<T>& M) const
{
  Matrix<T> result(M);
  for (long nr=0; nr<_nrows; nr++)
    for (long nc=0; nc<_ncols; nc++)
      result._M[nr][nc] *= _M[nr][nc];
  return result;
}
//---------------------------------------------------------
template <class T>
inline Vector<T>& Matrix<T>::operator[] (const long nrow) const
{
  #if EXCEPTIONHANDLING
    if ((nrow<0) || (nrow >= _nrows))
      throw Range("Matrix::operator[] (nrow)");
  #endif
  return _M[nrow];
}
//---------------------------------------------------------
template <class T>
Vector<T> Matrix<T>::operator() (const long ncol) const
{
  #if EXCEPTIONHANDLING
    if ((ncol<0) || (ncol >= _ncols))
      throw Range("Matrix::operator() (ncol)");
  #endif
  Vector<T> result(_nrows);
  for (long nr=0; nr<_nrows; nr++)
     result[nr] = _M[nr][ncol];
  return result;
}
//---------------------------------------------------------
template <class T>
Matrix<T>& Matrix<T>::operator= (const Matrix<T>& M)
{
  if (this != &M)
  {
    if (_nrows!=M._nrows)
    {
      delete[] _M;
      _M = new Vector<T>[M._nrows];
      _nrows = M._nrows;
    }
    for (long nr=0; nr<M._nrows; nr++)
      _M[nr] = M._M[nr];
    _ncols = M._ncols;
  }
  return *this;
}
//---------------------------------------------------------
template <class T>
Matrix<T> Matrix<T>::operator+ (const Matrix<T>& M) const
{
  #if EXCEPTIONHANDLING
    if ((_nrows!=M._nrows) || (_ncols!=M._ncols))
      throw IllegalOperation("Matrix::operator+(Matrix)");
  #endif
  Matrix<T> result(*this);
  for (long nr=0; nr<_nrows; nr++)
    result._M[nr] += M._M[nr];
  return result;
}
//---------------------------------------------------------
template <class T>
Matrix<T> Matrix<T>::operator- (const Matrix<T>& M) const
{
  #if EXCEPTIONHANDLING
    if ((_nrows!=M._nrows) || (_ncols!=M._ncols))
      throw IllegalOperation("Matrix::operator-(Matrix)");
  #endif
  Matrix<T> result(*this);
  for (long nr=0; nr<_nrows; nr++)
    result._M[nr] -= M._M[nr];
  return result;
}
//---------------------------------------------------------
template <class T>
Matrix<T> Matrix<T>::operator* (const Matrix<T>& M) const
{
  #if EXCEPTIONHANDLING
    if (_ncols!=M._nrows)
      throw IllegalOperation("Matrix::operator*(Matrix)");
  #endif
  Matrix<T> result(_nrows,M._ncols);
  for (long nr1=0; nr1<_nrows; nr1++)
    for (long nc2=0; nc2<M._ncols; nc2++)
    {
      T sum=0;
      for (long nc1=0; nc1<_ncols; nc1++)
        sum += _M[nr1][nc1]*M._M[nc1][nc2];
      result._M[nr1][nc2]=sum;
    }
  return result;
}
//---------------------------------------------------------
template <class T>
Vector<T> Matrix<T>::operator* (const Vector<T>& v) const
{
  #if EXCEPTIONHANDLING
    if (_ncols!=v.length())
      throw IllegalOperation("Matrix::operator*(Vector)");
  #endif
  Vector<T> result(_nrows);
  for (long nr=0; nr<_nrows; nr++)
  {
    T sum=0;
    for (long nc=0; nc<_ncols; nc++)
      sum += _M[nr][nc]*v[nc];
    result[nr]=sum;
  }
  return result;
}
//---------------------------------------------------------
template <class T>     // Kroneckerproduct
Matrix<T> Matrix<T>::operator% (const Matrix<T>& M) const
{
  Matrix<T> result(_nrows*M._nrows,_ncols*M._ncols);

  for (long nr1=0; nr1<_nrows; nr1++)
    for (long nc1=0; nc1<_ncols; nc1++)
      for (long nr2=0; nr2<M._nrows; nr2++)
        for (long nc2=0; nc2<M._ncols; nc2++)
          result._M[nr1*M._nrows+nr2][nc1*M._ncols+nc2]
            = _M[nr1][nc1]*M._M[nr2][nc2];
  return result;
}
//---------------------------------------------------------
template <class T>
Matrix<T> Matrix<T>::operator+ (const Vector<T>& v) const
{
  #if EXCEPTIONHANDLING
    if ((_nrows==_ncols) && (_nrows==v.length()))
      throw IllegalOperation("Matrix::operator+(Vector)");
  #endif
  Matrix<T> result(*this);
  for (long nr=0; nr<_nrows; nr++)
    result._M[nr][nr] += v[nr];
  return result;
}
//---------------------------------------------------------
template <class T>
Matrix<T> Matrix<T>::operator- (const Vector<T>& v) const
{
  #if EXCEPTIONHANDLING
    if ((_nrows==_ncols) && (_nrows==v.length()))
      throw IllegalOperation("Matrix::operator-(Vector)");
  #endif
  Matrix<T> result(*this);
  for (long nr=0; nr<_nrows; nr++)
    result._M[nr][nr] -= v[nr];
  return result;
}
//---------------------------------------------------------
template <class T>
Matrix<T> Matrix<T>::operator+ (const T& x) const
{
  Matrix<T> result(*this);
  for (long nr=0; nr<_nrows; nr++)
    result._M[nr][nr] += x;
  return result;
}
//---------------------------------------------------------
template <class T>
Matrix<T> Matrix<T>::operator- (const T& x) const
{
  Matrix<T> result(*this);
  for (long nr=0; nr<_nrows; nr++)
    result._M[nr][nr] -= x;
  return result;
}
//---------------------------------------------------------
template <class T>
Matrix<T> Matrix<T>::operator* (const T& x) const
{
  Matrix<T> result(*this);
  for (long nr=0; nr<_nrows; nr++)
    for (long nc=0; nc<_ncols; nc++)
      result._M[nr][nc] *= x;
  return result;
}
//---------------------------------------------------------
template <class T>
Matrix<T> Matrix<T>::operator/ (const T& x) const
{
  Matrix<T> result(*this);
  for (long nr=0; nr<_nrows; nr++)
    for (long nc=0; nc<_ncols; nc++)
      result._M[nr][nc] /= x;
  return result;
}
//---------------------------------------------------------
template <class T>
inline void Matrix<T>::operator+= (const Matrix<T>& M)
{*this = *this+M;}
//---------------------------------------------------------
template <class T>
inline void Matrix<T>::operator-= (const Matrix<T>& M)
{*this = *this-M;}
//---------------------------------------------------------
template <class T>
inline void Matrix<T>::operator*= (const Matrix<T>& M)
{*this = *this*M;}
//---------------------------------------------------------
template <class T>
inline void Matrix<T>::operator%= (const Matrix<T>& M)
{*this = *this%M;}
//---------------------------------------------------------
template <class T>
inline void Matrix<T>::operator+= (const Vector<T>& v)
{*this = *this+v;}
//---------------------------------------------------------
template <class T>
inline void Matrix<T>::operator-= (const Vector<T>& v)
{*this = *this-v;}
//---------------------------------------------------------
template <class T>
inline void Matrix<T>::operator+= (const T& x)
{*this = *this+x;}
//---------------------------------------------------------
template <class T>
inline void Matrix<T>::operator-= (const T& x)
{*this = *this+x;}
//---------------------------------------------------------
template <class T>
inline void Matrix<T>::operator*= (const T& x)
{*this = *this+x;}
//---------------------------------------------------------
template <class T>
inline void Matrix<T>::operator/= (const T& x)
{*this = *this+x;}
//---------------------------------------------------------
// Friend operators
/*
template <class T>
Matrix<T> operator% (const Vector<T>& v1,const Vector<T>& v2)
{
  long lv1=v1.length();
  long lv2=v2.length();

  Matrix<T> result(lv1,lv2);
  for (long nr=0; nr<lv1; nr++)
    for (long nc=0; nc<lv2; nc++)
      result._M[nr][nc] = v1[nr]*v2[nc];
  return result;
}
//---------------------------------------------------------
template <class T>
inline Matrix<T> operator+ (const T& x,const Matrix<T>& M)
{return M+x;}
//---------------------------------------------------------
template <class T>
Matrix<T> operator- (const T& x,const Matrix<T>& M)
{
  Matrix<T> result(M._nrows,M._ncols);
  for (long nr=0; nr<M._nrows; nr++)
    for (long nc=0; nc<M._ncols; nc++)
      result.M[nr][nc] = x - M._M[nr][nc];
  return result;
}
//---------------------------------------------------------
template <class T>
inline Matrix<T> operator* (const T& x,const Matrix<T>& M)
{return M*x;}
//---------------------------------------------------------
template <class T>
Vector<T> operator* (const Vector<T>& v,const Matrix<T>& M)
{
  #if EXCEPTIONHANDLING
    if (M._nrows!=v.length())
      throw IllegalOperation("operator*(Vector,Matrix)");
  #endif
  Vector<T> result(_ncols);
  for (long nc=0; nc<_ncols; nc++)
  {
    T sum=0;
    for (long nr=0; nr<_nrows; nr++)
      sum += v[nr]*M[nr][nc];
    result[nc]=sum;
  }
  return result;
}
//---------------------------------------------------------
template <class T>
istream& operator>> (istream& is, Matrix<T>& M)
{
  for  (long nr=0; nr<M._nrows; nr++)
    for (long nc=0; nc<M._ncols; nc++)
       is >> M[nr][nc];
  return is;
}
//---------------------------------------------------------
template <class T>
ostream& operator<< (ostream& os, const Matrix<T>& M)
{
  for (long nr=0; nr<M._nrows; nr++)
  {
    os << "| ";
    for (long nc=0; nc<M._ncols; nc++)
    {
      os.precision(M._precision); os.width(M._width);
      os << M._M[nr][nc] << ' ';
    }
    os << "|" << endl;
  }
  return os;
}
*/
template <class T>
Matrix<T> operator% (const Vector<T>& v1,const Vector<T>& v2)
{
  long lv1=v1.length();
  long lv2=v2.length();

  Matrix<T> result(lv1,lv2);
  for (long nr=0; nr<lv1; nr++)
    for (long nc=0; nc<lv2; nc++)
      result[nr][nc] = v1[nr]*v2[nc];
  return result;
}
//---------------------------------------------------------
template <class T>
inline Matrix<T> operator+ (const T& x,const Matrix<T>& M)
{return M+x;}
//---------------------------------------------------------
template <class T>
Matrix<T> operator- (const T& x,const Matrix<T>& M)
{
  Matrix<T> result(M._nrows,M._ncols);
  for (long nr=0; nr<M._nrows; nr++)
    for (long nc=0; nc<M._ncols; nc++)
      result[nr][nc] = x - M[nr][nc];
  return result;
}
//---------------------------------------------------------
template <class T>
inline Matrix<T> operator* (const T& x,const Matrix<T>& M)
{return M*x;}
//---------------------------------------------------------
template <class T>
Vector<T> operator* (const Vector<T>& v,const Matrix<T>& M)
{
  #if EXCEPTIONHANDLING
    if (M._nrows!=v.length())
      throw IllegalOperation("operator*(Vector,Matrix)");
  #endif
  Vector<T> result(_ncols);
  for (long nc=0; nc<_ncols; nc++)
  {
    T sum=0;
    for (long nr=0; nr<_nrows; nr++)
      sum += v[nr]*M[nr][nc];
    result[nc]=sum;
  }
  return result;
}
//---------------------------------------------------------
template <class T>
istream& operator>> (istream& is, Matrix<T>& M)
{
  for  (long nr=0; nr<M._nrows; nr++)
    for (long nc=0; nc<M._ncols; nc++)
       is >> M[nr][nc];
  return is;
}
//---------------------------------------------------------
template <class T>
ostream& operator<< (ostream& os, const Matrix<T>& M)
{
  for (long nr=0; nr<M.rows(); nr++)
  {
    os << "| ";
    for (long nc=0; nc<M.cols(); nc++)
    {
      os.precision(M.outputprecision()); os.width(M.outputwidth());
      os << M[nr][nc] << ' ';
    }
    os << "|" << endl;
  }
  return os;
}
\end{verbatim}
