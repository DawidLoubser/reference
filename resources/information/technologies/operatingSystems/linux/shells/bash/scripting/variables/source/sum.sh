#!/bin/bash
sum=0
for arg in $* ; do
  let sum=$sum+$arg
done
echo "sum = $sum"
