\begin{verbatim}
#ifndef __ERRHANDL_H
#define __ERRHANDL_H

#define EXCEPTIONHANDLING 1

#include <stdexcept>

using namespace std;

// BASE CLASS FOR ALL EXCEPTION HANDLING
// =====================================
class Exception
{
  public:
    const char* source;
    Exception(): source(NULL) {};
    Exception(const char* src): source(src) {};
};

// BASE CLASS FOR ALL MATH ERRORS
// ==============================
class MathError: public Exception
{
  public:
    MathError() {};
    MathError(const char* src): Exception(src) {};
};

// NOW ALL THE SPECIAL INSTANCES OF MATH ERRORS
// ============================================
class DivideByZero: public MathError
{
  public:
    DivideByZero() {};
    DivideByZero(const char* src)
      : MathError(src) {};
};

class Overflow: public MathError
{
  public:
    Overflow() {};
    Overflow(const char* src)
      : MathError(src) {};
};

class IllegalOperation: public MathError
{
  public:
    IllegalOperation() {};
    IllegalOperation(const char* src)
      : MathError(src) {};
};

// BASE CLASS FOR ALL MEMORY ERRORS
// ================================
class MemoryError: public Exception
{
  public:
    MemoryError() {};
    MemoryError(const char* src): Exception(src) {};
};

// NOW ALL THE SPECIAL INSTANCES OF MEMORY ERRORS
// ==============================================
class OutOfMemory: public MemoryError
{
  public:
    OutOfMemory() {};
    OutOfMemory(const char* src)
      : MemoryError(src) {};
};

class Range: public MemoryError
{
  public:
    Range() {};
    Range(const char* src)
      : MemoryError(src) {};
};

// SOME OTHER EXCEPTIONS
// =====================
class IllegalArguments: public Exception
{
  public:
    IllegalArguments() {};
    IllegalArguments(const char* src): Exception(src) {};
};

class IllegalCall: public Exception
{
  public:
    IllegalCall() {};
    IllegalCall(const char* src): Exception(src) {};
};

#endif
\end{verbatim}
