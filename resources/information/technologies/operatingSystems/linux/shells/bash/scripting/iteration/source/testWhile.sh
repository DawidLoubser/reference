#!/bin/bash
sum=0
while test "$#" -gt "1"
do
  echo "adding $1"
  let sum=$sum+$1
  shift
done
echo "sum = $sum"
