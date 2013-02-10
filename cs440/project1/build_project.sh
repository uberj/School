#! /bin/bash

if [ "$1" == "clean" ]
then
	rm -rf *.class 
	echo "Removing *.class files. . ." 
	exit 0
fi

export CLASSPATH=/usr/local/apps/berkeleydb/berkeleydb.5.3/lib/db.jar:. 
export LD_LIBRARY_PATH=/usr/local/apps/berkeleydb/berkeleydb.5.3/lib

dot_java=`ls *.java`
javac -classpath $CLASSPATH $dot_java 
java -classpath $CLASSPATH Main 

