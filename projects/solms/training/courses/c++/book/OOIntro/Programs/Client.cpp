\begin{verbatim}
// Client.cpp
//
#include "Client.h"

Client::Client(char* name)
{
  ++_numInstances;
  _name = name;
}

Client::~Client()
{
  --_numInstances;
}

int Client::numInstances()
{
  return _numInstances;
}

int Client::_numInstances = 0;
\end{verbatim}
