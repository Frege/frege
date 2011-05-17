====﻿Frege System Distribution ======


License:  The code is open sourced under a BSD license, see file LICENSE.txt.


Prerequisites


1. A computer with 256MB memory available to user processes. 
   For compiling very large programs (like the yacc generated parser of the frege compiler), 
   3 to 4 times more memory will be needed.
2. 20 MB disk space
3. Java 6 compatible JDK. However, the java compiler from the Oracle Java JDK will 
   fail to compile frege generated java code due to a long standing bug. 
   Therefore, if one wants the JDK from Oracle,  then JDK 7 is required.
4. With the official release of JDK7 from Oracle, frege will be developed further 
   to make use of the new fork/join API in java.util.concurrent, so JDK 7 is a safe bet.
5. Compiler developers will need perl, make and berkeley yacc - look for byacc, pbyacc or byaccj.
6. Documentation writers need a latex system of their choice.


How to compile, run and document frege programs

-   Download the frege3.17.*.jar from the download page. In the following text, 
    this file is called frege3.jar

-   There is no IDE yet. Use your preferred editor. 
    A good choice is jEdit, I plan to publish some configuration files I have 
    crafted to implement a “frege edit mode” with syntax highlighting 
    and one click compilation using the Console plugin.
    
-   Set your commandline so that it can display unicode characters. (on Windows, try: chcp 65001)

-   Make sure the jdk7 java compiler is in the path: javac -version

-   ***provisionally until frege targets JDK 7*** 
    copy the rt.jar from your java 6 SDK or JRE to the current directory 
    under the name java6-rt.jar (on UNIX, a symbolic link would do)
    Theoretically, if you already have JDK 7 and want stick to it,
    it should also work to copy the rt.jar from there to ./java6-rt.jar (untested)
    
-   display usage page of frege compiler: java -jar frege3.jar -help

 -  compile your program with: java -jar frege3.jar test.fr
    Neither the source code file nor the frege3.jar have to be in the current directory.
    (Of course, in that case, the command above must be adapted accordingly.)
    Unlike in java, the source path does *not* have to match the module name.
    However, when the modul name is x.y.Z, the class file goes into dest/x/y/Z.class,
    where dest is the (already existing) directory sepcified in the -d option, which
    is the current directory by default. 
    You'll also find the intermediate java file in dest/x/y/Z.java (just in case you're interested
    to see really incomprehensible java code - please protect children and young programming adepts
    from looking at it)

-   If your program contained a main function, you can now run it with  the following command: 
    java -cp .;frege3.jar Test  # where Test is the package/module name
                                # under Linux, write : instead of ;
    
-   Generate a documentation for your module or for any other module from the frege3.jar: 
    java -cp .;frege3.jar frege.tools.Doc Test # other package names here 

How to recompile the compiler

1. get the source distribution with 
   svn checkout http://frege.googlecode.com/svn/ frege-read-only
2. cd to the checked out directory and make subdirectories build and doc if not present already.
   Download the current frege3.*.jar from the Download tab and place it in the
   working directory under the name fregec.jar
   In addition, we currently also need the java6-rt.jar as described above.
3. check if the Makefile macros JAVAC, YACC and JAVA point to the correct executables. 
   It is ok to use a java from a JDK/JRE 6 or 7.
4. on Windows run 
   make
5. on Unix run  
   make frege.mk && make -f frege.mk
   If that doesn't work, you might check mkmk.pl for details. 
   Basically, this script replaces semicolons in classpaths with colons.
   (I used to do development both on Linux and Windows, but sticked finally with
   Windows because the JDK 7 developer preview was not available for Linux)
6. This builds a first compiler using ./fregec.jar, then this first compiler is used 
   to build a second compiler and then this second compiler is used 
   to build the final compiler. In the process, the javadoc for the runtime classes is generated.
7. make fregec.jar 
   Makes further  library code, quickchecks some properties of Prelude functions 
   and finally creates a new jar.
8. make docu 
   Makes documentation for most modules. Check the doc directory when it's done.