#! /bin/bash

if [ "$1" == "clean" ]
then
	rm query.out.txt
	rm -rf *.class 
	echo "Removing *.class files. . ." 
	exit 0
fi

export CLASSPATH=/usr/local/lib/berkeleydb.5.3/lib/db.jar:. 
export LD_LIBRARY_PATH=/usr/local/lib/berkeleydb.5.3/lib
mkdir imdb 
dot_java=`ls *.java`
javac -classpath $CLASSPATH $dot_java

if [ "$1" == "test" ]
then 
#	java -Xmx2048m -classpath $CLASSPATH Main 1
#	java -Xmx2048m -classpath $CLASSPATH Main 2 29993.xml
#	java -Xmx2048m -classpath $CLASSPATH Main 3 29500.xml 30000.xml
#	java -Xmx2048m -classpath $CLASSPATH Main 4 2961
#	java -classpath $CLASSPATH Main 5 2000 3000
#	java -classpath $CLASSPATH Main 6 20000.xml 30000.xml 2000 3000
	java -classpath $CLASSPATH Main 7 videos 
	java -Xmx2048m -classpath $CLASSPATH Main 1
#	java -Xmx2048m -classpath $CLASSPATH Main 2 472621.xml
#	java -Xmx2048m -classpath $CLASSPATH Main 3 400000.xml 402000.xml
#	java -Xmx2048m -classpath $CLASSPATH Main 4 3879 
#	java -classpath $CLASSPATH Main 5 2000 3000
#	java -classpath $CLASSPATH Main 6 400000.xml 500000.xml 3000 4000
#	java -classpath $CLASSPATH Main 7 Arnold 
	exit 0 
fi
#java -classpath $CLASSPATH Main $args 
echo "Completed tests." 
	

	

