#!perl

use strict;
use warnings;
use Find::File;

mkdir "dist" unless -d "dist"; 
system q{find.exe build/frege -name "*.java" -exec rm.exe "{}" ";"};

my $version = qx{java.exe -cp build frege.compiler.Main -version}
print "making dist for version: '$version'\n"
