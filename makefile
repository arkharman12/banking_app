#makefile

all:
	javac *.java

clean: 
	rm -f *.class

run: LoginWindow
	java LoginWindow
