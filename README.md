What is Frege?
==============

<img align="right" src="resources/Frege_logo.png"/>
Frege is a **pure** functional programming language for the JVM in the spirit of Haskell.
It enjoys a strong static type system with powerful type inference and
[non-strict](http://en.wikipedia.org/wiki/Non-strict_programming_language) - also known as _lazy_ - evaluation.

Frege programs are compiled to Java and run on the JVM.

The similarity to Haskell is actually strong enough that many users call it "_a_ Haskell for the JVM".

A Taste of Frege
----------------

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

The `greeting` function is **pure**, meaning it is _stateless_ and _free of side effects_.
Therefore, it is _threadsafe_ and its results may be _automatically cached_ since given the same argument, the result will always be the same.

The `main` function is **impure**. It takes a list of Strings and does not return just "void" as in most other JVM languages but the
type `IO ()`, telling that it may produce side effects like printing to the console. The Frege **type system** guarantees
that any caller of `main` must also be of some `IO` type and is thus also marked as impure. That way, the lack of purity percolates up the whole call chain.

"Hello World" already shows the tenet of _"islands of purity"_ (greeting) in a _"sea of imperative code"_ (main).

Since the purity information is carried through the **type system**, the compiler can potentially use it for many
**optimizations** such as pre-calculation, deferred execution, parallel execution, caching, and elimination of common subexpressions.

> Frege is **strongly** and **statically** typed, even though we haven't declared any types in the code above.
> If not declared, the types are _inferred_. When declared, the given types are checked against the inferred ones.

**2. No mutable state**

Much can be achieved in Frege in one line of code and here is an example that you can paste into the
[Online REPL](http://try.frege-lang.org/). It calculates the fixpoint of the cosine function, i.e. the
value where [`cos(x) == x`](http://www.wolframalpha.com/input/?i=cos+0.7390851332151607).

Implementations in imperative languages usually involve introducing local mutable state. Not so in Frege:

    import frege.prelude.Math (cos)
    (fst . head . dropWhile (uncurry (!=))) (zip cs (tail cs)) where cs = iterate cos 1.0

After execution it should show you the value

     0.7390851332151607

The code is most likely incomprehensible for a Frege/Haskell newcomer at first but you would not believe how
obvious and straightforward it is once you know the parts.
* `cs` is an _infinite_ list (a stream in Java terms) of cosine values that starts with `cos 1.0` and then `iterate`s to `cos(cos(1.0))`, `cos(cos(cos(1.0)))`, and so forth.
* `zip cs (tail cs)` produces an infinite list of pairs of any two adjacent values in `cs`.
* `uncurry` holds onto each element of a given pair and the `(!=)` function compares these elements for in-equality.
* `dropWhile` reads from the infinite list as long as the cosine values in each pair are not equal.
* The remaining list (the infinite list of pairs of equal cosine values) has a first pair called `head` and `fst` returns the first element of that pair, which yields the final result.

This code is **pure**. The inferred type is `Double`.
The code does not rely on any mutable state (not even internally). Therefore it is _threadsafe_ and the result can be _automatically cached_.

What's in for me?
-----------------
**For the Java programmer**

Frege offers you the opportunity to **learn and use a new programming paradigm**
that shines with
* a solid mathematical foundation,
* **pure** functions,
* **immutability** by default,
* side-effects only when declared,
* **robustness** under composition and concurrency,
* and a **type system** that is unparalleled on the JVM with its combination of power, simplicity and expressiveness.

You can still reuse your existing knowledge of the Java platform and its vast set of libraries.
Frege interoperates with Java such that you can easily
[call Frege from Java code](https://github.com/Frege/frege/wiki/Calling-Frege-Code-from-Java) and vice versa.
But unlike other approaches,
[calling Java from Frege](http://mmhelloworld.github.io/blog/2013/07/10/frege-hello-java/)
doesn't undermine the language guarantees.

> When calling Java from Frege, you have to declare the Java types in rigid Frege terms in order to
> preserve the Frege language characteristics, especially purity, thread safety, and lazy evaluation.

Learning Frege essentially means that **you will also learn Haskell** and thus your effort pays off twice, since
you also get to know a very popular non-JVM language with 25+ years of development, a great community,
many (free) books, publications, tutorials, online courses, and considerable industry demand.


**For the Haskell programmer**

Frege gives you the opportunity to **use your skills on the JVM**.
Most idiomatic Haskell code will run in Frege unmodified or with only minimal, obvious adaptions.
Even more important: you can bring your purely functional problem solution strategies to your Java projects.

From now on you can also enjoy on the JVM:
* the terse Haskell syntax
* pure functions and lambdas
* algebraic data types and **typeclasses** with parametric polymorphism
* powerful type inference
* **higher rank types**
* lazy evaluation on infinite data structures
* pattern matching, list comprehensions, do-notation, point-free style, operators, modules
* functors, monoids, semigroups, monads, and all your other beloved mathematical abstractions.

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
Note that Frege requires at least JDK 7 to compile and run programs.

A number of tools are **written in Frege**:
* the Frege compiler itself,
* the Frege [standard library]( http://www.frege-lang.org/doc/frege/Prelude.html),
* the Frege [command-line REPL](https://github.com/Frege/frege-repl),
* the Frege web-based, full-stack [interactive online REPL](http://try.frege-lang.org/),
* the Frege [Eclipse plugin](https://github.com/Frege/eclipse-plugin), 
* and the documentation tool.
This should speak for itself regarding stability, functional completeness and performance of the language.

The documentation is provisional and the library supports almost all of the Haskell 2010 standard library
with the remaining [known differences](https://github.com/Frege/frege/wiki/Differences-between-Frege-and-Haskell)
being there for good reason.

See the [Getting Started](https://github.com/Frege/frege/wiki/Getting-Started) page for 
getting started at the command-line or read the [Eclipse plugin](https://github.com/Frege/eclipse-plugin) page.
You can develop [Frege inside Intellij IDEA](https://github.com/Frege/frege/wiki/Using-Frege-in-Intellij-IDEA)
and there is build automation support for
Maven, Gradle, and Leinigen.

The awesome QuickCheck library for advanced unit testing comes bundled with the language.

Related Projects
----------------

* [Maven Compiler Plugin for the Frege language](https://github.com/talios/frege-maven-plugin), by Mark Derricut 
* [Frege compiler/library as an OSGi bundle](https://github.com/talios/frege-bundle), by Mark Derricut 
* [Apache Maven Tile for the Frege Programming Language](https://github.com/talios/frege-maven-tile), by Mark Derricut 
* [A Leiningen plugin to compile Frege code](https://github.com/seancorfield/lein-fregec), by Sean Corfield
* [Gradle Frege plugin](https://github.com/galderz/gradle-frege-plugin), by Galder Zamarreño
* [Real World Frege](https://github.com/Dierk/Real_World_Frege/), by Dierk König

Contributions
-------------

If you are interested in contributing, here are some hot topics:

* Write Frege code to support more of the Java API.
* Port Haskell libraries or tools.
* Open issues in the issues tracker if you find bugs, errors in documentation, etc.
* Help make Frege popular by writing code for projects like Rosetta Stone or Computer Language Shootout.
* Contribute to the related projects mentioned above, or make your own.

Contact
-------

**For discussions**

You can contact the project members through the
[discussion group](http://groups.google.com/group/frege-programming-language)
devoted to the Frege programming language.

**For questions**

Specific programming problems are best solved on
[Stack Overflow](http://stackoverflow.com/questions/tagged/frege),
we check questions tagged "frege" on a regular basis.

**For casual chat (and quick questions)**

There's a #frege channel on [Freenode IRC](https://freenode.net) where
some project members and Frege users hang out. You can use any IRC client
you like or Freenode's [WebChat interface](https://webchat.freenode.net)
if you don't want to install IRC software.

**For issues only**

If you find a bug or have an idea for enhancements, please let us know by opening an issue in the
[issue tracker](https://github.com/Frege/frege/issues).
(You'll need a GitHub account to do this.)
_Please keep discussions to the [forum](http://groups.google.com/group/frege-programming-language)
and questions to [Stack Overflow](http://stackoverflow.com/questions/tagged/frege)._

Links
----
* [Frege Wiki](https://github.com/Frege/frege/wiki/_pages)
* [Language reference](http://www.frege-lang.org/doc/Language.pdf)
* [Author's Blog](http://fregepl.blogspot.com/), [Dierk's Blog](http://www.canoo.com/blog/tag/frege/)
* [edX Functional Programming course FP101x](https://www.edx.org/course/introduction-functional-programming-delftx-fp101x) with exercises in Frege
* Introduction to Frege: [video](https://www.parleys.com/play/543fa326e4b06e1184ae41e6/chapter44/about), [slides](http://de.slideshare.net/Mittie/frege-purely-functional-programming-on-the-jvm)

Recommended reading
* [John Hughes: Why functional programming matters](http://www.cs.kent.ac.uk/people/staff/dat/miranda/whyfp90.pdf)
* [Book: Real-World Haskell](http://book.realworldhaskell.org/read/) (free online)
* [Book: Learn you a Haskell](http://learnyouahaskell.com/chapters) (free online)
* [Book: Programming in Haskell](http://www.amazon.com/Programming-Haskell-Graham-Hutton/dp/0521692695/)

API Docs
--------
* [Online Docs (Frege Standard Library)](http://www.frege-lang.org/doc/fregedoc.html)
* [Online Docs (Runtime Javadoc)](http://www.frege-lang.org/doc/index.html)

Copyright and License
---------
Copyright (c) Ingo Wechsung, 2011-2015. All rights reserved.
The use and distribution terms for this software are covered by the
[BSD 3-clause license](http://opensource.org/licenses/BSD-3-Clause)
which can be found in the file LICENSE.txt at the root of this distribution.
By using this software in any fashion, you are agreeing to be bound by the terms of this license.
You must not remove this notice, or any other, from this software.
