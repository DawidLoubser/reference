\begin{verbatim}
//                    Replace.CPP
//---------------------------------------------------------
// Search the contents of a file and replace all occurances
// of oldstr with the string newstr writing the result into
// a new file.

#include <stdlib.h>            // for exit()
#include <string>            // for strlen()
#include <fstream>           // for fileIO
#include <iostream>          // for console IO

using namespace std;

void replace(ifstream& infile, ofstream& outfile,
             char oldStr[], char newStr[], long int& numSubstitutions)
{
  char c;
  unsigned int k=0;
  numSubstitutions = 0;
  while (!infile.eof())   // while not end of input file
  {
     infile.get(c);  // read next character from input file

     if (c==oldStr[k])    // if character conforms to next
     {                    // character of string to be replaced
       ++k;               // increment pointer which points to
                          // number of chars which aggree.
       if (k==strlen(oldStr))  // If entire string aggrees with
       {                       // string to be replaced, write
         outfile << newStr;    // replacement string to output
         k = 0;                // file & reset substring pointer.
         ++numSubstitutions;   // Counting no of replacements.
       }
     }
     else                  // if next character does not aggree:
     {
       for (unsigned int kk=0; kk<k; ++kk) // First write substring which
       outfile << oldStr[kk];     // did aggree, then
       outfile << c;              // the char which didnt aggree
       k=0;                       // and reset substring pointer
     }
  };
}


int main()
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

  char oldStr[80], newStr[80];

  cout << "Enter string to be replaced throughout file: ";
  cin  >> oldStr;
  cout << "Enter replacement string: ";
  cin  >> newStr;

  long int numSubstitutions = 0;
  replace(infile, outfile, oldStr, newStr, numSubstitutions);

  infile.close();
  outfile.close();

  cout << "The string <" << oldStr << "> has been replaced by <"
       << newStr << "> " << numSubstitutions << " times." << endl;

  char k; cin >> k;
  
  return 0;
}
\end{verbatim}
