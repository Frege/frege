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


import frege.List(Tree, keyvalues, keys, insertkv)
import Data.List as DL(elemBy)
import frege.compiler.Data      as D
import frege.compiler.Nice      except (group, annotation, break)
import frege.compiler.Utilities as U(
    posItem, posLine, unqualified, tuple)
import frege.compiler.GUtil


// this will speed up the parser by a factor of 70, cause yyprods comes out monotyped.
private yyprod1 :: [(Int, YYsi ParseResult Token)]
    -> StG (YYsi ParseResult Token, [(Int, YYsi ParseResult Token)])


/*
 The following definitions are not strictly necessary, but they help
 to avoid truly crazy type signatures for parse stack itmes and
 considerably speed up type checking in giving the result types of
 certain nonterminal reduction rules.
 Note that you cannot lie about the type of nonterminal reduction rules.
 Note that types like "Maybe x" on the RHS must be given like so: (Maybe x)
 */
//%type package         ParseResult
//%type script          ParseResult
//%type varop           Token
//%type thenx           Token
//%type elsex           Token
//%type mbdot           Token
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
//%type word            String
//%type words           [String]
//%type varid           (Pos String)
//%type varids          [Pos String]
//%type fldid           (Position, String, Visibility, Bool)
//%type strictfldid     (Position, String, Visibility, Bool)
//%type plainfldid      (Position, String, Visibility, Bool)
//%type fldids          [(Position, String, Visibility, Bool)]
//%type qvarid          SName
//%type qvarop          SName
//%type qvarids         [SName]
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
//%type tauSB           [TauS]
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
//%type kind            Kind
//%type simplekind      Kind
//%explain mbdot        '.' or '•'
//%explain thenx        then branch
//%explain elsex        else branch
//%explain qualifiers   qualified type name
//%explain package      a package
//%explain packageclause a package clause
//%explain packagename  a package name
//%explain packagename1 a package name
//%explain script      a frege script
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
//%explain word         a word
//%explain words        words
//%explain varidkw      a variable name
//%explain varid        a variable name
//%explain varids       a list of field names
//%explain qvarid       a qualified variable name
//%explain qvarop       a qualified variable name
//%explain qvarids      a list of qualified variable names
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
//%explain tauSB        a list of types separated by '|'
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
//%explain kind         a type kind
//%explain simplekind   a type kind
//%explain fldid        a field specification
//%explain strictfldid  a field specification
//%explain plainfldid   a field specification
//%explain fldids       field specifications
%}

%token VARID CONID QVARID QCONID QUALIFIER DOCUMENTATION
%token PACKAGE IMPORT INFIX INFIXR INFIXL NATIVE DATA WHERE CLASS
%token INSTANCE ABSTRACT TYPE TRUE FALSE IF THEN ELSE CASE OF DERIVE
%token LET IN WHILE DO FORALL PRIVATE PROTECTED PUBLIC PURE THROWS
%token INTCONST STRCONST LONGCONST FLTCONST DBLCONST CHRCONST REGEXP BIGCONST
%token ARROW DCOLON GETS EARROW TARROW DOTDOT
%token LOP1 LOP2 LOP3 LOP4 LOP5 LOP6 LOP7 LOP8 LOP9 LOP10 LOP11 LOP12 LOP13 LOP14 LOP15 LOP16
%token ROP1 ROP2 ROP3 ROP4 ROP5 ROP6 ROP7 ROP8 ROP9 ROP10 ROP11 ROP12 ROP13 ROP14 ROP15 ROP16
%token NOP1 NOP2 NOP3 NOP4 NOP5 NOP6 NOP7 NOP8 NOP9 NOP10 NOP11 NOP12 NOP13 NOP14 NOP15 NOP16
%token NOP0 LOP0 ROP0       /*** pseudo tokens never seen by parser */
%token INTERPRET

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
                                                        YYM.return $ Program.Module (a,b,d) }}
    | packageclause WHERE '{' definitions '}'   { \(a,d,p)\w\_\b\_ -> do {
                                                        changeST Global.{sub <- SubSt.{
                                                            thisPos = p}};
                                                        YYM.return $ Program.Module (a,b,d) }}
    | INTERPRET script {\_\d -> d}
    ;

script:
    expr {\e -> do {
                                YYM.return $ Program.Expression e}}
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
    | packageclause words '(' qvarids ')'   { \p\vs\v\qs\_ -> do {
                                                     g <- getST;
                                                     let {clause = unwords vs};
                                                     let {expected = ["inline" , "inline candidates"]};
                                                     when (clause `notElem` expected) do {
                                                        yyerror (yyline v) (show (head expected) ++ " expected instead of " ++ show clause)
                                                     };
                                                     changeST Global.{sub <- SubSt.{
                                                            toExport = qs}};
                                                     YYM.return p;}
                                                 }
    ;

word:
    VARID                           { Token.value }
    ;

words:
    word                            { single }
    | word words                    { (:) }
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
    importitem                      { \s      -> ImportItem.{alias = (U.enclosed . Token.value . SName.id . ImportItem.name) s} s}
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

