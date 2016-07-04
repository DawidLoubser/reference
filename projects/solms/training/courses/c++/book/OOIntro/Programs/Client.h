\begin{verbatim}
// Client.h
//
#ifndef Client_H
#define Client_H
class Client
{
  public:
    Client(char* name);

    ~Client();

    static int numInstances();

  private:
    char* _name;
    static int _numInstances;
};
#endif
\end{verbatim}
