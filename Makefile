# Makefile for the frege compiler distribution

# $Author$
# $Revision$
# $Id$
# $Date$

.SUFFIXES: .class .fr

JAVAC = javac -encoding UTF-8
YACC = pbyacc
JAVA = java7 "-Dfrege.javac=javac -J-Xmx512m"
JAVAP = $(JAVA) -Dfrege.inPrelude=true



DOC  = doc/frege
DOCF = doc/frege/compiler
DIR0 = build/cfrege
PREL0  = $(DIR0)/prelude
COMPF0  = $(DIR0)/compiler
LIBF0   = $(DIR0)/lib
LIBJ0   = $(DIR0)/j
TOOLSF0 = $(DIR0)/tools
DIR1 = build/afrege
PREL1  = $(DIR1)/prelude
COMPF1  = $(DIR1)/compiler
LIBF1   = $(DIR1)/lib
LIBJ1   = $(DIR1)/j
TOOLSF1 = $(DIR1)/tools
DIR2 = build/bfrege
PREL2   = $(DIR2)/prelude
COMPF2  = $(DIR2)/compiler
LIBF2   = $(DIR2)/lib
LIBJ2   = $(DIR2)/j
TOOLSF2 = $(DIR2)/tools
DIR  = build/frege
PREL    = $(DIR)/prelude
COMPF   = $(DIR)/compiler
LIBF    = $(DIR)/lib
LIBJ    = $(DIR)/j
TOOLSF  = $(DIR)/tools
COMPS   = frege/compiler


FREGE    = $(JAVA) -Xss30m -Xmx900m -cp build
FREGEP   = $(JAVAP) -Xss30m -Xmx900m -cp build
FREGECJ  = $(FREGE) -jar fregec.jar -fp build -d build -nocp -hints
FREGECJP = $(FREGEP) -jar fregec.jar -fp build -d build -nocp -hints
FREGECC  = $(FREGE) frege.compiler.Main  -d build -hints
FREGECCP = $(FREGEP) frege.compiler.Main  -d build -hints
FREGEC0  = $(FREGECJ) -prefix a
FREGEC0P = $(FREGECJP) -prefix a
FREGEC1  = $(FREGE) afrege.compiler.Main -d build -hints -prefix b
FREGEC1P = $(FREGEP) afrege.compiler.Main -d build -hints -prefix b
FREGEC2  = $(FREGE) -server bfrege.compiler.Main -d build -hints
FREGEC2P = $(FREGEP) -server bfrege.compiler.Main -d build -hints
FREGEC3  = $(FREGECJ) -prefix c
FREGEC3P = $(FREGECJP) -prefix c
GENDOC   = $(FREGE)  frege.tools.Doc -d doc

# Prelude files in the order they must be compiled
PRELUDE  =  frege/prelude/Base.fr frege/prelude/Native.fr  frege/prelude/Text.fr \
            frege/contrib/dgronau/Math.fr frege/contrib/dgronau/Floating.fr


{frege/prelude}.fr{$(PREL1)}.class::
	$(FREGEC0) $<
# {frege/compiler}.fr{$(COMPF1)}.class::
#	$(FREGEC0) $<
# {frege/lib}.fr{$(LIBF1)}.class::
#	$(FREGEC0) $<
{frege/tools}.fr{$(TOOLSF1)}.class::
	$(FREGEC0) $<
# {frege}.fr{$(DIR1)}.class::
#	$(FREGEC0) $<
{frege/prelude}.fr{$(PREL0)}.class::
	$(FREGEC3) $<
{frege/compiler}.fr{$(COMPF0)}.class::
	$(FREGEC3) $<
{frege/lib}.fr{$(LIBF0)}.class::
	$(FREGEC3) $<
{frege/tools}.fr{$(TOOLSF0)}.class::
	$(FREGEC3) $<
{frege}.fr{$(DIR0)}.class::
	$(FREGEC3) $<
# {frege/lib}.fr{$(LIBF)}.class::
#	$(FREGECC) $<
{frege/tools}.fr{$(TOOLSF)}.class::
	$(FREGECC) $<
{frege/prelude}.fr{$(PREL)}.class::
	$(FREGEC2) $<

