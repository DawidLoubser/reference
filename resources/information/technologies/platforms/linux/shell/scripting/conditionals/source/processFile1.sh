<![CDATA[
#!/bin/bash

if [ $# -ne 1 ]
then
  echo "Usage: processFile <fileName>"
fi

if test -x $1
then
 echo "$1 is executable. Shall execute it now."
 exec ./$1
else
 echo "$1 is not executable. Shall list it for you instead." 
 cat $1	
fi
]]>
