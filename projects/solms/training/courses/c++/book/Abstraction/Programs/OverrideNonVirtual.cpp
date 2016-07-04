\begin{verbatim}
#include <iostream.h>

class A
{
  public:
    void virtual f()
    {
      cout << "Requested virtual service f() from an instance of A."
           << endl;
    }
    void g()
    {
      cout << "Requested non-virtual service g() from an instance of A."
           << endl;
    }
};

class B: public virtual A
{
  public:
    void virtual f()
    {
      cout << "Requested virtual service f() from an instance of B."
           << endl;
    }
    void g()
    {
      cout << "Requested non-virtual service g() from an instance of B."
           << endl;
    }
};

void main()
{
  B* b = new B();

  A* a = b;   // legal because a B is an A.
  // We have 2 pointers to the same object, an instance of B.

  cout << "Requesting virtual service f():" << endl;
  cout << "  We request the same service from the same object through" << endl;
  cout << "  two different message paths (two different pointers."
       << endl << endl;

  a->f();
  b->f();

  cout << endl << "Behaved as expected. Now requesting non-virtual service g():"
       << endl;
  cout << "  Once again we request the same service from the same object" << endl
       << "  through two different message paths (two different pointers."
       << endl << endl;
          date
  a->g();
  b->g();

  cout << endl << "This time the object behaved differently, depending on the"
       << endl
       << "message path (pointer) used to deliver the service request message."
       << endl;

  char c; cin >> c;
}
\end{verbatim}
