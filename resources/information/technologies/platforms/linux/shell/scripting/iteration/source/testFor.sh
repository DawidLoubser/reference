<![CDATA[
#!/bin/bash
numMonth=0
for month in January February March April May June July August September October November December
do
		let numMonth=$numMonth+1
		echo "$numMonth -> $month"
done
]]>