all:  frege.mk runtime compiler library tools # fregec.jar

sanitycheck:
	$(JAVA) -version

stage1: prel0 compiler0 $(TOOLSF0)/LexConvt.class $(TOOLSF0)/YYgen.class
	cp frege/tools/yygenpar.fr frege/tools/YYgenparM.fr build/cfrege/tools
	jar  -cf    fregec.jar -C build cfrege frege
	jar  -uvfe  fregec.jar cfrege.compiler.Main
	@echo you can do now backwards incompatible changes


frege.mk: Makefile mkmk.pl
	perl mkmk.pl <Makefile >frege.mk

dist: fregec.jar
	perl mkdist.pl

fregec.jar: tools $(DIR)/check1
	jar  -cf    fregec.jar -C build frege
	jar  -uvfe  fregec.jar frege.compiler.Main

$(DIR)/check1: $(DIR)/PreludeProperties.class
	$(JAVA) -cp build frege.PreludeProperties && echo Prelude Properties checked >$(DIR)/check1

$(LIBF)/Random.class: $(DIR)/Prelude.class frege/lib/Random.fr
	$(FREGEC2)  frege/lib/Random.fr
$(LIBF)/QuickCheck.class: $(LIBF)/Random.class frege/lib/QuickCheck.fr
	$(FREGEC2)  frege/lib/QuickCheck.fr
$(LIBF)/ForkJoin.class: $(DIR)/Prelude.class frege/lib/ForkJoin.fr
	$(FREGEC2)  frege/lib/ForkJoin.fr
#
# The j library conatins native definitions from java and javax
#
$(LIBJ)/Lang.class: $(DIR)/Prelude.class frege/j/Lang.fr
	$(FREGECC) frege/j/Lang.fr
$(LIBJ)/Awt.class: $(LIBJ)/Util.class frege/j/Awt.fr
	$(FREGECC) frege/j/Awt.fr
$(LIBJ)/Swing.class: $(LIBJ)/Lang.class $(LIBJ)/Awt.class frege/j/Swing.fr
	$(FREGECC) frege/j/Swing.fr
$(LIBJ)/Util.class: $(LIBJ)/Lang.class frege/j/Util.fr
	$(FREGECC) frege/j/Util.fr

$(DIR)/PreludeProperties.class: $(COMPF)/Main.class $(LIBF)/QuickCheck.class frege/PreludeProperties.fr
	$(FREGECC)   frege/PreludeProperties.fr
$(TOOLSF)/Doc.class: $(COMPF)/Main.class frege/tools/Doc.fr
	$(FREGECC)  -make frege.tools.Doc
$(TOOLSF)/YYgen.class: frege/tools/YYgen.fr $(DIR)/Prelude.class
	$(FREGECC)  -make frege/tools/YYgen.fr
$(TOOLSF)/LexConvt.class: frege/tools/LexConvt.fr $(DIR)/Prelude.class
	$(FREGECC)  -make frege/tools/LexConvt.fr
$(TOOLSF1)/YYgen.class: $(DIR1)/Prelude.class frege/tools/YYgen.fr
	$(FREGEC0)  -make frege.tools.YYgen

library: $(LIBF)/Random.class $(LIBF)/QuickCheck.class $(LIBJ)/Swing.class \
    $(LIBJ)/Util.class $(LIBF)/ForkJoin.class
tools: $(TOOLSF)/Doc.class $(TOOLSF)/YYgen.class $(TOOLSF)/LexConvt.class
#
# final compiler
#
compiler: compiler2 $(COMPF)/Grammar.class $(COMPF)/Main.class library tools
	cp frege/tools/yygenpar.fr frege/tools/YYgenparM.fr build/frege/tools
	@echo Compiler ready

$(COMPF)/Grammar.class: frege/compiler/Grammar.fr $(COMPF)/Scanner.class
	$(FREGEC2) -v frege/compiler/Grammar.fr
