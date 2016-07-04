#!/bin/bash
NUMARGS=2
BAD_ARGS_ERROR=-1
if [ $# -ne 2 ]
then
  echo "Usage: testArithmetic num1 num2"
	exit $BAD_ARGS_ERROR
fi	

let result=$1+$2; echo "$1+$2 = `expr $1 + $2`"
echo "$1-$2 = `expr $1 - $2`"
echo "$1*$2 = `expr $1 '* '$2`"
echo "$1/$2 = `expr $1 / $2`"
let result=$1**$2; echo "$1**$2 = $result"
echo "$1%$2 = `expr $1 % $2`"

exit 0
