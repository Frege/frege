# Makefile for the frege compiler distribution

# $Author$
# $Revision$
# $Id$
# $Date$

JAVAC5 = C:\opt\java5\bin\javac
JAVAC = javac
YACC = pbyacc
JAVA = java

#.SUFFIXES: .class .java .fr
#
#.java.class:
#	$(JAVAC) $<
#
#.fr.java:
#	$(JAVA) $(COMPJ).Frege -j $<

DIR1 = stage1/x3frege
DIR2 = stage2/y3frege
DIR3 = stage2/z3frege
DOC  = doc/y3frege
DOCF = doc/y3frege/compiler
COMPF1 = $(DIR1)/compiler
LIBF1  = $(DIR1)/lib
TOOLSF1 = $(DIR1)/tools
COMPF2 = $(DIR2)/compiler
LIBF2  = $(DIR2)/lib
TOOLSF2 = $(DIR2)/tools
COMPF  = frege/compiler
FREGEC0 = $(JAVA) -Xss100m -Xmx400m -cp frege2.jar frege.compiler.Main -3 -hints -C stage1 -D stage1 -nocp -prefix x3
# DOC0    = $(JAVA) -cp frege2.jar frege.tools.Doc -D doc -prefix x3 -C stage1 -nocp
FREGE1  = $(JAVA) -Xss24m -Xmx1g -cp stage1
FREGE2  = $(JAVA) -Xss52m -Xmx1200m -cp stage2
FREGEC1 = $(FREGE1) x3frege.compiler.Main -fp stage2 -d stage2 -nocp -prefix y3 -O -hints
FREGEC2 = $(FREGE2) y3frege.compiler.Main -fp stage2 -d stage2 -nocp -prefix z3 -hints -v
DOC0    = $(FREGE1) x3frege.tools.Doc     -fp stage2 -d doc    -nocp -prefix y3
DOC1    = $(FREGE2) y3frege.tools.Doc     -fp stage2 -d doc    -nocp -prefix y3




all:  frege.mk compiler1 # fregec.jar

frege.mk: Makefile mkmk.pl
	perl mkmk.pl <Makefile >frege.mk


chess1: lib1 stage1/x3Chess.class

stage1/x3Chess.class: Chess.fr
	$(FREGEC0) Chess.fr

chesstest: chess1
	$(JAVA) -cp stage1 x3Chess

compiler1: lib1  $(COMPF1)/Scanner.class   $(COMPF1)/Classtools.class \
				 $(COMPF1)/Data.class      $(COMPF1)/Utilities.class \
				 $(PASSES1) \
				 $(COMPF1)/Main.class      $(DIR1)/tools/Doc.class

PASSES1 = $(COMPF1)/Grammar.class \
		$(COMPF1)/Fixdefs.class   $(COMPF1)/Import.class    $(COMPF1)/Enter.class \
		$(COMPF1)/TAlias.class    $(COMPF1)/Transdef.class  $(COMPF1)/Classes.class \
		$(COMPF1)/Transform.class $(COMPF1)/Typecheck.class $(COMPF1)/TCUtil.class \
		$(COMPF1)/GenMeta.class   $(COMPF1)/GenJava.class

