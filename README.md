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

The compiler, an Eclipse plugin and a provisional version of the documentation can be downloaded. Note that Frege requires JDK 7 to compile and run programs.

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

News
----

Whenever a new release is available, there may be backward compatibilty issues, please check them out.

* 2012-4-04 release 3.19.112 with enhancements/changes in compiler, language, libraries and fregIDE
* 2012-2-06 fregIDE with outline view, standard library enhancements
* 2012-1-10 First version of fregIDE. Also a binary compatible frege3.18.120 jar.
* 2012-1-06 Uploaded frege3.18.110 with small language changes and enhancements
* 2011-12-18 Uploaded frege3.18.70.jar, which conntains fixes for  Issue 19  and  Issue 20
* 2011-11-20 Release 3.18.0 uploaded, see backward compatibilty
* 2011-11-11 The "Differences from Haskell" document is now a wiki page.
* 2011-10-21 Prelude re-structured, Show is Haskell compatible, see Releases
* 2011-10-15 enhancements in import, documentation tool
* 2011-10-01 Fixed  issue 14  with r185
* 2011-9-29 Fixed  issue 8 ,  issue 10 ,  issue 12  and  issue 13  in frege3.17.184.jar
* 2011-9-27 Fixed  issue 7  and  issue 9  in frege3.17.178.jar
* 2011-9-26 small cosmetic changes, Language.pdf now in Portrait-1-column format
* 2011-9-22 switch to Haskell style comment syntax
* 2011-9-20 frege-jar with fork/join support uploaded
* 2011-9-18 Some issues fixed.
* 2011-9-15 Now downloadable: generated html documentation (javadocs of the runtime + frege module docs)

[Author's Blog](http://fregepl.blogspot.com/)

[Nightly Builds](http://jamestastic.com/jenkins/job/frege)

[Online Docs (Runtime Javadoc)](http://jamestastic.com/frege-doc/)

[Online Docs (Frege)](http://jamestastic.com/frege-doc/frege/)