#!/usr/bin/perl

use strict;
use warnings;

while (<>) {
    m/^JAVAC5/ and do { print "JAVAC5 = echo no java5 here\n"; next };
    s/pbyacc/byaccj/;
    # s/\bMakefile\b/frege.mk/;
    s/\.;build/.:build/;
    s/shadow;/shadow:/;
    print;
}
    
