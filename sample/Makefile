all: build/Main.class

clean:
	$(RM) -r build

run: build/Main.class
	java -cp ../build:build Main

.PHONY: all clean run

build/Main.class: Main.fr
	mkdir -p build
	java -cp ../build frege.compiler.Main -d build -ascii -make Main
