# Makefile for the frege compiler distribution.
# When you need to run this under Windows (poor guy!),
# change the path separator characters.

#
# Make sure you have sensible values for JAVAC, YACC and JAVA
# The standard distribution needs a Java 1.7 (or higher) JDK.
# Because people may need previous JDKs/JREs for different work,
# there are 2 mechanisms to get the right java:
#
#   - put the JDK7 in your PATH after other JDKs, and make java7 a symbolic link to
#     the JDK7 java binary. (On Windows, just copy java.exe to java7.exe)
#   - For UNIX users: make the following alias:
#         alias fmake='make JAVA="/path/to/jdk7/java" '
#
# YACC should be a BSD compatible yacc. This can be obtained from the net at various places.
# Windows users look for pbyacc.exe, Ubuntu users use
#
#	sudo apt-get install byacc-j  # byacc and pbyacc should also work
#

.SUFFIXES: .class .fr

JAVAC = javac -encoding UTF-8
YACC =`which byacc || which byaccj || which pbyacc || false`
JAVA = java "-Dfrege.javac=internal"


DOC  = ../frege.github.com/doc
DOCF = doc/frege/compiler
DIR1 = build/afrege
PREL1  = $(DIR1)/prelude
COMPF1  = $(DIR1)/compiler
LIBF1   = $(DIR1)/lib
DATA1   = $(DIR1)/data
CONTROL1 = $(DIR1)/control
LIBJ1   = $(DIR1)/j
TOOLSF1 = $(DIR1)/tools
DIR2 = build/bfrege
PREL2   = $(DIR2)/prelude
COMPF2  = $(DIR2)/compiler
LIBF2   = $(DIR2)/lib
DATA2   = $(DIR2)/data
LIBJ2   = $(DIR2)/j
TOOLSF2 = $(DIR2)/tools
DIR  = build/frege
PREL    = $(DIR)/prelude
COMPF   = $(DIR)/compiler
LIBF    = $(DIR)/lib
DATA   = $(DIR)/data
LIBJ    = $(DIR)/j
TOOLSF  = $(DIR)/tools
COMPS   = frege/compiler


FREGE    = $(JAVA) -Xss4m -Xmx2g -cp build

#	compile using the fregec.jar in the working directory
FREGECJ  = $(FREGE)  -jar fregec.jar  -d build -hints

#	compile compiler1 with fregec.jar, uses prelude sources from shadow/
FREGEC0  = $(FREGECJ) -prefix a -sp shadow:.

#	compile compiler2 with compiler1
FREGEC1  = $(FREGE) afrege.compiler.Main -d build -hints -target 1.7 -inline -prefix b

#	compile final compiler with compiler2
FREGEC2  = $(FREGE) bfrege.compiler.Main -d build -hints -target 1.7 -O

#	final compiler
FREGECC  = $(FREGE) frege.compiler.Main  -d build -hints -target 1.7 -O

#	shadow Prelude files in the order they must be compiled
SPRELUDE  =  shadow/frege/prelude/PreludeBase.fr \
		shadow/frege/control/Semigroupoid.fr shadow/frege/control/Category.fr \
		shadow/frege/prelude/PreludeList.fr shadow/frege/prelude/PreludeMonad.fr \
		shadow/frege/prelude/Maybe.fr \
		shadow/frege/prelude/PreludeIO.fr \
		shadow/frege/prelude/PreludeArrays.fr \
		shadow/frege/java/Lang.fr \
		shadow/frege/java/util/Regex.fr \
		shadow/frege/prelude/PreludeText.fr \
		shadow/frege/prelude/Math.fr shadow/frege/java/lang/Math.fr
#	Prelude files in the order they must be compiled
PRELUDE  =  frege/prelude/PreludeBase.fr \
		frege/control/Semigroupoid.fr frege/control/Category.fr \
		frege/prelude/PreludeList.fr frege/prelude/PreludeMonad.fr \
		frege/prelude/Maybe.fr \
		frege/prelude/PreludeIO.fr \
		frege/prelude/PreludeArrays.fr \
		frege/java/Lang.fr \
		frege/java/Util.fr \
		frege/java/util/Regex.fr \
		frege/prelude/PreludeText.fr \
		frege/prelude/Math.fr frege/java/lang/Math.fr

all:  runtime compiler fregec.jar

shadow-prelude:
	jar -cf shadow.jar $(PRELUDE)
	cd shadow && jar -xf ../shadow.jar
	rm shadow.jar

clean:
	rm -rf build/afrege build/bfrege build/frege
	rm -rf build
	mkdir build


sanitycheck:
	$(JAVA) -version

dist: fregec.jar
	perl scripts/mkdist.pl

fregec.jar: test
	jar  -cf    fregec.jar -C build frege
	jar  -uvfe  fregec.jar frege.compiler.Main
	java -jar fregec.jar -version
	cp fregec.jar fallback.jar

