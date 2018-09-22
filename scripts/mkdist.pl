#!perl

use strict;
use warnings;

#
# on that damn Windows SUA it insists on having *.exe
# Why can't a giant like Microsoft not have a sound
# POSIX-ish subsystem and a decent shell?
# Its the same sh*t like in Windows 95.
# They've done barely anything in almost 20 years.
#
my $exe = "";
$exe = ".exe" unless defined $ENV{SHELL} and length $ENV{SHELL} > 0;

mkdir "dist" unless -d "dist";

#   remove all java source files from the distribution, they're big
system (qq{find$exe}.q{ build/frege -name "*.java" -exec rm "{}" ";"});

#   find out the version
my $version = qx{java$exe -cp build frege.compiler.Main -version | head -1};
chomp $version;
$version =~ s/\s//g;
my $latest  = $version;
$latest =~ s/^(3\.\d+)\.\d+(.*)/$1-latest$2/;
print "making dist for version: '$version'\n";



#   make "executable" frege*.jar
chdir "./build";
my $entrypoint = -f 'frege/Starter.class' ? "frege.Starter" : "frege.compiler.Main";
# open  MANI, ">manifest.txt";
# print MANI "Manifest-Version: 1.0\n";
# print MANI "Main-Class: $entrypoint\n";
# print MANI "Class-Path: jline-2.14.6.jar\n";
# close MANI;
system qq{jar$exe -xf ../lib/jline-2.14.6.jar} if -f "../lib/jline-2.14.6.jar" && -d "frege/repl";
unlink "META-INF/MANIFEST.MF";
system qq{jar$exe -cfe ../dist/frege$version.jar $entrypoint frege/ jline/ org/ META-INF/};
chdir "..";

#   ship documentation
#   sometimes it's good to have it offline
# system qq{jar$exe -cf  dist/htmldocs$version.zip doc/frege doc/*.html doc/*.css doc/package-list};

#
#   Collect and jar the library sources.
#   These can be downloaded in order to make
#   a pseudo Eclipse project, so navigation
#   to library source code will be possible.
#
my @frsrcs = grep { $_ !~ m{frege/(contrib|compiler|rt)/|frege/Scrap} }
                (split /\s+/, qx{find$exe frege -name "*.fr" -print});
system qq{jar$exe -uf dist/frege$version.jar @frsrcs};
system qq{ln$exe -f dist/frege$version.jar dist/frege$latest.jar};
my $pwd = `pwd$exe`; chomp $pwd;
print "you can reference this also with $pwd/dist/frege$latest.jar\n";