RT2DIR = stage2/frege/rt
RUNTIME2 = stage2/frege/MD.class    stage2/frege/compiler/JavaUtils.class \
		$(RT2DIR)/Value.class       $(RT2DIR)/Lazy.class        $(RT2DIR)/Unknown.class \
		$(RT2DIR)/Boxed.class       $(RT2DIR)/Constant.class    $(RT2DIR)/Ref.class \
		$(RT2DIR)/Fun.class         $(RT2DIR)/Fun1.class        $(RT2DIR)/Fun2.class \
		$(RT2DIR)/Fun3.class        $(RT2DIR)/Fun4.class \
		$(RT2DIR)/Fun5.class        $(RT2DIR)/Fun6.class \
		$(RT2DIR)/Fun7.class        $(RT2DIR)/Fun8.class \
		$(RT2DIR)/Fun9.class        $(RT2DIR)/Fun10.class \
		$(RT2DIR)/Fun11.class       $(RT2DIR)/Fun12.class \
		$(RT2DIR)/Fun13.class       $(RT2DIR)/Fun14.class \
		$(RT2DIR)/Fun15.class       $(RT2DIR)/Fun16.class \
		$(RT2DIR)/Fun17.class       $(RT2DIR)/Fun18.class \
		$(RT2DIR)/Fun19.class       $(RT2DIR)/Fun20.class \
		$(RT2DIR)/Fun21.class       $(RT2DIR)/Fun22.class \
		$(RT2DIR)/Fun23.class       $(RT2DIR)/Fun24.class \
		$(RT2DIR)/Fun25.class       $(RT2DIR)/Fun26.class \
		$(RT2DIR)/Product1.class    $(RT2DIR)/Product2.class    $(RT2DIR)/Product3.class \
		$(RT2DIR)/Product4.class    $(RT2DIR)/Product5.class    $(RT2DIR)/Product6.class \
		$(RT2DIR)/Product7.class 	$(RT2DIR)/Product8.class 	$(RT2DIR)/Product9.class \
		$(RT2DIR)/Product10.class	$(RT2DIR)/Product11.class	$(RT2DIR)/Product12.class \
		$(RT2DIR)/Product13.class \
		$(RT2DIR)/Product14.class \
		$(RT2DIR)/Product15.class \
		$(RT2DIR)/Product16.class \
		$(RT2DIR)/Product17.class \
		$(RT2DIR)/Product18.class \
		$(RT2DIR)/Product19.class \
		$(RT2DIR)/Product20.class \
		$(RT2DIR)/Product21.class \
		$(RT2DIR)/Product22.class \
		$(RT2DIR)/Product23.class \
		$(RT2DIR)/Product24.class \
		$(RT2DIR)/Product25.class \
		$(RT2DIR)/Product26.class \
		$(RT2DIR)/Array.class stage2/frege/RT.class



compiler2: lib2  $(COMPF2)/Main.class
$(COMPF2)/Main.class : $(COMPF1)/Main.class
	$(FREGEC1) -v -make frege.compiler.Main
compiler3: lib3  $(COMPF3)/Main.class
$(COMPF3)/Main.class : $(COMPF2)/Main.class $(COMPF3)/Grammar.class
	$(FREGEC2) -v frege/compiler/Fixdefs.fr
	$(FREGEC2) -v frege/compiler/Enter.fr
	$(FREGEC2) -v frege/compiler/Import.fr
	$(FREGEC2) -v frege/compiler/TCUtil.fr
	$(FREGEC2) -v frege/compiler/Transdef.fr
	$(FREGEC2) -v frege/compiler/Classes.fr
	$(FREGEC2) -v frege/compiler/TAlias.fr
	$(FREGEC2) -v frege/compiler/Transform.fr
	$(FREGEC2) -v frege/compiler/Typecheck.fr
	$(FREGEC2) -v frege/compiler/GenMeta.fr
	$(FREGEC2) -v frege/compiler/GenJava.fr
	$(FREGEC2) -v frege/compiler/Main.fr

$(COMPF3)/Grammar.class: lib3 $(COMPF2)/Main.class frege/compiler/Grammar.fr
	$(FREGEC2) -v frege/compiler/Classtools.fr
	$(FREGEC2) -v frege/compiler/Data.fr
	$(FREGEC2) -v frege/compiler/Utilities.fr
	$(FREGEC2) -v frege/compiler/Scanner.fr
	$(FREGEC2) -v frege/compiler/Grammar.fr

PASSES2 = $(COMPF2)/Grammar.class \
		$(COMPF2)/Fixdefs.class   $(COMPF2)/Import.class    $(COMPF2)/Enter.class \
		$(COMPF2)/TAlias.class    $(COMPF2)/Transdef.class  $(COMPF2)/Classes.class \
		$(COMPF2)/Transform.class $(COMPF2)/Typecheck.class $(COMPF2)/TCUtil.class \
		$(COMPF2)/GenMeta.class   $(COMPF2)/GenJava.class

