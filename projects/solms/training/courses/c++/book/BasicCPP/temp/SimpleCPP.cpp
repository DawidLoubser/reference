\begin{verbatim}
//                     SIMPLCPP.CPP
//-------------------------------------------------------

  #include <iostream.h>  // including library iostream for cout
                         // cout is used to write onto the terminal

  const float version = 1.10; // definition of a global floating
                              // point constant
  float t;                    // definition of a global variable

//-----------------------------------------------------

  float f(float x)  /* This is a function definition for f(x).
                       f takes a floating point variable as a
                       parameter and returns another float.  */
  {
    float y = x + x*x;   // definition of a local variable y
    return y;            // return result of function evaluation
  }

//-----------------------------------------------------

  void main()            // start of the main program
  {
    float t = 3.1;       // declaration of local variables t hiding
                         // the global variable t.
                         // t is initialised to 3.1

    float y = f(t);      // Here the function f is called and the local
                         // variable y is declared and assigned to the
                         // result of the function evaluation.

    cout << "y = " << y << endl;  // x is pushed onto the standard
                                  // output stream (i.e. usually
                                  // written on the computer screen).

  }

\end{verbatim}
