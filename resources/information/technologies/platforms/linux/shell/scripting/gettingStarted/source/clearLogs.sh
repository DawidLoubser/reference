<![CDATA[
#!/bin/bash
#
# This script must be run as root.
#
cd /var/log
rm previousMessages.zip
echo "removed previousMessages.zip"
zip previousMessages.zip messages
echo "zipped current log messages"
cat /dev/null > messages   
echo "cleared messages log."
]]>