frege/compiler/Grammar.fr: frege/compiler/Grammar.y
	@echo 1 shift/reduce conflict is ok
	$(YACC) -v frege/compiler/Grammar.y
	$(FREGE) -cp fregec.jar frege.tools.YYgen -m StIO frege/compiler/Grammar.fr
	$(FREGE) -cp fregec.jar frege.tools.LexConvt frege/compiler/Grammar.fr
	rm -f frege/compiler/Grammar.fr.bak
$(COMPF)/Scanner.class: $(DIR)/Prelude.class frege/compiler/Scanner.fr
	$(FREGEC2)  -make frege.compiler.Scanner
$(COMPF)/Main.class: $(DIR)/Prelude.class frege/compiler/Main.fr
	$(FREGEC2)  -make frege.compiler.Main
$(DIR)/Prelude.class: $(COMPF2)/Main.class $(PRELUDE)
	rm -rf $(COMPF)
	rm -rf $(DIR)/prelude
	rm -f $(DIR)/Prelude.class $(DIR)/IO.class $(DIR)/List.class  $(DIR)/Tuples.class
	$(JAVAC) -d build -cp build frege/compiler/JavaUtils.java
	$(FREGEC2P)  $(PRELUDE)
	$(FREGEC2)  -make  frege.Prelude

compiler2: $(COMPF2)/Main.class
	@echo stage 2 compiler ready


$(COMPF2)/Main.class: $(DIR2)/Prelude.class # frege/compiler/Main.fr
	$(FREGEC1) -v -make frege.compiler.Main
$(DIR2)/Prelude.class: $(COMPF1)/Main.class frege/Prelude.fr $(PRELUDE)
	rm -rf $(COMPF2)
	rm -rf $(DIR2)
	$(FREGEC1P)  $(PRELUDE)
	$(FREGEC1)  -make frege.Prelude

$(DIR2)/prelude/Text.class: $(COMPF1)/Main.class
	$(FREGEC1P)  -x9 -v frege/prelude/Text.fr

SOURCES  =      $(COMPS)/Scanner.fr   $(COMPS)/Classtools.fr \
				$(COMPS)/Data.fr      $(COMPS)/Utilities.fr \
				$(COMPS)/Main.fr      $(COMPS)/Grammar.y \
		$(COMPS)/Fixdefs.fr   $(COMPS)/Import.fr    $(COMPS)/Enter.fr \
		$(COMPS)/TAlias.fr    $(COMPS)/Transdef.fr  $(COMPS)/Classes.fr \
		$(COMPS)/Transform.fr $(COMPS)/Typecheck.fr $(COMPS)/TCUtil.fr \
		$(COMPS)/GenMeta.fr   $(COMPS)/GenJava7.fr  $(COMPS)/GenUtil.fr


CLASSES  =       $(COMPF1)/Scanner.class   $(COMPF1)/Classtools.class \
				 $(COMPF1)/Data.class      $(COMPF1)/Utilities.class \
				 $(COMPF1)/Grammar.class \
		$(COMPF1)/Fixdefs.class   $(COMPF1)/Import.class    $(COMPF1)/Enter.class \
		$(COMPF1)/Transdef.class   $(COMPF1)/TCUtil.class   \
		$(COMPF1)/TAlias.class    $(COMPF1)/Classes.class \
		$(COMPF1)/Transform.class $(COMPF1)/Typecheck.class \
		$(COMPF1)/GenUtil.class \
		$(COMPF1)/GenMeta.class   $(COMPF1)/GenJava7.class

#
# GNU make apparently does not understand our meta rules
#
$(DIR1)/IO.class: frege/IO.fr
	$(FREGEC0) $?
$(DIR1)/List.class: frege/List.fr
	$(FREGEC0) $?
$(DIR1)/Tuples.class: frege/Tuples.fr
	$(FREGEC0) $?
$(COMPF1)/Fixdefs.class: $(COMPS)/Fixdefs.fr
	$(FREGEC0) $?
$(COMPF1)/Import.class: $(COMPS)/Import.fr
	$(FREGEC0) $?
$(COMPF1)/Enter.class: $(COMPS)/Enter.fr
	$(FREGEC0) $?
$(COMPF1)/Transdef.class: $(COMPS)/Transdef.fr
	$(FREGEC0) $?