test: compiler1 runtime2 lib2
	$(FREGEC1)  S.fr


#
#   Documentation
#

docu:       $(DIR1)/tools/Doc.class compiler2 \
            $(DOC)/Prelude.html     $(DOC)/List.html        $(DOC)/IO.html \
			$(DOCF)/Scanner.html    $(DOCF)/Classtools.html $(DOCF)/Data.html \
			$(DOCF)/Utilities.html  $(DOCF)/Main.html       $(DOCF)/Grammar.html \
			$(DOCF)/Fixdefs.html    $(DOCF)/Import.html     $(DOCF)/Enter.html \
			$(DOCF)/TAlias.html     $(DOCF)/Transdef.html   $(DOCF)/Classes.html \
			$(DOCF)/Transform.html  $(DOCF)/Typecheck.html  $(DOCF)/TCUtil.html \
			$(DOCF)/GenMeta.html    $(DOCF)/GenJava.html \
			$(DOC)/lib/PP.html      $(DOC)/tools/YYgen.html \
			$(DOC)/tools/Doc.html

#
#   Runtime
#

runtime1: stage1/frege/MD.class stage1/frege/Run.class stage1/frege/compiler/JavaUtils.class
runtime2: compiler1 $(COMPF1)/Main.class $(RUNTIME2) doc/index.html

doc/index.html: $(RUNTIME2)
	javadoc -private -sourcepath . -d doc frege frege.rt

stage1/frege/MD.class: frege/MD.java
	$(JAVAC) -d stage1 frege/MD.java
stage1/frege/Run.class: frege/Run.java
	$(JAVAC) -d stage1 frege/Run.java
stage1/frege/CA.class: frege/CA.java
	$(JAVAC5) -d stage1 frege/CA.java
stage1/frege/compiler/JavaUtils.class: stage1/frege/CA.class $(COMPF)/JavaUtils.java
	$(JAVAC) -d stage1 -cp stage1 $(COMPF)/JavaUtils.java

stage2/frege/MD.class: frege/MD.java
	$(JAVAC) -d stage2 frege/MD.java
stage2/frege/compiler/JavaUtils.class: stage2/frege/MD.class $(COMPF)/JavaUtils.java
	$(JAVAC) -d stage2 -cp stage2 $(COMPF)/JavaUtils.java
stage2/frege/RT.class: frege/RT.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Boxed.class: frege/rt/Boxed.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Value.class: frege/rt/Value.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Constant.class: frege/rt/Constant.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Lazy.class: frege/rt/Lazy.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Ref.class: frege/rt/Ref.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Array.class: frege/rt/Array.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Unknown.class: frege/rt/Unknown.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun.class: frege/rt/Fun.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun1.class: frege/rt/Fun1.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product1.class: frege/rt/Product1.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun2.class: frege/rt/Fun2.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun3.class: frege/rt/Fun3.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun4.class: frege/rt/Fun4.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun5.class: frege/rt/Fun5.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun6.class: frege/rt/Fun6.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun7.class: frege/rt/Fun7.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun8.class: frege/rt/Fun8.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun9.class: frege/rt/Fun9.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun10.class: frege/rt/Fun10.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun11.class: frege/rt/Fun11.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun12.class: frege/rt/Fun12.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun13.class: frege/rt/Fun13.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun14.class: frege/rt/Fun14.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun15.class: frege/rt/Fun15.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun16.class: frege/rt/Fun16.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun17.class: frege/rt/Fun17.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun18.class: frege/rt/Fun18.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun19.class: frege/rt/Fun19.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun20.class: frege/rt/Fun20.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun21.class: frege/rt/Fun21.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun22.class: frege/rt/Fun22.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun23.class: frege/rt/Fun23.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun24.class: frege/rt/Fun24.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun25.class: frege/rt/Fun25.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Fun26.class: frege/rt/Fun26.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product2.class: frege/rt/Product2.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product3.class: frege/rt/Product3.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product4.class: frege/rt/Product4.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product5.class: frege/rt/Product5.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product6.class: frege/rt/Product6.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product7.class: frege/rt/Product7.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product8.class: frege/rt/Product8.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product9.class: frege/rt/Product9.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product10.class: frege/rt/Product10.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product11.class: frege/rt/Product11.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product12.class: frege/rt/Product12.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product13.class: frege/rt/Product13.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product14.class: frege/rt/Product14.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product15.class: frege/rt/Product15.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product16.class: frege/rt/Product16.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product17.class: frege/rt/Product17.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product18.class: frege/rt/Product18.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product19.class: frege/rt/Product19.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product20.class: frege/rt/Product20.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product21.class: frege/rt/Product21.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product22.class: frege/rt/Product22.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product23.class: frege/rt/Product23.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product24.class: frege/rt/Product24.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product25.class: frege/rt/Product25.java
	$(JAVAC) -d stage2 -cp stage2 $?
