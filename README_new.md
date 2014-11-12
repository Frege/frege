![Frege Logo](resources/Frege_logo.png)
What is Frege?
==============

Frege is a **pure** functional programming language for the JVM in the spirit of Haskell.
It enjoys a strong static type system with powerful type inference and
[non-strict](http://en.wikipedia.org/wiki/Non-strict_programming_language) - also known as _lazy_ - evaluation.

Frege programs are compiled to Java and run on the JVM.

The similarity to Haskell is actually strong enough that many users call it "_a_ Haskell for the JVM".

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

