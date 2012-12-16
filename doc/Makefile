# makefile for frege docu
# $Revision$
# $Header: E:/iwcvs/fc/doc/Makefile,v 1.4 2010/10/21 14:28:00 ingo Exp $
# $Log: Makefile,v $
# Revision 1.4  2010/10/21 14:28:00  ingo
# make all commands quite
#
# Revision 1.3  2007/10/01 16:17:01  iw
# - new chapters
#
# Revision 1.2  2007/09/29 17:56:12  iw
# - index and bibliography
#
# Revision 1.1  2007/09/22 16:01:24  iw
# - documentation
#
# Revision 1.3  2007/09/21 16:40:27  iw
# - compiler compiled by the java compiler compiles itself
#
# Revision 1.2  2006/11/12 14:49:16  iw
# - implemented layout
#
# Revision 1.1  2006/05/21 18:00:17  iw
# CVS on memorystick
#
# Revision 1.2  2006/05/20 14:02:41  iw
# make all makes Docu.pdf and Doku.dvi
#

CHAPTERS = chaptertypes.tex chaptermodules.tex chapterdeclarations.tex \
            chapterlex.tex chapterexpr.tex chapternative.tex

IMAGES = utfcode.eps

all: Language.pdf Implementation.pdf

Language.ps: Language.dvi
	dvips Language

Language.pdf: Language.dvi
	dvipdfm -q Language

Language.dvi: Language.tex $(CHAPTERS) $(IMAGES)
	latex -interaction batchmode -quiet Language.tex
	latex -interaction batchmode -quiet Language.tex
	latex -interaction batchmode -quiet Language.tex
	makeindex Language
	latex -interaction batchmode -quiet Language.tex

Implementation.ps: Implementation.dvi
	dvips Implementation

Implementation.pdf: Implementation.dvi
	dvipdfm -q Implementation

Implementation.dvi: Implementation.tex
	latex -interaction batchmode -quiet Implementation.tex

# utfcode.eps: utfcode.pnm
#	pnmtops -nocenter -dpi 72  -noturn -rle utfcode.pnm > utfcode.eps
#
#utfcode.pnm: utfcode.TIF
#	tifftopnm utfcode.tif >utfcode.pnm

Test.ps: Test.dvi
	dvips Test

Test.pdf: Test.dvi
	dvipdfm -q Test

Test.dvi: Test.tex
	latex -interaction batchmode -quiet Test.tex

test: Test.pdf
