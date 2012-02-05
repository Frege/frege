%{
/* «•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»

    Copyright © 2011, Ingo Wechsung
    All rights reserved.

    Redistribution and use in source and binary forms, with or
    without modification, are permitted provided that the following
    conditions are met:

        Redistributions of source code must retain the above copyright
        notice, this list of conditions and the following disclaimer.

        Redistributions in binary form must reproduce the above
        copyright notice, this list of conditions and the following
        disclaimer in the documentation and/or other materials provided
        with the distribution. Neither the name of the copyright holder
        nor the names of its contributors may be used to endorse or
        promote products derived from this software without specific
        prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE
    COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
    IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
    WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
    PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER
    OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
    SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
    LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
    USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
    AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
    LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
    IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
    THE POSSIBILITY OF SUCH DAMAGE.

    «•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•» */

/**
*   This is the grammar for the Frege language ($Revision$).
*/
package frege.compiler.Grammar where

    /*
     * !!! DO NOT CHANGE FILE Grammar.fr, IT HAS BEEN CREATED AUTOMATICALLY !!!
     */

/*
 * $Author$
 * $Revision$
 * $Date$
 * $Id$
 */

-- import frege.IO(stdout, stderr, <<, BufferedReader)
import frege.List(Tree, keyvalues, keys, insertkv)
import Data.List as DL(elemBy)
import frege.compiler.Data      as D
import frege.compiler.Nice      except (group, annotation, break)
import frege.compiler.Utilities as U(
    posItem, posLine, unqualified, tuple)


version = v "$Revision$" where
    v (m ~ #(\d+)#) | Just g <- m.group 1 = g.atoi
    v _ = 0


type ParseResult = (String, [Def], Maybe String)
type Def = DefinitionT
type Exp = ExprT SName
type Pat = PatternT SName
type Item = Token
type Qual = Either (Maybe (Pos Pat), Exp) [Def]
type Guard = (Position, [Qual], Exp)

infixl 16 `nApp`

// this will speed up the parser by a factor of 70, cause yyprods comes out monotyped.
private yyprod1 :: [(Int, YYsi ParseResult Token)]
    -> StG (YYsi ParseResult Token, [(Int, YYsi ParseResult Token)])

yyerror pos s = U.error pos (msgdoc s)
yyline  = Token.position
yyval   = Token.value

yynice t = case tok of
        DOCUMENTATION -> "documentation comment"
        CHAR          -> show (tv).[0]
        STRCONST      -> "literal " ++ start tv ++ "\""
        LEXERROR      -> "error token " ++ start tv
        EARROW        -> "'=>'"
        ARROW         -> "'->'"
        DCOLON        -> "'::'"
        GETS          -> "'<-'"
        _             -> if Token.line t > 0 then "token " ++ show tv else tv
    where
        tok = yytoken t
        tv = Token.value t
        start tv
            | length tv > 8 = substr tv 0 7 ++ "..."
            | otherwise = tv

yyshow  = Token.show
yyfromCh c = Token CHAR (ctos c) 0 0 0
yyfromId n
    | n >= PACKAGE, n <= INFIXR = Token n (String.toLowerCase (show n)) 1 0 0
    | n == CONID = Token n "constructor or type name" 0 0 0
    | n == VARID = Token n "variable name" 0 0 0
    | otherwise = Token n (show n) 0 0 0
yychar t
    | Token.tokid t == CHAR = (Token.value t).[0]
    | otherwise = '\0'
yytoken t = Token.tokid t
vid t = (Token.value t, Pos t t)

/*
 The following definitions are not strictly necessary, but they help
 to avoid truly crazy type signatures for parse stack itmes and
 considerably speed up type checking in giving the result types of
 certain nonterminal reduction rules.
 Note that you cannot lie about the type of nonterminal reduction rules.
 Note that types like "Maybe x" on the RHS must be given like so: (Maybe x)
 */
//%type package         ParseResult
//%type varop           Token
//%type commata         Int
//%type semicoli        Int
//%type packagename     (Pos String)
//%type packagename1    (Pos String)
//%type nativename      String
//%type nativepur       Bool
//%type docs            String
//%type opstring        String
//%type boundvar        String
//%type operators       [String]
//%type boundvars       [String]
//%type packageclause   (String, Maybe String, Position)
//%type unop            Token
//%type operator        Token
//%type rop13           Token
//%type aeq             Token
//%type varidkw         Token
//%type varid           (Pos String)
//%type varids          [Pos String]
//%type qvarid          SName
//%type qconid          SName
//%type qunop           (Pos String)
//%type binop           (Pos String)
//%type tyname          SName
//%type annoitem        (Pos String)
//%type nativestart     (Pos String)
//%type importspec      ImportItem
//%type importspecs     [ImportItem]
//%type memspec         ImportItem
//%type memspecs        [ImportItem]
//%type importitem      ImportItem
//%type alias           Token
//%type annoitems       [Pos String]
//%type importliste     ImportList
//%type definitions     [Def]
//%type definition      [Def]
//%type import          Def
//%type infix           Def
//%type fixity          Def
//%type typedef         Def
//%type classdef        Def
//%type instdef         Def
//%type derivedef       Def
//%type nativedef       Def
//%type impurenativedef Def
//%type datadef         Def
//%type datainit        Def
//%type annotation      [Def]
//%type fundef          [Def]
//%type documentation   Def
//%type topdefinition   [Def]
//%type publicdefinition [Def]
//%type plocaldef       [Def]
//%type dplocaldef      [Def]
//%type localdef        [Def]
//%type localdefs       [Def]
//%type letdef          [Def]
//%type letdefs         [Def]
//%type wherelet        [Def]
//%type visibledefinition [Def]
//%type wheredef        [Def]
//%type tyvar           TauS
//%type tv              TauS
//%type tvapp           TauS
//%type tau             TauS
//%type tapp            TauS
//%type simpletype      TauS
//%type simpletypes     [TauS]
//%type tauSC           [TauS]
//%type dvars           [TauS]
//%type sigma           SigmaS
//%type forall          SigmaS
//%type rhofun          RhoS
//%type rhotau          RhoS
//%type rho             RhoS
//%type fldtype         (Pos String, SigmaS)
//%type fldtypes        [(Pos String, SigmaS)]
//%type field           (String, Exp)
//%type fields          [(String, Exp)]
//%type getfield        (Token, Bool,Exp)
//%type getfields       [(Token,Bool,Exp)]
//%type unex            Exp
//%type term            Exp
//%type appex           Exp
//%type binex           Exp
//%type expr            Exp
//%type topex           Exp
//%type lambda          Exp
//%type primary         Exp
//%type literal         Exp
//%type exprSC          [Exp]
//%type exprSS          [Exp]
//%type pattern         Pat
//%type funhead         (Position, String, [Pat])
//%type confld          [ConField SName]
//%type conflds         [ConField SName]
//%type contypes        [ConField SName]
//%type dalt            DConS
//%type simpledalt      DConS
//%type strictdalt      DConS
//%type visdalt         DConS
//%type dalts           [DConS]
//%type calt            CAltS
//%type calts           [CAltS]
//%type lcqual          Qual
//%type lcquals         [Qual]
//%type dodefs          [Qual]
//%type gqual           Qual
//%type gquals          [Qual]
//%type guard           Guard
//%type guards          [Guard]
//%type qualifiers      (Token -> SName)
//%explain qualifiers   qualified type name
//%explain package      a package
//%explain packageclause a package clause
//%explain packagename  a package name
//%explain packagename1 a package name
//%explain semicoli     the next definition
//%explain varop        a variable or an operator
//%explain operator     an operator
//%explain operators    some operators
//%explain import       a package import
//%explain infix        a fixity declaration
//%explain fixity       the start of a fixity declaration
//%explain typedef      a type declaration
//%explain annotation   an annotation
//%explain qconid       a qualified constructor or type name
//%explain docs         a sequence of doc comments
//%explain importliste  an import list
//%explain importspecs  a list of import items
//%explain importspec   an import specification
//%explain memspec      a member import specification
//%explain memspecs     a list of member imports
//%explain qunop        a qualified unary operator
//%explain unop         an unary operator
//%explain varidkw      a variable name
//%explain varid        a variable name
//%explain varids       a list of field names
//%explain qvarid       a qualified variable name
//%explain importitem   an import item
//%explain alias        a simple name for a member or import item
//%explain binop        a binary operator
//%explain commata      a sequence of one or more ','
//%explain topdefinition a top level declaration
//%explain publicdefinition a declaration
//%explain localdef     a local declaration
//%explain plocaldef    a protected or private local declaration
//%explain dplocaldef   a commented local declaration
//%explain localdefs    local declarations
//%explain definition   a declaration
//%explain visibledefinition   a protected or private declaration
//%explain definitions  declarations
//%explain opstring     an operator
//%explain tyvar        a type variable
//%explain tv           a type variable
//%explain tvapp        a type variable application
//%explain dvars        a sequence of type variables
//%explain tyname       a type constructor
//%explain tau          a non function type
//%explain tauSC        a list of types
//%explain simpletype   a non function type
//%explain simpletypes  non function types
//%explain rhofun       a type
//%explain rhotau       a type
//%explain rho          a constrained type
//%explain tapp         a type application
//%explain forall       a qualified type
//%explain sigma        a qualified type
//%explain boundvar     a type variable bound in a forall
//%explain boundvars    type variables bound in a forall
//%explain rop13        ':'
//%explain aeq          '='
//%explain classdef     a type class declaration
//%explain instdef      an instance declaration
//%explain derivedef    an instance derivation
//%explain wheredef     declarations local to a class, instance or type
//%explain annoitems    a list of items to annotate
//%explain annoitem     an annotated item
//%explain nativestart  a native item
//%explain nativedef    a declaration of a native item
//%explain impurenativedef    a declaration of a native item
//%explain nativename   a valid java identifier
//%explain nativepur    a native data type
//%explain documentation documentation
//%explain funhead      left hand side of a function or pattern binding
//%explain binex        binary expression
//%explain unex         unary expression
//%explain appex        function application
//%explain primary      a primary expression
//%explain term         a term
//%explain fundef       a function or pattern binding
//%explain topex        a top level expression
//%explain expr         an expression
//%explain literal      a literal
//%explain wherelet     a where clause
//%explain letdef       a function or pattern binding
//%explain letdefs      declarations in a let expression or where clause
//%explain datadef      a data definition
//%explain datainit     a data definition
//%explain dalt         a variant of an algebraic datatype
//%explain simpledalt   a variant of an algebraic datatype
//%explain strictdalt   a variant of an algebraic datatype
//%explain visdalt      a variant of an algebraic datatype
//%explain dalts        an algebraic datatype
//%explain contypes     constructor types
//%explain conflds      constructor fields
//%explain confld       a constructor field
//%explain calt         case alternative
//%explain calts        list of case alternatives
//%explain pattern      a pattern
//%explain lcqual       a list comprehension qualifier
//%explain lcquals      list comprehension qualifiers
//%explain dodefs       do expression qualifiers
//%explain gqual        a guard qualifier
//%explain gquals       guard qualifiers
//%explain guard        a guarded expression
//%explain guards       guarded expressions
//%explain exprSC       list of expressions separated by ','
//%explain exprSS       list of expressions separated by ';'
//%explain lambda       a lambda abstraction
//%explain field        field
//%explain fields       field list
//%explain getfield     field
//%explain getfields    field list
%}

%token VARID CONID QVARID QCONID QUALIFIER DOCUMENTATION
%token PACKAGE IMPORT INFIX INFIXR INFIXL NATIVE DATA WHERE CLASS
%token INSTANCE ABSTRACT TYPE TRUE FALSE IF THEN ELSE CASE OF DERIVE
%token LET IN WHILE DO FORALL PRIVATE PROTECTED PUBLIC PURE
%token INTCONST STRCONST LONGCONST FLTCONST DBLCONST CHRCONST REGEXP BIGCONST
%token ARROW DCOLON GETS EARROW TARROW
%token LOP1 LOP2 LOP3 LOP4 LOP5 LOP6 LOP7 LOP8 LOP9 LOP10 LOP11 LOP12 LOP13 LOP14 LOP15 LOP16
%token ROP1 ROP2 ROP3 ROP4 ROP5 ROP6 ROP7 ROP8 ROP9 ROP10 ROP11 ROP12 ROP13 ROP14 ROP15 ROP16
%token NOP1 NOP2 NOP3 NOP4 NOP5 NOP6 NOP7 NOP8 NOP9 NOP10 NOP11 NOP12 NOP13 NOP14 NOP15 NOP16
%token NOP0 LOP0 ROP0       /*** pseudo tokens never seen by parser */

%start package

%right      ARROW
%right      ROP16           /*** Operators & Precedence ***/
%left       LOP16
%nonassoc   NOP16
%right      ROP15
%left       LOP15
%nonassoc   NOP15
%right      ROP14
%left       LOP14
%nonassoc   NOP14
%right      ROP13
%left       LOP13
%nonassoc   NOP13
%right      ROP12
%left       LOP12
%nonassoc   NOP12
%right      ROP11
%left       LOP11
%nonassoc   NOP11
%right      ROP10
%left       LOP10
%nonassoc   NOP10
%right      ROP9
%left       LOP9
%nonassoc   NOP9
%right      ROP8
%left       LOP8
%nonassoc   NOP8
%right      ROP7
%left       LOP7
%nonassoc   NOP7
%right      ROP6
%left       LOP6
%nonassoc   NOP6
%right      ROP5
%left       LOP5
%nonassoc   NOP5
%right      ROP4
%left       LOP4 '-'
%nonassoc   NOP4
%right      ROP3
%left       LOP3
%nonassoc   NOP3
%right      ROP2
%left       LOP2
%nonassoc   NOP2
%right      ROP1
%left       LOP1
%nonassoc   NOP1
%right      ROP0
%left       LOP0
%nonassoc   NOP0

%%

package:
    packageclause ';' definitions               { \(a,d,p)\w\b     -> do {
                                                        changeST Global.{sub <- SubSt.{
                                                            thisPos = p}};
                                                        YYM.return (a,b,d) }}
    | packageclause WHERE '{' definitions '}'   { \(a,d,p)\w\_\b\_ -> do {
                                                        changeST Global.{sub <- SubSt.{
                                                            thisPos = p}};
                                                        YYM.return (a,b,d) }}
    ;

nativename:
      VARID                     { \t -> Token.value t }
    | CONID                     { \t -> Token.value t }
    | VARID  '.' nativename     { \a\_\c -> Token.value a ++ "." ++ c }
    | QUALIFIER  nativename     { \a\c   -> Token.value a ++ "." ++ c }
    | STRCONST                  { \x -> let s = Token.value x; i = length s - 1 in substr s 1 i }
    ;

packagename1:
    CONID                       { \t     -> do {
                                                changeST Global.{sub <- SubSt.{
                                                    idKind <- insertkv (KeyTk t) (Left())}};
                                                YYM.return (Token.value t, yyline t) }}
    | varidkw '.' packagename1  { \a\_\(c,p) -> (repljavakws (Token.value a) ++ "." ++ c,
                                                 (yyline a).merge p) }
    | QUALIFIER packagename1    { \a\(c,p)   -> (Token.value a ++ "." ++ c,
                                                 (yyline a).merge p) }
    ;

packagename:
    packagename1                { \(nm, pos) -> (magicPack nm, pos) }
    ;                                        

docs:
    DOCUMENTATION                       { Token.value }
    | DOCUMENTATION docs                { \b\a   -> (Token.value b ++ "\n" ++ a) }
    | DOCUMENTATION semicoli docs       { \b\_\a -> (Token.value b ++ "\n" ++ a) }
    ;

packageclause:
    docs PACKAGE packagename                { \docu\p\b   -> (fst b, Just docu, snd b) }
    | PACKAGE packagename                   { \p\b        -> (fst b, Nothing, snd b) }
    | docs PROTECTED PACKAGE packagename    { \docu\p\_\b   -> do {
                                                    g <- getST;
                                                    changeST Global.{options = g.options.{
                                                        flags = U.setFlag g.options.flags INPRELUDE}};
                                                    YYM.return (fst b, Just docu, snd b) }}
    | PROTECTED PACKAGE packagename         { \p\_\b   -> do {
                                                    g <- getST;
                                                    changeST Global.{options = g.options.{
                                                        flags = U.setFlag g.options.flags INPRELUDE}};
                                                    YYM.return (fst b, Nothing, snd b) }}
    ;

semicoli:
    ';'                             { const 1 }
    | ';' semicoli                  { \_\n -> 1+n}
    ;

definitions:
    definition
    | definition semicoli               { const }
    | definition semicoli definitions   { \a\_\b -> a ++ b }
    ;

definition:
    documentation                       { single }
    | documentation definition          { (:) }
    | topdefinition
    | visibledefinition
    ;

visibledefinition:
    PRIVATE     publicdefinition        { \_\ds -> map (updVis Private) ds }
    | PROTECTED publicdefinition        { \_\ds -> map (updVis Protected) ds }
    | PUBLIC    publicdefinition        { \_\ds -> map (updVis Public) ds }
    | ABSTRACT  datadef                 { \_\(d::Def) -> [d.{ctrs <- map updCtr}] }
    ;


topdefinition:
    import                              { single }
    | infix                             { single }
    | publicdefinition
    ;

documentation:
    DOCUMENTATION                     { \t -> DocDcl {pos = yyline t, text = t.value}}
    ;

publicdefinition:
    typedef                             { single }
    | datadef                           { single }
    | classdef                          { single }
    | instdef                           { single }
    | derivedef                         { single }
    | localdef
    ;


localdefs:
    dplocaldef
    | dplocaldef semicoli                { const }
    | dplocaldef semicoli localdefs      { \d\_\ds -> d ++ ds }
    ;

localdef:
    annotation
    | nativedef                         { single }
    | fundef
    ;

plocaldef:
    localdef
    | PRIVATE   localdef                { \_\ds -> map (updVis Private) ds }
    | PROTECTED localdef                { \_\ds -> map (updVis Protected) ds }
    | PUBLIC    localdef                { \_\ds -> map (updVis Public) ds }
    ;

dplocaldef:
    documentation                       { single }
    | documentation dplocaldef          { (:) }
    | plocaldef
    ;

letdef:
    annotation
    | fundef
    ;


letdefs:
    letdef
    | letdef semicoli                   { const }
    | letdef semicoli letdefs           { \ds1\_\ds2 -> ds1 ++ ds2 }
    ;


import:
    IMPORT   packagename importliste
        { \i\b\c -> ImpDcl {pos=snd b, pack=fst b, imports=c, as=Nothing} }
    | IMPORT packagename VARID CONID importliste { \i\p\a\c\l -> do
            when (Token.value a != "as") do
                yyerror (yyline a) (show "as" ++ " expected instead of " ++ show (Token.value a))
            changeST Global.{sub <- SubSt.{idKind <- insertkv (KeyTk c) (Left()) }}
            YYM.return ImpDcl {pos = snd p, pack = fst p, imports = l, as = Just (Token.value c)}
        }
    | IMPORT packagename CONID importliste { \i\p\c\l -> do
            changeST Global.{sub <- SubSt.{idKind <- insertkv (KeyTk c) (Left()) }}
            YYM.return ImpDcl {pos = snd p, pack = fst p, imports = l, as = Just (Token.value c)}
        }
    ;

importliste:
    { linkAll }
    | varid '(' importspecs ')' { \v\_\is\_ -> do
            when ( posItem v `notElem` [ "except", "excluding", "without", "außer", "ohne", "hiding" ]) do
                yyerror (posLine v) (show "hiding" ++ " expected instead of " ++ show (posItem v))
            YYM.return linkAll.{items=is}
        }
    | '(' ')'               { \_\_    -> linkNone }
    | '(' importspecs ')'   { \_\is\_ -> linkNone.{items = is}  }
    | PUBLIC importliste    { \_\il   -> ImportList.{publik = true} il }
    ;

importspecs:
    importspec                   { single }
    | importspec ','             { \s\_ -> [s] }
    | importspec ',' importspecs { liste  }
    ;

importitem:
    qvarid                          { \v        -> protoItem.{ name = v } }
    | CONID '(' memspecs ')'        { \v\_\ms\_ -> protoItem.{ name = Simple v, members = Just ms} }
    | CONID '(' ')'                 { \v\_\_    -> protoItem.{ name = Simple v, members = Just []} }
    | qconid                        { \v        -> protoItem.{ name = v } }
    | operator                      { \t        -> protoItem.{ name = Simple t } }
    | unop                          { \v        -> protoItem.{ name = Simple v} }
    ;

importspec:
    importitem                      { \s      -> ImportItem.{alias = (U.enclosed • Token.value • SName.id • ImportItem.name) s} s}
    | importitem alias              { \s\a    -> ImportItem.{alias = U.enclosed (Token.value a)} s }
    | PUBLIC importspec             { \_\s    -> ImportItem.export s }
    ;

memspec:
    alias               { \v     -> protoItem.{ name  = Simple v,
                                                alias = U.enclosed (Token.value v)} }
    | alias  alias      { \v\a   -> protoItem.{ name  = Simple v,
                                                alias = U.enclosed (Token.value a)} }
    | PUBLIC memspec    { \_\s   -> ImportItem.export s }
    ;

memspecs:
    memspec                 { single }
    | memspec ','           { \s\_ -> [s] }
    | memspec ',' memspecs  { liste  }
    ;

alias:
    VARID
    | CONID
    | operator
    ;

varid:   VARID              { vid }
    ;
    
varidkw: 
    VARID
    | DATA                  { Token.{tokid = VARID} }
    | TYPE                  { Token.{tokid = VARID} }
    | NATIVE                { Token.{tokid = VARID} }
    | PURE                  { Token.{tokid = VARID} }
    | PACKAGE               { Token.{tokid = VARID} }
    | IMPORT                { Token.{tokid = VARID} }
    ;    
    
varids:
    varid                   { single }
    | varid ',' varids      { liste }
    ;

qvarid:  QUALIFIER QUALIFIER varop  { \n\t\v     -> With2 n t v}
    |    QUALIFIER varop            { \t\v       -> With1 t v}
    |    VARID                      { \v         -> Simple v }
    ;
qconid:  QUALIFIER QUALIFIER CONID  { \n\t\v     -> With2 n t v}
    |    QUALIFIER CONID            { \t\v       -> With1 t v}
    |    CONID                      { \v         -> Simple v }
    ;

varop:
    VARID | operator | unop

operator:
      LOP1 | LOP2 | LOP3 | LOP4 | LOP5 | LOP6 | LOP7 | LOP8 | LOP9 | LOP10 | LOP11 | LOP12 | LOP13 | LOP14 | LOP15 | LOP16
    | ROP1 | ROP2 | ROP3 | ROP4 | ROP5 | ROP6 | ROP7 | ROP8 | ROP9 | ROP10 | ROP11 | ROP12 | ROP13 | ROP14 | ROP15 | ROP16
    | NOP1 | NOP2 | NOP3 | NOP4 | NOP5 | NOP6 | NOP7 | NOP8 | NOP9 | NOP10 | NOP11 | NOP12 | NOP13 | NOP14 | NOP15 | NOP16
    ;

unop: '!' | '?' ;


fixity:
      INFIX  INTCONST   { \f\i -> do
                                    t <- U.infixop (yyline i) NOP1 (atoi (Token.value i) - 1)
                                    YYM.return (FixDcl {pos=Pos f i, opid=t, ops=[]}) }
    | INFIXL INTCONST   { \f\i -> do
                                    t <- U.infixop (yyline i) LOP1 (atoi (Token.value i) - 1)
                                    YYM.return (FixDcl {pos=Pos f i, opid=t, ops=[]}) }
    | INFIXR INTCONST   { \f\i -> do
                                    t <- U.infixop (yyline i) ROP1 (atoi (Token.value i) - 1)
                                    YYM.return (FixDcl {pos=Pos f i, opid=t, ops=[]}) }
    ;


opstring:
    operator                { Token.value }
    | VARID                 { Token.value }
    ;

operators:
    opstring                { single  }
    | opstring operators    { (:) }
    ;

infix:
    fixity operators        { \(def::Def)\o -> def.{ops = o}}
    ;

annotation:
    annoitems DCOLON sigma  { \as\_\s -> map (annotation s) as }
        ;

annoitem:
    varid
    | '(' operator ')'          { \_\a\_ -> do binop a }
    | '(' unop ')'              { \_\a\_ -> vid a }
    | '(' '-' ')'               { \_\a\_ -> vid a }
    ;

annoitems:
    annoitem                    { single   }
    | annoitem ',' annoitems    { liste    }
    ;


nativedef:
    PURE impurenativedef        { \_\(d::Def) -> d.{isPure = true} }
    | impurenativedef
    ;

nativestart:
      NATIVE annoitem      { flip const }
    | NATIVE operator      { \_\b  -> do binop b }
    | NATIVE unop          { \_\b  -> vid b }
    | NATIVE '-'           { \_\b  -> vid b }
    ;

impurenativedef:
    nativestart DCOLON sigma
                    { \item\col\t -> NatDcl {pos=posLine item, vis=Public, name=posItem item,
                                                meth=posItem item, typ=t, isPure=false, doc=Nothing}}
    | nativestart nativename DCOLON sigma
                    { \item\j\col\t -> NatDcl {pos=posLine item, vis=Public, name=posItem item,
                                                meth=j, typ=t, isPure=false, doc=Nothing}}
    | nativestart operator   DCOLON sigma
                    { \item\o\col\t -> do {
                            o <- binop o;
                            YYM.return (NatDcl {pos=posLine item, vis=Public, name=posItem item,
                                                meth=posItem o, typ=t, isPure=false, doc=Nothing})}}
    | nativestart unop      DCOLON sigma
                    { \item\o\col\t -> NatDcl {pos=posLine item, vis=Public, name=posItem item,
                                                meth=Token.value o, typ=t, isPure=false, doc=Nothing}}
    ;


boundvars:
      boundvar                    { single }
    | boundvar boundvars          { (:)    }
    ;

boundvar:
    VARID                          { Token.value }
    ;

sigma:
    forall
    | rho                          { ForAll [] }
    ;

forall:
      FORALL boundvars                  '.' rho    { \_\bs\_\r      -> ForAll bs r }
    ;

rho:
    tapp EARROW rhofun              { \tau\t\rho -> do
                                        context <- U.tauToCtx tau
                                        YYM.return (Rho.{context} rho)
                                    }
    | rhofun
    ;

rhofun:
    '(' forall ')'  ARROW rhofun            { \_\a\_\_\b -> RhoFun [] a b }
    | rhotau        ARROW rhofun            { \a\_\b     -> RhoFun [] (ForAll [] a) b }
    | rhotau
    ;

rhotau:
    tapp                                    { RhoTau [] }



tau:
    tapp
    | tapp ARROW tau    { \a\f\b ->  TFun a b }
    ;

tauSC:
    tau                 { single }
    | tau ',' tauSC     { liste  }
    ;

tapp:
    simpletypes         { \taus -> Tau.mkapp (head taus) (tail taus) }
    ;

simpletype:
    tyvar
    | tyname            { \(tn::SName) -> TCon (yyline tn.id) tn}
    | '(' tau ')'      { \_\t\_ -> t }
    | '(' tau ',' tauSC ')'
                        {\_\t\(c::Token)\ts\_ ->
                            let
                                tus = t:ts;
                                i = length tus;
                                tname = With1 baseToken c.{tokid=CONID, value=tuple i}
                            in  (TCon (yyline c) tname).mkapp tus
                        }
    | '[' tau ']'      {\a\t\_ -> TApp (TCon (yyline a)
                                             (With1 baseToken a.{tokid=CONID, value="[]"}))
                                        t }
    ;



tyvar:
    VARID                   { \n -> TVar (yyline n) (Token.value n)  }
;


tyname:
    qconid
    | '[' ']'               { \(a::Token)\_ -> With1 baseToken a.{tokid=CONID, value="[]"} }
    | '(' ')'               { \(a::Token)\_ -> With1 baseToken a.{tokid=CONID, value="()"} }
    | '(' commata ')'       { \(z::Token)\n\_ -> With1 baseToken z.{tokid=CONID, value=tuple (n+1)} }
    | '(' ARROW ')'         { \_\(a::Token)\_ -> With1 baseToken a.{tokid=CONID, value="->"} }
    ;


classdef:
    CLASS CONID tyvar wheredef       {
        \_\i\(tv::TauS)\defs -> ClaDcl {pos = yyline i, vis = Public, name = Token.value i,
                        clvar=tv, supers=[], defs = defs, doc = Nothing}
    }
    | CLASS CONID tapp EARROW varid wheredef {
        \_\i\tau\_\v\defs -> do
            ctxs <- U.tauToCtx tau
            sups <- classContext (Token.value i) ctxs (posItem v)
            YYM.return (ClaDcl {pos = yyline i, vis = Public, name = Token.value i,
                             clvar = TVar (posLine v) (posItem v),
                             supers = sups, defs = defs, doc = Nothing})
    }
    ;


instdef:
    INSTANCE tyname sigma wheredef {
        \ins\t\r\defs -> InsDcl {pos = getpos r, vis = Public, clas=t, typ=r, defs=defs, doc=Nothing}
    }
    ;


derivedef:
    DERIVE tyname sigma     { \d\t\r -> DrvDcl {pos = yyline d, vis = Public, clas=t, typ=r, doc=Nothing}}
    ;

datadef:
    datainit wheredef       { \def\defs -> (def::Def).{defs = defs} }
    ;

nativepur:
    NATIVE PURE     { \_\_ -> true  }
    | PURE NATIVE   { \_\_ -> true  }
    | NATIVE        { \_   -> false }
    ;

datainit:
    DATA CONID '=' nativepur nativename {
        \dat\d\docu\pur\jt -> JavDcl {pos=yyline d, vis=Public, name=Token.value d,
                                    jclas=jt, vars=[], defs=[], isPure = pur, doc=Nothing}
    }
    | DATA CONID dvars '=' nativepur nativename {
        \dat\d\ds\docu\pur\jt -> JavDcl {pos=yyline d, vis=Public, name=Token.value d,
                                    jclas=jt, vars=ds, defs=[], isPure = pur, doc=Nothing}
    }
    | DATA CONID dvars '=' dalts {
        \dat\d\ds\docu\alts -> DatDcl {pos=yyline d, vis=Public, name=Token.value d,
                                       vars=ds, ctrs=alts, defs=[], doc=Nothing}
    }
    | DATA CONID '=' dalts {
        \dat\d\docu\alts -> DatDcl {pos=yyline d, vis=Public, name=Token.value d,
                                    vars=[], ctrs=alts, defs=[], doc=Nothing}
    }
    ;

dvars:
    tyvar                       { single }
    | tyvar dvars               { (:) }
    ;

dalts:
    dalt                        { single }
    | dalt '|' dalts            { liste  }
    ;

dalt:
    visdalt
    | visdalt DOCUMENTATION     { \dc\doc -> (dc::DConS).{doc = Just (Token.value doc)} }
    | DOCUMENTATION visdalt     { \doc\dc -> (dc::DConS).{doc = Just (Token.value doc)} }
    ;

visdalt:
    strictdalt
    | PUBLIC    strictdalt      { \_\dc -> (dc::DConS).{vis = Public}    }
    | PRIVATE   strictdalt      { \_\dc -> (dc::DConS).{vis = Private}   }
    // PROTECTED strictdalt      { \_\dc -> (dc::DConS).{vis = Protected} }
    ;

strictdalt:
    '!' simpledalt              { \_\dcon ->  DCon.{strict=true} dcon }
    | simpledalt
    ;

simpledalt:
    CONID                       { \c        -> DCon {pos=yyline c, vis=Public, strict=false,
                                                name=Token.value c, flds=[], doc=Nothing } }
    | CONID '{' conflds '}'     { \c\_\fs\_ -> DCon {pos=yyline c, vis=Public, strict=false,
                                                name=Token.value c, flds=fs, doc=Nothing } }
    | CONID contypes            { \c\fs     -> DCon {pos=yyline c, vis=Public, strict=false,
                                                name=Token.value c, flds=fs, doc=Nothing } }
    ;

contypes:
    simpletypes                 { map (Field Position.null Nothing Nothing • ForAll [] • RhoTau []) }
    ;

simpletypes:
    simpletype                  { single }
    | simpletype simpletypes    { (:) }
    ;

conflds:
    confld
    | confld ','                    { const }
    | confld DOCUMENTATION          { \cs\(d::Token) -> map ConField.{doc <- addDoc d.value} cs }
    | confld ',' conflds            { \as\c\ls -> as ++ ls }
    | confld DOCUMENTATION conflds  { \as\(d::Token)\ls -> map ConField.{doc <- addDoc d.value} as ++ ls }
    ;

confld:
    varids DCOLON tau           { \vs\_\t -> [Field (snd v) (Just (fst v)) Nothing (ForAll [] (RhoTau [] t)) | v <- vs ]}
    | docs varids DCOLON tau    { \(d::String)\vs\_\t ->
                                        map ConField.{doc=Just d}
                                            [Field (snd v) (Just (fst v)) Nothing (ForAll [] (RhoTau [] t)) | v <- vs ]
                                }
    ;

typedef:
    TYPE CONID '=' tau         { \t\i   \_\r -> TypDcl {pos=yyline i, vis=Public, name=Token.value i, vars=[], rho=RhoTau [] r, doc=Nothing}}
    | TYPE CONID dvars '=' tau { \t\i\vs\_\r -> TypDcl {pos=yyline i, vis=Public, name=Token.value i, vars=vs, rho=RhoTau [] r, doc=Nothing}}
    ;

wheredef :
                                  { [] }
    | WHERE '{' '}'               { \_\_\_ -> []}
    | WHERE '{' localdefs '}'   { \_\_\defs\_ -> defs}
    ;

wherelet:
    WHERE '{' '}'               { \_\_\_ -> []}
    | WHERE '{' letdefs '}'     { \_\_\defs\_ -> defs}
    ;


fundef:
    funhead '=' expr        { \fh\eq\expr -> fundef fh expr }
    | funhead guards        { \fh\gds -> fungds fh gds }
    | fundef wherelet       { \fdefs\defs ->
        case fdefs of
            [fd@FunDcl {expr=x}] -> YYM.return [fd.{expr = nx}] where
                                nx = Let Nil defs x Nothing
            _ -> do
                yyerror (head fdefs).pos ("illegal function definition, where { ... } after annotation?")
                YYM.return fdefs
    }
    ;


funhead:
    binex                           { \x -> do
                                            x <- funhead x
                                            YYM.return x
                                    }
    ;


literal:
    TRUE                            { \x ->  Lit (yyline x) LBool "true"   Nothing}
    | FALSE                         { \x ->  Lit (yyline x) LBool "false"  Nothing}
    | CHRCONST                      { \x ->  Lit (yyline x) LChar   (Token.value x) Nothing }
    | STRCONST                      { \x ->  Lit (yyline x) LString (Token.value x) Nothing }
    | INTCONST                      { \x ->  Lit (yyline x) LInt    (Token.value x) Nothing }
    | BIGCONST                      { \x ->  Lit (yyline x) LBig    (bignum x)      Nothing }
    | LONGCONST                     { \x ->  Lit (yyline x) LLong   (Token.value x) Nothing }
    | FLTCONST                      { \x ->  Lit (yyline x) LFloat  (Token.value x) Nothing }
    | DBLCONST                      { \x ->  Lit (yyline x) LDouble (Token.value x) Nothing }
    | REGEXP                        { \x ->  do litregexp x }
    ;

pattern:
    binex                           { \ex -> do exprToPat ex }
    ;

aeq: ARROW | '=';                   // we can make grammar conflict free if case pat -> ex is forbidden


lcqual:
    gqual
    |expr '=' expr                  { \e\t\x -> do { fh <- funhead e; YYM.return (Right (fundef fh x)) }}
    | LET '{' letdefs '}'           { \_\_\ds\_ -> Right ds }
    ;

lcquals:
    lcqual                          { single }
    | lcqual ',' lcquals            { liste  }
    | lcqual ','                    { (const @ single) }
    ;


dodefs:
    lcqual                          { single }
    | lcqual semicoli               { (const @ single) }
    | lcqual semicoli dodefs        { liste }
    ;


gqual:
    expr                            { \e ->  Left (Nothing, e) }
    | expr GETS expr                { \p\g\e -> do
                                        pat <- exprToPat p
                                        YYM.return (Left (Just (pat, getpos pat), e)) }
    ;

gquals:
    gqual                          { single }
    | gqual ',' gquals             { liste  }
    | gqual ','                    { (const @ single) }
    ;

guard:
    '|' gquals aeq expr            { \a\qs\_\x  -> (yyline a, qs, x) }
    ;

guards:
    guard                           { single }
    | guard guards                  { (:) }
    ;


calt:
    pattern aeq expr              { \p\a\e ->
                                        CAlt {env=Nil, pat=p, ex=e}}
    | pattern guards                { \p\gs -> guardedalt p gs}
    | calt wherelet                 {\(calt::CAltS)\defs ->
                                        let
                                            nx = Let Nil defs calt.ex Nothing;
                                        in calt.{ ex = nx } }
    ;

calts:
    calt                            { single }
    | calt ';'  calts               { liste  }
    | calt ';'                      { \a\_    ->  [a] }
    ;


lambda:
      '\\' pattern lambda           { \_\p\l   -> Lam Nil p l Nothing}
    | '\\' pattern ARROW  topex     { \_\p\_\x -> Lam Nil p x Nothing}
    ;


expr:
    topex DCOLON sigma               { \x\_\t  -> Ann {ex = x, typ=Just t} }
    | topex
    ;

topex:
      IF expr THEN expr ELSE topex     { \_\c\_\t\_\e  -> Ifte c t e Nothing}
    | CASE  expr OF '{' calts   '}'    { \_\e\_\_\as\_ -> Case CNormal e as Nothing}
    | LET '{' letdefs '}' IN  topex    { \_\_\ds\_\_\e -> Let Nil ds e Nothing}
    | lambda
    | binex

    ;

binex:
    binex ROP16 binex                   { mkapp }
    | binex LOP16 binex                 { mkapp }
    | binex NOP16 binex                 { mkapp }
    | binex ROP15 binex                 { mkapp }
    | binex LOP15 binex                 { mkapp }
    | binex NOP15 binex                 { mkapp }
    | binex ROP14 binex                 { mkapp }
    | binex LOP14 binex                 { mkapp }
    | binex NOP14 binex                 { mkapp }
    | binex ROP13 binex                 { mkapp }
    | binex LOP13 binex                 { mkapp }
    | binex NOP13 binex                 { mkapp }
    | binex ROP12 binex                 { mkapp }
    | binex LOP12 binex                 { mkapp }
    | binex NOP12 binex                 { mkapp }
    | binex ROP11 binex                 { mkapp }
    | binex LOP11 binex                 { mkapp }
    | binex NOP11 binex                 { mkapp }
    | binex ROP10 binex                 { mkapp }
    | binex LOP10 binex                 { mkapp }
    | binex NOP10 binex                 { mkapp }
    | binex ROP9  binex                 { mkapp }
    | binex LOP9  binex                 { mkapp }
    | binex NOP9  binex                 { mkapp }
    | binex ROP8  binex                 { mkapp }
    | binex LOP8  binex                 { mkapp }
    | binex NOP8  binex                 { mkapp }
    | binex ROP7  binex                 { mkapp }
    | binex LOP7  binex                 { mkapp }
    | binex NOP7  binex                 { mkapp }
    | binex ROP6  binex                 { mkapp }
    | binex LOP6  binex                 { mkapp }
    | binex NOP6  binex                 { mkapp }
    | binex ROP5  binex                 { mkapp }
    | binex LOP5  binex                 { mkapp }
    | binex NOP5  binex                 { mkapp }
    | binex ROP4  binex                 { mkapp }
    | binex LOP4  binex                 { mkapp }
    | binex '-'   binex                 { mkapp }
    | '-' binex                         { \m\x -> nApp (Vbl (yyline m) (With1 baseToken m.{tokid=VARID, value="negate"}) Nothing) x}
    | binex NOP4  binex                 { mkapp }
    | binex ROP3  binex                 { mkapp }
    | binex LOP3  binex                 { mkapp }
    | binex NOP3  binex                 { mkapp }
    | binex ROP2  binex                 { mkapp }
    | binex LOP2  binex                 { mkapp }
    | binex NOP2  binex                 { mkapp }
    | binex ROP1  binex                 { mkapp }
    | binex LOP1  binex                 { mkapp }
    | binex NOP1  binex                 { mkapp }
    | binex ROP0  appex                 { mkapp }   // we need this only for precedence trickery
    | appex LOP0  binex                 { mkapp }
    | appex NOP0  appex                 { mkapp }
    | appex
    ;

appex:
    unex
    | appex unex                        { nApp }
    ;


unex:
    primary
    | unop unex                        { \u\p -> nApp (Vbl {pos=yyline u, name=Simple u, typ=Nothing}) p}
    ;

qualifiers:
    QUALIFIER                         { With1 }
    | QUALIFIER QUALIFIER             { With2 }
    ;

primary:
    term
    | DO  '{' dodefs  '}'             { \d\_\defs\_   -> do mkMonad (yyline d) defs }
    | primary   '.' VARID             { \p\_\(v::Token) -> Mem p v Nothing}
    | primary   '.' operator          { \p\_\v -> do {v <- unqualified v;
                                                    YYM.return (Mem p v Nothing)}}
    | primary   '.' unop              { \p\_\v -> Mem p v Nothing}
    | qualifiers    '{' VARID '?' '}' { \q\_\(v::Token)\_\_ ->
                                            Vbl (yyline v) (q v.{value <- ("has$" ++)}) Nothing}
    | qualifiers    '{' VARID '=' '}' { \q\_\(v::Token)\_\_ ->
                                            Vbl (yyline v) (q v.{value <- ("upd$" ++)}) Nothing}
    | qualifiers    '{' VARID GETS '}' { \q\_\(v::Token)\_\_ ->
                                            Vbl (yyline v) (q v.{value <- ("chg$" ++)}) Nothing}
    | qualifiers    '{' getfields '}' { \q\(p::Token)\fs\_ -> let {
                        // n   = Simple q;
                        flp = Vbl (yyline p) (wellKnown p "flip") Nothing;
                        bul = Vbl (yyline p) (wellKnown p "•")    Nothing;
                        c []     = undefined;
                        c (f:fs) = fold cex (chup f) fs where {
                            cex x f = bul `nApp` x `nApp` chup f;
                            chup :: (Token, Bool, Exp) -> Exp;
                            chup (r, true, e)  = flp `nApp` Vbl (yyline r) (q r.{value <- ("chg$"++)}) Nothing `nApp` e;
                            chup (r, false, e) = flp `nApp` Vbl (yyline r) (q r.{value <- ("upd$"++)}) Nothing `nApp` e;
                                      }} in c fs }
    | primary   '.' '{' VARID '?' '}' { \p\_\_\(v::Token)\_\_ -> Mem p v.{value <- ("has$"++)} Nothing}
    | primary   '.' '{' VARID '=' '}' { \p\_\_\(v::Token)\_\_ -> Mem p v.{value <- ("upd$"++)} Nothing}
    | primary   '.' '{' VARID GETS '}' {\p\_\_\(v::Token)\_\_ -> Mem p v.{value <- ("chg$"++)} Nothing}
    | primary   '.' '{' getfields '}' { \x\(p::Token)\_\fs\_ ->
                                let {
                        u x [] = x;
                        u x ((r::Token, true , e):xs) = u (Mem x r.{value <- ("chg$" ++)} Nothing  `nApp` e)  xs;
                        u x ((r::Token, false, e):xs) = u (Mem x r.{value <- ("upd$" ++)} Nothing  `nApp` e)  xs;
                                } in u x fs}
    | primary '.' '[' expr ']'      { \p\(t::Token)\_\v\_  ->
                                        Mem p t.{tokid=VARID, value="frozenGetAt"} Nothing
                                            `nApp` v}
    | primary '.' '[' expr '=' expr ']'
                                    { \p\(t::Token)\_\v\_\x\_ ->
                                        Mem p t.{tokid=VARID, value="updAt"} Nothing
                                            `nApp` v `nApp` x }
    | primary '.' '[' expr GETS expr ']'
                                    { \p\(t::Token)\_\v\_\x\_ ->
                                        Mem p t.{tokid=VARID, value="setAt"} Nothing
                                            `nApp` v `nApp` x }
    ;

term:
    qvarid                          { \x   -> Vbl {pos=yyline (SName.id x), name=x, typ=Nothing} }
    | literal
    | '_'                           { \t   -> Vbl {pos = yyline t, name = Simple t.{tokid=VARID, value="_"}, typ=Nothing} }  // only valid as pattern
    | qconid                        { \qc  -> Con {pos=yyline (SName.id qc), name=qc, typ=Nothing} }
    | qconid '{'        '}'         { \qc\_\z    -> ConFS {pos=Pos (SName.id qc) z, name=qc, fields=[], typ=Nothing}}
    | qconid '{' fields '}'         { \qc\_\fs\z -> ConFS {pos=Pos (SName.id qc) z, name=qc, fields=fs, typ=Nothing}}
    | '(' ')'                       { \z\_   -> Con (yyline z) (With1 baseToken z.{tokid=CONID, value="()"}) Nothing}
    | '(' commata ')'               { \z\n\_ -> Con (yyline z) (With1 baseToken z.{tokid=CONID, value=tuple (n+1)}) Nothing}
    | '(' unop ')'                  { \_\x\_ -> Vbl {pos=yyline x, name=Simple x, typ=Nothing} }
    | '(' operator ')'              { \_\o\_ -> (varcon o) (yyline o) (Simple o) Nothing}
    | '(' '-' ')'                   { \_\m\_ -> (Vbl (yyline m) (With1 baseToken m) Nothing) }
    | '(' operator expr ')'         { \z\o\x\_ ->  let // (+1) --> flip (+) 1
                                        flp = Vbl (yyline o) (With1 baseToken underlineToken.{value="flip"}) Nothing
                                        op  = (varcon o) (yyline o) (Simple o) Nothing
                                        ex = nApp (nApp flp op) x
                                    in ex}
    | '(' binex operator ')'        { \_\x\o\_ ->  // (1+) --> (+) 1
                                        nApp ((varcon o) (yyline o) (Simple o) Nothing) x}
    | '(' binex '-' ')'             { \_\x\o\_ ->  // (1+) --> (+) 1
                                        nApp ((varcon o) (yyline o) (Simple o) Nothing) x}
    | '(' expr ',' exprSC ')'       { \a\e\(x::Token)\es\_ -> fold nApp (Con (yyline a)
                                                                   (With1 baseToken x.{tokid=CONID, value=tuple (1+length es)})
                                                                   Nothing)
                                                              (e:es)}
    | '(' expr ';' exprSS ')'       { \a\e\(x::Token)\es\_ -> fold nApp (Vbl (yyline a)
                                                                   (With1 baseToken x.{tokid=VARID, value="strictTuple" ++ show (1+length es)})
                                                                    Nothing)
                                                              (e:es)}
    | '(' expr ')'                  { \_\x\_ -> x }
    | '[' ']'                       { \a\z ->  Con (Pos a z) (With1 baseToken z.{tokid=CONID, value="[]"}) Nothing}
    | '[' exprSC ']'                { \b\es\z -> foldr (\a\as -> nApp (nApp (Con (yyline b) (With1 baseToken b.{tokid=CONID, value=":"}) Nothing) a) as)
                                                       (Con (yyline z)  (With1 baseToken z.{tokid=CONID, value="[]"}) Nothing)
                                                       es}
    | '[' expr '|' lcquals ']'      { \(a::Token)\e\b\qs\(z::Token) -> do {
				let {nil = z.{tokid=CONID, value="[]"}};
				listComprehension (yyline b) e qs
                                            (Con {name = With1 baseToken nil, pos = nil.position, typ = Nothing})
                                    }}
    ;

commata:
    ','                             { const 1 }
    | ',' commata                   { ((+) • const 1) }
    ;

fields:
    field                           { single }
    | field ',' fields              { \a\c\ls ->
                                        if elemBy (using fst) a ls then do {
                                                U.warn (yyline c) (msgdoc ("field `" ++ fst a
                                                    ++ "` should appear only once."));
                                                YYM.return ls
                                            } else
                                                YYM.return (a:ls)
                                    }
    | field ','                     { (const @ single) }
    ;

getfields:
    getfield                        { single }
    | getfield ',' getfields        { liste  }
    | getfield ','                  { (const @ single) }
    ;

getfield:
      VARID GETS expr               { \s\_\x ->  (s, true,  x) }
    | VARID '=' expr                { \s\_\x ->  (s, false, x) }
    | VARID                         { \s     ->  (s, false, Vbl (yyline s) (Simple s) Nothing) }
    ;

field:
    varid '='  expr                  { \s\_\x ->  (posItem s, x) }
    | varid                          { \s     ->  (posItem s, Vbl (posLine s) (Simple (posLine s).first) Nothing) }
    ;

exprSC :
    expr                            { single }
    | expr ',' exprSC               { liste  }
    | expr ','                      { (const @ single) }
    ;
exprSS:
    expr                            { single }
    | expr ';' exprSS               { liste }
    | expr ';'                      { (const @ single) }
    ;

%%

single x = [x]
liste x _ xs = x:xs

addDoc :: String -> Maybe String -> Maybe String
addDoc second  Nothing = Just second
addDoc second (Just first) = Just (first ++ "\n" ++ second)

/// return 'Con' if it is (:)
varcon o
    | Token.value o == ":" = Con
    // Token.value o == "Prelude.:" = Con
    | m ~ #(\w+'*`?$)# <- Token.value o, Just s <- m.group 1, (s.charAt 0).isUpperCase = Con
    | otherwise = Vbl

/// check that operator is unqualified
binop op = do
    tok <- unqualified op
    YYM.return (vid tok)

/// make a binary expression
mkapp a op b = varcon op (yyline op) (Simple op) Nothing `nApp` a `nApp` b



/**
 * change the visibility of a definition
 */
updVis :: Visibility -> DefinitionT  -> DefinitionT
updVis v d = d.{vis = v}

/**
 * set the visibility of a constructor to 'Private'
 */
updCtr :: DConS -> DConS
updCtr dc = dc.{vis = Private}

/*
 * create an annotation
 */
annotation :: SigmaS -> Pos String -> Def
annotation sig (item, pos) = AnnDcl { pos=pos, vis=Public, name=item, typ=sig, doc=Nothing}

exprToPat :: Exp -> YYM Global Pat

exprToPat (Con {pos,name}) = YYM.return (PCon {pos,qname=name,pats=[]})
exprToPat (ConFS {pos,name,fields}) = do
        pfs <- mapSt fpat fields
        YYM.return (PConFS {pos,qname=name,fields=pfs})
    where
        fpat (n,x) = do p <- exprToPat x; YYM.return (n,p)
exprToPat (Vbl  p (Simple Token{value="_"}) _) = do
        u <- U.uniqid
        YYM.return (PVar p ("_" ++ show u))
exprToPat (Vbl p (n@With1 Token{value="Prelude"} Token{value=m~#^strictTuple(\d+)$#}) _)
        | Just s <- m.group 1 = YYM.return (PCon p n.{id<-Token.{value=tuple s.atoi}} [])
exprToPat (Vbl n (Simple x) _)   = YYM.return (PVar n (U.enclosed x.value))
exprToPat (Lit p k v _) = YYM.return (PLit p k v)
exprToPat (App (Vbl _ (Simple Token{value="!"}) _) b _) = do
    p <- exprToPat b
    YYM.return (PStrict p)
exprToPat (App (App (Vbl _ (Simple Token{value="@"}) _) b _) c _)
        | Vbl n (Simple x) _ <- b = do
            cp <- exprToPat c
            YYM.return (PAt n (U.enclosed x.value) cp)
        | App (Vbl _ (Simple Token{value="!"}) _) (Vbl n (Simple x) _) _ <- b = do
            cp <- exprToPat c
            YYM.return (PStrict (PAt n (U.enclosed x.value) cp))
        | otherwise = do
            bs <- U.showexM b
            yyerror (getpos b) (("pattern " ++ bs ++ " not allowed left from @"))
            exprToPat c


exprToPat (App (App (Vbl _ (Simple Token{value="~"}) _) b _) c _)
        | Vbl p (Simple x) _ <- b = do
            cp <- regPat c
            YYM.return (PMat p x.value cp)
        | App (Vbl _ (Simple Token{value="!"}) _) (Vbl p (Simple x) _) _ <- b = do
            cp <- regPat c
            YYM.return (PStrict (PMat p x.value cp))
        | otherwise = do
            bs <- U.showexM b
            yyerror (getpos b) (("pattern " ++ bs ++ " not allowed left from ~"))
            exprToPat c
        where
            regPat (Lit {kind=LRegex, value=regex}) = YYM.return regex
            regPat e = do
                    es <- U.showexM e
                    yyerror (getpos e) (("regex expected right from ~, found " ++ es))
                    YYM.return "regex"



exprToPat (e@App a b _) = do
        pa <- exprToPat a;
        pb <- exprToPat b;
        case pa of
            // PApp _ _ -> YYM.return (PApp pa pb)
            PCon p n ps -> YYM.return (PCon p n (ps++[pb]))
            _ -> do
                es <- U.showexM e
                yyerror (getpos e) (("illegal pattern, only constructor applications are allowed " ++ es))
                YYM.return (PVar {pos=getpos e, var="_"})



exprToPat (Ann e (Just t)) = do
        p <- exprToPat e
        YYM.return (PAnn p t)


exprToPat e =
    do
        es <- U.showexM e
        yyerror pos (("can't make pattern from " ++ es))
        YYM.return (PVar pos "_")
    where
        pos = getpos e



/**
 * Process left hand side of a  function or pattern binding
 * in case it's a variable it resolves to something like
 *
 *  @v = expr@ or
 *  @Nothing = expr@
 */
funhead :: Exp -> YYM Global (Position, String, [Pat])
funhead (ex@Vbl {name}) = do
        pat <- exprToPat ex
        case pat of
            PVar p v ->  YYM.return  (p, v, [])
            somepat  ->  YYM.return  (getpos somepat, "let", [somepat])
/**
 * Otherwise it should be an application
 * > a b c = ....
 * Constructor applications like @(Just x)@ or @(x:xs)@ or @[a,b,c]@ are patterns.
 * Unary application @!p@ is also a pattern.
 * And last but not least, x at p is a pattern.
 */

funhead (ex@App e1 e2 _)
    | Vbl _ (Simple Token{value="!"})  _ <- e1 = do
            pex <- exprToPat ex
            YYM.return (getpos pex, "let", [pex])
    | otherwise = do
        pat <- exprToPat x
        ps  <- mapSt exprToPat xs
        case pat of
            PVar p "@"  -> do
                at <- exprToPat ex 
                YYM.return (p, "let", [at])
            PVar p var  -> YYM.return (p, var, ps)
            PCon p n [] -> YYM.return (p, "let", [PCon p n ps])
            _ -> do
                es <- U.showexM ex
                yyerror (getpos x) ("bad function head " ++ es)
                YYM.return (getpos x, "bad", [pat])
    where
        flatex = map fst (U.flatx ex)
        x = head flatex
        xs = tail flatex


funhead ex = do
        let pos = getpos ex
        es <- U.showexM ex
        yyerror pos ("illegal left hand side of a function definition: " ++ es)
        YYM.return (pos, "_", [])

/**
 * construct a function definition as list
 */
fundef (pos, name, pats) expr = [FunDcl {poss=[pos], vis=Public, name, pats, expr, doc=Nothing}];

/**
 * construct a function with guards
 */
fungds funhead gds = let
                expr = gdsexpr gds;
                (gdln,_,_)   = head gds;
            in fundef funhead expr;



guardedalt :: Pat -> [Guard] -> CAltS
guardedalt p gds =
    case gdsexpr gds of
        x @ Case CWhen _ (alt:_) _
              -> CAlt {env = alt.env, pat=p, ex = x}
        // wrong -> error ("no casewhen : " ++ show wrong)
        // commented out deliberately as this
        // a) should never happen
        // b) if it happens nevertheless, we'll need a hint as to what expr is this
        //    but we can't show an expression outside of the StIO Monad, sorry.


gdsexpr :: [Guard] -> Exp
gdsexpr gds = (flatten @ map trans) gds where
        trans (line,quals,ex) = tg line ex quals
        /*
        * tg ([], x) = x
        * tg (p <- ex : qs, c) = casefallthru (ex) of { p -> TG(qs, c) }
        * tf (ex:qs, c) = casefallthru (ex) of { true -> TG(qs, c) }
        */
        tg ln ex [] = ex
        tg ln ex (Left (p, x):qs) = case p of
                Nothing -> Case CWhen x [calt.{ pat = PLit {kind = LBool, value = "true", pos = ln}}] Nothing
                Just (pat, line) -> Case CWhen x [calt.{ pat = pat }] Nothing
           where
                calt = CAlt {env = Nil, pat = PVar {var = "_", pos = ln}, ex = tg ln ex qs}
        tg ln ex (Right _:_) = error ("line " ++ show ln ++ ": let definition in guard?")
        /*
         * [case e1 of { p1 -> x1 }, case e2 of { p2 -> x2 }, ...
         * ->
         * case e1 of {
         *  p1 -> x1;
         *  _ -> case e2 of {
         *      p2 -> x2:
         *      _ -> ...
         *      }
         *  }
         */
        flatten  []  = error "flatten []"
        flatten  [x] = x
        flatten  ((x@Case CWhen xex (xalts@alt0:_) t):xs) =
            let
                y = flatten xs
                alt = CAlt {env = alt0.env, pat = PVar { var = "_", pos = alt0.pat.pos}, ex = y}
            in
                Case CWhen xex (xalts ++ [alt]) t
        // flatten  wrong = error ("flatten: not a case "  ++ show (map (Exp.show) wrong))
        // commented out deliberately as this
        // a) should never happen
        // b) if it happens nevertheless, we'll need a hint as to what expr is this
        //    but we can't show an expression outside of the StIO Monad, sorry.

/**
 * Check if a pattern is refutable where
 * any constructors except tuple constructors are regarded as refutable.
 * Thus, if the function returns @false@, the pattern is definitely irrefutable.
 * If it returns @true@, the pattern contains some constructor, but at this time
 * we can't decide yet if this is a product constructor.
 */
refutable :: Pat -> Bool
refutable (PVar _ _)     = false
refutable (PAt _ _ p)    = refutable p
refutable (PCon _ name ps)
    | name.id.value == "()" && null ps = false
    | name.id.value `elem` [tuple n | n <- 2..26] = any refutable ps
    | otherwise = true
refutable (PConFS {qname}) = true
refutable (PAnn p _)     = refutable p
// refutable (app@PApp _ _) = any refutable (U.flatp app)
refutable (PLit _ _ _)   = true
refutable (PMat _ _ _)   = true
refutable (PStrict p)    = refutable p

/**
 * List comprehension employs the follwoing translation scheme /TQ [e | Q] L/ where
 * [Q] stands for a possibly empty list of qualifiers
 * [e] for the expression left of the vertical bar in the list comprehension
 * [p] for a pattern
 * [Li] for a list valued expression
 * [B]  for a boolean valued expression
 *
 * When the parser recognizes a list comprehension @comp@, it is translated
 * immediately to an expression with @TQ comp []@
 *
 * > TQ [e | p <- L1, Q] L2
 * > = let h us = case us of {
 * >                 [] -> L2;
 * >                 p:xs' -> TQ [ e where Q ]  (h xs');
 * >                 _:xs' -> h xs';
 * >     } in h L1;
 * > TQ [e | B; Q]  L
 * > = if B then TQ [e | Q] L else L
 * > TQ [e | let p = x, Q]  L
 * > = let p = x in TQ [e | Q] L
 * > TQ [e | ]  L
 * > = e : L
 */
listComprehension pos e [] l2 = YYM.return (cons `nApp` e `nApp` l2)
     where
        f = Position.first pos
        con  = f.{tokid=CONID, value=":"}
        cons = Con {name = With1 baseToken con, pos = con.position, typ = Nothing}

listComprehension pos e (q:qs) l2 = case q of
    Right defs                 -> do   // let defs
        rest <- rest
        YYM.return (Let Nil defs rest Nothing)
    Left (Nothing, b)          -> do   // b
        rest <- rest
        YYM.return (Ifte b rest l2 Nothing)
    Left (Just (pat, pos), xs) -> do   // pat <- x
        uid <- U.uniqid
        let
            f     = Position.first pos
            h     = Simple f.{tokid = VARID, value = "lc" ++ show uid }
            us    = Simple f.{tokid = VARID, value = "_us" ++ show uid }
            xsn   = Simple f.{tokid = VARID, value = "_xs" ++ show uid }
            nil   = f.{tokid=CONID, value="[]"}
            cons  = f.{tokid=CONID, value=":"}
            tolst = listSourceToList.{id <- Token.{line=f.line, col=f.col, offset=f.offset}}
            hvar  = Vbl  h.id.position h Nothing
            usvar = Vbl  us.id.position us Nothing
            tlvar = Vbl  tolst.id.position tolst  Nothing 
            uspat = PVar us.id.position ("_us" ++ show uid)
            xsvar = Vbl  xsn.id.position xsn Nothing
            xspat = PVar xsn.id.position ("_xs" ++ show uid)
            anpat = PVar h.id.{value="_"}.position "_"
            pnil  = PCon nil.position (With1 baseToken nil) []
            pcons p ps = PCon cons.position (With1 baseToken cons) [p, ps]  // p:ps
            calt1 = CAlt {env = Nil, pat = pnil, ex = l2 }  // [] -> l2
        hxs <- listComprehension pos e qs (hvar `nApp` xsvar)
        let
            // p:xs -> TQ [e|qs] (h xs)
            calt2 = CAlt {env = Nil, pat = pcons pat xspat, ex = hxs}
            // _:xs -> h xs
            calt3 = CAlt {env = Nil, pat = pcons anpat xspat, ex = hvar `nApp` xsvar}
            calts = if refutable pat then [calt2, calt1, calt3] else [calt2, calt1]
            ecas = Case CNormal usvar calts  Nothing
            hdef = FunDcl {poss = [pos], vis = Private, name=h.id.value, pats=[uspat], expr=ecas, doc = Nothing}
        YYM.return (Let Nil [hdef] (nApp hvar (nApp tlvar xs)) Nothing)
  where
        rest = listComprehension pos e qs l2

/**
 * This function provides the syntactic sugar for monadic @do@-expressions
 * by transforming
 * > do { e1; p2 <- e2; let defs; ...}
 * to
 * > e1 >> (e2 >>= (\n -> case n of p2 -> let defs in do ...
 * >                                _ -> e2.fail "pattern match failure"))
 */

mkMonad line [e]
    | Left (Nothing, x) <- e = YYM.return x
    | Left (Just _, x)  <- e = do
            yyerror line ("last statement in a monadic do block must not be  pat <- ex")
            YYM.return x
    | Right _ <- e = do
            yyerror line ("last statement in a monadic do block must not be  let decls")
            YYM.return (Vbl line (With1 baseToken line.first.{tokid=VARID, value="undefined"}) Nothing)

mkMonad line (e:es)
    | Left (Nothing,  x) <- e
        =  do
            rest <- mkMonad line es
            YYM.return (bind0 `nApp` x `nApp` rest)
    | Left (Just pps, x) <- e, (pat, pos) <- pps
        = do
            rest <- mkMonad line es
            let res = /* if refutable pat
                      // x >>= \of -> CASE of OF pat -> do ...; _ -> fail in "pattern failed"
                    then bind  `nApp`  x `nApp` (Lam Nil (ofpat pos) (failcase pos pat rest) Nothing)
                    else */ bind  `nApp`  x `nApp` (Lam Nil pat rest Nothing)
            YYM.return res
    | Right defs <- e = do
            rest <- mkMonad line es
            YYM.return (Let Nil defs rest  Nothing)
    where
        f = Position.first line
        wellknown x = With1 monadToken f.{tokid=VARID, value=x}
        local x = Simple f.{tokid=VARID, value=x}
        bind0 = Vbl line (wellknown ">>") Nothing
        bind  = Vbl line (wellknown ">>=") Nothing
        ofvar pos = Vbl pos (local "of") Nothing
        failvar =   Vbl line (wellknown "fail") Nothing
        ofpat pos = PVar pos  "of"
        def pos   = PVar pos  "_"
        failcase pos pat rest = Case CNormal (ofvar pos) [alt1, alt2]  Nothing where
            alt1 = CAlt {env = Nil, pat = pat, ex = rest}
            alt2 = CAlt {env = Nil, pat = def pos, ex = failure }
            // version 2 needed application to invar, we don't
            failure = failvar /*`nApp` invar*/  `nApp` Lit line LString "\"pattern failed\"" Nothing

mkMonad _ _ = Prelude.error "empty monadic do block"


// backslash
bs = '\\';
aQuote = '"';
rex [] sb = packed (reverse (aQuote:sb))
rex ('"':cs) sb = rex cs (aQuote:bs:sb);
rex ('\\':'#':cs) sb = rex cs ('#':sb);
/*
rex ('\\':'n':cs) sb = rex cs (sb << '\\' << 'n');
rex ('\\':'b':cs) sb = rex cs (sb << '\\' << 'b');
rex ('\\':'t':cs) sb = rex cs (sb << '\\' << 't');
rex ('\\':'f':cs) sb = rex cs (sb << '\\' << 'f');
rex ('\\':'r':cs) sb = rex cs (sb << '\\' << 'r');
rex ('\\':'0':cs) sb = rex cs (sb << '\\' << '0');
rex ('\\':'1':cs) sb = rex cs (sb << '\\' << '1');
rex ('\\':'2':cs) sb = rex cs (sb << '\\' << '2');
rex ('\\':'3':cs) sb = rex cs (sb << '\\' << '3');
rex ('\\':'4':cs) sb = rex cs (sb << '\\' << '4');
rex ('\\':'5':cs) sb = rex cs (sb << '\\' << '5');
rex ('\\':'6':cs) sb = rex cs (sb << '\\' << '6');
rex ('\\':'7':cs) sb = rex cs (sb << '\\' << '7');
*/
rex ('\\':'\\':cs) sb = rex cs (bs:bs:bs:bs:sb)
rex ('\\':c:cs) sb    = rex cs (c:bs:bs:sb)
rex (c:cs) sb = rex cs (c:sb)

/// translate regex to java string
reStr rs =  rex (unpacked rs)  [ aQuote ]

litregexp x = do
        let re = reStr (Token.value x)
        case regcomp (Token.value x) of
            Left exc -> do
                U.error (yyline x) (stack (text "regular expression syntax: " : map text (#\r?\n#.splitted exc.getMessage)))
                YYM.return (Lit (yyline x) LRegex re Nothing)
            Right _ ->
                YYM.return (Lit (yyline x) LRegex re Nothing)

--- extract the value of a 'BIGCONST' literal without the trailing N
bignum :: Token -> String
bignum x = strhead x.value (x.value.length-1)

classContext :: String -> [ContextS] -> String -> StG [SName]
classContext clas ctxs cvar = do
        g <- getST
        mapSt (sup g) ctxs
    where
        sup g (Ctx {pos, cname, tau = TVar {var}}) | var == cvar = stio cname
        sup g (Ctx {pos, cname, tau}) = do
            yyerror pos
                ("illegal constraint on `" ++ nice tau g ++ "`, only `" ++ cvar ++ "` may be constrained here")
            stio cname

yyEOF = Token {tokid=CHAR, value=" ", line=maxBound, col=maxBound, offset=maxBound}.position
/**
 * the parser pass
 */
pass :: [Token] -> StG (Maybe ParseResult) // Global -> IO (Maybe ParseResult, Global)
pass = yyparse
