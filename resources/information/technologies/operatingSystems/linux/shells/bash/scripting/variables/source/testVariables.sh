#!/bin/bash
a=17
let c=b=2*$a+2
echo "b=$b, c=$c"
unset b
echo "b=$b, c=$c"
javaFiles=`find /home/fritz/temp -name *.java`
echo -e "Processing the following Java files:\n$javaFiles"
echo "How old are you?"
read ageInYears
let ageInDays=ageInYears*36525/100
echo "You are $ageInDays days old"
