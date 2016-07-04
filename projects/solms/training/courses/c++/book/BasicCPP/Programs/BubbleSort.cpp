\begin{verbatim}
//                        BubbleSort.cpp
//----------------------------------------------------------------
// A Bubble sort algorithm defined on a template. It can hence sort
// an array of any class, e.g. integers, floating point numbers, characters,
// or any user-defined class (e.g. a data base of employees)

#include <iostream>

using namespace std;

template <class T>                             // T is determined by the
void BubbleSort (T vec[], const int length)    // calling program
{
  int exchanged, nleft = length;

  do
  {
    exchanged = 0;
    nleft--;
    for (int n=0; n<nleft; n++)
    {
      if (vec[n] > vec[n+1])  // if next element smaller: exchange
      {
        T dummy  = vec[n+1];
        vec[n+1] = vec[n];
        vec[n]   = dummy;
        exchanged++;
      }
    }
  } while (exchanged);  // if no exchanges in previous loop: done
}

int main()
{
  int lngth=5;
  float* fvec = new float[lngth];    // create an array of floats

  fvec[0]=8.1; fvec[1]=3.7;  fvec[2]=4.2; fvec[3]=9.0; fvec[4]=7.2;

  BubbleSort (fvec, lngth);      // bubble sort this vector

  for (int i=0; i<lngth; i++)    // show result on screen
    cout << "fvec[" << i << "] = " << fvec[i] << endl;

  delete[] fvec;   // vector no longer needed => discard

  cout << endl;

  lngth=6;                       // the same for vector of chars
  char* cvec = new char[lngth];

  cvec[0]='s'; cvec[1]='d'; cvec[2]='r'; cvec[3]='a'; cvec[4]='z';
  cvec[5]='m';

  BubbleSort (cvec, lngth);

  for (int i=0; i<lngth; i++)
    cout << "cvec[" << i << "] = " << cvec[i] << endl;

  delete[] cvec;

  char k; cin >> k;
  
  return 0;
}
\end{verbatim}
