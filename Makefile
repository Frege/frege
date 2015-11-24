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

DOC                   = ../frege.github.com/doc
BUILD                 = build
BUILD6                = $(BUILD)6
BUILD7                = $(BUILD)7
BUILD_TEST            = $(BUILD)/test
BUILD_FREGE           = $(BUILD)/frege
BUILD_FREGE_COMPILER  = $(BUILD_FREGE)/compiler
BUILD_FREGE_TOOLS     = $(BUILD_FREGE)/tools
FREGE_COMPILER        = frege/compiler

BUILD_AFREGE          = $(BUILD)/afrege
BUILD_AFREGE_COMPILER = $(BUILD_AFREGE)/compiler

BUILD_BFREGE          = $(BUILD)/bfrege
BUILD_BFREGE_COMPILER = $(BUILD_BFREGE)/compiler
FREGEC_VERSION        = 3.23.365-g185c1b5

JAVAC    = javac -encoding UTF-8
YACC     = `which byacc || which byaccj || which pbyacc || false`
JAVA     = java -Dfrege.javac=internal
CP       = cp -pf
RM       = rm -fr
MKDIR    = mkdir -p
FREGE    = $(JAVA) -Xss4m -Xmx1800m -cp $(BUILD)

#	compile using the fregec.jar in the working directory
FREGECJ  = $(FREGE) -jar fregec.jar -d $(BUILD) -hints

#	compile compiler1 with fregec.jar, uses prelude sources from shadow/
FREGEC0  = $(FREGECJ) -prefix a -sp shadow:.

#	compile compiler2 with compiler1
FREGEC1  = $(FREGE) afrege.compiler.Main -d $(BUILD) -hints -target 1.7 -inline -prefix b

#	compile final compiler with compiler2
FREGEC2  = $(FREGE) bfrege.compiler.Main -d $(BUILD) -hints -target 1.7 -O

#	final compiler
FREGECC  = $(FREGE) frege.compiler.Main -d $(BUILD) -hints -target 1.7 -O

#	Prelude files in the order they must be compiled
PRELUDE  = \
		frege/prelude/PreludeBase.fr \
		frege/control/Semigroupoid.fr \
		frege/control/Category.fr \
		frege/prelude/PreludeList.fr \
		frege/prelude/PreludeMonad.fr \
		frege/prelude/Maybe.fr \
		frege/prelude/PreludeIO.fr \
		frege/prelude/PreludeArrays.fr \
		frege/java/Lang.fr \
		frege/java/util/Regex.fr \
		frege/prelude/PreludeText.fr \
		frege/prelude/Math.fr \
		frege/java/lang/Math.fr

#	shadow Prelude files in the order they must be compiled
SPRELUDE  = $(addprefix shadow/, $(PRELUDE))

.PHONY: all clean diffs dist distclean docu fetch-fregec.jar rebuild runtime sanitycheck savejava shadow-prelude test tools

all: runtime compiler fregec.jar
	@echo "[1;42mMaking $@[0m"

fetch-fregec.jar:
	curl -H 'Accept: application/vnd.github.v3.raw' -kL -o fregec.jar `grep curl .travis.yml | sed 's/.*https:/https/'`

shadow-prelude:
	@echo "[1;43mMaking $@[0m"
	jar -cf shadow.jar $(PRELUDE)
	cd shadow && jar -xf ../shadow.jar
	$(RM) shadow.jar

$(BUILD):
	@echo "[1;42mMaking $@[0m"
	$(MKDIR) $@

clean:
	@echo "[1;42mMaking $@[0m"
	$(RM) $(BUILD)

distclean: clean
	@echo "[1;42mMaking $@[0m"
	$(RM) dist frege/Version.fr fallback.jar fregec.jar y.output y.tab.c

sanitycheck:
	@echo "[1;42mMaking $@[0m"
	$(JAVA) -version

dist: fregec.jar
	@echo "[1;42mMaking $@[0m"
	perl scripts/mkdist.pl

fregec.jar: test
	@echo "[1;43mMaking $@[0m"
	jar -cf $@ -C $(BUILD) frege
	jar -uvfe $@ frege.compiler.Main
	java -jar $@ -version

