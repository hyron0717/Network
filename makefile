JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        client.java \
        server.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
