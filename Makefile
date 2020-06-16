# Makefile for the frege compiler distribution.
# When you need to run this under Windows (poor guy!),
# change the path separator characters.
PATHSEP = :
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

TARGET                = 1.8
DOC                   = ../frege.github.com/doc
BUILD                 = build
BUILD7                = $(BUILD)7
BUILD_TEST            = $(BUILD)/test
BUILD_REGRESSION      = $(BUILD)/regression
BUILD_FREGE           = $(BUILD)/frege
BUILD_FREGE_COMPILER  = $(BUILD_FREGE)/compiler
BUILD_FREGE_TOOLS     = $(BUILD_FREGE)/tools
FREGE_COMPILER        = frege/compiler

BUILD_AFREGE          = $(BUILD)/afrege
BUILD_AFREGE_COMPILER = $(BUILD_AFREGE)/compiler

BUILD_BFREGE          = $(BUILD)/bfrege
BUILD_BFREGE_COMPILER = $(BUILD_BFREGE)/compiler

JAVAC    = javac -encoding UTF-8
YACC     = `which byacc || which byaccj || which pbyacc || false`
JAVA     = java -Dfrege.javac=internal
CP       = cp -pf
RM       = rm -rf
MKDIR    = mkdir -p
TIME     = time
TIMEPROG = `which gtime || which time || false`
FREGE    = $(TIME) $(JAVA) -Xss4m -Xmx2222m -cp $(BUILD)

#	compile using the fregec.jar in the working directory
FREGECJ  = $(FREGE) -jar fregec.jar -d $(BUILD) -hints -ascii

#	compile compiler1 with fregec.jar, uses prelude sources from shadow/
FREGEC0  = $(FREGECJ) -nocp -prefix a -sp "shadow$(PATHSEP)."  -target 1.7 

#	compile compiler2 with compiler1
FREGEC1  = $(FREGE) afrege.compiler.Main -d $(BUILD) -hints -greek -inline -prefix b -target $(TARGET)

#	compile final compiler with compiler2
FREGEC2  = $(FREGE) bfrege.compiler.Main -d $(BUILD) -hints -fraktur  -O -target $(TARGET)

#	final compiler
FREGECC  = $(FREGE) frege.compiler.Main -d $(BUILD) -hints -O -target $(TARGET)

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
		frege/java/Util.fr \
		frege/java/util/Regex.fr \
		frege/prelude/PreludeText.fr \
		frege/prelude/Math.fr \
		frege/java/lang/Math.fr

#	command to compile the interpreter
COMPILEREPL=$(TIME) $(JAVA) -Xss2m -cp build:$(JLINE) frege.compiler.Main -d build -O -target $(TARGET) -make  -sp .:$(REPLSP):$(INTERPRETERSP) frege.Starter
REPLSP=../frege-repl/frege-repl-core/src/main/frege:../frege-repl/frege-repl-nativedeps/src/main/java/
INTERPRETERSP=../frege-interpreter/frege-interpreter-core/src/main/frege:../frege-interpreter/frege-interpreter-java-support/src/main/java/

# from  https://oss.sonatype.org/content/groups/public/jline/jline/2.14.6/jline-2.14.6.jar
JLINE=lib/jline-2.14.6.jar

#	shadow Prelude files in the order they must be compiled
SPRELUDE  = $(addprefix shadow/, $(PRELUDE))

.PHONY: all clean diffs dist distclean docu fetch-fregec.jar rebuild runtime sanitycheck savejava shadow-prelude test tools

all: runtime compiler fregec.jar
	@echo "[1;42mMaking $@[0m"

fetch-fregec.jar:
	curl -H 'Accept: application/vnd.github.v3.raw' -kL -o fregec.jar `grep curl .travis.yml | sed 's/.*https:/http:/' | sed 's/\s*$$//'`

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
	test -d ../frege-interpreter/ && test -d ../frege-repl/ && test -f $(JLINE) && $(COMPILEREPL)
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

#
#	Avoid recompilation of everything, just remake the compiler with itself
#
rebuild: $(FREGE_COMPILER)/grammar/Frege.fr
	@echo "[1;43mMaking $@[0m"
	$(FREGEC2) -make frege.compiler.Main frege.ide.Utilities

test: compiler
	@echo "[1;42mMaking $@[0m"
	$(FREGECC) -make frege/StandardLibrary.fr
	$(RM) -rf $(BUILD_TEST)
	$(MKDIR) $(BUILD_TEST)
	$(FREGECC) -d $(BUILD_TEST) tests/qc
	$(JAVA) -Xss4m -cp $(BUILD) frege.tools.Quick -v $(BUILD_TEST)

regression: 
	@echo We assume we have a compiler.
	$(RM)     $(BUILD_REGRESSION)
	$(MKDIR)  $(BUILD_REGRESSION)
	# $(JAVAC)   -d $(BUILD_REGRESSION) tests/comp/*.java
	$(FREGECC) -d $(BUILD_REGRESSION) -make  tests/comp/*.fr
	@echo Now we check whether all difficult code that had an issue earlier will still run
	for i in `echo tests/comp/*.fr | sed 's/\//./g' | sed 's/\.fr//g'` ;do  echo; echo $$i -------------------;if $(JAVA) -cp $(BUILD_REGRESSION):$(BUILD) $$i ;then echo $$i OK; else echo $$i FAILED; exit 1;fi; done 

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
	( test -d $(BUILD_BFREGE) -a $(BUILD_AFREGE_COMPILER)/Main.class -nt $(BUILD_BFREGE_COMPILER)/Main.class && $(RM) $(BUILD_BFREGE) ) || true
	$(FREGEC1) -v -make frege.compiler.Main
	@echo stage 2 compiler ready

compiler1: $(FREGE_COMPILER)/grammar/Frege.fr frege/Version.fr
	@echo "[1;42mMaking $@[0m"
	$(FREGEC0) -make frege.compiler.Main
	@echo stage 1 compiler ready

runtime:
	@echo "[1;42mMaking $@[0m"
	mkdir -p build shadow
	[ "$(TARGET)" = "1.7" ] || $(JAVAC) -d build -source 1.8 -target 1.8 frege/run8/*.java
	$(JAVAC) -d build -nowarn -source 1.7 -target 1.7 frege/runtime/*.java frege/run/*.java frege/run7/*.java
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