$(COMPF1)/TCUtil.class: $(COMPS)/TCUtil.fr
	$(FREGEC0) $?
$(COMPF1)/TAlias.class: $(COMPS)/TAlias.fr
	$(FREGEC0) $?
$(COMPF1)/Classes.class: $(COMPS)/Classes.fr
	$(FREGEC0) $?
$(COMPF1)/Transform.class: $(COMPS)/Transform.fr
	$(FREGEC0) $?
$(COMPF1)/Typecheck.class: $(COMPS)/Typecheck.fr
	$(FREGEC0) $?
$(COMPF1)/GenMeta.class: $(COMPS)/GenMeta.fr
	$(FREGEC0) $?
$(COMPF1)/GenJava7.class: $(COMPS)/GenJava7.fr
	$(FREGEC0) $?
$(COMPF1)/GenUtil.class: $(COMPS)/GenUtil.fr
	$(FREGEC0) $?
$(LIBF1)/Random.class: frege/lib/Random.fr
	$(FREGEC0) $?
$(LIBF1)/PP.class: frege/lib/PP.fr
	$(FREGEC0) $?
$(LIBF1)/QuickCheck.class: frege/lib/QuickCheck.fr
	$(FREGEC0) $?

PRE1 = $(DIR1)/Prelude.class $(DIR1)/IO.class $(DIR1)/List.class $(DIR1)/Tuples.class


compiler1: $(RUNTIME)  $(DIR1)/check1 $(COMPF1)/Grammar.class $(COMPF1)/Main.class
	@echo stage 1 compiler ready

$(COMPF1)/Grammar.class: frege/compiler/Grammar.fr $(COMPF1)/Scanner.class
	$(FREGEC0)  -make frege.compiler.Grammar
$(COMPF1)/Scanner.class: $(PRE1) frege/compiler/Scanner.fr
	$(FREGEC0)  -make frege.compiler.Scanner
$(COMPF1)/Main.class : $(PRE1) $(LIBF1)/PP.class $(CLASSES)
	$(FREGEC0)  -make frege.compiler.Main
$(DIR1)/Prelude.class: $(PRELUDE) frege/Prelude.fr
	rm -rf $(COMPF1)
	rm -rf $(DIR1)
	$(FREGEC0P) $(PRELUDE)
	$(FREGEC0)  -make frege.Prelude
$(DIR1)/PreludeProperties.class: $(LIBF1)/Random.class $(LIBF1)/QuickCheck.class
	$(FREGEC0)  frege/PreludeProperties.fr
$(DIR1)/check1: $(PRE1) $(LIBF1)/Random.class $(LIBF1)/QuickCheck.class $(DIR1)/PreludeProperties.class
	$(JAVA) -cp build afrege.PreludeProperties && echo Prelude Properties checked >$(DIR1)/check1


PRE0 = $(DIR0)/IO.class $(DIR0)/List.class $(DIR0)/Tuples.class


compiler0: $(DIR0)/check1 $(COMPF0)/Main.class
	@echo stage 0 compiler ready

$(COMPF0)/Main.class : $(PRE0) $(LIBF0)/PP.class frege/compiler/Grammar.fr # fregec.jar
	$(FREGEC3)  -make frege.compiler.Main

prel0:
	rm -rf $(COMPF0)
	rm -rf $(DIR0)
	$(FREGEC3P)  $(PRELUDE)
	$(FREGEC3)  -make frege.Prelude
$(DIR0)/PreludeProperties.class: $(PRE0) $(LIBF0)/Random.class $(LIBF0)/QuickCheck.class frege/PreludeProperties.fr
	$(FREGEC3)  frege/PreludeProperties.fr
$(DIR0)/check1: $(PRE0)  $(DIR0)/PreludeProperties.class
	$(JAVA) -cp build cfrege.PreludeProperties && echo Prelude Properties checked >$(DIR0)/check1


#
#   Runtime
#

runtime: $(RUNTIME)  doc/index.html
	@echo Runtime is complete.