// varids:
//    varid                   { single }
//    | varid ',' varids      { liste  }
//    ;

qvarids:
    qvarop                  { single }
    | qvarop ',' qvarids    { liste  }
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

qvarop:  QUALIFIER QUALIFIER varop  { \n\t\v     -> With2 n t v}
    |    QUALIFIER varop            { \t\v       -> With1 t v}
    |    varop                      { \v         -> Simple v }
    ;

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
                                                meth=posItem item, typ=t, isPure=false, 
                                                throwing=[], doc=Nothing}}
    | nativestart nativename DCOLON sigma
                    { \item\j\col\t -> NatDcl {pos=posLine item, vis=Public, name=posItem item,
                                                meth=j, typ=t, isPure=false, 
                                                throwing=[], doc=Nothing}}
    | nativestart operator   DCOLON sigma
                    { \item\o\col\t -> do {
                            o <- binop o;
                            YYM.return (NatDcl {pos=posLine item, vis=Public, name=posItem item,
                                                meth=posItem o, typ=t, isPure=false, 
                                                throwing=[], doc=Nothing})}}
    | nativestart unop      DCOLON sigma
                    { \item\o\col\t -> NatDcl {pos=posLine item, vis=Public, name=posItem item,
                                                meth=Token.value o, typ=t, isPure=false, 
                                                throwing=[], doc=Nothing}}
    | impurenativedef THROWS tauSC
                    { \def\_\taus -> Def.{throwing=taus} def } 
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
    FORALL boundvars mbdot rho      { \_\bs\_\r      -> ForAll  [ (b,KVar) | b <- bs ]  r }
    ;

mbdot:
    '.'
    | ROP1                         { \dot -> do
                                        when (Token.value dot != "•") do
                                            yyerror (yyline dot)
                                                ("'.' expected instead of " ++ show dot.value)
                                        YYM.return dot
                                    }
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
    | tapp ARROW tau    { \a\f\b ->  TApp (TApp (TCon (yyline f) (With1 baseToken f.{tokid=CONID, value="->"})) a) b }
    ;

tauSC:
    tau                 { single }
    | tau ',' tauSC     { liste  }
    ;

tauSB:
    tau                 { single }
    | tau '|' tauSB     { liste  }
    ;

tapp:
    simpletypes         { \taus -> Tau.mkapp (head taus) (tail taus) }
    ;

simpletype:
    tyvar
    | tyname            { \(tn::SName) -> TCon (yyline tn.id) tn}
    | '(' tau ')'       { \_\t\_ -> t }
    | '(' tau ',' tauSC ')'
                        {\_\t\(c::Token)\ts\_ ->
                            let
                                tus = t:ts;
                                i = length tus;
                                tname = With1 baseToken c.{tokid=CONID, value=tuple i}
                            in  (TCon (yyline c) tname).mkapp tus
                        }
    | '(' tau '|' tauSB ')' { \_\t\e\ts\_ -> mkEither (yyline e) t ts }
    | '[' tau ']'      {\a\t\_ -> TApp (TCon (yyline a)
                                             (With1 baseToken a.{tokid=CONID, value="[]"}))
                                        t }
    ;



tyvar:
    VARID                        { \n         -> TVar (yyline n) KVar (Token.value n)  }
    | '('  VARID DCOLON kind ')' { \_\n\_\k\_ -> TVar (yyline n) k    (Token.value n)  }
;


tyname:
    qconid
    | '[' ']'               { \(a::Token)\_ -> With1 baseToken a.{tokid=CONID, value="[]"} }
    | '(' ')'               { \(a::Token)\_ -> With1 baseToken a.{tokid=CONID, value="()"} }
    | '(' commata ')'       { \(z::Token)\n\_ -> With1 baseToken z.{tokid=CONID, value=tuple (n+1)} }
    | '(' ARROW ')'         { \_\(a::Token)\_ -> With1 baseToken a.{tokid=CONID, value="->"} }
    ;

kind:
    simplekind ARROW kind     { \a\_\c -> KApp a c }
    | simplekind
    ;

simplekind:
    LOP3                    { const KType }
    | VARID                 { \v -> do
                                let w = Token.value v
                                if w == "generic" then return KGen
                                else do
                                    yyerror (yyline v) ("expected `generic` instead of `" ++ w ++ "`")
                                    return KType
                            }
    | '(' kind ')'          { \_\b\_ -> b }
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
                             clvar = TVar (posLine v) KVar (posItem v),
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
    PURE NATIVE     { \_\_ -> true  }
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
      '!' simpledalt            { \_\dcon ->  DCon.{ -- strict=true,
                                                    flds <-map ConField.{strict=true}}  dcon }
    | '?' simpledalt            { \_\dcon ->  DCon.{ -- strict=false,
                                                    flds <-map ConField.{strict=false}} dcon }
    | simpledalt
    ;

