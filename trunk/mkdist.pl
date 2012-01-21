#!perl

use strict;
use warnings;


mkdir "dist" unless -d "dist";
system q{find.exe build/frege -name "*.java" -exec rm.exe "{}" ";"};

my $version = qx{java.exe -cp build frege.compiler.Main -version};
chomp $version;
$version =~ s/\s//g;
print "making dist for version: '$version'\n";
system qq{jar.exe -cfe dist/frege$version.jar frege.compiler.Main  -C build frege};
system qq{jar.exe -cf  dist/htmldocs$version.zip doc/frege doc/*.html doc/*.css doc/package-list};
