\begin{verbatim}
#include <vector>
#include <iostream>

#include "Person.h"

using namespace std;

void print(vector<double>& v)
{
  cout << "v = [ ";
  for (vector<double>::iterator iter = v.begin();
       iter != v.end(); ++iter)
           cout << (*iter) << " ";
  cout << "]" << endl;
}

int main()
{
  vector<double> v(20);
  v[0] = 11.1;   v[10] = 1.2;
  v[1] = 11.5;   v[11] = 2.2;
  v[2] = 8.1;    v[12] = 2.1;
  v[3] = 9.11;   v[13] = 1.3;
  v[4] = 6.35;   v[14] = 4.2;
  v[5] = 11.1;   v[15] = 1.3;
  v[6] = 8.45;   v[16] = 4.1;
  v[7] = 9.67;   v[17] = 1.2;
  v[8] = 9.76;   v[18] = 1.7;
  v[9] = 9.45;   v[19] = 6.3;

  cout << "Before sorting: " << endl;
  print(v);

  sort(v.begin(), v.end());

  cout << "After sorting: " << endl;
  print(v);

  v.insert(v.begin(), -999);

  v.insert(v.end(), 999);

  cout << "After inserting: " << endl;
  print(v);

  vector<Person*> persons(3);
  persons[0] = new Person("Jack Hill", "6541654");
  persons[1] = new Person("Jill Crack", "6641654");
  persons[2] = new Person("Jack Jillian", "6741654");
  for (vector<Person*>::iterator iter = persons.begin();
       iter != persons.end(); ++iter)
           cout << (**iter) << " ";
  cout << endl;

  double* dvec = new double[3];
  dvec[0] = 2.1;
  dvec[1] = 1.2;
  dvec[2] = 3.1;

  double* end = &dvec[2];

  sort(dvec, end);

  cout << dvec[0] << " " << dvec[1] << " " << dvec[2] << endl;

  char c; cin >> c;

  return 0;
}
\end{verbatim}
