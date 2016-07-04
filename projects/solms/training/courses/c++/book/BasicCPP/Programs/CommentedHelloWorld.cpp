\begin{verbatim}
/*
  This simple australian application simply prints a greeting onto
  the standard output stream -- usually the screen or terminal.
  
  ( A block comment starts with a foraward-slash-star and ends with a 
    star-bask-slash.)
*/

#include <iostream>    // For terminal output via cout.

using namespace std; // The std libary elemments are packaged
                      // in the namespace, std.

int main()
{
  cout << "Hi there, mate!\n";  

  /*
    The following two lines prevent the application from terminating
    such that if the app is run from an IDE, one sees the output.
  */
  char c;
  cin >> c;

  return 0;
}
\end{verbatim}
