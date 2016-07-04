#ifndef ChequeAccount_h
#define ChequeAccount_h

struct ChequeAccountInterface
{
  void *             (*create)       (const char * const accountNo, const double chequeFee);
  const char * const (*getAccountNo) (const void * const this);
  double             (*getBalance)   (const void * const this);
  void               (*credit)       (const void * const this, const double amount);
  int                (*debit)        (const void * const this, const double amount);
  void               (*print)        (const void * const this); 
  void               (*delete)       (void * * this);
  
  double             (*getChequeFee) (const void * const this);
};

extern const struct ChequeAccountInterface * const ChequeAccount;
#endif