fregec7.jar:: savejava
	@echo "[1;43mMaking $@[0m"
	@echo The following will probably only work if you just made a compiler
	$(RM) $(BUILD7)
	$(MKDIR) $(BUILD7)
	@echo You can ignore the compiler warning.
	$(JAVAC) -J-Xmx1g -source 1.7 -target 1.7 -sourcepath save -d $(BUILD7) save/$(FREGE_COMPILER)/Main.java
	jar -cf $@ -C $(BUILD7) frege
	jar -uvfe $@ frege.compiler.Main
	@echo Looks good .... let us try to make the tools and library ... 
	$(JAVA) -Xmx1g -Xss4m -Dfrege.javac="javac -source 1.7 -target 1.7" -jar $@ -d $(BUILD7) -nocp -fp $(BUILD7) -make \
	    frege/StandardTools.fr frege/StandardLibrary.fr
	@echo Still running? Now we have it almost .... 
	$(CP) frege/tools/yygenpar-fr frege/tools/YYgenparM-fr frege/tools/fregedoc.html $(BUILD7)/frege/tools
	jar -cf $@ -C $(BUILD7) frege
	jar -uvfe $@ frege.compiler.Main
	$(CP) $@ ../eclipse-plugin/lib/fregec.jar

fregec6.jar: fregec.jar savejava
	@echo "[1;43mMaking $@[0m"
	@echo The following will probably only work if you just made a fregec.jar
	@echo Adapting the sources for dumb old java6 ....
	$(CP) frege/runtime/Concurrent.java6 save/frege/runtime/Concurrent.java
	$(CP) frege/runtime/Runtime.java6 save/frege/runtime/Runtime.java
	$(CP) frege/runtime/CompilerSupport.java6 save/frege/runtime/CompilerSupport.java
	$(RM) $(BUILD6)
	$(MKDIR) $(BUILD6)
	@echo You can ignore the compiler warning.
	$(JAVAC) -J-Xmx1g -source 1.6 -target 1.6 -sourcepath save -d $(BUILD6) save/$(FREGE_COMPILER)/Main.java
	jar -cf $@ -C $(BUILD6) frege
	jar -uvfe $@ frege.compiler.Main
	@echo Looks good .... let us try to make the tools and library ... 
	grep -v ForkJoin frege/StandardLibrary.fr >save/StandardLibrary.fr
	$(JAVA) -Xmx1g -Xss4m -Dfrege.javac="javac -source 1.6 -target 1.6" -jar $@ -d $(BUILD6) -nocp -fp $(BUILD6) -make \
	    frege/StandardTools.fr save/StandardLibrary.fr
	@echo Still running? Now we have it almost .... 
	$(CP) frege/tools/yygenpar-fr frege/tools/YYgenparM-fr frege/tools/fregedoc.html $(BUILD6)/frege/tools
	jar -cf $@ -C $(BUILD6) frege
	jar -uvfe $@ frege.compiler.Main
	@echo
	@echo !-------------- PLEASE NOTE ----------------------------------------------
	@echo ! The new compiler will itself generate java6 classes if run in a JDK6.
	@echo ! Unfortunately, the Java 6 compiler may not understand proper Java.
	@echo ! To avoid those problems, use this JAR always thus:
	@echo !    java -Dfrege.javac=\"javac -source 1.6 -target 1.6\" -jar fregec6.jar ...
	@echo ! where javac is a JDK-7 compiler!
	@echo !-------------------------------------------------------------------------
	@echo

#
#	Avoid recompilation of everything, just remake the compiler with itself and jar it.
#
rebuild:
	@echo "[1;43mMaking $@[0m"
	$(FREGEC2) -make frege.compiler.Main frege.ide.Utilities
	$(RM) $(BUILD)/fregec.jar
	jar -cf $(BUILD)/fregec.jar -C $(BUILD) frege
	jar -uvfe $(BUILD)/fregec.jar frege.compiler.Main
	#$(CP) $(BUILD)/fregec.jar ../eclipse-plugin/lib/fregec.jar