fregec7.jar:  savejava
	@echo The following will probably only work if you just made a compiler
	rm -rf build7
	mkdir build7
	@echo You can ignore the compiler warning.
	$(JAVAC) -J-Xmx1g -source 1.7 -target 1.7 -sourcepath save -d build7 \
	    save/frege/compiler/Main.java
	jar -cf   fregec7.jar -C build7 frege
	jar -uvfe fregec7.jar frege.compiler.Main
	@echo Looks good .... let us try to make the tools and library ... 
	$(JAVA) -Xmx1g -Xss4m -Dfrege.javac="javac -source 1.7 -target 1.7" -jar fregec7.jar -d build7 -nocp -fp build7 -make \
	    frege/StandardTools.fr frege/StandardLibrary.fr
	@echo Still running? Now we have it almost .... 
	cp frege/tools/yygenpar-fr frege/tools/YYgenparM-fr frege/tools/fregedoc.html build7/frege/tools
	jar -cf   fregec7.jar -C build7 frege
	jar -uvfe fregec7.jar frege.compiler.Main
	cp fregec7.jar ../eclipse-plugin/lib/fregec.jar
 
fregec6.jar: fallback.jar savejava
	@echo The following will probably only work if you just made a fregec.jar
	@echo Adapting the sources for dumb old java6 ....
	cp frege/runtime/Concurrent.java6 save/frege/runtime/Concurrent.java
	cp frege/runtime/Runtime.java6    save/frege/runtime/Runtime.java
	cp frege/runtime/CompilerSupport.java6    save/frege/runtime/CompilerSupport.java
	rm -rf build6
	mkdir build6
	@echo You can ignore the compiler warning.
	$(JAVAC) -J-Xmx1g -source 1.6 -target 1.6 -sourcepath save -d build6 \
	    save/frege/compiler/Main.java
	jar -cf   fregec6.jar -C build6 frege
	jar -uvfe fregec6.jar frege.compiler.Main
	@echo Looks good .... let us try to make the tools and library ... 
	grep -v ForkJoin frege/StandardLibrary.fr >save/StandardLibrary.fr
	$(JAVA) -Xmx1g -Xss4m -Dfrege.javac="javac -source 1.6 -target 1.6" -jar fregec6.jar -d build6 -nocp -fp build6 -make \
	    frege/StandardTools.fr save/StandardLibrary.fr
	@echo Still running? Now we have it almost .... 
	cp frege/tools/yygenpar-fr frege/tools/YYgenparM-fr frege/tools/fregedoc.html build6/frege/tools
	jar -cf   fregec6.jar -C build6 frege
	jar -uvfe fregec6.jar frege.compiler.Main
	@echo
	@echo !-------------- PLEASE NOTE ----------------------------------------------
	@echo !  The new compiler will itself generate java6 classes if run in a JDK6.  
	@echo !  Unfortunately, the Java 6 compiler may not understand proper Java.     
	@echo !  To avoid those problems, use this JAR always thus:                     
	@echo !      java  -Dfrege.javac=\"javac -source 1.6 -target 1.6\" -jar fregec6.jar ...  
	@echo !  where javac is a JDK-7 compiler!                                       
	@echo !-------------------------------------------------------------------------
	@echo
	
#
#	Avoid recompilation of everything, just remake the compiler with itself and jar it.
#	One should have a fallback.jar, just in case ....
#
test-jar: fallback.jar
	$(FREGEC2) -make frege.compiler.Main frege.ide.Utilities
	jar  -cf    fregec.jar -C build frege
	jar  -uvfe  fregec.jar frege.compiler.Main
	cp fregec.jar  ../eclipse-plugin/lib/fregec.jar


test: compiler
	$(FREGECC)  -make  frege/StandardLibrary.fr
	rm -rf buildt
	mkdir -p buildt
	$(FREGECC)  -d buildt  tests/qc
	$(JAVA) -Xss4m -cp build frege.tools.Quick -v buildt
	rm -rf buildt


$(DIR)/PreludeProperties.class:  frege/PreludeProperties.fr $(COMPF)/Main.class
	$(FREGECC) -make  frege/PreludeProperties.fr

# 	$(TOOLSF)/Doc.class $(TOOLSF)/YYgen.class $(TOOLSF)/LexConvt.class
tools: $(COMPF)/Main.class
	$(FREGECC) -make  frege/StandardTools.fr

#
# final compiler
#
compiler: $(COMPF)/Main.class 
	cp frege/tools/yygenpar-fr frege/tools/YYgenparM-fr frege/tools/fregedoc.html build/frege/tools
	@echo Compiler ready