RTDIR    = build/frege/rt
RUNTIME  = build/frege/MD.class    $(COMPF)/JavaUtils.class \
		$(RTDIR)/Lazy.class        $(RTDIR)/Value.class       $(RTDIR)/FV.class \
		$(RTDIR)/Unknown.class \
		$(RTDIR)/Val.class         $(RTDIR)/Box.class \
		$(RTDIR)/Lambda.class      $(RTDIR)/MH.class \
		$(RTDIR)/Lam1.class        $(RTDIR)/Lam2.class      $(RTDIR)/Lam3.class \
		$(RTDIR)/Lam4.class        $(RTDIR)/Lam5.class      $(RTDIR)/Lam6.class \
		$(RTDIR)/Lam7.class        $(RTDIR)/Lam8.class      $(RTDIR)/Lam9.class \
		$(RTDIR)/Lam10.class        $(RTDIR)/Lam11.class      $(RTDIR)/Lam12.class \
		$(RTDIR)/Lam13.class        $(RTDIR)/Lam14.class      $(RTDIR)/Lam15.class \
		$(RTDIR)/Lam16.class        $(RTDIR)/Lam17.class      $(RTDIR)/Lam18.class \
		$(RTDIR)/Lam19.class        $(RTDIR)/Lam20.class      $(RTDIR)/Lam21.class \
		$(RTDIR)/Lam22.class        $(RTDIR)/Lam23.class      $(RTDIR)/Lam24.class \
		$(RTDIR)/Lam25.class        $(RTDIR)/Lam26.class \
		$(RTDIR)/Prod1.class    $(RTDIR)/Prod2.class      $(RTDIR)/Prod3.class \
		$(RTDIR)/Prod4.class    $(RTDIR)/Prod5.class      $(RTDIR)/Prod6.class \
		$(RTDIR)/Prod7.class    $(RTDIR)/Prod8.class      $(RTDIR)/Prod9.class \
		$(RTDIR)/Prod10.class   $(RTDIR)/Prod11.class     $(RTDIR)/Prod12.class \
		$(RTDIR)/Prod13.class   $(RTDIR)/Prod14.class     $(RTDIR)/Prod15.class \
		$(RTDIR)/Prod16.class   $(RTDIR)/Prod17.class     $(RTDIR)/Prod18.class \
		$(RTDIR)/Prod19.class   $(RTDIR)/Prod20.class     $(RTDIR)/Prod21.class \
		$(RTDIR)/Prod22.class   $(RTDIR)/Prod23.class     $(RTDIR)/Prod24.class \
		$(RTDIR)/Prod25.class   $(RTDIR)/Prod26.class \
		$(RTDIR)/Ref.class \
		$(RTDIR)/Array.class       $(RTDIR)/SwingSupport.class \
		$(RTDIR)/FregeCompiler.class \
		build/frege/RT.class
#		$(RTDIR)/Boxed.class       $(RTDIR)/Constant.class \
#		$(RTDIR)/Fun.class         $(RTDIR)/Fun1.class        $(RTDIR)/Fun2.class \
#		$(RTDIR)/Fun3.class        $(RTDIR)/Fun4.class \
#		$(RTDIR)/Fun5.class        $(RTDIR)/Fun6.class \
#		$(RTDIR)/Fun7.class        $(RTDIR)/Fun8.class \
#		$(RTDIR)/Fun9.class        $(RTDIR)/Fun10.class \
#		$(RTDIR)/Fun11.class       $(RTDIR)/Fun12.class \
#		$(RTDIR)/Fun13.class       $(RTDIR)/Fun14.class \
#		$(RTDIR)/Fun15.class       $(RTDIR)/Fun16.class \
#		$(RTDIR)/Fun17.class       $(RTDIR)/Fun18.class \
#		$(RTDIR)/Fun19.class       $(RTDIR)/Fun20.class \
#		$(RTDIR)/Fun21.class       $(RTDIR)/Fun22.class \
#		$(RTDIR)/Fun23.class       $(RTDIR)/Fun24.class \
#		$(RTDIR)/Fun25.class       $(RTDIR)/Fun26.class \
#		$(RTDIR)/Product1.class    $(RTDIR)/Product2.class      $(RTDIR)/Product3.class \
#		$(RTDIR)/Product4.class    $(RTDIR)/Product5.class      $(RTDIR)/Product6.class \
#		$(RTDIR)/Product7.class    $(RTDIR)/Product8.class      $(RTDIR)/Product9.class \
#		$(RTDIR)/Product10.class   $(RTDIR)/Product11.class     $(RTDIR)/Product12.class \
#		$(RTDIR)/Product13.class   $(RTDIR)/Product14.class     $(RTDIR)/Product15.class \
#		$(RTDIR)/Product16.class   $(RTDIR)/Product17.class     $(RTDIR)/Product18.class \
#		$(RTDIR)/Product19.class   $(RTDIR)/Product20.class     $(RTDIR)/Product21.class \
#		$(RTDIR)/Product22.class   $(RTDIR)/Product23.class     $(RTDIR)/Product24.class \
#		$(RTDIR)/Product25.class   $(RTDIR)/Product26.class \





