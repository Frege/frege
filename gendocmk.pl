#!perl -w

#
#   Create a makefile to make the documentation
#   Run this script from the directory where the main Makefile
#   is located, then run the makefile
#

use warnings;
use strict;

open(my $in, qq{find frege -name "*.fr" -print|})
    or die "Can't run find: $!";

my @all = qw(build/frege/tools/Doc.class);

while (<$in>) {
    chomp;
    next if m{/(compiler|contrib)/};
    next if m{^frege/(Scrap|StandardLibrary|PreludeProperties)\.fr$};
    my $fr = $_;
    my $html = $fr;
    $html =~ s/\.fr$/.html/;
    $html = "doc/$html";
    my $pack = $fr;
    $pack =~ s/\.fr//;
    $pack =~ s{/}/./g;
    my $class = $fr;
    $class =~ s/\.fr$/.class/;
    $class = "build/$class";
#    print "$class: $fr\n";
#    print "\tjava -Xss1m -cp build frege.compiler.Main -d build -make $pack\n";
    print "$html: $class\n";
    print "\tjava -Xss1m -cp build frege.tools.Doc -d doc $pack\n";
    push @all, $html;
}
print "docu: ", join(" ", @all), "\n";
print "\techo Documentation up to date\n";
