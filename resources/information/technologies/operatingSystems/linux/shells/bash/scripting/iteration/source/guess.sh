#!/bin/bash

secret=7

until [ $guess -eq $secret ]
do
  echo "Guess my secret number (between 0 and 9)"
	read guess
done
echo "Congratulations!! You finally guessed it."