$(COMPF)/grammar/Frege.class: frege/compiler/grammar/Frege.fr $(COMPF)/common/Desugar.class
	$(FREGEC2) -make frege/compiler/grammar/Frege.fr
frege/compiler/grammar/Frege.fr: frege/compiler/grammar/Frege.y
	@echo We should have 5 shift/reduce conflicts in the grammar.
	$(YACC) -v frege/compiler/grammar/Frege.y
	$(FREGE) -cp fregec.jar frege.tools.YYgen -m State  frege/compiler/grammar/Frege.fr

frege/Version.fr: .git/index
	perl scripts/mkversion.pl >frege/Version.fr

$(COMPF)/Main.class: compiler2
	( test -d $(DIR) -a $(COMPF2)/Main.class -nt $(COMPF)/Main.class && rm -rf $(COMPF) $(TOOLSF) $(DIR)/prelude ) || true
	$(FREGEC2)  -make frege.compiler.Main frege/StandardTools.fr

compiler2: compiler1
	( test -d $(DIR2) -a $(COMPF1)/Main.class -nt $(COMPF2)/Main.class && rm -rf $(DIR2) ) || true
	$(FREGEC1) -v -make frege.compiler.Main
	@echo stage 2 compiler ready



SOURCES  =      $(COMPS)/Scanner.fr   $(COMPS)/Classtools.fr \
		$(COMPS)/types/Positions.fr $(COMPS)/enums/Flags.fr \
		$(COMPS)/types/Global.fr      $(COMPS)/Utilities.fr \
		$(COMPS)/GUtil.fr \
		$(COMPS)/Main.fr      $(COMPS)/Grammar.fr   $(COMPS)/Grammar.y \
		$(COMPS)/Fixdefs.fr   $(COMPS)/Import.fr    $(COMPS)/Enter.fr \
		$(COMPS)/TAlias.fr    \
		$(COMPS)/Javatypes.fr $(COMPS)/Kinds.fr \
		$(COMPS)/Transdef.fr  $(COMPS)/Classes.fr \
		$(COMPS)/Transform.fr \
		$(COMPS)/tc/Methods.fr $(COMPS)/tc/Patterns.fr \
		$(COMPS)/Typecheck.fr \
		$(COMPS)/tc/Util.fr \
		$(COMPS)/gen/Util.fr  $(COMPS)/gen/Const.fr \
		$(COMPS)/gen/Bindings.fr $(COMPS)/gen/Match.fr \
		$(COMPS)/GenMeta.fr   $(COMPS)/GenJava7.fr


CLASSES  =       $(COMPF1)/Scanner.class   $(COMPF1)/Classtools.class \
		$(COMPF1)/types/Positions.class \
		$(COMPF1)/types/Global.class      $(COMPF1)/Utilities.class \
		$(COMPF1)/GUtil.class	$(COMPF1)/Grammar.class \
		$(COMPF1)/Fixdefs.class   $(COMPF1)/Import.class \
		$(COMPF1)/gen/Const.class  $(COMPF1)/gen/Util.class \
		$(COMPF1)/Enter.class \
		$(COMPF1)/Javatypes.class $(COMPF1)/Kinds.class $(COMPF1)/Transdef.class \
		$(COMPF1)/tc/Util.class   \
		$(COMPF1)/TAlias.class    $(COMPF1)/Classes.class \
		$(COMPF1)/tc/Methods.class $(COMPF1)/tc/Patterns.class \
		$(COMPF1)/Typecheck.class $(COMPF1)/Transform.class \
		$(COMPF1)/gen/Bindings.class $(COMPF1)/gen/Match.class \
		$(COMPF1)/GenMeta.class   $(COMPF1)/GenJava7.class


compiler1: frege/compiler/grammar/Frege.fr frege/Version.fr 
	$(FREGEC0)  -make frege.compiler.Main
	@echo stage 1 compiler ready

runtime:
	mkdir -p build
	$(JAVAC) -d build -source 1.7 -target 1.7 frege/runtime/*.java frege/run/*.java
	@echo Runtime is complete.



#
#   Documentation
#


doc/index.html: $(RUNTIME)


docu: fregec.jar
	javadoc -private -sourcepath . -d $(DOC) -encoding UTF-8 frege.runtime
	$(JAVA) -cp fregec.jar frege.tools.Doc -v -d $(DOC) -x frege.compiler,frege.runtime,frege.S,frege.V,frege.PreludePr,frege.run fregec.jar
	$(JAVA) -cp fregec.jar frege.tools.MakeDocIndex $(DOC)


#
#   Difference between 2 compilers
#   The output of the first must have been stored in "save" (see "savejava")
#   Compares all java files in save/frege with those in build/frege
#
diffs:
	diff -b -r -x "*.class" -I "This code was generated with the frege compiler version" -I "^ +source=" save  build

savejava:
	perl scripts/savejava.pl
