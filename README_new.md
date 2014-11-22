What is Frege?
==============

<img align="right" src="resources/Frege_logo.png"/>
Frege is a **pure** functional programming language for the JVM in the spirit of Haskell.
It enjoys a strong static type system with powerful type inference and
[non-strict](http://en.wikipedia.org/wiki/Non-strict_programming_language) - also known as _lazy_ - evaluation.

Frege programs are compiled to Java and run on the JVM.

The similarity to Haskell is actually strong enough that many users call it "_a_ Haskell for the JVM".

Examples
--------

**1. Hello World**

This is the classic starter with a slight extension to show the fluent usage from Java and the benefits
of having a type system that can recognize purity.

```frege
module Hello where

greeting friend = "Hello, " ++ friend ++ "!"

main args = do
    println (greeting "World")
```

This code will compile to `Hello.class` and `Hello.java` with a regular Java main method that one can start the usual Java way.

Moreover, the `Hello.class` will have a method

    public static String greeting(String ...) {...}
that one can call from Java or any other JVM language.

The `greeting` function is *pure*, meaning it is _stateless_ and _free of side effects_.
Therefore, it is _threadsafe_ and its results may be _automatically cached_ since given the same argument, the result will always be the same.

The `main` function is *impure*. It takes a list of Strings and does not return just "void" as in most other JVM languages but the
type `IO ()`, telling that it may produce side effects like printing to the console. The Frege **type system** guarantees
that any caller of `main` must also be of some `IO` type and is thus also marked as impure. That way, the lack of purity percolates up the whole call chain.

"Hello World" already shows the tenet of _"islands of purity"_ (greeting) in a _"sea of imperative code"_ (main).

Since the purity information is carried through the **type system**, they compiler can use it for many possible
**optimizations** such as pre-calculation, deferred execution, parallel execution, caching, and elimination of common subexpressions.

**2. A small idiomatic example**

Much can be achieved in Frege in one line of code and here is an example that you can paste into the
[Online REPL](https://github.com/Frege/frege-repl). It calculates the fixpoint of the cosine function, i.e. the
value where [`cos(x) == x`](http://www.wolframalpha.com/input/?i=cos+0.7390851332151607).

Implementations in imperative languages usually involve introducing local mutable state. Not so in Frege:

    import frege.prelude.Math (cos)
    (fst . head . dropWhile (uncurry (!=))) (zip cs (tail cs)) where cs = iterate cos 1.0

After execution it should show you the value

     0.7390851332151607

The code is most likely incomprehensible for a Frege/Haskell newcomer at first but you would not believe how
obvious and straightforward it is once you know the parts.
* `cs` is an _infinite_ list (a stream in Java terms) of cosine values that starts with `cos 1.0` and then iterates to `cos(cos(1.0)`, `cos(cos(cos(1.0))`, and so forth.
* `zip cs (tail cs)` produces an infinite list of pairs of any two adjacent values in `cs`.
* `uncurry` holds onto each element of a given pair and the function `(!=)` (a so-called sectioning) compares these elements for in-equality
* `dropWhile` reads from the infinite list as long as the cosine values in each pair are not equal
* The remaining list (the infinite list of pairs of equal cosine values) has a first pair called `head` and `fst` returns the first element of that pair, which yields the final result.

This code is *pure*. The inferred type is `Double`.
The code does not rely on any mutable state (not even internally). Therefore it is _threadsafe_ and the result can be _automatically cached_.

Motivation
----------
**For the Java programmer:** Frege offers you the opportunity to **learn and use a new programming paradigm**
that shines with
* a solid mathematical foundation,
* pure functions,
* immutability by default,
* side-effects only when declared,
* robustness under composition and concurrency,
* and a type system that is unparalleled on the JVM with its combination of power, simplicity and expressiveness.

You can still reuse your existing knowledge of the Java platform and its vast set of libraries.
Frege interoperates with Java such that you can easily
[call Frege from Java code](https://github.com/Frege/frege/wiki/Calling-Frege-Code-from-Java) and vice versa.
But unlike other approaches,
[calling Java from Frege](http://mmhelloworld.github.io/blog/2013/07/10/frege-hello-java/)
doesn't undermine the language guarantees.

When calling Java from Frege, you have to declare the Java types in rigid Frege terms in order to
preserve the Frege language characteristics, especially purity, thread safety, and lazy evaluation.

Learning Frege essentially means that you will also learn Haskell and thus your effort pays off twice, since
you also get to know a very popular non-JVM language with 25+ years of development, a great community,
many (free) books, publications, tutorials, online courses, and considerable industry demand.


**For the Haskell programmer**: Frege gives you the opportunity to **use your skills on the JVM**.
Most idiomatic Haskell code will run in Frege unmodified or with only minimal, obvious adaptions.
Even more important: you can bring your purely functional problem solution strategies to your Java projects.

From now on you can also enjoy on the JVM:
* the terse Haskell syntax
* pure functions and lambdas
* algebraic data types and typeclasses with parametric polymorphism
* powerful type inference
* higher rank types
* lazy evaluation on infinite data structures
* pattern matching, list comprehensions, do-notation, point-free style, operators, modules
* functors, monoids, semigroups, monads, and all your other beloved mathematical abstractions.

There have been attempts to [port Haskell to the JVM](http://www.haskell.org/haskellwiki/GHC/FAQ#Why_isn.27t_GHC_available_for_.NET_or_on_the_JVM.3F) before,
though said projects seem failed or stuck. The common wisdom suggests that it is not easily possible.

Frege is thought as a substitute for this missing GHC port. 
While not derived from any existing Haskell implementation, it is pretty much equivalent to Haskell 2010.
Please see the [wiki page that details the differences](https://github.com/Frege/frege/wiki/Differences-between-Frege-and-Haskell).

The Name
--------
The Frege programming language is named after and in honor of Gottlob Frege
who published the ideas of higher-order functions, partial function application, and many more concepts of formal logic
that we now take for granted back in the 19th century.

If you are curious how this name is pronounced, [you can use this translator page](http://translate.google.de/#de/en/Frege) to get it right.
Just click the audio symbol in the left (german) part.

Project State
-------------

The compiler, an Eclipse plugin and a provisional version of the documentation can be [downloaded](https://github.com/Frege/frege/releases). 
Note that Frege requires JDK 7 to compile and run programs.

The compiler and the documentation tool are quite stable, the documentation provisional and the library is evolving. 
It already supports important parts of the Haskell 2010 standard library.

See the [Getting Started](https://github.com/Frege/frege/wiki/Getting-Started) page for 
getting started at the command-line or read the [Eclipse plugin](https://github.com/Frege/eclipse-plugin) page.
You can develop [Frege inside Intellij IDEA](https://github.com/Frege/frege/wiki/Using-Frege-in-Intellij-IDEA)
and there is build automation support for
Maven, Gradle, and Leinigen.

There is also an [interpreter (REPL)](https://github.com/Frege/frege-repl). 
An online version of the REPL is available [here](http://try.frege-lang.org/).

The awesome QuickCheck library for advanced unit testing comes bundled with the language.

The Frege standard library and the Frege compiler are themselves fully written in Frege.

Contributions
-------------

If you are interested in contributing, here are some hot topics:

* write Frege code to support more of the Java API
* port Haskell libraries or tools
* open issues on the Issues page if you find bugs, errors in documentation, etc.
* help make Frege popular by writing code for projects like Rosetta Stone or Computer Language Shootout.
* contribute to [Real World Frege](https://github.com/Dierk/Real_World_Frege/)

Contact
-------

You can contact the project members through the
[discussion group](http://groups.google.com/group/frege-programming-language)
devoted to the Frege programming language.
Specific programming problems are best solved on
[Stack Overflow](http://stackoverflow.com/questions/tagged/frege),
we check questions tagged "frege" on a regular basis.

If you find a bug or have an idea for enhancements, please let us know by opening an issue in the issue tracker.
(You'll need a GitHub account to do this.)

Please understand that the issue tracker is neither a discussion forum nor a place to ask questions.

Links
----
* [Frege Wiki](https://github.com/Frege/frege/wiki/_pages)
* [Language reference](http://www.frege-lang.org/doc/Language.pdf)
* [Author's Blog](http://fregepl.blogspot.com/)


API Docs
--------
* [Standard library (prelude)]( http://www.frege-lang.org/doc/frege/Prelude.html)
* [Online Docs (Frege)](http://www.frege-lang.org/doc/index.html)
* [Online Docs (Runtime Javadoc)](http://www.frege-lang.org/doc/index.html)

