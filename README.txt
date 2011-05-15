Frege System Distribution


License:  The code is open sourced under a BSD license, see file LICENSE.txt.


Prerequisites


1. A computer with 256MB memory available to user processes. 
   For compiling very large programs (like the yacc generated parser of the frege compiler), 
   substantial more memory will be needed.
2. 20 MB disk space
3. Java 6 compatible JDK. However, the java compiler from the Oracle Java JDK will 
   fail to compile frege generated java code due to a long standing bug. 
   Therefore, if one wants Oracle,  JDK 7 is required.
4. With the official release of JDK7 from Oracle, frege will be developed further 
   to make use of the new fork/join API in java.util.concurrent, so JDK 7 is a safe bet.
5. Compiler developers will need perl, make and berkeley yacc - look for byacc, pbyacc or byaccj.
6. Documentation writers need a latex system of their choice.


How to compile, run and document frege programs

1.  Download the frege3.17.*.jar from the download page. In the following text, 
    this file is called frege3.jar

2.  There is no IDE yet. Use your preferred editor. 
    A good choice is jEdit, I plan to publish some configuration files I have 
    crafted to implement a “frege edit mode” with syntax highlighting 
    and one click compilation using the Console plugin.
    
3.  Set your commandline so that it can display unicode characters. (on Windows, try chcp 65001)

4.  Make sure the java compiler is in the path: javac -version

5.  provisionally until frege targets JDK 7: 
    copy the rt.jar from your java 6 SDK or JRE to the current directory 
    under the name java6-rt.jar (on UNIX, a symbolic link would do)
    
6.  display usage page of frege compiler: java -jar frege3.jar -help

7.  compile your program with: java -jar frege3.jar test.fr

8.  neither the source code file nor the frege3.jar have to be in the current directory

9.  If your program contained a main function, you can now run it with  the following command: 
    java -cp .;frege3.jar Test where Test is the package/module name
    
10. Generate a documentation for your module or for any other module from the frege3.jar: 
    java -cp .;frege3.jar frege.tools.Doc Test # other package names here 

How to recompile the compiler

1. get the source distribution with 
   svn checkout http://frege.googlecode.com/svn/ frege-read-only
2. cd to the checked out directory and make subdirectories build and doc if not present already.
   Download the current frege3.*.jar from the Download tab and place it in the
   working directory under the name fregec.jar
3. check if the Makefile macros JAVAC, YACC and JAVA point to the correct executables. 
   It is ok to use a java from a JDK/JRE 6 or 7.
4. on Windows run 
   make
5. on Unix run  
   make frege.mk && make -f frege.mk
5.1. you might check mkmk.pl for details. Basically, this script replaces semicolons 
   in classpaths with colons.
6. This builds a first compiler using ./frege3.jar, then this first compiler is used 
   to build a second compiler and then this second compiler is used 
   to build the final compiler. In the process, the javadoc for the runtime classes is generated.
7. make target fregec.jar makes further  library code, 
   quickchecks some properties of Prelude functions and finally creates a new jar.
8. make target docu makes documentation for most modules. Check the doc directory.