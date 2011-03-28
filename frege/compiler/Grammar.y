%{
// enable UTF-8: «««««••••••••••••»»»»»»
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

import frege.IO(stdout stderr << BufferedReader)
import frege.List(Tree keyvalues keys)
import frege.compiler.Data                      as D
import frege.compiler.Utilities
        (posItem posLine unqualified
          tuple)
                                                as U

version = v "$Revision$" where
    v (m ~ #(\d+)#) | Just g <- m.group 1 = g.atoi
    v _ = 0


type ParseResult = (String, [Def], Maybe String)
type Def = DefinitionT
type Exp = ExprT String
type Pat = PatternT String
type Item = Token
type Qual = Either (Maybe (Pos Pat), Exp) [Def]
type Guard = (Line, [Qual], Exp)

infixl 16 `nApp`

// this will speed up the parser by a factor of 70, cause yyprods comes out monotyped.
private yyprod1 :: [(Int, YYsi ParseResult Token)]
    -> StG (YYsi ParseResult Token, [(Int, YYsi ParseResult Token)])

yyerror = U.error
yyline  = Token.line
yyval   = Token.value

yynice t = case tok of
        DOCUMENTATION -> "documentation comment"
        CHAR          -> show (tv).[0]
        STRCONST      -> "literal " ++ start tv
        _             -> if yyline t > 0 then "token " ++ show tv else tv
    where
        tok = yytoken t
        tv = Token.value t
        start tv
            | length tv > 8 = substr tv 0 5 ++ "..." ++ substr tv 0 1
            | otherwise = tv

yyshow  = Token.show
yyfromCh c = Token CHAR (ctos c) 0 0
yyfromId n
    | n >= PACKAGE, n <= INFIXR = Token n (String.toLowerCase (show n)) 1 0
    | n == CONID = Token n "constructor or type name" 0 0
    | n == VARID = Token n "variable name" 0 0
    | otherwise = Token n (show n) 0 0
yychar t
    | Token.tokid t == CHAR = (Token.value t).[0]
    | otherwise = '\0'
yytoken t = Token.tokid t
vid t = (Token.value t; Token.line t)

/*
 The following definitions are not strictly necessary, but they help
 to avoid truly crazy type signatures for parse stack itmes and
 considerably speed up type checking in giving the result types of
 certain nonterminal reduction rules.
 Note that you cannot lie about the type of nonterminal reduction rules.
 Note that types like "Maybe x" on the RHS must be given like so: (Maybe x)
 */
//%type package         ParseResult
//%type commata         Int
//%type semicoli        Int
//%type packagename     String
//%type nativename      String
//%type nativepur       Bool
//%type dconid          String
//%type docs            String
//%type opstring        String
//%type boundvar        String
//%type operators       [String]
//%type boundvars       [String]
//%type packageclause   (String, Maybe String)
//%type unop            Token
//%type operator        Token
//%type rop13           Token
//%type aeq             Token
//%type varid           (Pos String)
//%type conid           (Pos String)
//%type qvarid          (Pos String)
//%type qconid          (Pos String)
//%type qunop           (Pos String)
//%type binop           (Pos String)
//%type unary           (Pos String)
//%type tyname          (Pos String)
//%type importitem      (Pos String)
//%type annoitem        (Pos String)
//%type nativestart     (Pos String)
//%type importlist      [Pos String]
//%type annoitems       [Pos String]
//%type importliste     (Maybe [(Pos String)])
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
//%type crhofun         RhoS
//%type constraint      ContextS
//%type constraints     [ContextS]
//%type constrlist      [ContextS]
//%type rhofun          RhoS
//%type fldtype         (Pos String, SigmaS)
//%type fldtypes        [(Pos String, SigmaS)]
//%type field           (String, Exp)
//%type fields          [(String, Exp)]
//%type getfield        (String, Bool,Exp)
//%type getfields       [(String,Bool,Exp)]
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
//%type funhead         (String, [Pat])
//%type confld          (Maybe String, SigmaS)
//%type conflds         [(Maybe String, SigmaS)]
//%type contypes        [(Maybe String, SigmaS)]
//%type dalt            DConS
//%type simpledalt      DConS
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
//%explain package      a package
//%explain packageclause a package clause
//%explain packagename  a package name
//%explain semicoli     a sequence of one or more ';'
//%explain operator     an operator
//%explain operators    some operators
//%explain import       a package import
//%explain infix        a fixity declaration
//%explain fixity       the start of a fixity declaration
//%explain typedef      a type declaration
//%explain annotation   an annotation
//%explain conid        a constructor or type name
//%explain qconid       a qualified constructor or type name
//%explain docs         a sequence of doc comments
//%explain importliste  a list of symbols to import
//%explain importlist   a sequence of symbols to import
//%explain qunop        a qualified unary operator
//%explain unop         an unary operator
//%explain unary        an unary operator
//%explain varid        a variable name
//%explain qvarid       a qualified variable name
//%explain importitem   a symbol in an import list
//%explain binop        a binary operator
//%explain dconid       a type or class name
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
//%explain crhofun      a constrained type
//%explain tapp         a type application
//%explain forall       a qualified type
//%explain sigma        a qualified type
//%explain boundvar     a type variable bound in a forall
//%explain boundvars    type variables bound in a forall
//%explain constraint   a type constraint
//%explain constrlist   a sequence of type constraints
//%explain constraints  type constraints
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

%token VARID CONID QUALIFIER DOCUMENTATION
%token PACKAGE IMPORT INFIX INFIXR INFIXL NATIVE DATA WHERE CLASS EXTENDS
%token INSTANCE ABSTRACT TYPE TRUE FALSE IF THEN ELSE CASE OF DERIVE
%token LET IN WHILE BREAK CONTINUE DO FORALL PRIVATE PUBLIC PROTECTED PURE
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
%left       LOP4
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
    packageclause ';' definitions               { \(a,d)\_\b     -> (a;b;d) }
    | packageclause WHERE '{' definitions '}'   { \(a,d)\_\_\b\_ -> (a;b;d) }
    ;

nativename:
      qconid                    { posItem }
    | qvarid                    { posItem }
    | qconid '.' nativename     { \a\_\c -> posItem a ++ "." ++ c }
    | qvarid '.' nativename     { \a\_\c -> posItem a ++ "." ++ c }
    | STRCONST                  { \x -> let s = Token.value x; i = length s - 1 in substr s 1 i }
    ;

packagename:
    qconid                              { posItem }
    | qvarid '.' packagename            { \a\_\c -> repljavakws (posItem a) ++ "." ++ c }
    | qconid '.' packagename            { \a\_\c -> posItem a ++ "." ++ c }
    ;

docs:
    DOCUMENTATION                       { Token.value }
    | DOCUMENTATION docs                { \b\a   -> (Token.value b ++ "\n" ++ a) }
    | DOCUMENTATION semicoli docs       { \b\_\a -> (Token.value b ++ "\n" ++ a) }
    // docs semicoli                    { const }
    ;

packageclause:
    docs PACKAGE packagename            { \docu\_\b   -> (b; Just docu) }
    | PACKAGE packagename               { \_\b        -> (b; Nothing) }
    ;

semicoli:
    ';'                             { const 1 }
    | ';' semicoli                  { \_\n -> 1+n}
    ;

definitions:
    definition                          // { single }
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
    PRIVATE     publicdefinition      { \_\ds -> map (updVis Private) ds }
    | PROTECTED publicdefinition      { \_\ds -> map (updVis Protected) ds }
    | PUBLIC    publicdefinition      { \_\ds -> map (updVis Public) ds }
    | ABSTRACT  datadef               { \_\(d::Def) -> [d.{ctrs <- map updCtr}] }
    ;


topdefinition:
    import                              { single }
    | infix                             { single }
    // documentation                     { single }
    | publicdefinition
    ;

documentation:
    DOCUMENTATION                     { \t -> DocDcl {pos = yyline t, text = t.value}}
    ;

publicdefinition:
    typedef                             { single }
    | datadef                           { single }
    | classdef                          { single }
    | derivedef                         { single }
    | instdef                           { single }
    | localdef
    ;


localdefs:
    dplocaldef
    | dplocaldef semicoli                { const }
    | dplocaldef semicoli localdefs      { \d\_\ds -> d ++ ds }
    ;

localdef:
    annotation
    // documentation                     { single }
    | nativedef                         { single }
    | fundef
    ;

plocaldef:
    localdef
    // documentation                     { single }
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
        { \i\b\c -> ImpDcl {pos=yyline i, pack=b, items=c, as=Nothing} }
    | IMPORT packagename importliste varid conid
        { \i\p\l\as\n ->
            do
                when ( posItem as != "as" )
                    $ yyerror (posLine as) (show "as" ++ " expected instead of " ++ show (posItem as))
                YYM.return (ImpDcl {pos = yyline i,
                                    pack=p, items=l, as=Just (posItem n)}) }
    ;

importliste:
    { Nothing }
    | '(' ')'               { \_\_   -> Just [] }
    | '(' importlist ')'    { \_\b\_ -> Just b  }
    ;

importlist:
    importitem              { (:[]) }
    | importitem importlist { (:)   }
    ;

varid:   VARID              { vid }
    ;
conid:   CONID              { vid }
    ;
qvarid:  QUALIFIER VARID    { \a\b -> (Token.value a ++ Token.value b, yyline b)}
    |    VARID              { vid }
    ;
qconid:  QUALIFIER CONID    { \a\b -> (Token.value a ++ Token.value b, yyline b)}
    |    CONID              { vid }
    ;
    /*
binop:
    operator                { \x -> do
                                tok <- unqualified x
                                YYM.return (vid tok)
                            }
    ;
    */
qunop:  QUALIFIER unop      { \a\b -> (Token.value a ++ Token.value b, yyline b)}
    |   unop                { vid }
    ;

importitem:
    qvarid
    | qconid
    | operator              { vid }
    | qunop
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
                                    YYM.return (FixDcl {pos=yyline f, opid=t, ops=[]}) }
    | INFIXL INTCONST   { \f\i -> do
                                    t <- U.infixop (yyline i) LOP1 (atoi (Token.value i) - 1)
                                    YYM.return (FixDcl {pos=Token.line f, opid=t, ops=[]}) }
    | INFIXR INTCONST   { \f\i -> do
                                    t <- U.infixop (yyline i) ROP1 (atoi (Token.value i) - 1)
                                    YYM.return (FixDcl {pos=Token.line f, opid=t, ops=[]}) }
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
    /*funhead*/ annoitems DCOLON sigma  { \as\_\s -> map (annotation s) as }
        /* {\as\d\t ->
        case as of {
            ("let", _, _) -> do
                    yyerror "invalid definition, pattern may not be followed by type.";
                for [];
            (x, pats, !docu) -> do
                    (if ! (null pats)
                        then yyerror ("invalid annotation, shoud be ("
                                        ++ x ++ ") :: " ++ show t)
                        else ())
                    for  [Anno (num d) Public {item=x ,typ=t,docu}]
        }}*/
        ;

annoitem:
    varid
    | '(' operator ')'          { \_\a\_ -> do binop a }
    | '(' unary ')'             { \_\a\_ -> a }
    ;

annoitems:
    annoitem                    { single   }
    | annoitem ',' annoitems    { liste    }
    ;

unary: unop     { vid }

nativedef:
    PURE impurenativedef        { \_\(d::Def) -> d.{isPure = true} }
    | impurenativedef
    ;

nativestart:
      NATIVE annoitem      { flip const }
    | NATIVE operator      { \_\b  -> do binop b }
    | NATIVE unary         { flip const }
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
    | nativestart unary      DCOLON sigma
                    { \item\o\col\t -> NatDcl {pos=posLine item, vis=Public, name=posItem item,
                                                meth=posItem o, typ=t, isPure=false, doc=Nothing}}
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
    | rhofun                       { ForAll [] }
    ;

forall:
      FORALL boundvars                 '.' rhofun    { \_\bs\_\r      -> ForAll bs r }
    | FORALL boundvars  constraints    '.' rhofun    { \_\bs\cs\_\r   -> ForAll bs (Rho.{context=cs} r)}
    | FORALL            constraints    '.' rhofun    { \_\cs\_\r      ->
                                    ForAll (keys (U.freeCtxTVars [] Nil cs)) (Rho.{context=cs} r)}
    ;

rhofun:
    '(' forall ')'  ARROW rhofun            { \_\a\_\_\b -> RhoFun [] a b }
    | tapp          ARROW rhofun            { \a\_\b     -> RhoFun [] (ForAll [] (RhoTau [] a)) b }
    | tapp                                  { RhoTau [] }
    ;

/*
crhofun:
      constraints EARROW rhofun              {\a\_\b -> Rho.{context = a} b }
    | rhofun
    ;
    */

constraints:
      constraint ',' constraints             { liste  }
    | constraint                             { single }
    ;


constrlist:
    '(' constraints ')'                     { \_\cs\_ -> cs }
    | constraints
    ;


constraint:
    qconid tv                               { \c\t -> Ctx {pos = posLine c, tau = t,
                                                            checked=false, cname=posItem c}}
    ;

tvapp:
    tv
    | tvapp tv          { \a\b -> TApp a b }

tv:
    varid               { \v -> TVar {pos=posLine v, var=posItem v, classes=[]}}
    | '(' tvapp ')'     { \_\t\_ -> t }
    ;

tau:
    tapp
    | tapp ARROW tau    { \a\f\b ->  TFun a b }
    ;

tauSC:
    tau                 { single }
    | tau ',' tauSC     { liste  }
    ;

tapp:
    simpletypes         { \(con:ts) -> Tau.mkapp con ts }
    ;

simpletype:
    tyvar
    | tyname            { \tn -> TCon (posLine tn) (posItem tn)}
    | '(' tau ')'      { \_\t\_ -> t }
    | '(' tau ',' tauSC ')'
                        {\_\t\c\ts\_ ->
                            let
                                tus = t:ts;
                                i = length tus;
                                tname = tuple i
                            in  (TCon (yyline c) tname).mkapp tus
                        }
    | '[' tau ']'      {\a\t\_ -> TApp (TCon (yyline a) "Prelude.[]") t }
    ;


rop13: ':'
        | ROP13         { \t -> if Token.value t == ":" then YYM.return t else do
                                yyerror (yyline t) ("':' expected instead of " ++ yynice t)
                                YYM.return t }
        ;

tyvar:
    varid                   { \n -> TVar (posLine n) (posItem n) [] }
    | tyname rop13 tyvar    { \t\_\(tv::TauS) -> do
                                    U.warn (posLine t) "deprecated constraint syntax"
                                    YYM.return tv.{classes <- (posItem t:)}    }
;


tyname:
    qconid
    | '[' ']'               { \a\_ -> ("Prelude.[]", yyline a) }
    | '(' ')'               { \a\_ -> ("Prelude.()", yyline a) }
    | '(' commata ')'       { \z\n\_ -> (tuple (n+1), yyline z) }
    | '(' ARROW ')'         { \_\a\_ -> ("Prelude.->", yyline a) }
    ;


classdef:
    CLASS conid tyvar wheredef       {
        \_\i\(tv::TauS)\defs -> ClaDcl {pos = posLine i, vis = Public, name = posItem i,
                        clvar=tv.{classes=[]}, supers=tv.classes, defs = defs, doc = Nothing}
    }
    | CLASS conid constrlist EARROW varid wheredef {
        \_\i\ctxs\_\v\defs -> do
            sups <- classContext (posItem i) ctxs (posItem v)
            YYM.return (ClaDcl {pos = posLine i, vis = Public, name = posItem i,
                             clvar = TVar (posLine v) (posItem v) [],
                             supers = sups, defs = defs, doc = Nothing})
    }
    ;


instdef:
    INSTANCE tyname sigma wheredef {
        \ins\t\r\defs -> InsDcl {pos = yyline ins, vis = Public, clas=posItem t, typ=r, defs=defs, doc=Nothing}
    }
    ;


derivedef:
    DERIVE tyname sigma     { \d\t\r -> DrvDcl {pos = yyline d, vis = Public, clas=posItem t, typ=r, doc=Nothing}}
    ;

dconid : conid              { posItem }
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
    DATA dconid '=' nativepur nativename {
        \dat\d\docu\pur\jt -> JavDcl {pos=yyline dat, vis=Public, name=d,
                                    clas=jt, vars=[], defs=[], isPure = pur, doc=Nothing}
    }
    | DATA dconid dvars '=' nativepur nativename {
        \dat\d\ds\docu\pur\jt -> JavDcl {pos=yyline dat, vis=Public, name=d,
                                    clas=jt, vars=ds, defs=[], isPure = pur, doc=Nothing}
    }
    | DATA dconid dvars '=' dalts {
        \dat\d\ds\docu\alts -> DatDcl {pos=yyline dat, vis=Public,
                                       name=d, vars=ds, ctrs=alts, defs=[], doc=Nothing}
    }
    | DATA dconid '=' dalts {
        \dat\d\docu\alts -> DatDcl {pos=yyline dat, vis=Public,
                                    name=d, vars=[], ctrs=alts, defs=[], doc=Nothing}
    }
    | DATA dconid '=' NATIVE '{' conflds '}'    {
        \dat\d\_\n\_\fs\_ ->
            let con = DCon {pos=yyline n, vis=Public,
                                                name=d, flds=fs, doc=Nothing }
            in DatDcl {pos=yyline dat, vis=Public,
                                    name=d, vars=[], ctrs=[con], defs=[], doc=Nothing}
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
    simpledalt
    | PUBLIC    simpledalt      { \_\dc -> (dc::DConS).{vis = Public}    }
    | PRIVATE   simpledalt      { \_\dc -> (dc::DConS).{vis = Private}   }
    | PROTECTED simpledalt      { \_\dc -> (dc::DConS).{vis = Protected} }
    ;

simpledalt:
    conid                       { \c        -> DCon {pos=posLine c, vis=Public,
                                                name=posItem c, flds=[], doc=Nothing } }
    | conid '{' conflds '}'     { \c\_\fs\_ -> DCon {pos=posLine c, vis=Public,
                                                name=posItem c, flds=fs, doc=Nothing } }
    | conid contypes            { \c\fs     -> DCon {pos=posLine c, vis=Public,
                                                name=posItem c, flds=fs, doc=Nothing } }
    ;

contypes:
    simpletypes                 { map ((,) Nothing • ForAll [] • RhoTau []) }
    ;

simpletypes:
    simpletype                  { single }
    | simpletype simpletypes    { (:) }
    ;

conflds:
    confld                      { single }
    | confld ','                { (const @ single) }
    | confld ',' conflds        { \a\c\ls ->
                                        if elemBy (using (unJust • fst)) a ls then do {
                                            U.error (yyline c) ("field `" ++ (unJust • fst) a
                                                ++ "` must appear only once.");
                                            YYM.return ls
                                        } else
                                            YYM.return (a:ls)
                                }
    ;

confld:
    // simpletype                  { (,) Nothing <~ ForAll [] <~ RhoTau }
    VARID DCOLON sigma            { \v\_\t -> (Just (Token.value v), t) }
    ;

typedef:
    TYPE dconid '=' tau         { \t\i   \_\r -> TypDcl {pos=yyline t, vis=Public, name=i, vars=[], rho=RhoTau [] r, doc=Nothing}}
    | TYPE dconid dvars '=' tau { \t\i\vs\_\r -> TypDcl {pos=yyline t, vis=Public, name=i, vars=vs, rho=RhoTau [] r, doc=Nothing}}
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
    funhead '=' expr        { \fh\eq\expr -> fundef fh (yyline eq) expr }
    | funhead guards        { \fh\gds -> fungds fh gds }
    | fundef wherelet       { \fdefs\defs ->
        case fdefs of
            [fd@FunDcl {expr=x}] -> YYM.return [fd.{expr = nx}] where
                                nx = Let Nil defs x Nothing
            _ -> do
                yyerror 0 "illegal function definition, where { ... } after annotation?"
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
    | BIGCONST                      { \x ->  Lit (yyline x) LBig    (Token.value x) Nothing }
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
    |expr '=' expr                  { \e\t\x -> do { fh <- funhead e; YYM.return (Right (fundef fh (yyline t) x)) }}
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
                                        YYM.return (Left (Just (pat, yyline g), e)) }
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
                                        CAlt {pos=yyline a, env=Nil, pat=p, ex=e}}
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
    // | DO  '{' dodefs  '}'             { \d\_\defs\_   -> do mkMonad (yyline d) defs }
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
    | qunop unex                        { \u\p -> nApp (Vbl {pos=posLine u, name=posItem u, typ=Nothing}) p}
    ;

primary:
    term
    | DO  '{' dodefs  '}'             { \d\_\defs\_   -> do mkMonad (yyline d) defs }
    | primary   '.' varid             { \p\_\v -> Mem p (posItem v) Nothing}
    | primary   '.' operator          { \p\_\v -> do {v <- binop v;
                                                    YYM.return (Mem p (posItem v) Nothing)}}
    | primary   '.' unary             { \p\_\v -> Mem p (posItem v) Nothing}
    | QUALIFIER     '{' varid '?' '}' { \q\_\v\_\_ ->
                                            Vbl (yyline q) (yyval q ++ "has$" ++ posItem v) Nothing}
    | QUALIFIER     '{' varid '=' '}' { \q\_\v\_\_ ->
                                            Vbl (yyline q) (yyval q ++ "upd$" ++ posItem v) Nothing}
    | QUALIFIER     '{' varid GETS '}' { \q\_\v\_\_ ->
                                            Vbl (yyline q) (yyval q ++ "chg$" ++ posItem v) Nothing}
    | QUALIFIER     '{' varid '=' expr '}' { \q\_\v\_\x\_ ->
                                         Vbl (yyline q) ("Prelude.flip") Nothing `nApp`
                                         Vbl (yyline q) (yyval q ++ "upd$" ++ posItem v) Nothing `nApp`
                                         x}
    | QUALIFIER     '{' varid '}'          { \q\_\v\_ ->        // Q.{n} --> Q.{n=n}
                                         Vbl (yyline q) ("Prelude.flip") Nothing `nApp`
                                         Vbl (yyline q) (yyval q ++ "upd$" ++ posItem v) Nothing `nApp`
                                         Vbl (posLine v) (posItem v) Nothing}
    | QUALIFIER     '{' varid GETS expr '}' { \q\_\v\_\x\_ ->
                                         Vbl (yyline q) ("Prelude.flip") Nothing `nApp`
                                         Vbl (yyline q) (yyval q ++ "chg$" ++ posItem v) Nothing `nApp`
                                         x}
    | primary   '.' '{' varid '?' '}' { \p\_\_\v\_\_ -> case p of {
                                            Con p n t -> Vbl p (n ++ ".has$" ++ posItem v) t;
                                            x       -> Mem x ("has$" ++ posItem v) Nothing}}
    | primary   '.' '{' varid '=' '}' { \p\_\_\v\_\_ -> case p of {
                                            Con p n t -> Vbl p (n ++ ".upd$" ++ posItem v) t;
                                            x       -> Mem x ("upd$" ++ posItem v) Nothing}}
    | primary   '.' '{' varid GETS '}' { \p\_\_\v\_\_ -> case p of {
                                            Con p n t -> Vbl p (n ++ ".chg$" ++ posItem v) t;
                                            x       -> Mem x ("chg$" ++ posItem v) Nothing}}
    | primary '.' '{' getfields '}' { \x\_\_\fs\_ ->
                                let
                                    u x [] = x
                                    u x ((r, true , e):xs) = u (Mem x ("chg$" ++ r) Nothing  `nApp` e)  xs
                                    u x ((r, false, e):xs) = u (Mem x ("upd$" ++ r) Nothing  `nApp` e)  xs
                                in u x fs}
    | primary '.' '[' expr ']'      { \p\_\_\v\_     -> Mem p "frozenGetAt" Nothing  `nApp` v}
    | primary '.' '[' expr '=' expr ']'
                                    { \p\_\_\v\_\x\_ -> Mem p "updAt" Nothing `nApp` v `nApp` x }
    | primary '.' '[' expr GETS expr ']'
                                    { \p\_\_\v\_\x\_ -> Mem p "setAt" Nothing `nApp` v `nApp` x }
    ;

term:
    varid                           { \x   -> Vbl {pos=posLine x, name=posItem x, typ=Nothing} }
    | literal
    | '_'                           { \t   -> Vbl {pos = yyline t, name = "_", typ=Nothing} }  // only valid as pattern
    | QUALIFIER VARID               { \q\v -> Vbl {pos=yyline v, typ=Nothing,
                                                    name = Token.value q ++ Token.value v }}
    | qconid                        { \qc  -> Con (posLine qc) (posItem qc) Nothing}
    | qconid '{'        '}'         { \qc\_\_    -> ConFS (posLine qc) (posItem qc) [] Nothing}
    | qconid '{' fields '}'         { \qc\_\fs\_ -> ConFS (posLine qc) (posItem qc) fs Nothing}
    | '(' ')'                       { \z\_ -> Con (yyline z)   "Prelude.()" Nothing}
    | '(' commata ')'               { \z\n\_ -> Con (yyline z) (tuple (n+1)) Nothing}
    | '(' operator ')'              { \_\o\_ -> (varcon o) (yyline o) (Token.value o) Nothing}
    | '(' unary ')'                 { \_\u\_ -> Vbl (posLine u) (posItem u) Nothing}
    | '(' operator expr ')'         { \z\o\x\_ ->  let // (+1) --> flip (+) 1
                                        flp = Vbl (yyline o) "Prelude.flip" Nothing
                                        op  = (varcon o) (yyline o) (Token.value o) Nothing
                                        ex = nApp (nApp flp op) x
                                    in ex}
    | '(' binex operator ')'           { \_\x\o\_ ->  // (1+) --> (+) 1
                                        nApp ((varcon o) (yyline o) (Token.value o) Nothing) x}
    | '(' expr ',' exprSC ')'       { \a\e\_\es\_ -> fold nApp (Con (yyline a)
                                                                   (tuple (1+length es))
                                                                   Nothing)
                                                              (e:es)}
    | '(' expr ';' exprSS ')'       { \a\e\_\es\_ -> fold nApp (Vbl (yyline a)
                                                                   ("Prelude.strictTuple" ++
                                                                        show (1+length es))
                                                                    Nothing)
                                                              (e:es)}
    | '(' expr ')'                  { \_\x\_ -> x }
    | '[' ']'                       { \z\_ ->  Con (yyline z) "Prelude.[]" Nothing}
    | '[' exprSC ']'                { \b\es\z -> foldr (\a\as -> nApp (nApp (Con (yyline b) "Prelude.:" Nothing) a) as)
                                                       (Con (yyline z)  "Prelude.[]" Nothing)
                                                       es}
    | '[' expr '|' lcquals ']'      { \_\e\b\qs\z -> do {
                                        listComprehension (yyline b) e qs (Con (yyline z) "Prelude.[]" Nothing) }}
    ;

commata:
    ','                             { const 1 }
    | ',' commata                   { ((+) @ const 1) }
    ;

fields:
    field                           { single }
    | field ',' fields              { \a\c\ls ->
                                        if elemBy (using fst) a ls then do {
                                                U.warn (yyline c) ("field `" ++ fst a
                                                    ++ "` should appear only once.");
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
      varid GETS expr               { \s\_\x ->  (posItem s, true,  x) }
    | varid '=' expr                { \s\_\x ->  (posItem s, false, x) }
    | varid                         { \s     ->  (posItem s, false, Vbl (posLine s) (posItem s) Nothing) }
    ;

field:
    varid '='  expr                  { \s\_\x ->  (posItem s, x) }
    // varid GETS expr                { \s\_\x ->  (posItem s, x) }       // unofficial
    | varid                          { \s     ->  (posItem s, Vbl (posLine s) (posItem s) Nothing) }
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


/// return 'Con' if it is (:)
varcon o
    | Token.value o == ":" = Con
    | Token.value o == "Prelude.:" = Con
    | m ~ #(\w+$)# <- Token.value o, Just s <- m.group 1, (s.charAt 0).isUpperCase = Con
    | otherwise = Vbl

/// check that operator is unqualified
binop op = do
    tok <- unqualified op
    YYM.return (vid tok)

/// make a binary expression
mkapp a op b = varcon op (Token.line op) (Token.value op) Nothing `nApp` a `nApp` b



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
exprToPat (Vbl  p "_" _) = do
        u <- U.uniqid
        YYM.return (PVar p ("_" ++ show u))
exprToPat (Vbl p (m~#^Prelude\.strictTuple(\d+)$#) _)
        | Just s <- m.group 1 = YYM.return (PCon p (tuple s.atoi) [])
exprToPat (Vbl n x _) = YYM.return (PVar n x)
exprToPat (Lit p k v _) = YYM.return (PLit p k v);
exprToPat (App (Vbl _ "!" _) b _) = do p <- exprToPat b; YYM.return (PStrict p)

exprToPat (App (App (Vbl _ "@" _) b _) c _)
        | Vbl n x _ <- b = do cp <- exprToPat c; YYM.return (PAt n x cp)
        | App (Vbl _ "!" _) (Vbl n x _) _ <- b = do cp <- exprToPat c; YYM.return (PStrict (PAt n x cp))
        | otherwise = do
            bs <- U.showexM b
            U.error (getpos b) ("pattern " ++ bs ++ " not allowed left from @")
            exprToPat c


exprToPat (App (App (Vbl _ "~" _) b _) c _)
        | Vbl p x _ <- b = do cp <- regPat c; YYM.return (PMat p x cp)
        | App (Vbl _ "!" _) (Vbl p x _) _ <- b = do cp <- regPat c; YYM.return (PStrict (PMat p x cp))
        | otherwise = do
            bs <- U.showexM b
            U.error (getpos b) ("pattern " ++ bs ++ " not allowed left from ~")
            exprToPat c
        where
            regPat (Lit {kind=LRegex, value=regex}) = YYM.return regex
            regPat e = do
                    es <- U.showexM e
                    U.error (getpos e) ("regex expected right from ~, found " ++ es)
                    YYM.return "regex"



exprToPat (e@App a b _) = do
        pa <- exprToPat a;
        pb <- exprToPat b;
        case pa of
            // PApp _ _ -> YYM.return (PApp pa pb)
            PCon p n ps -> YYM.return (PCon p n (ps++[pb]))
            _ -> do
                es <- U.showexM e
                U.error (getpos e) ("illegal pattern, only constructor applications are allowed " ++ es)
                YYM.return (PVar {pos=getpos e, var="_"})



exprToPat (Ann e (Just t)) = do
        p <- exprToPat e
        YYM.return (PAnn p t)

/*
exprToPat (Inv e (Memfun s)) = PVar newfs x
    where
        (fs, x)   = exprToPatVar e
        newfs     = (s, PVar [] s):fs
    ;
    */
/*
exprToPat (Inv e (Recget s)) = PVar newfs x
    where
        (fs, x)   = exprToPatVar e
        newfs     = (s, PVar [] s):fs
    ;
    */
/*
exprToPat (Inv e (Recupd vs u)) = PVar newfs x
    where
        (fs, x)   = exprToPatVar e
        pu        = exprToPat u
        newfs     = (vs, pu):fs
    ;
    */

exprToPat e =
    do
        es <- U.showexM e
        U.error pos ("can't make pattern from " ++ es)
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
funhead :: Exp -> YYM Global (String, [Pat])
funhead (ex@Vbl {name}) = do
        pat <- exprToPat ex
        case pat of
            PVar _ p ->  YYM.return  (p; [])
            somepat  ->  YYM.return  ("let"; [somepat])
/**
 * Otherwise it should be an application
 * > a b c = ....
 * Constructor applications like @(Just x)@ or @(x:xs)@ or @[a,b,c]@ are patterns.
 * Unary application @!p@ is also a pattern.
 */

funhead (ex@App e1 e2 _)
    | Vbl _ "!"  _ <- e1 = do
            pex <- exprToPat ex
            YYM.return ("let"; [pex])
    | otherwise = do
        pat <- exprToPat x
        ps  <- mapSt exprToPat xs
        case pat of
            PVar _ p    -> YYM.return (p; ps)
            PCon p n [] -> YYM.return ("let"; [PCon p n ps])
            _ -> do
                es <- U.showexM ex
                yyerror (getpos x) ("bad function head " ++ es)
                YYM.return ("bad"; [pat])
    where
        x:xs = map fst (U.flatx ex)




/**
 * Also patterns are @x.foo x.{bar}@ and @x.{baz=p}@.
 */
 /*
funhead (ex@Inv _ inv)
    | Memfun _ <- inv    = pattern
    | Recget _ <- inv    = pattern
    | Recupd _ _ <- inv  = pattern
    where pattern = ("let"; [exprToPat ex]; getDoc())
    ;
    */

funhead ex = do
        let pos = getpos ex
        es <- U.showexM ex
        U.error pos ("illegal left hand side of a function definition: " ++ es)
        YYM.return ("_", [])

/**
 * construct a function definition as list
 */
//fundef ("let", [PStrict (PVar [] (a,_))], docu) pos expr = [Fun pos Public {name=a, pats=[], expr, strict=true, docu}];
//fundef ("let", [pat], docu) pos expr = letpat pos pat expr docu;
fundef (name, pats) pos expr = [FunDcl {pos=pos, vis=Public, name, pats, expr, doc=Nothing}];

/**
 * construct a function with guards
 */
fungds funhead gds = let
                expr = gdsexpr gds;
                (gdln,_,_)   = head gds;
            in fundef funhead gdln expr;



guardedalt :: Pat -> [Guard] -> CAltS
guardedalt p gds =
    case gdsexpr gds of
        x @ Case CWhen _ (alt:_) _
              -> CAlt {pos = alt.pos, env = alt.env, pat=p, ex = x}
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
                Just (pat, line) -> Case CWhen x [calt.{ pat = pat, pos = line}] Nothing
           where
                calt = CAlt {pos = ln, env = Nil, pat = PVar {var = "_", pos = ln}, ex = tg ln ex qs}
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
                alt = CAlt {pos = alt0.pos, env = alt0.env, pat = PVar { var = "_", pos = alt0.pos}, ex = y}
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
    | name `elem` [tuple n | n <- 2..26] = any refutable ps
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
        cons = Con {name = "Prelude.:", pos = pos, typ = Nothing}

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
            h     = "_h" ++ show uid ++ "line" ++ show pos
            hvar  = Vbl  pos h Nothing
            usvar = Vbl  pos ("_us" ++ show uid) Nothing
            uspat = PVar pos ("_us" ++ show uid)
            xsvar = Vbl  pos ("_xs" ++ show uid) Nothing
            xspat = PVar pos ("_xs" ++ show uid)
            anpat = PVar pos "_"
            pnil  = PCon pos "Prelude.[]" []
            pcons p ps = PCon pos "Prelude.:" [p, ps]  // p:ps
            calt1 = CAlt {pos = pos, env = Nil, pat = pnil, ex = l2 }  // [] -> l2
        hxs <- listComprehension pos e qs (hvar `nApp` xsvar)
        let
            // p:xs -> TQ [e|qs] (h xs)
            calt2 = CAlt {pos = pos, env = Nil, pat = pcons pat xspat, ex = hxs}
            // _:xs -> h xs
            calt3 = CAlt {pos = pos, env = Nil, pat = pcons anpat xspat, ex = hvar `nApp` xsvar}
            calts = if refutable pat then [calt2, calt1, calt3] else [calt2, calt1]
            ecas = Case CNormal usvar calts  Nothing
            hdef = FunDcl {pos = pos, vis = Private, name=h, pats=[uspat], expr=ecas, doc = Nothing}
        YYM.return (Let Nil [hdef] (nApp hvar xs) Nothing)
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
            U.error line "last statement in a monadic do block must not be  pat <- ex"
            YYM.return x
    | Right _ <- e = do
            U.error line "last statement in a monadic do block must not be  let decls"
            YYM.return (Vbl line "Prelude.undefined" Nothing)

mkMonad line (e:es)
    | Left (Nothing,  x) <- e
        =  do
            rest <- mkMonad line es
            YYM.return (bind0 `nApp` x `nApp` rest)
    | Left (Just pps, x) <- e, (pat, pos) <- pps
        = do
            rest <- mkMonad line es
            let res = if refutable pat
                      // LET in = x IN in >>= \of -> CASE of OF pat -> do ...; _ -> fail in "pattern failed"
                    then Let Nil [indef x] (bind  `nApp` invar `nApp` (Lam Nil ofpat (failcase pat rest) Nothing)) Nothing
                    else bind  `nApp`  x `nApp` (Lam Nil pat rest Nothing)
            YYM.return res
    | Right defs <- e = do
            rest <- mkMonad line es
            YYM.return (Let Nil defs rest  Nothing)
    where
        indef e = FunDcl {pos = (getpos e), vis = Private, name="in", pats=[], expr=e, doc = Nothing}
        bind0 = Vbl line "Prelude.>>" Nothing
        bind  = Vbl line "Prelude.>>=" Nothing
        invar = Vbl line "in" Nothing
        ofvar = Vbl line "of" Nothing
        failvar = Vbl line "Prelude.fail" Nothing
        ofpat = PVar line  "of"
        def   = PVar line  "_"
        failcase pat rest = Case CNormal ofvar [alt1, alt2]  Nothing where
            alt1 = CAlt {pos = line, env = Nil, pat = pat, ex = rest}
            alt2 = CAlt {pos = line, env = Nil, pat = def, ex = failure }
            // version 2 needed application to invar, we don't
            failure = failvar /*`nApp` invar*/  `nApp` Lit line LString "\"pattern failed\"" Nothing

mkMonad _ _ = Prelude.error "empty monadic do block"


// backslash
bs = '\\';
quot = '"';
rex [] sb = cstos (reverse (quot:sb))
rex ('"':cs) sb = rex cs (quot:bs:sb);
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
reStr rs =  rex (stocs rs)  [ quot ]

litregexp x = do
        let re = reStr (Token.value x)
        case regcomp (Token.value x) of
            Left exc -> do
                U.error (yyline x) ("regular expression syntax: " ++ exc.getMessage)
                YYM.return (Lit (yyline x) LRegex re Nothing)
            Right _ ->
                YYM.return (Lit (yyline x) LRegex re Nothing)

classContext clas ctxs cvar = mapSt sup ctxs
    where
        sup (Ctx {pos, cname, tau = TVar {var}}) | var == cvar = stio cname
        sup (Ctx {pos, cname}) = do
            U.error pos ("Illegal constraint, only " ++ cname ++ " " ++ cvar ++ " is allowed")
            stio cname

/**
 * the parser pass
 */
pass :: [Token] -> StG (Maybe ParseResult) // Global -> IO (Maybe ParseResult, Global)
pass = yyparse