build/frege/MD.class: frege/MD.java
	$(JAVAC) -d build frege/MD.java
$(COMPF)/JavaUtils.class: build/frege/MD.class frege/compiler/JavaUtils.java
	$(JAVAC) -d build -cp build frege/compiler/JavaUtils.java
$(DIR)/RT.class: frege/RT.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lambda.class: frege/rt/Lambda.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Val.class: frege/rt/Val.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Box.class: frege/rt/Box.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/MH.class: frege/rt/MH.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/FV.class: frege/rt/FV.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam1.class: frege/rt/Lam1.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam2.class: frege/rt/Lam2.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam3.class: frege/rt/Lam3.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam4.class: frege/rt/Lam4.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam5.class: frege/rt/Lam5.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam6.class: frege/rt/Lam6.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam7.class: frege/rt/Lam7.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam8.class: frege/rt/Lam8.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam9.class: frege/rt/Lam9.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam10.class: frege/rt/Lam10.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam11.class: frege/rt/Lam11.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam12.class: frege/rt/Lam12.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam13.class: frege/rt/Lam13.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam14.class: frege/rt/Lam14.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam15.class: frege/rt/Lam15.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam16.class: frege/rt/Lam16.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam17.class: frege/rt/Lam17.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam18.class: frege/rt/Lam18.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam19.class: frege/rt/Lam19.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam20.class: frege/rt/Lam20.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam21.class: frege/rt/Lam21.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam22.class: frege/rt/Lam22.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam23.class: frege/rt/Lam23.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam24.class: frege/rt/Lam24.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam25.class: frege/rt/Lam25.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lam26.class: frege/rt/Lam26.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Boxed.class: frege/rt/Boxed.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Value.class: frege/rt/Value.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Constant.class: frege/rt/Constant.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Lazy.class: frege/rt/Lazy.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Ref.class: frege/rt/Ref.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Array.class: frege/rt/Array.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Unknown.class: frege/rt/Unknown.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun.class: frege/rt/Fun.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun1.class: frege/rt/Fun1.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product1.class: frege/rt/Product1.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun2.class: frege/rt/Fun2.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun3.class: frege/rt/Fun3.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun4.class: frege/rt/Fun4.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun5.class: frege/rt/Fun5.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun6.class: frege/rt/Fun6.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun7.class: frege/rt/Fun7.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun8.class: frege/rt/Fun8.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun9.class: frege/rt/Fun9.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun10.class: frege/rt/Fun10.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun11.class: frege/rt/Fun11.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun12.class: frege/rt/Fun12.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun13.class: frege/rt/Fun13.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun14.class: frege/rt/Fun14.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun15.class: frege/rt/Fun15.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun16.class: frege/rt/Fun16.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun17.class: frege/rt/Fun17.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun18.class: frege/rt/Fun18.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun19.class: frege/rt/Fun19.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun20.class: frege/rt/Fun20.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun21.class: frege/rt/Fun21.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun22.class: frege/rt/Fun22.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun23.class: frege/rt/Fun23.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun24.class: frege/rt/Fun24.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun25.class: frege/rt/Fun25.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Fun26.class: frege/rt/Fun26.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product2.class: frege/rt/Product2.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product3.class: frege/rt/Product3.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product4.class: frege/rt/Product4.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product5.class: frege/rt/Product5.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product6.class: frege/rt/Product6.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product7.class: frege/rt/Product7.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product8.class: frege/rt/Product8.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product9.class: frege/rt/Product9.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product10.class: frege/rt/Product10.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product11.class: frege/rt/Product11.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product12.class: frege/rt/Product12.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product13.class: frege/rt/Product13.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product14.class: frege/rt/Product14.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product15.class: frege/rt/Product15.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product16.class: frege/rt/Product16.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product17.class: frege/rt/Product17.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product18.class: frege/rt/Product18.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product19.class: frege/rt/Product19.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product20.class: frege/rt/Product20.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product21.class: frege/rt/Product21.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product22.class: frege/rt/Product22.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product23.class: frege/rt/Product23.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product24.class: frege/rt/Product24.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product25.class: frege/rt/Product25.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Product26.class: frege/rt/Product26.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod1.class: frege/rt/Prod1.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod2.class: frege/rt/Prod2.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod3.class: frege/rt/Prod3.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod4.class: frege/rt/Prod4.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod5.class: frege/rt/Prod5.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod6.class: frege/rt/Prod6.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod7.class: frege/rt/Prod7.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod8.class: frege/rt/Prod8.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod9.class: frege/rt/Prod9.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod10.class: frege/rt/Prod10.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod11.class: frege/rt/Prod11.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod12.class: frege/rt/Prod12.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod13.class: frege/rt/Prod13.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod14.class: frege/rt/Prod14.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod15.class: frege/rt/Prod15.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod16.class: frege/rt/Prod16.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod17.class: frege/rt/Prod17.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod18.class: frege/rt/Prod18.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod19.class: frege/rt/Prod19.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod20.class: frege/rt/Prod20.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod21.class: frege/rt/Prod21.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod22.class: frege/rt/Prod22.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod23.class: frege/rt/Prod23.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod24.class: frege/rt/Prod24.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod25.class: frege/rt/Prod25.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/Prod26.class: frege/rt/Prod26.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/SwingSupport.class: frege/rt/SwingSupport.java
	$(JAVAC) -d build -cp build $?
