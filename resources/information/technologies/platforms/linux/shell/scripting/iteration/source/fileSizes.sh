#!/bin/bash

filter=''

if [ $# -eq 1 ]
then
	filter=$1
fi	
echo "Using filter: $filter"

files=`ls $filter`

totalSize=0
for file in $files
do
  size=`filesize $file`
  echo "$file -> $size"
  let totalSize=$[totalSize+size]
done
echo "Total disk space required by all shell scripts = $totalSize"
	
