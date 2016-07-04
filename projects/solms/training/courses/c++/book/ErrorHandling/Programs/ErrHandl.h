\begin{verbatim}
#ifndef __ERRHANDL_H
#define __ERRHANDL_H

#define EXCEPTIONHANDLING 1

// BASE CLASS FOR ALL EXCEPTION HANDLING
// =====================================
class Exception
{
  public:
    const char const * source;
    Exception(): source(NULL) {};
    Exception(const char const * src): source(src) {};
};

// BASE CLASS FOR ALL MATH ERRORS
// ==============================
class MathError: public Exception
{
  public:
    MathError() {};
    MathError(const char const * src): Exception(src) {};
};

// NOW ALL THE SPECIAL INSTANCES OF MATH ERRORS
// ============================================
class DivideByZero: public MathError
{
  public:
    DivideByZero() {};
    DivideByZero(const char const * src)
      : MathError(src) {};
};

class Overflow: public MathError
{
  public:
    Overflow() {};
    Overflow(const char const * src)
      : MathError(src) {};
};

class IllegalOperation: public MathError
{
  public:
    IllegalOperation() {};
    IllegalOperation(const char const * src)
      : MathError(src) {};
};

// BASE CLASS FOR ALL MEMORY ERRORS
// ================================
class MemoryError: public Exception
{
  public:
    MemoryError() {};
    MemoryError(const char const * src): Exception(src) {};
};

// NOW ALL THE SPECIAL INSTANCES OF MEMORY ERRORS
// ==============================================
class OutOfMemory: public MemoryError
{
  public:
    OutOfMemory() {};
    OutOfMemory(const char const * src)
      : MemoryError(src) {};
};

class Range: public MemoryError
{
  public:
    Range() {};
    Range(const char const * src)
      : MemoryError(src) {};
};

// SOME OTHER EXCEPTIONS
// =====================
class IllegalArguments: public Exception
{
  public:
    IllegalArguments() {};
    IllegalArguments(const char const * src): Exception(src) {};
};

class IllegalCall: public Exception
{
  public:
    IllegalCall() {};
    IllegalCall(const char const * src): Exception(src) {};
};

#endif
\end{verbatim}
