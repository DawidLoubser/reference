\begin{verbatim}
//                        INTRST1.CPP
//---------------------------------------------------------------

/* Calculates the return of an investment after being invested at
   a fixed interest rate (either compounded daily or compounded
   monthly). */

   #include <iostream.h>  // for cin and cout
   #include <stdlib.h>    // for exit()

void main()
{

  float invest, rate;  // declaring 2 floating point variables

  enum CompoundingType {daily,monthly,unknown} compounding;

   /* CompoundType is an enumeration type which can take the values
      daily and monthly and compounding is declared as a variable of
      that type. */

  char inputchar;  // declaring a character (byte) variable

  cout << "Enter amount invested: R";
  cin  >> invest;

  if (invest < 0)
  {
    cout << "*** ERROR *** : negative amount invested" << endl;
    exit(0);
      /* exit terminates the process. Before termination all files
         are closed and all buffered output is written. */
  }

  cout << "Enter interest rate (%/year) : ";
  cin  >> rate;

  if (rate < 0)
    cout << "*** Warning *** : entered negative interest rate" << endl;

  compounding = unknown;

  while (compounding == unknown)
  {
    cout << "Enter the compounding period (d for daily or m for monthly): ";
    cin  >> inputchar;
    switch (inputchar)
    {
      case 'm': compounding = monthly;
                break;
      case 'd': compounding = daily;
                break;
      default:  compounding = unknown;
                cout << "unknown compounding period entered" << endl;
                break;
    }
  }

  if (compounding == monthly)
    {
      int nomonths;  // declared local to this block { ... }

      cout << "Enter number of months invested : ";
      cin  >> nomonths;

      for (int nm=1; nm <= nomonths; nm++)
        invest += invest * rate/(float)(100.0*12.0);
          // typecasting to float (Visual C++ defaults to double)

      cout << "The investment after " << nomonths
           << " months is R" << invest << endl;
    }
  else
    {
      long int nodays;
      cout << "Enter number of days invested : ";
      cin >> nodays;

      for (int nd=1; nd <= nodays; nd++)
        invest += invest * rate/(float)(100.0*365.0);

      cout << "The investment after " << nodays
           << " days is R" << invest << endl;
   }
}

\end{verbatim}