$(RT2DIR)/Product26.class: frege/rt/Product26.java
	$(JAVAC) -d stage2 -cp stage2 $?
#
#   Libraries
#

lib1: runtime1 \
	$(DIR1)/Prelude.class    $(DIR1)/List.class \
	$(DIR1)/IO.class         $(DIR1)/Tuples.class \
	$(DIR1)/lib/PP.class     $(DIR1)/tools/YYgen.class

$(DIR1)/Prelude.class: frege/Prelude2.fr
	rm -rf $(DIR1)
	$(FREGEC0) frege/Prelude2.fr
$(DIR1)/Tuples.class: frege/Tuples.fr $(DIR1)/Prelude.class
	$(FREGEC0) frege/Tuples.fr
$(DIR1)/List.class: frege/List2.fr $(DIR1)/Prelude.class
	$(FREGEC0) frege/List2.fr
$(DIR1)/IO.class: frege/IO2.fr $(DIR1)/Prelude.class
	$(FREGEC0) frege/IO2.fr
$(DIR1)/lib/PP.class: frege/lib/PP.fr $(DIR1)/Prelude.class
	$(FREGEC0) frege/lib/PP.fr

lib2: runtime2 $(COMPF1)/Main.class  \
	$(DIR2)/Prelude.class    $(DIR2)/IO.class \
	$(DIR2)/List.class       $(DIR2)/Tuples.class \
	$(DIR2)/lib/PP.class

lib3: runtime2 $(COMPF2)/Main.class  \
	$(DIR3)/Prelude.class    $(DIR3)/IO.class \
	$(DIR3)/List.class       $(DIR3)/Tuples.class \
	$(DIR3)/lib/PP.class


$(DIR2)/Prelude.class: frege/Prelude.fr $(COMPF1)/Main.class $(RUNTIME2)
	rm -rf $(DIR2)
	$(FREGEC1) frege/Prelude.fr
$(DIR2)/Tuples.class: frege/Tuples.fr $(DIR2)/Prelude.class
	$(FREGEC1) frege/Tuples.fr
$(DIR2)/List.class: frege/List.fr $(DIR2)/IO.class
	$(FREGEC1) frege/List.fr
$(DIR2)/IO.class: frege/IO.fr $(DIR2)/Prelude.class
	$(FREGEC1) frege/IO.fr
$(DIR2)/lib/PP.class: frege/lib/PP.fr $(DIR2)/List.class
	$(FREGEC1) frege/lib/PP.fr

$(DIR3)/Prelude.class: frege/Prelude.fr $(COMPF2)/Main.class $(RUNTIME2)
	rm -rf $(DIR3)
	$(FREGEC2) frege/Prelude.fr
$(DIR3)/Tuples.class: frege/Tuples.fr $(DIR3)/Prelude.class
	$(FREGEC2) frege/Tuples.fr
