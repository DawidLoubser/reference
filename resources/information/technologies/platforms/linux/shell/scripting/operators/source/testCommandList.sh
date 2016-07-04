#!/bin/bash
a=6    
y=20
let "x = ((y=$a/2, $y+1))"
echo "y = $y"   # prints 3
echo "x = $x"  # prints 4
