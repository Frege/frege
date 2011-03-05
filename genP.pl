#!perl -w

use warnings;
use strict;

my $n = 1;
my $j;

while ($n < 27) {
    open J, ">frege/rt/Product$n.java" or die "can't open $!";
    my @targs = map { "T$_" } (1..$n);                  # T1, T2, T3
    my @nargs = map {"final Lazy<T$_> arg$_" } (1..$n); # final Lazy<T1> arg1, ...
    my $cnargs = join (",", @nargs);                    # "final ... arg1, ...."
    my @args  = map { "arg$_" } (1..$n);                # arg1, arg2
    my $crargs = join(",", reverse @args);              # "arg2, arg1"
    my $rt = $targs[$n];                                # Tn
    my $ctargs = join (",", @targs);                    # "T1, T2, T3"
    my $cargs  = join (",", @args);                     # "arg1, arg2"
    my $p = $n-1;
    my @ptargs = @targs; shift @ptargs;
    my $cptargs = join(",", @ptargs);
    my @pargs = map { "final Lazy<T$_> arg$_" } (2..$n);
    my @rpargs = reverse @pargs;
    my $crpargs = join(",", @rpargs);
    print J "package frege.rt;\r\n";
    print J <<'TEXT';
// $Author$
// $Date$
// $Rev$
// $Id$
TEXT
    print J <<"TEXT";
/**
 * <p> Base class for values constructed with $n-ary constructors. </p>
 *
 * <p> This will be extended by constructors of sum types and by product types.
 *  Subclasses must implement the {\@link Value#_c} method and the
 * {\@link Lazy} interface.
 * </p>
 *
 * <p> Note that Product<sub><em>$n</em></sub> is not a subclass of Product<sub><em>$p</em></sub>! </p>
 */
public abstract class Product$n<$ctargs> implements Value {
    /** <p> Must be implemented by subclasses to return their constructor number. </p> */
    public abstract int _c();
    /** <p> Default implementation of the {\@link Lazy#_u} method. </p>
     *  \@return false
     */
    final public boolean _u() { return false; }
TEXT
    for ($j = 1; $j <= $n; $j++) {
        print J <<"TEXT";
    /** <p>Field $j </p> */
    public final Lazy<T$j> m$j;
    /** <p> Frege function to get field $j lazily. </p> */
    public final static class Get$j<$ctargs, T extends Product$n<$ctargs>>
            extends Fun1<T, T$j> {
        public final Lazy<T$j> r(final Lazy<T> arg1) {
            return arg1._e().m$j;
        }
        private final static Get$j single = new Get$j();
        \@SuppressWarnings("unchecked")
        public final static <$ctargs, T extends Product$n<$ctargs>>
            Get$j<$ctargs,T> n() {
                return (Get$j<$ctargs,T>) single;
        }
    }
TEXT
    }
    print J <<"TEXT";
    /** <p> Constructor. </p> */
    protected Product$n($cnargs) {
TEXT
    for ($j = 1; $j <= $n; $j++) {
        print J <<"TEXT";
        m$j = arg$j;
TEXT
    }
    print J <<"TEXT";
    }
}
TEXT
    close J;
    $n++;
}