$(RTDIR)/FregeCompiler.class: frege/rt/FregeCompiler.java
	$(JAVAC) -d build -cp build $?

#
#   Documentation
#

docu:       $(TOOLSF)/Doc.class \
            $(DOC)/Prelude.html     $(DOC)/List.html        $(DOC)/IO.html \
            $(DOC)/prelude/Text.html    $(DOC)/prelude/Base.html \
            $(DOC)/prelude/Native.html \
			$(DOCF)/Scanner.html    $(DOCF)/Classtools.html $(DOCF)/Data.html \
			$(DOCF)/Utilities.html  $(DOCF)/Main.html       $(DOCF)/Grammar.html \
			$(DOCF)/Fixdefs.html    $(DOCF)/Import.html     $(DOCF)/Enter.html \
			$(DOCF)/TAlias.html     $(DOCF)/Transdef.html   $(DOCF)/Classes.html \
			$(DOCF)/Transform.html  $(DOCF)/Typecheck.html  $(DOCF)/TCUtil.html \
			$(DOCF)/GenUtil.html    $(DOCF)/GenMeta.html    $(DOCF)/GenJava7.html   \
			$(DOC)/lib/PP.html      $(DOC)/lib/ForkJoin.html \
			$(DOC)/lib/Random.html  $(DOC)/lib/QuickCheck.html \
			$(DOC)/tools/YYgen.html \
			$(DOC)/tools/Doc.html   $(DOC)/j/Lang.html      $(DOC)/j/Awt.html \
			$(DOC)/j/Util.html      $(DOC)/j/Swing.html


doc/index.html: $(RUNTIME)
	javadoc -private -sourcepath . -d doc -encoding UTF-8 frege frege.rt


#
# docu targets
#
$(DOC)/Prelude.html: $(DIR)/Prelude.class
	$(GENDOC) frege.Prelude
