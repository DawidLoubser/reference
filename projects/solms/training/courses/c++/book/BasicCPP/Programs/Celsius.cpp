\begin{verbatim}
//                         CELSIUS.CPP
//-------------------------------------------------------------------
//  This program converts degrees celsius to degrees Fahrenheit
//  and vise versa. It can also print a table of comparison
//  for a certain temperature range.

#include <ctype.h>       // for toupper (convert to upper case)
#include <iostream>      // for cin and cout

#include <fstream>

using namespace std;

const double a1 = 1.8;             // Constants required for conversions
const double a0 = 32;              // between 'C and 'F.

double Fahrenheit(const double celsius)    // Function converting 'C to 'F.
{
  return a1*celsius+a0;
}

double Celsius(const double fahrenheit)    // Function converting 'F to 'C.
{
  return (fahrenheit-a0)/a1;
}

int main()                       // Main program
{
  char choice;
  bool happy;
  do
  {
    cout << "C  ->  convert degrees celsius to degrees fahrenheit"
         << endl;
    cout << "F  ->  convert degrees fahrenheit to degrees celsius"
         << endl;
    cout << endl << "Enter choice (C/F) : ";
    cin  >> choice;

    choice = (char)toupper(choice);  // convert to upper case
    happy  = ((choice == 'C') || (choice == 'F'));

    if (!happy)
      cout << "*** ERROR *** : Illegal choice: ReEnter." << endl;

  } while (!happy);

  char table;
  cout << "Do you want a table of degrees celsius "
       << "versus degrees fahrenheit (y/n)? ";
  cin  >> table;

  if ((table == 'n') || (table == 'N'))
    {
      switch (choice)
      {
        case 'C': double celsius;
                  cout << "Enter degrees celsius : ";
                  cin  >> celsius;
                  cout << celsius << " degrees celsius = "
                       << Fahrenheit(celsius)
                       << " degrees fahrenheit." << endl;
                  break;

        case 'F': double fahrenheit;
                  cout << "Enter degrees fahrenheit : ";
                  cin  >> fahrenheit;
                  cout << fahrenheit << " degrees fahrenheit = "
                       << Celsius(fahrenheit) << " degrees celsius."
                       << endl;
                  break;
      }
    }
  else
    {
      double lower, upper, step;
      cout << "Enter lower limit of range : ";
      cin  >> lower;
      cout << "Enter upper limit of range : ";
      cin  >> upper;
      cout << "Enter increment : ";
      cin  >> step;

      double temp = lower;

      while (temp <= upper)
      {
        switch (choice)
        {
          case 'C': cout << temp << " <-> " << Fahrenheit(temp)
                         << endl;
                    break;

          case 'F': cout << temp << " <-> " << Celsius(temp)
                         << endl;
                    break;
        }
        temp += step;
      }
    }
    char c; cin >> c;
    return 0;
}
\end{verbatim}
