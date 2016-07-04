#!/bin/bash

cat $0 > temp.sh  # stream this shell script into the temporary file.

if cat temp.sh
then
  rm temp.sh
fi	
