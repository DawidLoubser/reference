#ifndef Account_c
#define Account_c

#include <string.h>

#include "Account.h"
#include "Object.c"

/*====================== Account State Structure ========================*/

struct AccountState
{
  char * accountNo;
  double balance;
};

const struct AccountInterface * const Account;

/*=========================== Account Class =============================*/

struct AccountClass
{
  void *             (*create)       (const char * const accountNo);
  
  const char * const (*getAccountNo) (const void * const this);
  double             (*getBalance)   (const void * const this);
  void               (*credit)       (const void * const this, const double amount);
  int                (*debit)        (const void * const this, const double amount);
  void               (*print)        (const void * const this);
  
  void               (*delete)       (void * * this);
};

const struct AccountClass * const accountClass;

/*====================== Account Methods ================================*/

/* NOTE: All methods declared static (private to file) and can hence
         not be accessed directly, only through the interface, Account
         defined above which has method pointers assigned to these
         methods. Implementation is encapssulated. */

static double getBalance_Account(const void * const state)
{
  return ((struct AccountState*)state)->balance;
};  

static const char * const getAccountNo_Account (const void * const state)
{
  struct AccountState * accountState = (struct AccountState*)state;
  return (accountState)->accountNo;
};

static void credit_Account(const void * const state, const double amount)
{
  ((struct AccountState*)state)->balance += amount;  
};

static int debit_Account(const void * const state, const double amount)
{
  if (amount  > ((struct AccountState*)state)->balance)
    return -1;
  else
    {  
      ((struct AccountState*)state)->balance -= amount;  
      return 0;
    }  
};

static void print_Account (const void * const state)
{
  struct AccountState * accountState = (struct AccountState*)state;
  
  printf("%s , balance = %10.2lf", accountState->accountNo, accountState->balance);
};

/* Constructor of Account Class */

static void * create_Account(const char * const accountNo)
{
  struct AccountState * newInstanceState 
    = (struct AccountState *)calloc(1, sizeof(struct AccountState));

  newInstanceState->accountNo = (char *)malloc(strlen(accountNo)+1);
  
  strcpy(newInstanceState->accountNo, accountNo);

  newInstanceState->balance = 0;
  
  struct Object *object = (struct Object *)malloc(sizeof(struct Object));
  
  object->state = newInstanceState;
  object->class = (void *)accountClass;

  return (void *)object;
};    

/* Destructor of Account Class */

static void delete_Account(void * * this)
{
  struct AccountState * * accountState = (struct AccountState * *)this;

  free ((*accountState)->accountNo);
  free (*accountState);
  
  *(struct Object * *)this = 0;
};  

/*============== Setting the pointers for the Account Methods ===========*/

/* Here we initialize the funnction pointers for the instance and class 
   methods. Instance methods get the instance reference (object reference)
   as argument, class services (static methods) do not. */
   
static const struct AccountClass theAccountClass =
{
  create_Account,       /* initializes function pointer for constructor */ 
  getAccountNo_Account, /* initializes function pointer for getAccountNo method. */
  getBalance_Account,   /* getBalance method */
  credit_Account,       /* credit method */
  debit_Account,        /* debit method */
  print_Account,        /* print method */
  delete_Account        /* initializes function pointer to destructor */
};  

/* Specifying a non-modifiable handle to the class with the function pointers
   fixed. */
const struct AccountClass * const accountClass = &theAccountClass;

/*======= Account Interface implements Virtual Method Table ========== */

/* The methods query the instance (object) for its class which is only known
   at run-time and then request the service from the class supplying the
   instance pointer as argument. This facilitates polymorphism. */
   
static const char * const getAccountNo_AccountInterface(const void * const this)
{
  struct Object * instance = (struct Object *)this;
  
  struct AccountClass * class = (struct AccountClass *)instance->class;
  struct AccountState * state = (struct AccountState *)instance->state;
  const char * const accountNo = class->getAccountNo(state);
  return accountNo;
}  

static double getBalance_AccountInterface(const void * const this)
{
  struct Object * instance = (struct Object *)this;
  
  struct AccountClass * class = (struct AccountClass *)instance->class;
  struct AccountState * state = (struct AccountState *)instance->state;
  
  return class->getBalance(state);
}  

static void credit_AccountInterface(const void * const this, const double amount)
{
  struct Object * instance = (struct Object *)this;
  
  struct AccountClass * class = (struct AccountClass *)instance->class;
  struct AccountState * state = (struct AccountState *)instance->state;
  
  class->credit(state, amount);
}  

static int debit_AccountInterface(const void * const this, const double amount)
{
  struct Object * instance = (struct Object *)this;
  
  struct AccountClass * class = (struct AccountClass *)instance->class;
  struct AccountState * state = (struct AccountState *)instance->state;
  
  return class->debit(state, amount);
}  

static void print_AccountInterface(const void * const this)
{
  struct Object * instance = (struct Object *)this;
  
  struct AccountClass * class = (struct AccountClass *)instance->class;
  struct AccountState * state = (struct AccountState *)instance->state;
  
  class->print(state);
}  

/* destructor is a dynamically bound innstance method */

static void delete_AccountInterface(void * * this)
{
  struct Object * * instance = (struct Object * *)this;
  
  struct AccountClass * class = (struct AccountClass *)(*instance)->class;
  struct AccountState * state = (struct AccountState *)(*instance)->state;
  struct AccountState * * state_p = &state;
  
  class->delete((void * *)state_p);
}  

/* Constructor statically bound */

static void * create_AccountInterface(const char * const accountNo)
{
  return create_Account(accountNo);
}  

/*============== Setting the pointers for the Account Interface ===========*/

/* Setting now the function pointers for the interface through which Account
   objeccts are accessed. */

static const struct AccountInterface theAccountInterface =
{
  create_AccountInterface,
         
  getAccountNo_AccountInterface,
  getBalance_AccountInterface,   
  credit_AccountInterface,       
  debit_AccountInterface,        
  print_AccountInterface,
  
  delete_AccountInterface        
};  

/* The type users are working with, Account, is actually an interface. Below we
   specify a non-modifiable handle to the interface which is exported in the
   header file. This public interface is used by users to access account 
   objects (as  well as possibly subclass instances of Account). */

const struct AccountInterface * const Account = &theAccountInterface;

#endif
