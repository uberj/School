JFLAGS = -g -classpath /usr/local/apps/berkeleydb/berkeleydb.5.3/lib/db.jar:.

JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Main.java \
		XMLFile.java \
		XMLFileBinding.java

all: classes
	java Main

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

