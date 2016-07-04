#!/bin/bash

if [ $# -ne 1 ]
then
  echo "Usage: processFile <fileName>"
fi

if test -d $1
then
 echo "$1 is a directory. Shall list its contents:"
 ls $1
elif [ -x $1 ]
then
 echo "$1 is executable. Shall execute it now."
 exec ./$1
elif test -f $1 -a -r $1 
then
  echo "$1 is a file which is not executable. Shall list it for you instead." 
  cat $1	
fi
