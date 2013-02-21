#!perl -w

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

# make sure subdir save exists and is empty
system qq{rm$exe -rf save};
mkdir "save" unless -d "save";

# collect all java sources in build/frege
chdir "build";
my @js = split /\s+/, qx{find$exe frege -name "*.java" -print};
# pack them in a jar
system qq{jar$exe -cf ../temp.jar @js};
chdir "../save";
# and unpack in save
system qq{jar$exe -xf ../temp.jar};
unlink "../temp.jar";
chdir ".." and system qq{rm$exe -rf save/META-INF};