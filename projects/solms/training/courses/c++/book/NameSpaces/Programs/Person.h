\begin{verbatim}
#ifndef Person_H
#define Person_H

#include <iostream>
#include <string>

using namespace std;

class Person
{
  public:
    Person(const string& name, const string& idNo);

    string name() const;

    string idNo() const;

    virtual string toString() const;

  //friends:
    friend ostream& operator<<(ostream& os, const Person&);

  private:
    string _name;
    string _idNo;
};
#endif
\end{verbatim}