simpledalt:
    CONID                       { \c        -> DCon {pos=yyline c, vis=Public, -- strict=false,
                                                name=Token.value c, flds=[], doc=Nothing } }
    | CONID '{' conflds '}'     { \c\_\fs\_ -> DCon {pos=yyline c, vis=Public, -- strict=false,
                                                name=Token.value c, flds=fs, doc=Nothing } }
    | CONID contypes            { \c\fs     -> DCon {pos=yyline c, vis=Public, -- strict=false,
                                                name=Token.value c, flds=fs, doc=Nothing } }
    ;

contypes:
    simpletypes                 { \taus -> do
                                    g <- getST
                                    let strict = U.strictMode g
                                        field  = Field Position.null Nothing Nothing Public strict
                                                    . ForAll [] . RhoTau []
                                    return (map field taus)
                                }
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
    fldids DCOLON sigma           { \vs\_\t -> [Field pos (Just name) Nothing vis strict t |
                                                (pos,name,vis,strict) <- vs ]
                                  }
    | docs fldids DCOLON sigma    { \(d::String)\vs\_\t ->
                                        map ConField.{doc=Just d}
                                            [Field pos (Just name) Nothing vis strict t |
                                                (pos,name,vis,strict) <- vs ]
                                  }
    ;

fldids:
      fldid                     { single }
    | fldid ',' fldids          { liste  }
    ;

fldid:
    strictfldid
    | PUBLIC  strictfldid        { \_ \(pos,name,vis,strict) -> (pos,name,Public, strict) }
    | PRIVATE strictfldid        { \_ \(pos,name,vis,strict) -> (pos,name,Private,strict) }
    ;

strictfldid:
    plainfldid
    | '!' plainfldid            { \_ \(pos,name,vis,strict) -> (pos,name,vis, true) }
    | '?' plainfldid            { \_ \(pos,name,vis,strict) -> (pos,name,vis, false) }
    ;

plainfldid:
    varid                       { \(name, pos) -> do
                                    g <- getST
                                    return (pos, name, Public, U.strictMode g)
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
                                nx = Let [] defs x Nothing
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
    | CHRCONST                      { \x ->  do
                                                let v = Token.value x
                                                when (length v > 3 && strhead v 2 != "'\\")
                                                    (yyerror (yyline x) ("bad char literal: " ++ v))
                                                YYM.return $ Lit (yyline x) LChar v Nothing
                                    }
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
    | lcqual ','                    { (const . single) }
    ;


dodefs:
    lcqual                          { single }
    | lcqual semicoli               { (const . single) }
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
    | gqual ','                    { (const . single) }
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
                                        CAlt {pat=p, ex=e}}
    | pattern guards                { \p\gs -> guardedalt p gs}
    | calt wherelet                 {\(calt::CAltS)\defs ->
                                        let
                                            nx = Let [] defs calt.ex Nothing;
                                        in calt.{ ex = nx } }
    ;

calts:
    calt                            { single }
    | calt ';'  calts               { liste  }
    | calt ';'                      { \a\_    ->  [a] }
    ;


lambda:
      '\\' pattern lambda           { \_\p\l   -> Lam p l Nothing}
    | '\\' pattern ARROW  topex     { \_\p\_\x -> Lam p x Nothing}
    ;


expr:
    topex DCOLON sigma               { \x\_\t  -> Ann {ex = x, typ=Just t} }
    | topex
    ;

thenx:
    ';' THEN                           { flip const }
    | THEN
    ;

elsex:
    ';' ELSE                           { flip const }
    | ELSE
    ;


topex:
      IF expr thenx expr elsex topex   { \_\c\_\t\_\e  -> Ifte c t e Nothing}
    | CASE  expr OF '{' calts   '}'    { \_\e\_\_\as\_ -> Case CNormal e as Nothing}
    | LET '{' letdefs '}' IN  topex    { \_\_\ds\_\_\e -> Let [] ds e Nothing}
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
    | '[' exprSC DOTDOT ']'         { \a\b\c\d   -> do mkEnumFrom   a b c d}
    | '[' exprSC DOTDOT expr ']'    { \a\b\c\d\e -> do mkEnumFromTo a b c d e}
    | '[' expr '|' lcquals ']'      { \(a::Token)\e\b\qs\(z::Token) -> do {
                let {nil = z.{tokid=CONID, value="[]"}};
                listComprehension (yyline b) e qs
                                            (Con {name = With1 baseToken nil, pos = nil.position, typ = Nothing})
                                    }}
    ;

commata:
    ','                             { const 1 }
    | ',' commata                   { ((+) . const 1) }
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
    | field ','                     { (const . single) }
    ;

getfields:
    getfield                        { single }
    | getfield ',' getfields        { liste  }
    | getfield ','                  { (const . single) }
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
    | expr ','                      { (const . single) }
    ;
exprSS:
    expr                            { single }
    | expr ';' exprSS               { liste }
    | expr ';'                      { (const . single) }
    ;
    

%%
/**
 * the parser pass
 */
pass :: [Token] -> StG (Maybe ParseResult) // Global -> IO (Maybe ParseResult, Global)
pass = yyparse
