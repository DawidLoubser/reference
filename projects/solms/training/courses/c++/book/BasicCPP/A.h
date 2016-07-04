\begin{verbatim}

class A
{
  public:
    void f();

  private: int k;

  friend void f(A& a);
};
\end{verbatim}
