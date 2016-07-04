echo "Starinting name server"
tnameserv -ORBInitialPort 2000 &
echo "Starting ElectionServer"
java -jar target/server-1.0-SNAPSHOT.jar -ORBInitialPort 2000

