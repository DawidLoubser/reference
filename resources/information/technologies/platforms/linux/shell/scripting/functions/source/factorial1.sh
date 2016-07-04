<![CDATA[
#!/bin/bash

function factorial ()
{
	arg=$1
  sum=1
	while [ $arg -gt 1 ]
	do
	  let sum=$sum*arg
		let arg=arg-1
	done
  return $sum	
}

if [ $# = 0 ]
then
  echo "Enter n: "
	read n;
else
  let n=$1
fi

if [ $n -gt 5 ]
then
  echo "Sorry, maximum is 5."
	exit 33
fi

factorial $n

echo "factorial($n) = $?"
]]>	
