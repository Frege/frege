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

sub run {
    my $cmd = shift;
    print "\t", $cmd, "\n";
    system $cmd;
}

# make sure subdir save exists and is empty
run qq{rm$exe -rf save};
mkdir "save" unless -d "save";

# collect all java sources in build/frege
chdir "build";
my @js = split /\s+/, qx{find$exe frege -name "*.java" -print};
# pack them in a jar
run qq{jar$exe -cf ../temp.jar @js};

chdir ".."   and run qq{jar$exe -uf temp.jar frege/runtime};
chdir "save" and run qq{jar$exe -xf ../temp.jar};
unlink "../temp.jar";
chdir ".."   and run qq{rm$exe -rf save/META-INF};