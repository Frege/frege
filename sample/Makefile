all:
	mkdir -p build
	-java -cp ../build frege.compiler.Main -d build -ascii -make *.fr

clean:
	$(RM) -r build

run: all
	for f in *.fr; do cls=$${f%.fr}; echo ========== $$cls ==========; java -cp ../build:build $$cls; done

.PHONY: all clean run
