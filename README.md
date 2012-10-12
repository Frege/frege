What is Frege?
==============

Frege is a non-strict, pure functional programming language in the spirit of Haskell. It enjoys a strong static type system with type inference. Higher rank types are supported, though type annotations are required for that.

Frege programs are compiled to Java and run in a JVM. Existing Java Classes and Methods can be used seamlessly from Frege.

The Frege programming language is named after and in honor of Gottlob Frege. (This is surprisingly hard for english speakers to pronounce, but [you can use this translator page](http://translate.google.de/#de/en/Frege) to get it right. Just click the audio symbol in the left (german) part.)

Motivation
----------

There have been attempts to [port Haskell to the JVM](http://www.haskell.org/haskellwiki/GHC/FAQ#Why_isn.27t_GHC_available_for_.NET_or_on_the_JVM.3F), though said projects seem failed or stuck. The common wisdom suggests that it is not easily possible.

Frege is thought as a substitute for this missing GHC port. While not derived from any existing Haskell implementation, it is more or less equivalent to Haskell 2010. Please see the wiki page that details the differences.

Project State
-------------

The compiler, an Eclipse plugin and a provisional version of the documentation can be [downloaded](https://github.com/Frege/frege/downloads). Note that Frege requires JDK 7 to compile and run programs.

The compiler and the documentation tool are quite stable, the documentation provisional and the library is evolving. It already supports important parts of the Haskell 2010 standard library.

See the [Getting Started](https://github.com/Frege/frege/wiki/Getting-Started) page for getting started at the command-line or read the Eclipse plugin page.

Contributions
-------------

If you are interested in contributing, here are some hot topics:

* write frege code to support more of the Java API
* create a nicer logo
* port Haskell libraries or tools
* open issues on the Issues page if you find bugs, errors in documentation, etc.
* vote for Enhancement issues you consider most important by "starring" them or create new ones.

Contact
-------

You can contact the project members through the
[discussion group](http://groups.google.com/group/frege-programming-language)
devoted to the Frege programming language.
Specific programming problems are best solved on
[Stack Overflow](http://stackoverflow.com/questions/tagged/frege),
we check questions tagged "frege" on a regular basis.

If you find a bug, please let us know by opening an issue in the issue tracker.
(You'll need a GitHub account to do this.)

Links
----

[Frege Wiki](https://github.com/Frege/frege/wiki/_pages)

[Author's Blog](http://fregepl.blogspot.com/)

[Nightly Builds](http://jenkins.jamestastic.com/job/frege/)

[Online Docs (Runtime Javadoc)](http://jamestastic.com/frege-doc/)

[Online Docs (Frege)](http://jamestastic.com/frege-doc/frege/)