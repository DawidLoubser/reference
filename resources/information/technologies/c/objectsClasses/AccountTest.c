#include "Account.h"

int main()
{
  void * account1 = Account->create("6542376");
  
  printf("Created account with object reference %d\n", account1);
  
  printf("Balance of account # %s is %lf\n",
    Account->getAccountNo(account1), Account->getBalance(account1));

  Account->credit(account1, 500);

  if (Account->debit(account1, 200) != 0)
    printf("debit failed.");

  printf("After crediting with 500 and debiting with 200 the balance of account # %s is %lf\n",
    Account->getAccountNo(account1), Account->getBalance(account1));

  Account->delete(&account1);
  
  printf("After deleting the account our object reference is %d\n", account1);

  return 0;
};
