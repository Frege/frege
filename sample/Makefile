all: build/Main.class

clean:
	$(RM) -r build

run: build/Main.class
	java -cp ../build:build Main

.PHONY: all clean run

build/Main.class: Main.fr build/J.class
	mkdir -p build
	java -cp ../build frege.compiler.Main -d build -ascii Main

build/J.class: J.java
	mkdir -p build
	javac -d build J.java
