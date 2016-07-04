#!/bin/bash

cat $0 > temp.sh  # stream this shell script into the temporary file.

echo "Delete temporary file when successfully printed (y,n)?"
read deleteTemp

if cat temp.sh
then
  if [ $deleteTemp = "y" ]
  then	
    rm temp.sh
		echo "Deleted temporary file"
  fi		
fi	
