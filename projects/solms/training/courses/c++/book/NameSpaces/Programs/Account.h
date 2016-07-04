\begin{verbatim}
#ifndef AccountH
#define AccountH
namespace SolmsTraining
{
 namespace finance
 {
  class Account
  {
    public:
      Account();
      Account(double initialBalance);

      ~Account();

      void credit(double amount);
      void debit(double amount);

      double balance();

    private:
      double _balance;
  };
 }
}

#endif
\end{verbatim}
