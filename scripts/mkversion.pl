#!perl -w


use strict;
use warnings;


my $version = qx{git describe --long};
my $commit = qx{git log -n 1};
chomp $version;
chomp $commit;
$version =~ s/-(\d+)-.*$/.$1/;
$commit =~ s/\n/\\n/g;
$commit =~ s/\r//g;
$commit =~ s/"/\\"/g;
$commit =~ s/<[^>]*>//;
print <<XXX;
-- automatically created with $0
-- based on git describe
package frege.Version where
    version = "$version"
    commit  = "$commit"
XXX