test: compiler
	@echo "[1;42mMaking $@[0m"
	$(FREGECC) -make frege/StandardLibrary.fr
	$(RM) -rf $(BUILD_TEST)
	$(MKDIR) $(BUILD_TEST)
	$(FREGECC) -d $(BUILD_TEST) tests/qc
	$(JAVA) -Xss4m -cp $(BUILD) frege.tools.Quick -v $(BUILD_TEST)

# 	$(BUILD_FREGE_TOOLS)/Doc.class $(BUILD_FREGE_TOOLS)/YYgen.class $(BUILD_FREGE_TOOLS)/LexConvt.class
tools: $(BUILD_FREGE_COMPILER)/Main.class
	$(FREGECC) -make frege/StandardTools.fr

#
# final compiler
#
compiler: $(BUILD_FREGE_COMPILER)/Main.class
	@echo "[1;42mMaking $@[0m"
	$(CP) frege/tools/yygenpar-fr frege/tools/YYgenparM-fr frege/tools/fregedoc.html $(BUILD_FREGE)/tools
	@echo Compiler ready

$(BUILD_FREGE_COMPILER)/grammar/Frege.class: $(FREGE_COMPILER)/grammar/Frege.fr $(BUILD_FREGE_COMPILER)/common/Desugar.class
	@echo "[1;43mMaking $@[0m"
	$(FREGEC2) -make $(FREGE_COMPILER)/grammar/Frege.fr

$(FREGE_COMPILER)/grammar/Frege.fr: $(FREGE_COMPILER)/grammar/Frege.y
	@echo "[1;43mMaking $@[0m"
	@echo We should have 5 shift/reduce conflicts in the grammar.
	$(YACC) -v $<
	$(FREGE) -cp fregec.jar frege.tools.YYgen -m State $@

frege/Version.fr: .git/index
	@echo "[1;43mMaking $@[0m"
	perl scripts/mkversion.pl > $@

$(BUILD_FREGE_COMPILER)/Main.class: compiler2
	@echo "[1;43mMaking $@[0m"
	( test -d $(BUILD_FREGE) -a $(BUILD_BFREGE_COMPILER)/Main.class -nt $(BUILD_FREGE_COMPILER)/Main.class && \
	    $(RM) $(BUILD_FREGE_COMPILER) $(BUILD_FREGE_TOOLS) $(BUILD_FREGE)/prelude ) || true
	$(FREGEC2) -make frege.compiler.Main frege/StandardTools.fr

compiler2: compiler1
	@echo "[1;42mMaking $@[0m"
	( test -d $(BUILD_BFREGE) -a $(COMPF1)/Main.class -nt $(BUILD_BFREGE_COMPILER)/Main.class && $(RM) $(BUILD_BFREGE) ) || true
	$(FREGEC1) -v -make frege.compiler.Main
	@echo stage 2 compiler ready

compiler1: $(FREGE_COMPILER)/grammar/Frege.fr frege/Version.fr
	@echo "[1;42mMaking $@[0m"
	$(FREGEC0) -make frege.compiler.Main
	@echo stage 1 compiler ready

runtime:
	@echo "[1;42mMaking $@[0m"
	$(JAVAC) -d $(BUILD) -source 1.7 -target 1.7 frege/runtime/*.java frege/run/*.java
	@echo Runtime is complete.

#
#	Documentation
#
doc/index.html: runtime

docu: fregec.jar
	@echo "[1;42mMaking $@[0m"
	javadoc -private -sourcepath . -d $(DOC) -encoding UTF-8 frege.runtime
	$(JAVA) -cp fregec.jar frege.tools.Doc -v -d $(DOC) -x frege.compiler,frege.runtime,frege.S,frege.V,frege.PreludePr,frege.run fregec.jar
	$(JAVA) -cp fregec.jar frege.tools.MakeDocIndex $(DOC)

#
#	Difference between 2 compilers
#	The output of the first must have been stored in "save" (see "savejava")
#	Compares all java files in save/frege with those in $(BUILD_FREGE)
#
diffs:
	@echo "[1;42mMaking $@[0m"
	diff -b -r -x "*.class" -I "This code was generated with the frege compiler version" -I "^ +source=" save $(BUILD)

savejava:
	@echo "[1;42mMaking $@[0m"
	perl scripts/savejava.pl

runtime: $(BUILD)

compiler compiler1 compiler2: $(BUILD) runtime
