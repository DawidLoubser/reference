\begin{verbatim}
// ClientTest.cpp.h
//
#include <iostream>
#include "Client.h"

using namespace std;

int main()
{
  Client* c1 = new Client("Peter");
  Client* c2 = new Client("Jill");

  cout << "numClients = " << Client::numInstances() << endl;

  delete c1;

  cout << "numClients = " << Client::numInstances() << endl;

  delete c2;

  cout << "numClients = " << Client::numInstances() << endl;

  char c; cin >> c;
  
  return 0;
}
\end{verbatim}