$(DIR3)/List.class: frege/List.fr $(DIR3)/IO.class
	$(FREGEC2) frege/List.fr
$(DIR3)/IO.class: frege/IO.fr $(DIR3)/Prelude.class
	$(FREGEC2) frege/IO.fr
$(DIR3)/lib/PP.class: frege/lib/PP.fr $(DIR3)/List.class
	$(FREGEC2) frege/lib/PP.fr

#
#   Tools
#
$(DIR1)/tools/YYgen.class: $(DIR1)/List.class $(DIR1)/IO.class frege/tools/YYgen.fr
	$(FREGEC0)  frege/tools/YYgen.fr

$(DIR1)/tools/Doc.class: frege/tools/Doc.fr $(COMPF1)/Main.class
	$(FREGEC0)  frege/tools/Doc.fr

tools2: $(DIR2)/tools/YYgen.class $(DIR2)/tools/Doc.class

$(DIR2)/tools/YYgen.class: $(DIR2)/List.class frege/tools/YYgen.fr
	$(FREGEC1)  frege/tools/YYgen.fr

$(DIR2)/tools/Doc.class: frege/tools/Doc.fr $(COMPF2)/Main.class
	$(FREGEC1)  frege/tools/Doc.fr


#
# compiler 1 targets
#
$(COMPF1)/Data.class: frege/compiler/Data.fr
	$(FREGEC0) frege/compiler/Data.fr
$(COMPF1)/Utilities.class: frege/compiler/Utilities.fr $(COMPF1)/Data.class $(COMPF1)/Classtools.class
	$(FREGEC0) frege/compiler/Utilities.fr
$(COMPF1)/Scanner.class: $(COMPF)/Scanner.fr $(COMPF1)/Classtools.class $(COMPF1)/Utilities.class
	$(FREGEC0) frege/compiler/Scanner.fr
$(COMPF1)/Classtools.class: frege/compiler/Classtools2.fr runtime1
	$(FREGEC0) frege/compiler/Classtools2.fr
$(COMPF1)/Grammar.class: $(COMPF)/Grammar.fr $(COMPF1)/Scanner.class
	$(FREGEC0) frege/compiler/Grammar.fr
$(COMPF)/Grammar.fr: $(COMPF)/Grammar.y $(DIR1)/tools/YYgen.class frege/tools/yygenpar.fr
	@echo 5 shift/reduce conflicts are ok
	$(YACC) -v $(COMPF)/Grammar.y
	$(JAVA)  -cp .;stage1 x3frege.tools.YYgen -m StIO $(COMPF)/Grammar.fr
$(COMPF1)/Fixdefs.class: $(COMPF)/Fixdefs.fr $(COMPF1)/Utilities.class
	$(FREGEC0) frege/compiler/Fixdefs.fr
$(COMPF1)/Import.class: $(COMPF)/Import.fr $(COMPF1)/Classtools.class $(COMPF1)/Utilities.class
	$(FREGEC0) frege/compiler/Import.fr
$(COMPF1)/Enter.class: $(COMPF)/Enter.fr $(COMPF1)/Utilities.class
	$(FREGEC0) frege/compiler/Enter.fr
$(COMPF1)/TAlias.class: $(COMPF)/TAlias.fr $(COMPF1)/Utilities.class $(COMPF1)/Transdef.class
	$(FREGEC0) frege/compiler/TAlias.fr
$(COMPF1)/Transdef.class: $(COMPF)/Transdef.fr $(COMPF1)/Utilities.class $(COMPF1)/Fixdefs.class $(COMPF1)/Enter.class
	$(FREGEC0) frege/compiler/Transdef.fr
$(COMPF1)/Classes.class: $(COMPF)/Classes.fr $(COMPF1)/Utilities.class $(COMPF1)/TCUtil.class
	$(FREGEC0) frege/compiler/Classes.fr
$(COMPF1)/Transform.class: $(COMPF)/Transform.fr $(COMPF1)/Utilities.class
	$(FREGEC0) frege/compiler/Transform.fr
