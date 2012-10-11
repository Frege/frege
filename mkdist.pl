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
my $version = qx{java$exe -cp build frege.compiler.Main -version};
chomp $version;
$version =~ s/\s//g;
print "making dist for version: '$version'\n";

#   make "executable" frege*.jar
system qq{jar$exe -cfe dist/frege$version.jar frege.compiler.Main  -C build frege};

#   ship documentation
#   sometimes it's good to have it offline
system qq{jar$exe -cf  dist/htmldocs$version.zip doc/frege doc/*.html doc/*.css doc/package-list};

#
#   Collect and jar the library sources.
#   These can be downloaded in order to make
#   a pseudo Eclipse project, so navigation
#   to library source code will be possible.
#
my @frsrcs = grep { $_ !~ m{frege/(contrib|compiler|rt)/|frege/Scrap} }
                (split /\s+/, qx{find$exe frege -name "*.fr" -print});
system qq{jar$exe -cf dist/libsources$version.zip @frsrcs}
