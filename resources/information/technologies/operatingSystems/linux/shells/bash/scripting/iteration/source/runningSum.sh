#!/bin/bash
echo "Reports running sum. Enter 0 to terminate"

sum=0

while [ 1 ]
do
  echo "Enter a number:"
	read num
	if [ $num -eq 0 ]
	then
	  break
	fi	
	let sum="$sum + $num"
	echo "Running sum = $sum"
done	
