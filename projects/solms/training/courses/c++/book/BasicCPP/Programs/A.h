\begin{verbatim}
#ifndef A_H
#define A_H
class A
{
  public:
    void f();

  private: int k;

  friend void f(A& a);
};
#endif
\end{verbatim}
