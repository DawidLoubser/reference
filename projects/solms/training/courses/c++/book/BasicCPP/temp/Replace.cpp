\begin{verbatim}
//                    REPLACE.CPP
//---------------------------------------------------------
// Search the contents of a file and replace all occurances
// of oldstr with the string newstr writing the result into
// a new file.

#include <stdlib.h>            // for exit()
#include <string.h>            // for strlen()
#include <fstream.h>           // for fileIO
#include <iostream.h>          // for console IO

void main()
{
  char inputfilename[60];
  cout << "Enter name of input file: "; cin  >> inputfilename;

  ifstream infile (inputfilename);  // opening input stream
  if (!infile)                      // fromfile inputfilename
  {
     cout << "*** ERROR *** :  cannnot open " << inputfilename
          << endl;
     exit(1);  // Flushes output buffers, closes files,
  };           // aborts program

  char outputfilename[60];
  cout << "Enter name of output file: "; cin  >> outputfilename;
  ofstream outfile (outputfilename); // open output file stream

  char oldstr[60], newstr[60];

  cout << "Enter string to be replaced throughout file: ";
  cin  >> oldstr;
  cout << "Enter replacemet string:                     ";
  cin  >> newstr;

  char c;  int k=0;  long int count=0;
  while (!infile.eof())   // while not end of input file
  {
     infile.get(c);  // read next character from input file

     if (c==oldstr[k])    // if character conforms to next
     {                    // character of string to be replaced
       ++k;               // increment pointer which points to
                          // number of chars which aggree.
       if (k==strlen(oldstr))  // If entire string aggrees with
       {                       // string to be replaced, write
         outfile << newstr;    // replacement string to output
         k = 0;                // file & reset substring pointer.
         ++count;              // Counting no of replacements.
       }
     }
     else                  // if next character does not aggree:
     {
       for (int kk=0; kk<k; ++kk) // First write substring which
       outfile << oldstr[kk];     // did aggree, then
       outfile << c;              // the char which didnt aggree
       k=0;                       // and reset substring pointer
     }
  };
  cout << "The string <" << oldstr << "> has been replaced by <"
       << newstr << "> " << count << " times." << endl;

  infile.close();
  outfile.close();
};
\end{verbatim}
