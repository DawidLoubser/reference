#include <string.h>

#include "Account.h"

struct AccountState
{
  char * accountNo;
  double balance;
};

const struct AccountClass * const Account;

/* NOTE: All methods declared static (private to file) and can hence
         not be accessed directly, only through the interface, Account
         defined above which has method pointers assigned to these
         methods. */
static double getBalance_Account(const void * const this)
{
  return ((struct AccountState*)this)->balance;
};  

static const char * const getAccountNo_Account (const void * const this)
{
  return ((struct AccountState*)this)->accountNo;
};

static void credit_Account(const void * const this, const double amount)
{
  ((struct AccountState*)this)->balance += amount;  
};

static int debit_Account(const void * const this, const double amount)
{
  if (amount  > ((struct AccountState*)this)->balance)
    return -1;
  else
    {  
      ((struct AccountState*)this)->balance -= amount;  
      return 0;
    }  
};

static void * create_Account(const char * const accountNo)
{
  struct AccountState * newInstance 
    = (struct AccountState *)calloc(1, sizeof(struct AccountState));

  newInstance->accountNo = (char *)malloc(strlen(accountNo)+1);
  
  strcpy(newInstance->accountNo, accountNo);

  newInstance->balance = 0;

  return newInstance;
};    

static void delete_Account(void * * this)
{
  free (*this);
  *this = 0;
};  

static const struct AccountClass theAccountClass =
{
  create_Account,       /* initializes function pointer for constructor */ 
  getAccountNo_Account,
  getBalance_Account,   /* initializes function pointer to getBalance method */
  credit_Account,       /* initializes function pointer to credit method */
  debit_Account,        /* initializes function pointer to debit method */
  delete_Account        /* initializes function pointer to destructor */
};  

const struct AccountClass * const Account = &theAccountClass;