$(DOC)/prelude/Base.html: $(DIR)/prelude/Base.class
	$(GENDOC) frege.prelude.Base
$(DOC)/prelude/Text.html: $(DIR)/prelude/Text.class
	$(GENDOC) frege.prelude.Text
$(DOC)/prelude/Native.html: $(DIR)/prelude/Native.class
	$(GENDOC) frege.prelude.Native
$(DOC)/List.html: $(DIR)/List.class
	$(GENDOC) frege.List
$(DOC)/IO.html: $(DIR)/IO.class
	$(GENDOC) frege.IO
$(DOC)/lib/PP.html: $(DIR)/lib/PP.class
	$(GENDOC) frege.lib.PP
$(DOC)/lib/ForkJoin.html: $(DIR)/lib/ForkJoin.class
	$(GENDOC) frege.lib.ForkJoin
$(DOC)/lib/QuickCheck.html: $(DIR)/lib/QuickCheck.class
	$(GENDOC) frege.lib.QuickCheck
$(DOC)/lib/Random.html: $(DIR)/lib/Random.class
	$(GENDOC) frege.lib.Random
$(DOC)/tools/YYgen.html: $(DIR)/tools/YYgen.class
	$(GENDOC) frege.tools.YYgen
$(DOC)/tools/Doc.html: $(DIR)/tools/Doc.class
	$(GENDOC) frege.tools.Doc
$(DOC)/j/Lang.html: $(LIBJ)/Lang.class
	$(GENDOC) frege.j.Lang
$(DOC)/j/Awt.html: $(LIBJ)/Awt.class
	$(GENDOC) frege.j.Awt
$(DOC)/j/Swing.html: $(LIBJ)/Swing.class
	$(GENDOC) frege.j.Swing
$(DOC)/j/Util.html: $(LIBJ)/Util.class
	$(GENDOC) frege.j.Util
$(DOCF)/Classtools.html: $(COMPF)/Classtools.class
	$(GENDOC) frege.compiler.Classtools
$(DOCF)/Scanner.html: $(COMPF)/Scanner.class
	$(GENDOC) frege.compiler.Scanner
$(DOCF)/Data.html: $(COMPF)/Data.class
	$(GENDOC) frege.compiler.Data
$(DOCF)/Utilities.html: $(COMPF)/Utilities.class
	$(GENDOC) frege.compiler.Utilities
$(DOCF)/Main.html: $(COMPF)/Main.class
	$(GENDOC) frege.compiler.Main
$(DOCF)/Grammar.html: $(COMPF)/Grammar.class
	$(GENDOC) frege.compiler.Grammar
$(DOCF)/Import.html: $(COMPF)/Import.class
	$(GENDOC) frege.compiler.Import
$(DOCF)/Fixdefs.html: $(COMPF)/Fixdefs.class
	$(GENDOC) frege.compiler.Fixdefs
$(DOCF)/Enter.html: $(COMPF)/Enter.class
	$(GENDOC) frege.compiler.Enter
$(DOCF)/TAlias.html: $(COMPF)/TAlias.class
	$(GENDOC) frege.compiler.TAlias
$(DOCF)/Transdef.html: $(COMPF)/Transdef.class
	$(GENDOC) frege.compiler.Transdef
$(DOCF)/Classes.html: $(COMPF)/Classes.class
	$(GENDOC) frege.compiler.Classes
$(DOCF)/Transform.html: $(COMPF)/Transform.class
	$(GENDOC) frege.compiler.Transform
$(DOCF)/Typecheck.html: $(COMPF)/Typecheck.class
	$(GENDOC) frege.compiler.Typecheck
$(DOCF)/TCUtil.html: $(COMPF)/TCUtil.class
	$(GENDOC) frege.compiler.TCUtil
$(DOCF)/GenMeta.html: $(COMPF)/GenMeta.class
	$(GENDOC) frege.compiler.GenMeta
$(DOCF)/GenUtil.html: $(COMPF)/GenUtil.class
	$(GENDOC) frege.compiler.GenUtil
$(DOCF)/GenJava7.html: $(COMPF)/GenJava7.class
	$(GENDOC) frege.compiler.GenJava7