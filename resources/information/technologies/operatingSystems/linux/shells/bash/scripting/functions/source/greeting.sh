#!/bin/bash

function greetingMessage()
{
  hour=`date | cut -c12-13`

  if [ $hour -lt 12 ]
  then
    msg="Good morning, $LOGNAME."
  elif [ $hour -ge 12 -a $hour -lt 18 ]	
  then
    msg="Good afternoon, $LOGNAME."
  else
    msg="Good evening, $LOGNAME."
  fi		
}

greetingMessage

echo "$msg Please try and not do too much damage on the machine today."	