$(COMPF1)/TCUtil.class: $(COMPF)/TCUtil.fr $(COMPF1)/Utilities.class
	$(FREGEC0) frege/compiler/TCUtil.fr
$(COMPF1)/Typecheck.class: $(COMPF)/Typecheck.fr $(COMPF1)/Transform.class $(COMPF1)/TCUtil.class
	$(FREGEC0) frege/compiler/Typecheck.fr
$(COMPF1)/GenMeta.class: $(COMPF)/GenMeta.fr $(COMPF1)/Utilities.class $(DIR1)/lib/PP.class
	$(FREGEC0) frege/compiler/GenMeta.fr
$(COMPF1)/GenJava.class: $(COMPF)/GenJava.fr $(COMPF1)/Transform.class $(COMPF1)/GenMeta.class
	$(FREGEC0) frege/compiler/GenJava.fr
$(COMPF1)/Main.class: $(PASSES1) $(COMPF)/Main.fr
	$(FREGEC0) frege/compiler/Main.fr


#
# docu targets
#
$(DOC)/Prelude.html: $(DIR2)/Prelude.class
	$(DOC0) frege.Prelude
$(DOC)/List.html: $(DIR2)/List.class
	$(DOC0) frege.List
$(DOC)/IO.html: $(DIR2)/IO.class
	$(DOC0) frege.IO
$(DOC)/lib/PP.html: $(DIR2)/lib/PP.class
	$(DOC0) frege.lib.PP
$(DOC)/tools/YYgen.html: $(DIR2)/tools/YYgen.class
	$(DOC0) frege.tools.YYgen
$(DOC)/tools/Doc.html: $(DIR2)/tools/Doc.class
	$(DOC0) frege.tools.Doc
$(DOCF)/Classtools.html: $(COMPF2)/Classtools.class
	$(DOC0) frege.compiler.Classtools
$(DOCF)/Scanner.html: $(COMPF2)/Scanner.class
	$(DOC0) frege.compiler.Scanner
$(DOCF)/Data.html: $(COMPF2)/Data.class
	$(DOC0) frege.compiler.Data
$(DOCF)/Utilities.html: $(COMPF2)/Utilities.class
	$(DOC0) frege.compiler.Utilities
$(DOCF)/Main.html: $(COMPF2)/Main.class
	$(DOC0) frege.compiler.Main
$(DOCF)/Grammar.html: $(COMPF2)/Grammar.class
	$(DOC0) frege.compiler.Grammar
$(DOCF)/Import.html: $(COMPF2)/Import.class
	$(DOC0) frege.compiler.Import
$(DOCF)/Fixdefs.html: $(COMPF2)/Fixdefs.class
	$(DOC0) frege.compiler.Fixdefs
$(DOCF)/Enter.html: $(COMPF2)/Enter.class
	$(DOC0) frege.compiler.Enter
$(DOCF)/TAlias.html: $(COMPF2)/TAlias.class
	$(DOC0) frege.compiler.TAlias
$(DOCF)/Transdef.html: $(COMPF2)/Transdef.class
	$(DOC0) frege.compiler.Transdef
$(DOCF)/Classes.html: $(COMPF2)/Classes.class
	$(DOC0) frege.compiler.Classes
$(DOCF)/Transform.html: $(COMPF2)/Transform.class
	$(DOC0) frege.compiler.Transform
$(DOCF)/Typecheck.html: $(COMPF2)/Typecheck.class
	$(DOC0) frege.compiler.Typecheck
$(DOCF)/TCUtil.html: $(COMPF2)/TCUtil.class
	$(DOC0) frege.compiler.TCUtil
$(DOCF)/GenMeta.html: $(COMPF2)/GenMeta.class
	$(DOC0) frege.compiler.GenMeta
$(DOCF)/GenJava.html: $(COMPF2)/GenJava.class
	$(DOC0) frege.compiler.GenJava