#ifndef Account_h
#define Account_h

struct AccountInterface
{
  void *             (*create)       (const char * const accountNo);
  const char * const (*getAccountNo) (const void * const this);
  double             (*getBalance)   (const void * const this);
  void               (*credit)       (const void * const this, const double amount);
  int                (*debit)        (const void * const this, const double amount);
  void               (*print)        (const void * const this);
  void               (*delete)       (void * * this);
};

extern const struct AccountInterface * const Account;
#endif
