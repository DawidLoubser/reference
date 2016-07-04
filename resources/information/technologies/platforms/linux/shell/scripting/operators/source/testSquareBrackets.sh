<![CDATA[
#!/bin/bash
echo "Enter x:"; read x
echo "Enter y:"; read y

echo "x*y = `expr $x '*' $y`"

echo "x*y = $[x*y]"
]]>
