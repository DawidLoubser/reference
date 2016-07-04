#ifndef ChequeAccount_c
#define ChequeAccount_c

#include <string.h>

#include "Account.h"
#include "ChequeAccount.h"
#include "Object.c"
#include "Account.c"

/*================== Cheque Account State Structure =====================*/

/* NOTE: Since ChequeAccount subclasses Account it must have the fields
         of AccountState in the same order and may only add to these fields. */

struct ChequeAccountState 
{
  char * accountNo;
  double balance;
  double chequeFee;
};

const struct ChequeAccountInterface * const ChequeAccount;

/*========================Cheque Account Class ==========================*/

/* NOTE: Since ChequeAccount subclasses Account it must have the methods
         of AccountClass in the same order and may only add to these methods. */

struct ChequeAccountClass
{
  void *             (*create)       (const char * const accountNo, double chequeFee);
  
  const char * const (*getAccountNo) (const void * const this);
  double             (*getBalance)   (const void * const this);
  void               (*credit)       (const void * const this, const double amount);
  int                (*debit)        (const void * const this, const double amount);
  void               (*print)        (const void * const this);
  
  void               (*delete)       (void * * this);
  
  double             (*getChequeFee) (const void * const this);
};

const struct ChequeAccountClass * const chequeAccountClass;

/*==================== Methods of  ChequeAccount Class ================*/

static int debit_ChequeAccount(const void * const state, double  amount)
{
  double chequeFee = ((struct ChequeAccountState*)state)->chequeFee;
  return debit_Account(state, amount+chequeFee); /* calling superclass */
};  

static double getChequeFee_ChequeAccount(const void * const state)
{
  return ((struct ChequeAccountState*)state)->chequeFee;
};  

static void print_ChequeAccount (const void * const state)
{
  struct ChequeAccountState * chequeAccountState = (struct ChequeAccountState*)state;
  
  print_Account(state);  /* calling superclass */
  printf(", cheque fee = %10.2lf", chequeAccountState->chequeFee);
};

static void * create_ChequeAccount(const char * const accountNo, const double chequeFee)
{
  struct ChequeAccountState * newInstanceState 
    = (struct ChequeAccountState *)calloc(1, sizeof(struct ChequeAccountState));

  newInstanceState->accountNo = (char *)malloc(strlen(accountNo)+1);
  
  strcpy(newInstanceState->accountNo, accountNo);

  newInstanceState->balance = 0;
  newInstanceState->chequeFee = chequeFee;
  
  struct Object *object = (struct Object *)malloc(sizeof(struct Object));
  
  object->state = newInstanceState;
  object->class = (void *)chequeAccountClass;

  return (void *)object;
};
  
static void delete_ChequeAccount(void * * this)
{
  struct ChequeAccountState * * chequeAccountState = (struct ChequeAccountState * *)this;

  free ((*chequeAccountState)->accountNo);
  free (*chequeAccountState);
  
  *(struct Object * *)this = 0;  /* set instance pointer to NULL to make it safe. */
};  

/*======== Setting the pointers for the Cheque Account Methods ===========*/

/* Here we initialize the funnction pointers for the instance and class 
   methods. Instance methods get the instance reference (object reference)
   as argument, class services (static methods) do not. 
   NOTE: Some methods are bound to the subclass and others are directly
         bound to the superclass (inheritance).*/

static const struct ChequeAccountClass theChequeAccountClass =
{
  create_ChequeAccount,       /* Constructor */ 
  
  getAccountNo_Account,
  getBalance_Account,
  credit_Account,    
  debit_ChequeAccount,
  print_ChequeAccount,
  
  delete_ChequeAccount,
  getChequeFee_ChequeAccount
};  

const struct ChequeAccountClass * const chequeAccountClass = &theChequeAccountClass;

/*=========== The interface methods doing the method mapping =============*/

/* The methods query the instance (object) for its class which is only known
   at run-time and then request the service from the class supplying the
   instance pointer as argument. This facilitates polymorphism. */

static double getChequeFee_ChequeAccountInterface(const void * const this)
{
  struct Object * instance = (struct Object *)this;
  
  struct ChequeAccountClass * class = (struct ChequeAccountClass *)instance->class;
  struct ChequeAccountState * state = (struct ChequeAccountState *)instance->state;
  return class->getChequeFee((void *)state);
}  

static int debit_ChequeAccountInterface(const void * const this, double amount)
{
  struct Object * instance = (struct Object *)this;
  
  struct ChequeAccountClass * class = (struct ChequeAccountClass *)instance->class;
  struct ChequeAccountState * state = (struct ChequeAccountState *)instance->state;
  return class->debit((void *)state, amount);
}  

static void print_ChequeAccountInterface(const void * const this)
{
  struct Object * instance = (struct Object *)this;
  
  struct ChequeAccountClass * class = (struct ChequeAccountClass *)instance->class;
  struct ChequeAccountState * state = (struct ChequeAccountState *)instance->state;
  
  class->print(state);
}  

/* destructor is a dynamically bound instance method */

static void delete_ChequeAccountInterface(void * * this)
{
  struct Object * * instance = (struct Object * *)this;
  
  struct ChequeAccountClass * class = (struct ChequeAccountClass *)(*instance)->class;
  struct ChequeAccountState * state = (struct ChequeAccountState *)(*instance)->state;
  struct ChequeAccountState * * state_p = &state;
  
  class->delete((void * *)state_p);
}  

/* Constructor statically bound */

static void * create_ChequeAccountInterface(const char * const accountNo,
                                                        double chequeFee)
{
  return create_ChequeAccount(accountNo, chequeFee);
}  

/*=========== Setting the pointers for the Cheque Account Methods =======*/

static const struct ChequeAccountInterface theChequeAccountInterface =
{
  create_ChequeAccountInterface,       

  getAccountNo_AccountInterface,
  getBalance_AccountInterface,   
  credit_AccountInterface,   
      
  debit_ChequeAccountInterface,        
  print_ChequeAccountInterface,        

  delete_ChequeAccountInterface,   
       
  getChequeFee_ChequeAccountInterface
};  

/* Specifying a non-modifiable handle to the interface. This public interface is used by
   users to access cheque account objects (as  well as possibly subclass instances of
   ChequeAccount. */
const struct ChequeAccountInterface * const ChequeAccount = &theChequeAccountInterface;

#endif
