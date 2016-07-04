\begin{verbatim}
#ifndef AccountH
#define AccountH
namespace SolmsTraining
{
 namespace finance
 {
  class Account; // Forward declaration of Account

  class InsufficientFunds
  {
    public:
      InsufficientFunds(Account* const source,
                        const double availableFunds)
         : _source(source), _availableFunds(availableFunds) {}

      Account* const getSource() const {return _source;}

      double availableFunds() {return _availableFunds;}

    private:
      Account* const _source;
      const double _availableFunds;
  };

  class Account
  {
    public:
      Account();
      Account(double initialBalance);

      ~Account();

      void credit(double amount);
      void debit(double amount) throw (InsufficientFunds);

      double balance();

    private:
      double _balance;
  };
 }
}

#endif
\end{verbatim}
