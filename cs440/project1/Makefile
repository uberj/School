JFLAGS = -g -classpath /usr/local/apps/berkeleydb/berkeleydb.5.3/lib/db.jar:. 

JVMFLAGS = -D java.library.path=".:/usr/local/apps/berkeleydb/berkeleydb.5.3/lib/"


JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Main.java \
		XMLFile.java \
		XMLFileBinding.java \
		Dbs.java \
		SizeKeyCreator.java

all: classes
	export CLASSPATH=.
	export LD_LIBRARY_PATH=/usr/local/apps/berkeleydb/berkeleydb.5.3/lib/:$$LD_LIBRARY_PATH
	java Main

build: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

