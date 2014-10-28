# Build instructions


This information is for developers that would like to contribute to the Frege project itself.
As a _user_ of Frege, who writes Frege code for compiling and running it, you may want to
look into the [Getting Started](https://github.com/Frege/frege/wiki/Getting-Started) wiki page.


* get the source distribution with

`git clone https://github.com/Frege/frege.git`

* cd to the checked out directory and make subdirectories `build`, `dist` and `doc` if not present already.
* Download the latest `frege3`._xx_._vvv_.`jar` and place it in the working directory under the name `fregec.jar`
* check if the Makefile macros JAVAC, YACC and JAVA point to the correct executables
* the Makefile macro JAVA defines the property `-Dfrege.javac`. You can specify here a different path for the java compiler that is to be called from the frege compiler. For example, suppose you need JDK 6 for your daily work, so that `java` and `javac` call the JDK 6 binaries. You could then rename or alias the JDK 7 binaries with `java7` and `javac7`. In this case, settings should look like

```
JAVAC=javac7
JAVA=java7 "-Dfrege.javac=javac7 -J-Xmx512m"
```

Run the following command:

`make frege.mk && make -f frege.mk runtime 'shadow-prelude' compiler`

The build will probably take no less than some minutes (this can vary widely) and consists of the following main steps:

* compilation of the compiler sources and library sources needed in the compiler with `fregec.jar`
* compilation of the same with the compiler built in the first step
* compilation of the sources with the compiler built in the second step

The result will be a bunch of class files below the `build` directory.
You can now run the compiler with

`java -cp build frege.compiler.Main -version`

To make a new `fregec.jar` from the fresh compiler, run

`make -f frege.mk fregec.jar`

The 3 builds are necessary for the following reason: The first build uses the *old* compiler to build a compiler that incorporates your changes. The second build uses the changed compiler and tries to build another compiler. If this works, and the second compiler itself can successfully create the 3rd compiler and if that one produces the same code as the second one (for a test program, say, where you test the new features), then (and only then) it is reasonable to assume that you didn't break anything.

## When recompiling doesn't work

Because the Frege compiler is self hosting, it cannot be guaranteed that you can do the following:

 - download fregec-3.x.y.jar
 - checkout the latest sources
 - compile the compiler

Unfortunately. Sorry. (This is also the reason why the nightly build is often broken.)

If that happens to you, please post a message in the news group and we will happy to get you a bleeding edge compiler.

When on MacOS, make sure you get a suitable yacc installation.
You may want to look at http://byaccj.sourceforge.net.
The one in XCode is not sufficient.

Set the YACC variable in Makefile to the yacc binary.

For rebuilding from scratch make sure you have deleted the old grammar file if there is one:

`rm frege/compiler/Grammar.fr`

Make sure you have the rights to create folders and files.

The make is a two-step process. It first creates a frege.mk file.
That file is then used start the actual make process.