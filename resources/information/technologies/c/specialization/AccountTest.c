#include "Account.h"
#include "ChequeAccount.h"

void processAccount(void * acc)
{
  printf("Processing account -> ");
  Account->print(acc);  /* done polymorphically */
  printf("\n");
  
  Account->credit(acc, 100); /* cheque accounts inherit credit */
  printf("After crediting with 100: "); 
  Account->print(acc);  /* done polymorphically */
  printf("\n");

  Account->debit(acc, 50);   /* done polymorphically */
  printf("After debiting with 50: ");
  Account->print(acc);  /* done polymorphically */
  printf("\n");
}

void testInheritanceAndPolymorphism()
{
   void * account = Account->create("Acc1");
   processAccount(account);   
   Account->delete((void * *)&account);  /* done polymorphically */
   
   account = ChequeAccount->create("ChAcc1", 6);
   processAccount(account);   
   Account->delete((void * *)&account);  /* done polymorphically */
}


int main()
{

  /* Testing inheritance */   
  
  testInheritanceAndPolymorphism(); 
  
  return 0;
};
